package com.devx.angular.shop.dto;

import lombok.Data;

import java.util.List;

@Data
public class BeerDetailDto {
	private String name;
	private String img;
	private String description;
	private String alc;
	private String ibu;
	private List<String> hops;
	private String style;
}
