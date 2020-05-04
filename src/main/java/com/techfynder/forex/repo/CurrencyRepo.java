package com.techfynder.forex.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techfynder.forex.objects.Currency;

public interface CurrencyRepo extends MongoRepository<Currency, String> {

    public Currency  findByCurrencyName(String currency);

}
