package com.example.demo.backbase.persistence.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="courses")
public class Courses {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column    
    private Integer id;

    @Column
    private Integer capacity;
    
    @Column
    private LocalDate endDate;
    
    @Column
    private Integer remaining;
    
    @Column
    private LocalDate startDate;
    
    @Column
    private String title;
    
    @JsonProperty("participants")
	@OneToMany(mappedBy = "courseId")
	private List<CourseRegistration> courseRegistration;
  
}