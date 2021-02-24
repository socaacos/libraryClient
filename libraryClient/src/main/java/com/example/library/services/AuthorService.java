package com.example.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.library.entities.Author;
@Service

public class AuthorService {
	
	@Autowired
	RestTemplate restTemplate;
	
	String uri = "http://localhost:8080/authors/";

	public List<Author> getAll()
	{
		ResponseEntity<List<Author>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Author>>() {});
		List<Author> authors = responseEntity.getBody();
		return authors;
	}
	
	public Author getById(int id)
	{
		Author author = restTemplate.getForObject(uri + id, Author.class);
		return author;
	}
	
	public List<Author> searchByName(String name)
	{
		ResponseEntity<List<Author>> responseEntity = restTemplate.exchange(uri + "search?name=" + name, HttpMethod.GET, null, new ParameterizedTypeReference<List<Author>>() {});
		List<Author> authors = responseEntity.getBody();
		return authors;
	}
	
	public Author create(Author newAuthor)
	{
		Author result = restTemplate.postForObject(uri, newAuthor, Author.class);
		return result;
	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri+id);
	}
	
	public Author update(int id, Author newAuthor)
	{
		HttpEntity<Author> entity = new HttpEntity<Author>(newAuthor);
		ResponseEntity<Author> author = restTemplate.exchange(uri + id, HttpMethod.PUT, entity, Author.class);
		return author.getBody();
	}

}
