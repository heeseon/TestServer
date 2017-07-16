package test.sample.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import test.sample.dao.MemberDAO;
import test.sample.dao.SampleDAO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;

	@Override
	public List<Map<String, Object>> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.selectAll();

	}

	@Override
	public void joinMember(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.insertMember(params);
	}

	@Override
	public boolean login(String id, String pwd) throws Exception {
		// TODO Auto-generated method stub
        Map<String, Object> result = memberDAO.selectOne(id);
        if(result == null)
            return false;
        else 
        {
            String oPwd = (String) result.get("pwd");
            if(oPwd==null)
                return false;
            else{
                if(oPwd.equals(pwd))
                    return true;
                else
                    return false;
            }
            
        }
	}

	@Override
	public Map<String, Object> getMemberInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.selectOne(id);
	}

	@Override
	public void memberUpdate(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
            Map<String, Object> record = memberDAO.selectOne((String)params.get("userid"));
            record.putAll(params); //원래있던거에 내가 받은걸로 수정
            memberDAO.updateMember(record);
 	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
        return "I am a girl";
	}

	@Override
	public void deleteMember(String id) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.deleteMember(id);
	}

}
