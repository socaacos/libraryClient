package com.example.library.dtos;

import lombok.Data;

@Data
public class ReportDto {

	private int id;

	private String request;

	private Integer status;

	public ReportDto(String request, int status) {
		this.request = request;
		this.status = status;
	}
	
	public ReportDto() {}
	
	
}
