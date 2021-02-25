package com.example.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.library.dtos.CityDto;

@Service
public class CityService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${server.url}")
	String uri;
	String c = "/cities";
		
	public List<CityDto> getAll()
	{
		ResponseEntity<List<CityDto>> responseEntity = restTemplate.exchange(uri +c , HttpMethod.GET, null, new ParameterizedTypeReference<List<CityDto>>() {});
		List<CityDto> cities = responseEntity.getBody();
		return cities;
	}

	public CityDto getById(int id)
	{
		CityDto city = restTemplate.getForObject(uri + c + "/" + id, CityDto.class);
		return city;
	}
	
	public List<CityDto> getByName(String cityName)
	{
		ResponseEntity<List<CityDto>> responseEntity = restTemplate.exchange(uri +c+ "/search?cityName=" + cityName, HttpMethod.GET, null, new ParameterizedTypeReference<List<CityDto>>() {});
		return responseEntity.getBody();
	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri+c+"/"+id);
	}
	
	public CityDto create(CityDto newCity)
	{
		return restTemplate.postForObject(uri +c, newCity, CityDto.class);
	}
	
	public CityDto update(int id, CityDto newCity)
	{
		HttpEntity<CityDto> entity = new HttpEntity<CityDto>(newCity);
		ResponseEntity<CityDto> response =  restTemplate.exchange(uri+c+"/" + id, HttpMethod.PUT, entity, CityDto.class);
		return response.getBody();
	}
}
