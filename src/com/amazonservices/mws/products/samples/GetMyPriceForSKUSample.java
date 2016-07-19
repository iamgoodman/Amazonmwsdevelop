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
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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
     */
    public static void main(String[] args) {

    	
    	
    	
        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();
        
     
     

        
        // Create a request.
        GetMyPriceForSKURequest request = new GetMyPriceForSKURequest();
        String sellerId = "***";
        request.setSellerId(sellerId);
        String mwsAuthToken = "***";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "***";
        request.setMarketplaceId(marketplaceId);
        
        SellerSKUListType sellerSKUList = new SellerSKUListType();
        
        List<String> a = new ArrayList();
        a.add("IB-D4208A");
        
        sellerSKUList.setSellerSKU(a);
        
        request.setSellerSKUList(sellerSKUList);
        
    

        // Make the call.
        String r = GetMyPriceForSKUSample.invokeGetMyPriceForSKU(client, request).toXML();
        
        
        
             System.out.println(r);
     
        
//sax parser for xml

        try {
        	

        	SAXParserFactory factory = SAXParserFactory.newInstance();
        	SAXParser saxParser = factory.newSAXParser();

        	DefaultHandler handler = new DefaultHandler() {

        	boolean SellerSKU = false;
        	boolean CurrencyCode = false;
        	boolean Amount = false;
        	

        	public void startElement(String uri, String localName,String qName, 
                        Attributes attributes) throws SAXException {

        		System.out.println("Start Element :" + qName);

        		if (qName.equalsIgnoreCase("SellerSKU")) {
        			SellerSKU = true;
        		}

        		if (qName.equalsIgnoreCase("CurrencyCode")) {
        			CurrencyCode = true;
        		}

        		if (qName.equalsIgnoreCase("Amount")) {
        			Amount = true;
        		}

        		

        	}

        	public void endElement(String uri, String localName,
        		String qName) {

        		System.out.println("End Element :" + qName);

        	}

        	public void characters(char ch[], int start, int length) {

        		if (SellerSKU) {
        			System.out.println(" SellerSKU : " + new String(ch, start, length));
        			 SellerSKU = false;
        		}

        		if (CurrencyCode) {
        			System.out.println("CurrencyCode : " + new String(ch, start, length));
        			CurrencyCode = false;
        		}

        		if (Amount) {
        			System.out.println("Amount : " + new String(ch, start, length));
        			Amount = false;
        		}

        	
        	}

             };

             saxParser.parse(new InputSource(new StringReader(r)), handler);
         
             } catch (Exception e) {
               e.printStackTrace();
             }
          
           }

        

    }


