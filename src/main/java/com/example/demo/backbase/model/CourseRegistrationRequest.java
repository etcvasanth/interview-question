package com.example.demo.backbase.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CourseRegistrationRequest {

   
    private int courseId;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    private String name;
    
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate cancelDate;
}
