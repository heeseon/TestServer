package test.sample.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaonline.spring.fileupload.form.MultipleFileUploadForm;
import test.common.common.CommandMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MultipleFileUploadController {

 @RequestMapping(value="/loadMultipleFileUploadMA")
 public String loadMultipleFileUploadMA(Map<String, Object> model) {
     return "MultipleFileUploadMA";
 }


 //Using @ModelAttribute annotation as method parameter.

@RequestMapping(value="/uploadMultipleFilesMA", method=RequestMethod.POST)
public String handleFileUploadMA(@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model){

	
	List<MultipartFile> files=multipleFileUploadForm.getFiles();
	
	System.out.println(" Files count " + files.size());
	
        try {
        	String filePath="/Users/hwangheeseon/web/upload_images";
        	for (int i=0;i<files.size();i++) {
        		File f = new File(filePath+files.get(i).getOriginalFilename());
        		files.get(i).transferTo(f);
        		f = null;
        	}
        } catch (Exception e) {
            return "Error While uploading your files " +  e.getMessage();
        }
        
        model.addAttribute("files", files);
        return "result";
    } 




@RequestMapping(value="/loadMultipleFileUpload")
public String loadMultipleFileUpload(Map<String, Object> model) {
    return "MultipleFileUpload";
}



// Without Using @ModelAttribute annotation

    @RequestMapping(value="/uploadMultipleFiles", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload( @RequestParam("files") MultipartFile files[]){
            try {
            	String filePath="/Users/hwangheeseon/web/upload_images";
            	StringBuffer result=new StringBuffer();
            	byte[] bytes=null;
            	result.append("Uploading of File(s) ");
            	
            	for (int i=0;i<files.length;i++) {
                    if (!files[i].isEmpty()) {
		                bytes = files[i].getBytes();
		                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+files[i].getOriginalFilename())));
		                stream.write(bytes);
		                stream.close();
		                
		               result.append(files[i].getOriginalFilename() + " Ok. ") ;
		               //bytes = null;
                    }
                    else 
                    	result.append( files[i].getOriginalFilename() + " Failed. ");

            	
            }
                return result.toString();

            } catch (Exception e) {
                return "Error Occured while uploading files." + " => " + e.getMessage();
            }
        
        
        
    } 
    
    
    @RequestMapping("displayimage")
    public void displayImage(HttpServletRequest req, HttpServletResponse response, String id) //
    {

		File imageFile = new File(id);
        response.setContentType(req.getContentType());
        response.setContentLength((int) imageFile.length());
        InputStream is = null;
        try {
            is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
        } catch(IOException e) {
        	e.printStackTrace();
        }finally {
        	try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
    }
        
    @RequestMapping(value = "/allpictures", method = RequestMethod.GET)
    public String getAllImages(HttpServletRequest req, HttpServletResponse response, Model model)
    {

       // String name = request.getUserPrincipal().getName();
       // model.addAttribute("username", name);

    	File dir = new File("/Users/hwangheeseon/Documents/workspace3/AppiumDemoWithJenkins/testResultDir/23-02-2016_04_41_06/screenshots");
		String[] extensions = new String[] { "png"};
		try {
			System.out.println("Getting all .png files in " + dir.getCanonicalPath()
					+ " including those in subdirectories");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<File> images = (List<File>) FileUtils.listFiles(dir, extensions, true);
		
		model.addAttribute("all", images);

        return "allpictures";
        
    }
    

}