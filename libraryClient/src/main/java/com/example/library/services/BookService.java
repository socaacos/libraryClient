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
import com.example.library.dtos.BookDto;

@Service

public class BookService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${server.url}")
	String uri;
	String b = "/books";
	
	public List<BookDto> getAll()
	{
		ResponseEntity<List<BookDto>> responseEntity = restTemplate.exchange(uri+b, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {});
		List<BookDto> books = responseEntity.getBody();
		return books;
	}
	
	public BookDto getById(int id)
	{
		BookDto result = restTemplate.getForObject(uri+b+"/" + id, BookDto.class);
		 return result;
	}
	
	public List<BookDto> getByPublisherOrTitle(AuthorDto author, String title)
	{
		//ne valja!!!
		ResponseEntity<List<BookDto>> responseEntity = restTemplate.exchange(uri+"",HttpMethod.GET,null,new ParameterizedTypeReference<List<BookDto>>() {});
				List<BookDto> books = responseEntity.getBody();
				return books;
	}
	
	public List<BookDto> searchByTitle(String title)
	{
		ResponseEntity<List<BookDto>> responseEntity =  restTemplate.exchange(uri+b+"/search?title=" + title, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {});
		List<BookDto> books = responseEntity.getBody();
		return books;
	}
	
	public BookDto create(BookDto newBook)
	{
		BookDto result = restTemplate.postForObject(uri+b, newBook, BookDto.class);
		return result;

	}
	
	public void delete(int id)
	{
		restTemplate.delete(uri+b+"/"  + id);
	}
	
	
	public BookDto update(int id, BookDto newBook)
	{
		HttpEntity<BookDto> entity = new HttpEntity<BookDto>(newBook);
		ResponseEntity<BookDto> book = restTemplate.exchange(uri+b+"/"  + id, HttpMethod.PUT, entity, BookDto.class);
		return book.getBody();
	}

}
