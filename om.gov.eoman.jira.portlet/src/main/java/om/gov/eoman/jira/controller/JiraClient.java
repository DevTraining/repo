package om.gov.eoman.jira.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import om.gov.eoman.jira.bean.IssueComment;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.SearchRestClient;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

public class JiraClient {

	private static final String JIRA_URL = "http://localhost:28080/";
    private static final String JIRA_USERNAME = "test";
    private static final String JIRA_PASSWORD = "test";
    
    
    private JiraRestClient createJiraRestClient() {
		final JiraRestClientFactory jiraRestClientFactory = new AsynchronousJiraRestClientFactory();
		return jiraRestClientFactory
				.createWithBasicHttpAuthentication(URI.create(JIRA_URL),
						JIRA_USERNAME,JIRA_PASSWORD);
	}
    public void addComment(IssueComment comment) throws Exception {
        IssueRestClient issueClient = createJiraRestClient().getIssueClient();
        Promise<Issue> issuePromise = issueClient.getIssue(comment.getId());
        Issue issue = issuePromise.claim();
        
        issueClient.addComment(issue.getCommentsUri(), Comment.valueOf(comment.getComment()));
    }
    
    public Iterable<BasicIssue> getIssues() throws Exception {
		SearchRestClient searchClient = createJiraRestClient().getSearchClient();
		String jql = "project = TST";
		SearchResult results = searchClient.searchJql(jql).claim();
    	return results.getIssues();
    }
    
    public Issue findIssueByIssueKey(String issueKey) {
    	IssueRestClient issueClient = createJiraRestClient().getIssueClient();
        Promise<Issue> issuePromise = issueClient.getIssue(issueKey);
        //Issue issue = issuePromise.claim();
    	return issuePromise.claim();
	}
    
    
    public static void main(String[] args) {
    	JiraClient client =new JiraClient();
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
			//log.error("Error: ", e);
			System.out.println(e);
		}
    	for(Iterator<BasicIssue> i = basicIssues.iterator();i.hasNext();) {
    		BasicIssue b = (BasicIssue)i.next();
            System.out.println(b.getKey()+" "+b.toString());
         }
    	System.out.println("--------------------------------");
    	for(Iterator<Issue> i = issues.iterator();i.hasNext();) {
    		Issue iss = (Issue)i.next();
            System.out.println(iss.getKey()+" "+iss.getSummary() +" "+iss.toString());
         }
    }
    
}
