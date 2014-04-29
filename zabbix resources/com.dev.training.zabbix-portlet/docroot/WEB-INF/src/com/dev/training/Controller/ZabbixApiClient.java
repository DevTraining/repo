package com.dev.training.Controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.logging.Logger;
import java.lang.Object;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PutMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class ZabbixApiClient 
{
	private static String ZABBIX_API_URL = "http://localhost:8090/zabbix/api_jsonrpc.php"; // 1.2.3.4 is your zabbix_server_ip
	private Logger logger;
	//private String AUTH_VALUE;
		
	public String authenticateUser() throws HttpException, IOException {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("jsonrpc", "2.0");		
			jsonObj.put("method", "user.authenticate");
//			jsonObj.put("method", "user.login"); -May need to use this
			jsonObj.put("params", (new JSONObject().put("user","Admin").put("password", "zabbix")));
			jsonObj.put("id", "0");
			
			System.out.println(jsonObj);
			JSONObject resultStr = executeRpcMethod(jsonObj);
			System.out.println(resultStr);
			
			String auth = (String) resultStr.get("result");
			return auth;
	}
	
	private JSONObject executeRpcMethod(JSONObject jsonObj) throws HttpException, IOException {
		// TODO Auto-generated method stub
		HttpClient client = new HttpClient();
		
		PutMethod putMethod = new PutMethod(ZABBIX_API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc"); // content-type is controlled in api_jsonrpc.php, so set it like this
		
		// create json object for apiinfo.version 
		//JSONObject jsonObj=new JSONObject("{\"jsonrpc\":\"2.0\",\"method\":\"apiinfo.version\",\"params\":[],\"auth\":\"a6e895b98fde40f4f7badf112fd983bf\",\"id\":2}");
		
		putMethod.setRequestBody(fromString(jsonObj.toString())); // put the json object as input stream into request body 
		String loginResponse = "";

		client.executeMethod(putMethod); // send to request to the zabbix api
		
		loginResponse = putMethod.getResponseBodyAsString(); // read the result of the response
		
		// Work with the data using methods like...
		JSONObject obj = new JSONObject(loginResponse); 

		return obj;
	}

	public String wrapAndCall(String json) throws JSONException, HttpException, IOException {
	
		HttpClient client = new HttpClient();
		
		PutMethod putMethod = new PutMethod(ZABBIX_API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc"); // content-type is controlled in api_jsonrpc.php, so set it like this
		
		// create json object for apiinfo.version 
		JSONObject jsonObj=new JSONObject(json);
		
		putMethod.setRequestBody(fromString(jsonObj.toString())); // put the json object as input stream into request body 
		System.out.println("jsonObj:\n"+jsonObj);
		
		String loginResponse = "";
		client.executeMethod(putMethod); // send to request to the zabbix api
		
		loginResponse = putMethod.getResponseBodyAsString(); // read the result of the response
		
		System.out.println("loginResponse: \n"+loginResponse); // print the result of the response
		
		JSONObject obj = new JSONObject(loginResponse); 
		System.out.println(obj);
		
		JSONArray result = (JSONArray) obj.get("result");
		
		if( result.length() == 0)
		return "INVALID";
		
		
			JSONObject item  = (JSONObject) result.get(0);
			String temp = (String) item.get("lastvalue");
			double sum = (Double.parseDouble(temp));
			
						DecimalFormat dec = new DecimalFormat("0.00");
						if ((sum/1000000000)>0)
						{
							String converter;
							converter = dec.format(sum/(1024*1024*1024));
							return String.valueOf(converter);
						}
						return "true";
		
	}

	
	public String wrapAndCallHost(String json) throws JSONException, HttpException, IOException {
		
		HttpClient client = new HttpClient();
		
		PutMethod putMethod = new PutMethod(ZABBIX_API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc"); // content-type is controlled in api_jsonrpc.php, so set it like this
		
		// create json object for apiinfo.version 
		JSONObject jsonObj=new JSONObject(json);
		
		putMethod.setRequestBody(fromString(jsonObj.toString())); // put the json object as input stream into request body 
		System.out.println("jsonObj:\n"+jsonObj);
		
		String loginResponse = "";
		client.executeMethod(putMethod); // send to request to the zabbix api
		
		loginResponse = putMethod.getResponseBodyAsString(); // read the result of the response
		
		System.out.println("loginResponse: \n"+loginResponse); // print the result of the response
		
		JSONObject obj = new JSONObject(loginResponse); 
		System.out.println(obj);
		
		JSONArray result = (JSONArray) obj.get("result");
		
		if( result.length() == 0)
		return "INVALID";
		
		double total=0;
		
		if (result != null && result.length() >0) {
			total = result.length();
		}
		return String.valueOf(total);

	
	}
	public static InputStream fromString(String str) throws UnsupportedEncodingException {
		byte[] bytes = str.getBytes("UTF-8");
		return new ByteArrayInputStream(bytes);
	}

	
	public static void main(String[] args) {
		try {
			ZabbixApiClient client = new ZabbixApiClient();
			String auth = client.authenticateUser();
			
			String ram = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"vm.memory.size\"}},\"auth\":\""+ auth +"\",\"id\":13}";
			String ramOutput = client.wrapAndCall(ram);
			
			System.out.println(ramOutput);
			
			String disk = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"vfs.fs.size\"}},\"auth\":\""+ auth +"\",\"id\":13}";
			String diskOutput = client.wrapAndCall(disk);
			//Double diskGB = Double.valueOf(diskOutput)/1073741824;
			System.out.println(diskOutput);
			
			String cpu = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"system.cpu.num\"}},\"auth\":\""+ auth +"\",\"id\":13}";
//			String cpu = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"system.cpu.util[0,user,avg5]\"}},\"auth\":\""+ auth +"\",\"id\":13}";

			
			String cpuOutput = client.wrapAndCall(cpu);
			System.out.println(cpuOutput);
	

			//String vms = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"system.cpu.num\"}},\"auth\":\""+ auth +"\",\"id\":2}";
			//String vmsOutput = client.wrapAndCall(vms);
			//System.out.println(vmsOutput);
			
				
			
			
			//String vfsNo = client.wrapAndCall(total);

			
			/*String ram = "{\"jsonrpc\":\"2.0\",\"method\":\"item.get\",\"params\":{\"hostids\": \"10084\",\"output\": \"extend\",\"search\":{\"key_\":\"vm.memory.size\"}},\"auth\":\""+ auth +"\",\"id\":2}";
			client.wrapAndCall(ram);
			*/
			//client.get
			
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}