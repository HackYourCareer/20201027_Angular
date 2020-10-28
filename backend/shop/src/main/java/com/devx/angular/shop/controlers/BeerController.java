package com.devx.angular.shop.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devx.angular.shop.dto.BeerDetailDto;
import com.devx.angular.shop.dto.BeerDto;
import com.devx.angular.shop.services.BeerService;

import io.swagger.annotations.ApiOperation;


@RestController
public class BeerController
{

	private BeerService beerService;

	@Autowired
	public BeerController(final BeerService beerService)
	{
		this.beerService = beerService;
	}

	@GetMapping(path = "beer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Return all beers")
	public List<BeerDto> getBeers()
	{
		return beerService.getAllBears();
	}

	@GetMapping(path = "beer/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Return detail about one beer")
	public BeerDetailDto getBeer(@PathVariable String name)
	{
		return beerService.getBeer(name);
	}

	@GetMapping(path = "test/beer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<BeerDto> getTestBeers()
	{
		return beerService.getAllBears();
	}

	@GetMapping(path = "test/beer/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public BeerDetailDto getTestBeer(@PathVariable String name)
	{
		return beerService.getBeer(name);
	}
}
