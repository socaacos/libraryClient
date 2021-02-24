package com.example.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.library.entities.Library;

@Service

public class LibraryService {

	@Autowired
	RestTemplate restTemplate;
	
	String uri = "http://localhost:8080/libraries/";
	
	public List<Library> getAll()
	{
		ResponseEntity<List<Library>> libraries = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Library>>() {});
		return libraries.getBody();
	}
	
	public Library getById(int id)
	{
		return restTemplate.getForObject(uri+id, Library.class);
	}
	
	public List<Library> getByName(String libraryName)
	{
		ResponseEntity<List<Library>> libraries = restTemplate.exchange(uri + "search?libraryName=" + libraryName, HttpMethod.GET, null, new ParameterizedTypeReference<List<Library>>() {
		});
		return libraries.getBody();
	}
	
	public Library create(Library newLibrary)
	{
		return restTemplate.postForObject(uri, newLibrary, Library.class);
	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri+id);
	}
	
	public Library update(int id, Library newLibrary)
	{
		HttpEntity<Library> entity = new HttpEntity<Library>(newLibrary);
		ResponseEntity<Library> response = restTemplate.exchange(uri+id, HttpMethod.PUT, entity, Library.class );
		return response.getBody();
	}
}
