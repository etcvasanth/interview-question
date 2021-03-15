package com.example.demo.backbase.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CourseRegistrationResponse {

	private Integer id;

	private Integer courseId;

	private String name;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate registrationDate;


}
