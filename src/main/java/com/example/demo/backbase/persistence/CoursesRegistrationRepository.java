package com.example.demo.backbase.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.backbase.persistence.entity.CourseRegistration;
import com.example.demo.backbase.persistence.entity.Courses;


@Repository
public interface CoursesRegistrationRepository extends CrudRepository<CourseRegistration, Integer> {
	
	@Query(value = "select * from course_registration where NAME=?1 AND COURSE_ID= ?2", nativeQuery = true)
	CourseRegistration findByName(String name, Courses course); 
}
