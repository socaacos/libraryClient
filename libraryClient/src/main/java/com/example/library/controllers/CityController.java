package com.example.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dtos.CityDto;
import com.example.library.services.CityService;

@RestController
@RequestMapping("cities")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@GetMapping()
	@ResponseBody
	public List<CityDto> getAll()
	{
		List<CityDto> cities = cityService.getAll();
		return cities;	
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<CityDto> searchCities(@RequestParam String cityName) {
		List<CityDto> cities=  cityService.getByName(cityName);
		return cities;
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public CityDto getById(@PathVariable int id)
	{
		CityDto city = cityService.getById(id);
		return city;
	}
	
	@PostMapping
	@ResponseBody
	public CityDto createCity(@RequestBody CityDto newCityDto) {
		CityDto newCity= cityService.create(newCityDto);
	    return newCity;
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteCity(@PathVariable int id) {
		cityService.delete(id);
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public CityDto updateCity(@PathVariable int id, @RequestBody CityDto newCityDto) {
		CityDto newCity = cityService.update(id, newCityDto);		
		return newCity;	
	    
	}

}
