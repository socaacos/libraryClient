package com.example.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.CityControllerApi;
import org.openapitools.client.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dtos.CityDto;


@RestController
@RequestMapping("cities")
public class CityController {
	
	@Autowired
	CityControllerApi cityApi;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping()
	@ResponseBody
	public List<CityDto> getAll()
	{
		List<City> cities;
		try {
			cities = cityApi.getAll1();
			List<CityDto> cityDtos = new ArrayList<CityDto>();
			for (City city : cities) {
				cityDtos.add(modelMapper.map(city, CityDto.class));
			}
							
				return cityDtos;	
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<CityDto> searchCities(@RequestParam String cityName) {
		List<City> cities;
		try {
			cities = cityApi.searchCities(cityName);
			List<CityDto> cityDtos = new ArrayList<CityDto>();
			for (City city: cities) {
				cityDtos.add(modelMapper.map(city, CityDto.class));
			}
						
			return cityDtos;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public CityDto getById(@PathVariable int id)
	{
		City city;
		try {
			city = cityApi.getById1(id);
			CityDto cityDto = modelMapper.map(city, CityDto.class);
			return cityDto;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@PostMapping
	@ResponseBody
	public CityDto createCity(@RequestBody CityDto newCityDto, @RequestHeader("Authorization") String auth) {
		City city = modelMapper.map(newCityDto, City.class);
		City newCity;
		try {
			cityApi.getApiClient().addDefaultHeader("Authorization", auth);
			newCity = cityApi.createCity(city);
			return modelMapper.map(newCity, CityDto.class);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCityDto;
	    
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteCity(@PathVariable int id, @RequestHeader("Authorization") String auth) {
		try {
			cityApi.getApiClient().addDefaultHeader("Authorization", auth);
			cityApi.deleteCity1(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public CityDto updateCity(@PathVariable int id, @RequestBody CityDto newCityDto, @RequestHeader("Authorization") String auth) {
		City city = modelMapper.map(newCityDto, City.class);		
		City newCity;
		try {
			cityApi.getApiClient().addDefaultHeader("Authorization", auth);
			newCity = cityApi.updateCity(id, city);
			return modelMapper.map(newCity, CityDto.class);	
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCityDto;		
		
	    
	}

}
