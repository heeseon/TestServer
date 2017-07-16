package test.sample.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import test.common.common.CommandMap;
import test.sample.service.MemberService;
import test.sample.service.SampleService;

@Controller
public class MemberController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="memberService")
	private MemberService memberService;

	@RequestMapping("test.do")
    public ModelAndView dummy(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("attr",memberService.getData());
        mav.setViewName("test");
        return mav;
    }
	
    @RequestMapping("memberList.do")
    public ModelAndView dummy2(){
        ModelAndView mav = new ModelAndView();
        try {
			mav.addObject("memberList",memberService.selectAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mav.setViewName("selectAll");
        return mav;
    }
    
    @RequestMapping("loginForm.do")
    public String loginForm(){
        return "loginForm";
    }
 
    @RequestMapping("joinForm.do")
    public void joinForm(){
        
    }
    
    @RequestMapping("join.do")
    public String join(CommandMap commandMap) //
    {
        System.out.println(commandMap.getMap());
        try {
			memberService.joinMember(commandMap.getMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:loginForm.do"; 
    }

    @RequestMapping("login.do")
    public ModelAndView login(HttpSession session,CommandMap commandMap){
        ModelAndView mav = new ModelAndView();
        try {
        	String id = (String) commandMap.getMap().get("id");
        	String pwd = (String) commandMap.getMap().get("pwd");

			if(memberService.login(id, pwd)){
			    session.setAttribute("userid", id);
			    mav.setViewName("redirect:main.do");
			}
			else{
			    //return "redirect:loginForm.do";
			    mav.setViewName("redirect:loginForm.do");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mav;
    
    }    
    
    @RequestMapping("main.do")
    public String main(Model model,HttpSession session ){
        String userid = (String) session.getAttribute("userid");
        if(userid == null)
            return "redirect:loginForm.do";
        else{
            try {
				model.addAllAttributes(memberService.getMemberInfo(userid));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return "main";
        }
 
        
    }
    
    @RequestMapping("logout.do")
    public String logout(HttpSession session){
//        session.invalidate();
        session.removeAttribute("userid");
        return "redirect:loginForm.do";
        
    }
    
    @RequestMapping("memberUpdateForm.do")
    public String memberUpdateForm(Model model,HttpSession session){
        String userid = (String) session.getAttribute("userid");
        if(userid == null)
            return "redirect:loginForm.do";
        try {
			model.addAllAttributes(memberService.getMemberInfo(userid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "memberUpdate";
    }
    
    @RequestMapping("memberUpdate.do")
    public String memberUpdate(CommandMap commandMap){
    	try {
			memberService.memberUpdate(commandMap.getMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:main.do";
    }

}
