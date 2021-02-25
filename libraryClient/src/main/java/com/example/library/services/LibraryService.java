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

import com.example.library.dtos.LibraryDto;

@Service

public class LibraryService {

	@Autowired
	RestTemplate restTemplate;
	@Value("${server.url}")
	String uri;
	String l = "/libraries";
	
	public List<LibraryDto> getAll()
	{
		ResponseEntity<List<LibraryDto>> libraries = restTemplate.exchange(uri+l, HttpMethod.GET, null, new ParameterizedTypeReference<List<LibraryDto>>() {});
		return libraries.getBody();
	}
	
	public LibraryDto getById(int id)
	{
		return restTemplate.getForObject(uri+l+"/"+id, LibraryDto.class);
	}
	
	public List<LibraryDto> getByName(String libraryName)
	{
		ResponseEntity<List<LibraryDto>> libraries = restTemplate.exchange(uri +l+ "/search?libraryName=" + libraryName, HttpMethod.GET, null, new ParameterizedTypeReference<List<LibraryDto>>() {
		});
		return libraries.getBody();
	}
	
	public LibraryDto create(LibraryDto newLibrary)
	{
		return restTemplate.postForObject(uri+l, newLibrary, LibraryDto.class);
	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri+l+"/"+id);
	}
	
	public LibraryDto update(int id, LibraryDto newLibrary)
	{
		HttpEntity<LibraryDto> entity = new HttpEntity<LibraryDto>(newLibrary);
		ResponseEntity<LibraryDto> response = restTemplate.exchange(uri+l+"/"+id, HttpMethod.PUT, entity, LibraryDto.class );
		return response.getBody();
	}
}
