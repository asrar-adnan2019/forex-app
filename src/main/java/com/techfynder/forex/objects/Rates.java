package com.techfynder.forex.objects;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {	
    @Id
	private String id;
	
	public Rates() {
		
	}
	
	
	@JsonAlias("USD")
	@JsonProperty("value_in_dollars")
	private String valueInDollars;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getValueInDollars() {
		return valueInDollars;
	}


	public void setValueInDollars(String valueInDollars) {
		this.valueInDollars = valueInDollars;
	}


	public Rates(String id, String valueInDollars) {
		super();
		this.id = id;
		this.valueInDollars = valueInDollars;
	}

	


	
}	
	
	

