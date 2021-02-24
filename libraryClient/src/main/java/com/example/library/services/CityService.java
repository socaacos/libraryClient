package com.example.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.library.entities.City;

@Service
public class CityService {
	
	@Autowired
	RestTemplate restTemplate;
	
	String uri = "http://localhost:8080/cities/";
		
	public List<City> getAll()
	{
		ResponseEntity<List<City>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<City>>() {});
		List<City> cities = responseEntity.getBody();
		return cities;
	}

	public City getById(int id)
	{
		City city = restTemplate.getForObject(uri + id, City.class);
		return city;
	}
	
	public List<City> getByName(String cityName)
	{
		ResponseEntity<List<City>> responseEntity = restTemplate.exchange(uri + "search?cityName=" + cityName, HttpMethod.GET, null, new ParameterizedTypeReference<List<City>>() {});
		return responseEntity.getBody();
	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri+id);
	}
	
	public City create(City newCity)
	{
		return restTemplate.postForObject(uri, newCity, City.class);
	}
	
	public City update(int id, City newCity)
	{
		HttpEntity<City> entity = new HttpEntity<City>(newCity);
		ResponseEntity<City> response =  restTemplate.exchange(uri + id, HttpMethod.PUT, entity, City.class);
		return response.getBody();
	}
}
