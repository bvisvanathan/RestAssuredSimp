package com.reqres.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.equalTo;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class GetRequest {	
	
	
	 RequestSpecification httpRequest = null;
	    Response response = null;
	    
      public  Logger logger;
	    
	    @BeforeSuite
	    public void setup()
	    {
	        logger=Logger.getLogger("ReqresRestAPI");
	        PropertyConfigurator.configure("log4j.properties");
	        logger.setLevel(Level.DEBUG);
	    }
	    
	    @BeforeTest
	    public void BeforeMethod() {
	        //declare the baseURL 
	        RestAssured.baseURI = "https://reqres.in";
	        //This specifices this is HTTP Protocol Specification
	        httpRequest = RestAssured.given(); 
	        logger.info("log test before method");
	    }
	    
	    @Test
	    public void GetSingleUserRequest() {
	        //response = httpRequest.request(Method.GET,"/api/users/2");
	        response = httpRequest.get("/api/users/2");
	        JsonPath js = response.jsonPath();
	        String email = js.get("data.email");
	        Assert.assertEquals(email,"janet.weaver@reqres.in");
	        String firstName = js.get("data.first_name");
	        Assert.assertEquals(firstName,"Janet");
	        String lastName = js.get("data.last_name");
	        Assert.assertEquals(lastName,"Weaver");
	        String avatar = js.get("data.avatar");
	        Assert.assertEquals(avatar,"https://reqres.in/img/faces/2-image.jpg");
	        int id = js.get("data.id");
	        Assert.assertEquals(id,2);
	        System.out.println("Email = " + email);
	        System.out.println("firstname = " + firstName);
	        System.out.println("lastname = " + lastName);
	        System.out.println("Avatar = " + avatar);
	        System.out.println("Id = " + id);
	        int StatusCode = response.getStatusCode();
	        String StatusLine = response.getStatusLine();
	        String ResponseBody =response.body().asString();
	        System.out.println("Status Code = " + StatusCode);
	        System.out.println("Status Line = " + StatusLine);
	        System.out.println("Response Body = " + ResponseBody);        
	    }
	    
	    
	    @Test
	    public void GetSingleUserRequestc() {
	        //response = httpRequest.request(Method.GET,"/api/users/2");
	       
	    	response =   RestAssured.given().
	    			     when().
	    			     get("/api/users/2").
	    			     then().
	    			     assertThat().statusCode(200).and().
	    			     header("Content-Type","application/json; charset=utf-8").
	    			     header("Content-Encoding","gzip").and().
	    			     body("data.email",equalTo("janet.weaver@reqres.in")).and().
	    			     body("data.first_name",equalTo("Janet")).and().
	    			     body("data.last_name",equalTo("Weaver")).and().
	    			     body("data.avatar",equalTo("https://reqres.in/img/faces/2-image.jpg")).and().
	    			     extract().
	    			     response();
	    			            	             
	    }
	    
	    
	    @Test
	    public void GetListofusersc() {
	        //response = httpRequest.request(Method.GET,"/api/users/2");
	       
	    	response =   RestAssured.given().
	    			     param("page","2").
	    			     when().
	    			     get("/api/users").
	    			     then().
	    			     assertThat().statusCode(200).and().
	    			     header("Content-Type","application/json; charset=utf-8").and().
	    			     header("Content-Encoding","gzip").and().
	    			     body("page",equalTo(2)).and().
	    			     extract().
	    			     response();
	    			            	             
	    }
	    
	  //  Content-Type application/json; charset=utf-8
	    //		Content-Encoding gzip
	    
	    @Test
	    public void GetListofusers() {
	    	
	    	 response = httpRequest.queryParam("page", 2).get("/api/users");
	    	 JsonPath js = response.jsonPath();
	    	 
	    	 int arraycount = js.get("data.size()");
	    	 System.out.println("ArrayCount = " + arraycount);
	    	 
	    	 for(int i=0;i<arraycount;i++) {
	    		 System.out.println("Email["+i+"] -" + js.get("data["+i+"].email"));
	    		 System.out.println("FirstName["+i+"] -" + js.get("data["+i+"].first_name"));
	    		 System.out.println("LastName["+i+"] -" + js.get("data["+i+"].last_name"));
	    		 System.out.println("Avatar["+i+"] -" + js.get("data["+i+"].avatar"));
	    		 System.out.println("Id["+i+"] -" + js.get("data["+i+"].id"));
	    		 
	    	 }
	    	 
	    	 Headers headers = response.getHeaders();
	    	 for(Header header:headers){
	    		 System.out.println(header.getName()+" "+header.getValue());
	    	 }
	    	 
	    	 
	    	 String email = js.get("data[0].email");
		        Assert.assertEquals(email,"michael.lawson@reqres.in");
		        String firstName = js.get("data[0].first_name");
		        Assert.assertEquals(firstName,"Michael");
		        String lastName = js.get("data[0].last_name");
		        Assert.assertEquals(lastName,"Lawson");
		        String avatar = js.get("data[0].avatar");
		        Assert.assertEquals(avatar,"https://reqres.in/img/faces/7-image.jpg");
		        int id = js.get("data[0].id");
		        Assert.assertEquals(id,7);
		        System.out.println("Email = " + email);
		        System.out.println("firstname = " + firstName);
		        System.out.println("lastname = " + lastName);
		        System.out.println("Avatar = " + avatar);
		        System.out.println("Id = " + id);  	 
	    	    int StatusCode = response.getStatusCode();
		        String StatusLine = response.getStatusLine();
		        String ResponseBody =response.body().asString();
		        System.out.println("Status Code = " + StatusCode);
		        System.out.println("Status Line = " + StatusLine);
		        System.out.println("Response Body = " + ResponseBody);
		        
		        	    	
	    }
	    
	    @Test
	    public void CreateuserRequestc() {
	    	 //import packages doen using short cut Ctrl+Shift+O
            //specify the type of the request
            JSONObject requestParams = new JSONObject();
            requestParams.put("name", "gmorpheus");
            requestParams.put("job", "gleader");
            
            response =   RestAssured.given().
            		 header("Content-Type","application/json; charset=utf-8").and().
            		 body(requestParams.toJSONString()).
   			         when().
   			     post("/api/users").
   			     then().
   			     assertThat().statusCode(201).and().
   			     header("Content-Type","application/json; charset=utf-8").and().
   			     extract().
   			     response();         
          
	    	
	    	
	    }
	    
	    
	    @Test
	    public void UpdateuserRequestc() {
	    	 //import packages doen using short cut Ctrl+Shift+O
            //specify the type of the request
	    	
	    	 JSONObject requestParams = new JSONObject();
	    	 requestParams.put("name", "morpheus");
	            requestParams.put("job", "zion resident");
	            
	            response =   RestAssured.given().
	            		 header("Content-Type","application/json; charset=utf-8").and().
	            		 body(requestParams.toJSONString()).
	   			         when().
	   			     put("/api/users/2").
	   			     then().
	   			     assertThat().statusCode(200).and().
	   			     header("Content-Type","application/json; charset=utf-8").and().
	   			     extract().
	   			     response();     	
	    	       
	    	
	    	
	    }
	    
	    
	    @Test
	    public void UpdateuserRequest() {
	    	 //import packages doen using short cut Ctrl+Shift+O
            //specify the type of the request
            JSONObject requestParams = new JSONObject();
            requestParams.put("name", "morpheus");
            requestParams.put("job", "zion resident");
            httpRequest.body(requestParams.toJSONString());
            Response response = httpRequest.put("/api/users/2");
            int statusCode = response.getStatusCode();
            String statusLine = response.getStatusLine();
            String responseBody = response.getBody().asString();
            System.out.println("Response Status Code : " + statusCode);
            System.out.println("Response Status Line : " + statusLine);
            System.out.println("Response Body : " + responseBody);
	    	
	    	
	    }
	    
	  
	    
	    
	    
	    
	    @Test
	    public void DeleteSingleUserRequest() {
	        //response = httpRequest.request(Method.GET,"/api/users/2");
	        response = httpRequest.delete("/api/users/2");
	        int StatusCode = response.getStatusCode();
	        String StatusLine = response.getStatusLine();
	        String ResponseBody =response.body().asString();
	        System.out.println("Status Code = " + StatusCode);
	        System.out.println("Status Line = " + StatusLine);
	        System.out.println("Response Body = " + ResponseBody);        
	    }
	    
	    @Test
	    public void DeleteSingleUserRequestc() {
	        //response = httpRequest.request(Method.GET,"/api/users/2");
	    	
	    	 
	    	response =   RestAssured.given().
	    			     when().
	    			     delete("/api/users/2").
	    			     then().
	    			     assertThat().statusCode(204).and().
	    			     extract().
	    			     response();    			            	             
	    	
	            
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	   

}
