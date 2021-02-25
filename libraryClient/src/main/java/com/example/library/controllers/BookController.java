
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

import com.example.library.dtos.AuthorDto;
import com.example.library.dtos.BookDto;
import com.example.library.services.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("books")
@Slf4j
public class BookController {
	
	@Autowired
	BookService bookService;
		
	@GetMapping()
	@ResponseBody
	public List<BookDto> getBooks(@RequestParam(required = false) AuthorDto author, @RequestParam(required = false) String title) {
		
		if (author == null && title == null)
		{
			List<BookDto> books = bookService.getAll();		
			return books;
		}
		List<BookDto> books = bookService.getByPublisherOrTitle(author, title);		
		return books;
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<BookDto> searchBooks(@RequestParam String title) {
		List<BookDto> books =  bookService.searchByTitle(title);
		return books;
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public BookDto getBookById(@PathVariable int id) {
		BookDto book = bookService.getById(id);
		return book;
	}

	
	@PostMapping
	@ResponseBody
	public BookDto createBook(@RequestBody BookDto newBookDto) {
		BookDto newBook = bookService.create(newBookDto);
	    return newBook;
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
    public String deleteBook(@PathVariable int id) {
		bookService.delete(id);
		return "Successfuly deleted.";
		
    }	
	
	@PutMapping(path="/{id}", produces = "application/json")
	@ResponseBody
	public BookDto updateBook(@PathVariable int id, @RequestBody BookDto newBookDto) {
		BookDto newBook = bookService.update(id, newBookDto);		
		return newBook;	
	    
	}

}
