package com.dev.training.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.io.IOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import sun.misc.IOUtils;

import com.dev.training.domain.SpringFileVO;

@Controller("springFileController")
@RequestMapping("VIEW") 
public class UploadController {
	@RenderMapping
    public String showHomePage(RenderRequest request, RenderResponse response){
        System.out.println("SpringFileController -> showHomePage -> Started");
        return "home";
    }
     
    @ModelAttribute("springFileVO")
    public SpringFileVO getCommandObject()
    {
        System.out.println("SpringFileController -> getCommandObject -> Building VO");
        return new SpringFileVO();
    }
 
    // For file upload
    @ActionMapping(params="formAction=fileUpload")
    public void fileUpload(@ModelAttribute SpringFileVO springFileVO, BindingResult bndingResult,
            ActionRequest request, ActionResponse response, SessionStatus sessionStatus){
        System.out.println("SpringFileController -> fileUpload -> Started");
         
        System.out.println("File Name :"+springFileVO.getFileData().getOriginalFilename());
        System.out.println("File Type :"+springFileVO.getFileData().getContentType());
         
        //File data processing logic here
        String fileName = springFileVO.getFileData().getOriginalFilename();
        String realPath = request.getPortletSession().getPortletContext().getRealPath("/");
        try {
	        //BufferedReader  in = new BufferedReader (new InputStreamReader(springFileVO.getFileData().getInputStream()));
        	springFileVO.getFileData().transferTo(new File(realPath+fileName));
	       // FileWriter fstream = new FileWriter(new File(realPath+fileName));
	        
	        //IOUtil.copy(springFileVO.getFileData().getInputStream(), fstream);
	        //fstream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        springFileVO.setServerImg(fileName);
        springFileVO.setMessage(realPath+fileName +" is upload successfully");
         
        System.out.println("SpringFileController -> FileUpload -> Completed");
        sessionStatus.setComplete();
    }
    /*
    @RequestMapping(value = "imageController/{imageId}")
    @ResponseBody
    public byte[] helloWorld(@PathVariable String imageId)  {
      //Image image = ;//obtain Image instance by id somehow from DAO/Hibernate
      //BufferedImage bufferedImage = ImageIO.read(request.getPortletSession().getPortletContext().getRealPath("/")+imageId);
      //return bufferedImage
    	return null;
    }*/
}
