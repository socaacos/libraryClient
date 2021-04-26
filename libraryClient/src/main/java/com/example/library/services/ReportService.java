package com.example.library.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.library.dtos.Report;
import com.example.library.dtos.ReportDto;
import com.example.library.repositories.ReportRepository;

public class ReportService {
	
	@Autowired
	ReportRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ReportDto create(ReportDto newReportDto)
	{
		Report report = modelMapper.map(newReportDto, Report.class);
		Report newReport= repository.save(report);
	    return modelMapper.map(newReport, ReportDto.class);	}

}
