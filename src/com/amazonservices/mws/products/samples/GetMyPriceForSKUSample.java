/*******************************************************************************
 * Copyright 2009-2016 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Products
 * API Version: 2011-10-01
 * Library Version: 2016-06-01
 * Generated: Mon Jun 13 10:07:47 PDT 2016
 */
package com.amazonservices.mws.products.samples;

import java.util.*;
import java.util.Map.Entry;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.soap.Node;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonservices.mws.client.*;
/*import com.amazonservices.mws.orders._2013_09_01.samples.Amazonorders;*/
import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;
import com.google.common.collect.Lists;


/** Sample call for GetMyPriceForSKU. */
public class GetMyPriceForSKUSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
	
	//global variable to store retrieved sku and selling price
	  public static String s,sp;
	  
	  
    public static GetMyPriceForSKUResponse invokeGetMyPriceForSKU(

            MarketplaceWebServiceProducts client, 
            GetMyPriceForSKURequest request) 
    
    {
    	
        try {
        	
            // Call the service.
            GetMyPriceForSKUResponse response = client.getMyPriceForSKU(request);
            
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            
            System.out.println("RequestId: "+rhmd.getRequestId());
            
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            
            String responseXml = response.toXML();
            
            System.out.println(responseXml);
            
            
            
            

            //Dom parser to par XML
            
            //intiliaze db connection here, do not put set up connection inside of loop, else multiple times will be executed 
            
            String url = "0";
            String username = "0";
            String password = "0";
            
            System.out.println("Loading driver...");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver loaded!");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            }
            
            
            
            System.out.println("Connecting database...");
            
            
            try {	
            	
            	//Initialize new Obj to add retrieved data to DB each time it is being retreived 
            	AmazonListing al = new AmazonListing(password, password, password, password, password, password, password, password, password, password, password, password, password, password, password, password, password, password, password, password, password, password);
            	
               /*
                //Does not work, input is a string, only takes afile 
                  File inputFile = new File(responseXml);*/
            	
            	//must use input as inputstream
            	
            	InputStream stream = new ByteArrayInputStream(responseXml.getBytes(StandardCharsets.UTF_8));
                
                DocumentBuilderFactory dbFactory 
                
                   = DocumentBuilderFactory.newInstance();
                
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                
                org.w3c.dom.Document doc = dBuilder.parse(stream);
                
                doc.getDocumentElement().normalize();
                
                System.out.println("Root element :" 
                   + doc.getDocumentElement().getNodeName());
                
                //reach the tag name of the very first node, and looping through each sku and selling price
                NodeList nList = doc.getElementsByTagName("GetMyPriceForSKUResult");
                
                System.out.println("----------------------------");
                
                for (int temp = 0; temp < nList.getLength(); temp++) {
                	
                   org.w3c.dom.Node nNode = nList.item(temp);
                   
                   System.out.println("\nCurrent Element :" 
                		   
                      + nNode.getNodeName());
                   
                   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                	   
                	   /*System.out.println("im Here");*/
                	   
                    /* 
                     //**Critical Error, Node can not be cast to type element
                      Node eElement = (Element) nNode;*/
                      
                	   org.w3c.dom.Node eElement =  nNode;
                     
                      
                      /*System.out.println("Im inside of an order");*/
                      
                      System.out.println("SKU : " 
                    		  
                         + ((org.w3c.dom.Element) eElement)
                         
                         .getElementsByTagName("SellerSKU")
                         .item(0)
                         .getTextContent());
                      
                      
                      
                 
                      //set order id to orders obj
                      al.setSku(
                    		  ((org.w3c.dom.Element) eElement) 
                         .getElementsByTagName("SellerSKU")
                         .item(0)
                         .getTextContent());
                      
                      
                    
                    //set Name to orders 
                 try{     
                    
                     String price = (((org.w3c.dom.Element) eElement) 
                     .getElementsByTagName("ListingPrice")
                     .item(0)
                     .getTextContent());
                     
                    al.setSellingprice(price);
                    
                    System.out.println("selling price for"+""+al.getSku()+""+"is"+""+al.getSellingprice());
                     
                 }
                 catch(NullPointerException e){
                     
               
                    
                    	 
                    	 System.out.println("no price, no vales will be set to selling price");
                    	 
                    	 al.setSellingprice(
                           		 "");
                        	 
                    	 
         
                 };
            
            
                 //write to db
                 
                 try (Connection connection = DriverManager.getConnection(url, username, password)) {
                 	
                 	
                     System.out.println("Database connected!");
                     
            
            
            
                     
                  // Update DB
                     String query = "UPDATE AmazonListing SET SellingPrice = ? WHERE SKU = ? ";
                     
                     //create the mysql insert preparedstatement
                     
                     PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
                    
                     //insert values to be updated to statement 
                     
                     preparedStmt.setString(1,al.getSellingprice());
                     
                     preparedStmt.setString(2,al.getSku());
            
            
                     // execute the preparedstatement
                     preparedStmt.execute();
                     System.out.println("Updated");
                     
                     
                     
                     //after inserting, close the connection of db
                     connection.close();
                     
                
                 
            
            
                 }catch (SQLException e) {
                     throw new IllegalStateException("Cannot connect the database!", e);
                 }
                 
                   }
                   
                }
                
                
            }catch (Exception e) {
           	 
           	 System.out.println("here is the canceled Order");
           	 

               e.printStackTrace();
               
            
            
            
            }
            
            
            
            
            
            return response;
        
        } catch (MarketplaceWebServiceProductsException ex) {
        	
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            
            if(rhmd != null) {
            	
                System.out.println("RequestId: "+rhmd.getRequestId());
                
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            
            System.out.println("Message: "+ex.getMessage());
            
            System.out.println("StatusCode: "+ex.getStatusCode());
            
            System.out.println("ErrorCode: "+ex.getErrorCode());
            
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;                                                                                                                              
        }
    }

    /**
     *  Command line entry point.
     * @throws IOException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, InterruptedException {

 
    	//retrieve SKU from DB
    	
//open db connection to retireve skus.
        
    	String url = "0";
        String username = "0";
        String password = "0";
        
        System.out.println("Loading driver...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        
        System.out.println("Connecting database...");
        
        
        
        
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
        	
        	
            System.out.println("Database connected!");
            
            
            
            
            //if successfully connected, will begin to retrieve sku #
           
    
            String query = "select SKU from AmazonListing";
              
            
            Statement st = connection.createStatement();
            
           
           //Intialize Arraylist to store sku;
            ArrayList<String> str = new ArrayList<String>();
            
         // execute the preparedstatement, and get result
            ResultSet rs = st.executeQuery(query);
    	
    	//use object attribute to store result, then store object attributes to a list
            
            AmazonListing al = new AmazonListing(query, query, query, query, query, query, query, query, query, query, query, query, query, query, query, query, query, query, query, query, query, query);
    	
    	//while there is sku in result, stored it in object, then add object to arraylist
    	
            while(rs.next()){
         	   
           	 
         	   //set retrieved ordernumbers from resultset rs to al object
         	al.setSku(rs.getString("SKU"));
         	
         	//write  al object to arraylist of string str
         	str.add(al.getSku());
         	   
    
         	   
         	   
            }
    	
            //test and try 

		    for(int i = 0; i<str.size();i++)
		    {
		    	
		    	
		    	System.out.println(str.get(i));
		    	
		    	
		    }
            
    	
    	
		    //after retrieving the result, close the connection
            connection.close();
            
    	
    	
        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();
        
     
     

        
        // Create a request.
        GetMyPriceForSKURequest request = new GetMyPriceForSKURequest();
        String sellerId = "0";
        request.setSellerId(sellerId);
        String mwsAuthToken = "0";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "0";
        request.setMarketplaceId(marketplaceId);

        //initialize for request input
        SellerSKUListType listofskus = new SellerSKUListType();
        
    
        //only accept 20 sku  at a time, this code is to send 20 at a time.  Lists.partition is google lang
        for (List<String> skupartition : Lists.partition(str, 20)) {
      	  
      	  System.out.println(skupartition.size());
      	  
      	  //set a list of size 50 to request
      	    listofskus.setSellerSKU(skupartition);
      	  
          //set request
      	    request.setSellerSKUList(listofskus);

            // Make the call.
      	  GetMyPriceForSKUSample.invokeGetMyPriceForSKU(client, request);
            
            
      	  System.out.println("Im about to get another set");
      	
      	
      	
            //time paused for each request, time out in mili seconds. 
            Thread.sleep(10000);
            
            
            
            
            
            
            
      	}
        
        
        

        
        
        }  catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        
        
        
    }
    
}
        