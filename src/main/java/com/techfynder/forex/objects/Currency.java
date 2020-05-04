package com.techfynder.forex.objects;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
	
	@Id
	String id;	
	
	@JsonProperty("base")
	String currencyName;

	private Rates rates;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public Currency(String id, String currency, Rates rates) {
		super();
		this.id = id;
		this.currencyName = currency;
		this.rates = rates;
	}


	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	
	public Currency() {
		
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}
}
