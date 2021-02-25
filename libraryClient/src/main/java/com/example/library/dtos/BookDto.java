package com.example.library.dtos;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.tomcat.jni.Library;

import lombok.Data;

@Data
public class BookDto 
{
	private Integer id;
	private String title;
	private AuthorDto author;
	private int publicationYear;
	private int numPages;
	private Collection<LibraryDto> libraries = new ArrayList<LibraryDto>();
}
