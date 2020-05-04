package com.techfynder.forex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techfynder.forex.objects.Currency;
import com.techfynder.forex.objects.Rates;
import com.techfynder.forex.service.CurrencyService;

@RestController()
public class CurrencyController {
    
	@Autowired
	private CurrencyService currServ;
	
	
	@GetMapping("/getcurrency")
	public Rates getCurrency(@RequestParam String symbol) {
	    Rates rates = currServ.callApi(symbol).getBody();		
	   
		return rates;		
		
	}
	
	
	@GetMapping("/getcurrencyfromdb")
	public Currency getCurrencyFromDb(@RequestParam String symbol) {
		
		Currency curr = currServ.findByCurrencySymbol(symbol);		
		return curr;		
		
	}
	
	
	@GetMapping("/getallcurrencies")
	public List<Currency> getAllCurrencies() {
		
		List<Currency> currList = null;
		currList = currServ.fetchAll();		
		return currList;		
		
	}
	
}
