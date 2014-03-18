package com.dev.training.Controller;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import com.dev.training.DBUser;
import com.dev.training.bean.User;

/**
 * Spring controller which implements the Controller interface.
 * 
 * @author asarin
 *
 */

@Controller("userController")
@RequestMapping("VIEW")
public class UserController    {

      @Autowired
      //private DBUser DBUser;
     private User user = new User();
      @RenderMapping
      public String listUsers(Map<String, Object> map) {
    	  
    	  System.out.println("listUsers");
    	  
             map.put("users", user);
             //DBUser.addUser(new User("Mazin", 26));
             map.put("listUsers", DBUser.getUsers());
             return "user";
      }
     
      @ActionMapping(params = "action=add")
      public void addUser(ActionRequest actionRequest,ActionResponse actionResponse, Model model,@ModelAttribute("users") User users, BindingResult result) throws IOException,
                    PortletException {
             //System.out.println("result"+ParamUtil.getString(actionRequest,"users"));
             System.out.println("name "+users.getUsername());
           //  if(null != users && !users.getUsername().isEmpty()){
	             DBUser.addUser(users);
	             this.user = new User();
            // }else{
            //	 System.out.println("Empty");
           //  }
             //return "contact";
            
      }
     
      @ActionMapping(params = "action=delete")
      public void deleteUser(@RequestParam("username") String username,ActionRequest actionRequest,ActionResponse actionResponse, Model model) throws IOException,
                    PortletException {
             System.out.println("result "+ username);
             DBUser.removeUser(new User(username, 0));
             user = new User();
             //return "contact";
            
      }
      
      @ActionMapping(params = "action=update")
      public void updateUser(@RequestParam("username") String username,ActionRequest actionRequest,ActionResponse actionResponse, Model model) throws IOException,
      PortletException {
             System.out.println("updateUser: "+username);
             
             user = DBUser.getUser(username);
             DBUser.removeUser(new User(username, 0));
             
             //return "user";
      }

}
