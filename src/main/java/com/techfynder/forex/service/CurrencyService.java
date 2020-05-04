package com.techfynder.forex.service;

import java.util.List;
import java.util.Optional;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techfynder.forex.objects.Currency;
import com.techfynder.forex.objects.Rates;
import com.techfynder.forex.repo.CurrencyRepo;


@Service
public class CurrencyService {

	@Autowired
	public CurrencyRepo currRepo;
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Bean
	public  RestTemplate restTemplate(RestTemplateBuilder builder) {
		CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		 RestTemplate rt = builder.build();
		 rt.setRequestFactory(requestFactory);
		 return rt;
	}
	
   public boolean save(Currency curr){
    	
    	boolean flag = false;
    	try {
        	if(curr!=null) {
        		if (findByCurrencySymbol(curr.getCurrencyName()) != null) {
        			System.out.println("Currency Already Exists");
        			return false;
        		}
        			
    		currRepo.save(curr);
    	    flag=true;
    	}
    	
    	}
    	
    	catch(Exception e) {
    		System.out.println("Exception "+e);
    	    flag =false;
    	}
		return flag;
    	
    }
    
    
    public boolean delete(Currency curr){
    	
    	boolean flag = false;
    	try {
        	if(curr!=null) {
    		currRepo.delete(curr);
    	    flag=true;
    	}
    	
    	}
    	
    	catch(Exception e) {
    		System.out.println("Exception "+e);
    	    flag =false;
    	}
		return flag;
    	
    }
    
    
  public List<Currency> fetchAll(){
    	
	  List<Currency> currList = null ;
    	try {
    		currList = currRepo.findAll();    	   	
    	}
    	
    	catch(Exception e) {
    		System.out.println("Exception "+e);    	  
    	}
		return currList;
    	
    }
    
  
  public Currency findByCurrencySymbol(String symbol){
	  
	  Currency curr = null;
  	
	  	try {	  		
	  		curr = currRepo.findByCurrencyName(symbol);    
    	}
    	
    	catch(Exception e) {
    		System.out.println("Exception "+e);    	  
    	}
		return curr;
    	
    }
  
  public ResponseEntity<Rates> callApi(String symbol) {
	  
	  String url ="https://api.ratesapi.io/api/latest?base="+symbol+"&symbols=USD";
	  Currency curr = null ;
	  	try {
	 //String url = "https://api.exchangeratesapi.io/latest?base=USD&symbols=INR";
	  curr = restTemplate.getForObject(url, Currency.class);
	  System.out.println("url is " +url);
	  System.out.println("Rates " +curr.getRates().getValueInDollars());
	  System.out.println("currency Name  is " +curr.getCurrencyName());
	 System.out.println("Saving to DB " +save(curr));	 
	  	}
	
	  	catch(Exception e) {
	  		System.out.println("Exception "+e);    	
	  	}
	  	
	return ResponseEntity.status(HttpStatus.OK).body(curr.getRates())  ;
	   
  }
	
}












