package com.techfynder.forex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.techfynder.forex.objects.Currency;
import com.techfynder.forex.objects.Rates;
import com.techfynder.forex.repo.CurrencyRepo;

@SpringBootTest(webEnvironment = WebEnvironment .RANDOM_PORT)
@ExtendWith(CurrencyParameterResolver.class)
public class CurrencyServiceTest {

	@InjectMocks
	private CurrencyService currServ;
	@Mock
	public CurrencyRepo currRepo;

	Rates r1;

	Currency c1;


	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		r1 = new Rates("1", "2");
		c1 = new Currency("1", "XYZ", r1);
		
	}

	@Test
	public void testSave(Currency curr) throws Exception {

		when(currRepo.save(c1)).thenReturn(c1);
		boolean flag = currServ.save(c1);
		assertTrue(flag);
		verify(currRepo, times(1)).save(c1);

	}

	@Test
	public void testFetchAll() throws Exception {

		List<Currency> currencies1 = new ArrayList<Currency>();
		currencies1.add(c1);
		when(currRepo.findAll()).thenReturn(currencies1);
		List<Currency> currencies2 =currServ.fetchAll();
		assertEquals(currencies1, currencies2);
		verify(currRepo, times(1)).findAll();
		
	}
	
	@Test
	public void testfindBySymbol() {
		when(currRepo.findByCurrencyName("XYZ")).thenReturn(c1);
		Currency c2 = currServ.findByCurrencySymbol("XYZ");
		assertEquals(c1, c2);
		verify(currRepo, times(1)).findByCurrencyName(c1.getCurrencyName());
		
	}


}
