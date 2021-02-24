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
import com.example.library.entities.Book;

@Service

public class BookService {
	
	@Autowired
	RestTemplate restTemplate;
	
	String uri = "http://localhost:8080/books/";
	public List<Book> getAll()
	{
		ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {});
		List<Book> books = responseEntity.getBody();
		return books;
	}
	
	public Book getById(int id)
	{
		 Book result = restTemplate.getForObject(uri + id, Book.class);
		 return result;
	}
	
	public List<Book> getByPublisherOrTitle(Author author, String title)
	{
		//ne valja!!!
		ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(uri+"",HttpMethod.GET,null,new ParameterizedTypeReference<List<Book>>() {});
				List<Book> books = responseEntity.getBody();
				return books;
	}
	
	public List<Book> searchByTitle(String title)
	{
		ResponseEntity<List<Book>> responseEntity =  restTemplate.exchange(uri+"search?title=" + title, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {});
		List<Book> books = responseEntity.getBody();
		return books;
	}
	
	public Book create(Book newBook)
	{
		Book result = restTemplate.postForObject(uri, newBook, Book.class);
		return result;

	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri + id);
	}
	
	
	public Book update(int id, Book newBook)
	{
		HttpEntity<Book> entity = new HttpEntity<Book>(newBook);
		ResponseEntity<Book> book = restTemplate.exchange(uri + id, HttpMethod.PUT, entity, Book.class);
		return book.getBody();
	}

}
