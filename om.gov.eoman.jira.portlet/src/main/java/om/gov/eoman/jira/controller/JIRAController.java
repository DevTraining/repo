package om.gov.eoman.jira.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import om.gov.eoman.jira.bean.IssueComment;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

/**
 * Spring controller which implements the Controller interface.
 * 
 * @author Mazin
 *
 */

@Controller("jiraController")
@RequestMapping("VIEW")
public class JIRAController{

	private static final Logger log = Logger.getLogger(JIRAController.class);
	JiraClient client =new JiraClient();
      @Autowired
      @RenderMapping
      public String Show(Map<String, Object> map){//RenderRequest request, RenderResponse response
    	  Iterable<BasicIssue> basicIssues = null;
    	  ArrayList<Issue> issues = new ArrayList<Issue>();
    	  
    	  try {
    		  basicIssues = client.getIssues();
			
			for(Iterator i = basicIssues.iterator();i.hasNext();) {
				BasicIssue b = (BasicIssue) i.next();
				issues.add(client.findIssueByIssueKey(b.getKey()));
		     }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("Error: ", e);
		}
    	  
    	  map.put("comment", new IssueComment());
    	  map.put("issues", issues.iterator());
         return "view";
      }
     
     
      @ActionMapping(params = "action=add")
      public void addComment(@ModelAttribute("comment") IssueComment comment,ActionRequest actionRequest,ActionResponse actionResponse, Model model) throws IOException,
                    PortletException {
             
             
             try {
				client.addComment(comment);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.error("Error: ", e);
				SessionErrors.add(actionRequest, "comm-error");
				return;
			}
             SessionMessages.add(actionRequest, "comm-add"); 
      }
      
      @ActionMapping(params = "action=update")
      public void updateUser(@RequestParam("username") String username,ActionRequest actionRequest,ActionResponse actionResponse, Model model) throws IOException,
      PortletException {
    	  
             //System.out.println("updateUser: "+username);
             //user = DBUser.getUser(username);
            // DBUser.removeUser(new User(username, 0));
      }
      
     

}
