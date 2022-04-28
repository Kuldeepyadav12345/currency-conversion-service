package com.training.boot.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.boot.ms.dao.CurrencyConversionRepository;
import com.training.boot.ms.model.CurrencyConverter;

@RestController
@RequestMapping(value = "/api/v1")
@RefreshScope
public class CurrencyConverterController {

	@Autowired
	private CurrencyConversionRepository repository;

	@Value("$(app.message)")
	private String message;

	@RequestMapping(value = "/from/{from}/to/{to}")
	public CurrencyConverter convertCurrency(@PathVariable String from, @PathVariable String to) {

		CurrencyConverter converter = new CurrencyConverter(null, from, to, null);
		Example<CurrencyConverter> conversionFilter = Example.of(converter);
		return repository.findOne(conversionFilter).get();
	}

	@GetMapping("/message")
	public String message() {
		return this.message;
	}
}
