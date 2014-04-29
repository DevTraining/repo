package com.dev.training.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Spring controller which implements the Controller interface.
 * 
 * @author Mazin
 *
 */

@Controller("zabbixController")
@RequestMapping("VIEW")
public class ZabbixController{
 
      @Autowired
      @RenderMapping
      public String listUsers(Map<String, Object> map) {
    	  
    	  ZabbixApiClient client = new ZabbixApiClient();
    	  try {
			String auth = client.authenticateUser();
			
			String ram = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"vm.memory.size\"}},\"auth\":\""+ auth +"\",\"id\":2}";
			String ramOutput = client.wrapAndCall(ram);
			System.out.println(ramOutput);
			
			String disk = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"vfs.fs.size\"}},\"auth\":\""+ auth +"\",\"id\":2}";
			String diskOutput = client.wrapAndCall(disk);
			//Double diskOutputGB = Double.valueOf(diskOutput)/1073741824;
			System.out.println(diskOutput);
			
			String cpu = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"system.cpu.num\"}},\"auth\":\""+ auth +"\",\"id\":2}";
			String cpuOutput = client.wrapAndCall(cpu);
			System.out.println(cpuOutput);
			
			String vms = "{\"jsonrpc\":\"2.0\",\"method\":\"host.get\",\"params\":{\"output\": \"extend\"},\"auth\":\""+ auth +"\",\"id\":2}";
			String vmsOutput = client.wrapAndCallHost(vms);
			System.out.println(vmsOutput);
			
			
			map.put("ram",client.wrapAndCall(ram) +" GB");
			map.put("disk",client.wrapAndCall(disk) +" GB");
			map.put("cpu",client.wrapAndCall(cpu));
			map.put("vms",client.wrapAndCallHost(vms));

			
			
    	  }catch(Exception e){
    		  System.out.println(e);
    	  }
    	  
         return "view";
      }
     /*
      @ActionMapping(params = "action=add")
      public void addUser(ActionRequest actionRequest,ActionResponse actionResponse,@ModelAttribute("users") User users, BindingResult result, Model model) throws IOException,
                    PortletException {
    	  boolean error=false;
             //System.out.println("result"+ParamUtil.getString(actionRequest,"users"));
             //System.out.println("name "+users.getUsername());
            // System.out.println("age "+users.getAge());

             //String name=ParamUtil.get(actionRequest, "username", "");  
             //String age=ParamUtil.get(actionRequest, "age", ""); 
             String name=user.getUsername();
             int age=user.getAge();
             
             String usernamePattren = "((?=.*[a-z])(?=.*[A-Z]).{10,15})";
             
             Pattern patternUsername = Pattern.compile(usernamePattren);
             
            // Pattern num = Pattern.compile("Integer.valueOf(age) < 21") 
             
             if(!patternUsername.matcher(name).matches()){
            	 error = true;
            	   SessionErrors.add(actionRequest, "username-isnot-match");  
            	  }
             	
            	if(Integer.valueOf(age) < 21 || Integer.valueOf(age) > 50){ 
            		error = true;
            		SessionErrors.add(actionRequest, "age-isnot-match");  
            	  }
            	  if(error){
            		  this.user = users;
            	  }else{
		             DBUser.addUser(users);
		             this.user = new User();
            	  }
            
      }
     
      @ActionMapping(params = "action=delete")
      public void deleteUser(@RequestParam("username") String username,ActionRequest actionRequest,ActionResponse actionResponse, Model model) throws IOException,
                    PortletException {
             //System.out.println("result "+ username);
             DBUser.removeUser(new User(username, 0));
             user = new User();
      }
      
      @ActionMapping(params = "action=update")
      public void updateUser(@RequestParam("username") String username,ActionRequest actionRequest,ActionResponse actionResponse, Model model) throws IOException,
      PortletException {
    	  
             //System.out.println("updateUser: "+username);
             user = DBUser.getUser(username);
             DBUser.removeUser(new User(username, 0));
      }
*/
}
