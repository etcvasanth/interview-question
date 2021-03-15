package com.example.demo.backbase.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Entity
@Table(name="course_registration" , uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "courseId"}) })
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne
	@JsonIgnore
	@JoinColumn(name = "courseId", nullable = false)
	private Courses courseId;
   
    @Column
    private String name;
    
    @Column
    private LocalDate registrationDate;
}