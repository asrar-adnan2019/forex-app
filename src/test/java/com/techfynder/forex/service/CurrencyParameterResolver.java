package com.techfynder.forex.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.techfynder.forex.objects.Currency;

public class CurrencyParameterResolver implements ParameterResolver {
  @Override
  public boolean supportsParameter(ParameterContext parameterContext, 
    ExtensionContext extensionContext) throws ParameterResolutionException {
      return parameterContext.getParameter().getType() == Currency.class;
  }
 
  @Override
  public Object resolveParameter(ParameterContext parameterContext, 
    ExtensionContext extensionContext) throws ParameterResolutionException {
      return new Currency();
  }
}