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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;
import java.math.BigDecimal;


import com.amazonservices.mws.client.*;
import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;


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
     */
    public static void main(String[] args) throws IOException {

    	
        //create hasmap to store SKU and price pairs.
        
        HashMap<String, String> newmap = new HashMap<String, String> (); 
    	
    

        
     
        
    	 // Location of the source file
       String sourceFilePath = "Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\AmazonListingFileLite1.xls";
       
 
    		   
       FileInputStream fileInputStream = null;
         
       // Array List to store the excel sheet data
       ArrayList excelData = new ArrayList();
       
       
     //String array to store SKUs to get price
       List<String> str = new ArrayList<String>();

         
       //A more robust importing method for importing excel data to arrays
       try {
             
           // FileInputStream to read the excel file
           fileInputStream = new FileInputStream(sourceFilePath);

           // Create an excel workbook
           HSSFWorkbook excelWorkBook = new HSSFWorkbook(fileInputStream);
             
           // Retrieve the first sheet of the workbook.
           HSSFSheet excelSheet = excelWorkBook.getSheetAt(0);
             
         
           // Iterate through the sheet rows and cells. 
           // Store the retrieved data in an arrayList
           java.util.Iterator<Row> rows = excelSheet.rowIterator();
           while (rows.hasNext()) {
               HSSFRow row = (HSSFRow) rows.next();
               java.util.Iterator<Cell> cells = row.cellIterator();

               ArrayList cellData = new ArrayList();
               while (cells.hasNext()) {
                   HSSFCell cell = (HSSFCell) cells.next();
                   cellData.add(cell);
               }

               excelData .add(cellData);
           }
             

           for(int i = 0; i<excelData.size();i++)
           {
          	 
          	 
          	String a = (excelData.get(i).toString().split(",")[0]);
          	
          	System.out.println(a.substring(1, a.length()));
          	
          	str.add(a.substring(1, a.length()));
          	 
           }
           
           
           for(int i = 0; i<str.size();i++)
           {
        	   
        	   
        	   System.out.println(str.get(i));
        	   
        	   
           }
           
           
         
         
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (fileInputStream != null) {
               try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           }
       }
       

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();
        
     
     

        
        // Create a request.
        GetMyPriceForSKURequest request = new GetMyPriceForSKURequest();
        String sellerId = "****";
        request.setSellerId(sellerId);
        String mwsAuthToken = "***";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "***";
        request.setMarketplaceId(marketplaceId);

        
        SellerSKUListType sellerSKUList = new SellerSKUListType();
        
    
        
        
        
        request.setSellerSKUList(sellerSKUList);
        
    //unable to send all request once, reaches maxium, need to send one at a time.  
     for (int i =0;i<str.size();i++)
    	 
     {
    	 
         List<String> a = new ArrayList<String>();
    	 
    	 a.add(str.get(i));
   
    	 sellerSKUList.setSellerSKU(a);
    	 
  
        // Make the call.
        String r = GetMyPriceForSKUSample.invokeGetMyPriceForSKU(client, request).toXML();
        
        
        
         /*    System.out.println(r);*/
     
        
//sax parser for xml, retrieve selling price

        try {
        	

        	SAXParserFactory factory = SAXParserFactory.newInstance();
        	SAXParser saxParser = factory.newSAXParser();

        	DefaultHandler handler = new DefaultHandler() {

        	boolean SellerSKU = false;
        
            //Only need to read sku once, this variable is to avoid the reprint of sku twice.
            boolean toprint = true;
        	
       
        	boolean SellingPrice = false;
        	

        	public void startElement(String uri, String localName,String qName, 
                        Attributes attributes) throws SAXException {

        		/*System.out.println("Start Element :" + qName);*/
        		
        		if (qName.equalsIgnoreCase("ListingPrice") ) {
        		
        			
        		  SellingPrice = true;
        		    
        			 
        		}
        		
        		if (qName.equalsIgnoreCase("SellerSKU")) {
        			SellerSKU = true;
        			
        		}

       		

        	}

        	public void endElement(String uri, String localName,
        		String qName) {


				
        		
        		if (qName.equalsIgnoreCase("ListingPrice")) {
        			
        			SellingPrice = false;
        		}
        		
        		if (qName.equalsIgnoreCase("SellerSKU")) {
        			   SellerSKU = false;
        		}

        		
        	}

        	public void characters(char ch[], int start, int length) {

        
        		
        		if (SellerSKU && toprint) {
        			
        			
        			System.out.println(" SellerSKU : " + new String(ch, start, length));
        			
        			String sku = new String(ch, start, length);
        			 
        			 s= sku;
 	
        			 toprint = false;
        			
        			
        			 
        		}

      
        		
        		if (SellingPrice) {
        			
        			String c = new String(ch, start, length);
        			 
        			 if(c.contains("USD"))
        			 {
        				 
        				 
        				 System.out.println("this is currency code");
        				 
        			 }
        			 else
        			 {
        				 System.out.println("Amount : " + new String(ch, start, length));
        	
        				 String bp = new String(ch, start, length);
        			 
        				 System.out.println("selling price is " + bp);
        	                  
        				 sp = bp;
        			 }
        			 
        		
        			 newmap.put(s,sp);
        			
        		}
        		
        		
        		
     
                       
        		
        	}

             };

            saxParser.parse(new InputSource(new StringReader(r)), handler);
             
     
            
	           
	 
	           
         
             } catch (Exception e) {
            	 
            	 
               e.printStackTrace();
             }
          
           }

     
     
     
     //write retrieved price to excel     
     
     	HSSFWorkbook wb = new HSSFWorkbook();
     	
     	
		HSSFSheet sheet = wb.createSheet("new sheet");
     
	
		 int rowIndex = 1; 

     
		 	for (Entry<String, String> entry : newmap.entrySet()) {
        	
        	
        	
	             String key = entry.getKey();
	             String values = entry.getValue();
	            
	              
	              Row row = sheet.createRow(rowIndex++);
	              
			      
	              	sheet.createRow(0).createCell(0).setCellValue("SKU");
	              	
			        sheet.getRow(0).createCell(1).setCellValue("Selling Price");
	              
			        
			        row.createCell(0).setCellValue(key);
			        
			        
			        row.createCell(1).setCellValue(values);
			        
			        
	              
	             
			        System.out.println("Key = " + key);
			        
                   
			        
			        System.out.println("Values = " + values );
			        
			              
			        
	              
	          }
	         
		 	
		 	
	     
		        String fileName = "Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\AmazonSellingPrice.xls";
		        
		        
		        
		        
		        
		        FileOutputStream fileOut = new FileOutputStream(fileName);
		        
		        
		        
		        
		        
		        wb.write(fileOut);
		        
		        
		        
		        
				 
		        
		        fileOut.close();
		        
   

		        
		        
		        
		        System.out.println("file created");
	
		        
		        
	
	    }

}
