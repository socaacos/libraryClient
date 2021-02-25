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

import com.example.library.dtos.AuthorDto;
@Service

public class AuthorService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${server.url}")
	String uri;
	String auth = "/authors";
	
	public List<AuthorDto> getAll()
	{
		ResponseEntity<List<AuthorDto>> responseEntity = restTemplate.exchange(uri + auth, HttpMethod.GET, null, new ParameterizedTypeReference<List<AuthorDto>>() {});
		List<AuthorDto> authors = responseEntity.getBody();
		return authors;
	}
	
	public AuthorDto getById(int id)
	{
		AuthorDto author = restTemplate.getForObject(uri + auth + "/" + id, AuthorDto.class);
		return author;
	}
	
	public List<AuthorDto> searchByName(String name)
	{
		ResponseEntity<List<AuthorDto>> responseEntity = restTemplate.exchange(uri + auth + "/search?name=" + name, HttpMethod.GET, null, new ParameterizedTypeReference<List<AuthorDto>>() {});
		List<AuthorDto> authors = responseEntity.getBody();
		return authors;
	}
	
	public AuthorDto create(AuthorDto newAuthor)
	{
		AuthorDto result = restTemplate.postForObject(uri + auth, newAuthor, AuthorDto.class);
		return result;
	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri+auth + "/" + id);
	}
	
	public AuthorDto update(int id, AuthorDto newAuthor)
	{
		HttpEntity<AuthorDto> entity = new HttpEntity<AuthorDto>(newAuthor);
		ResponseEntity<AuthorDto> author = restTemplate.exchange(uri + auth + "/" + id, HttpMethod.PUT, entity, AuthorDto.class);
		return author.getBody();
	}

}
