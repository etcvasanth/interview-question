package com.example.demo.backbase.service.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.backbase.model.CourseRequest;
import com.example.demo.backbase.model.CourseResponse;
import com.example.demo.backbase.persistence.entity.Courses;

@Component
public class CourseServiceMapper {

	public Courses mapCourse(CourseRequest courseRequest) {
		Courses courses = new Courses();
		courses.setTitle(courseRequest.getTitle());
		courses.setStartDate(courseRequest.getStartDate());
		courses.setEndDate(courseRequest.getEndDate());
		courses.setCapacity(courseRequest.getCapacity());
		courses.setRemaining(courseRequest.getCapacity());
		return courses;
	}

	public CourseResponse mapCourseResponse(Courses course) {
		CourseResponse courseResponse = new CourseResponse();
		courseResponse.setId(course.getId());
		courseResponse.setTitle(course.getTitle());
		courseResponse.setStartDate(course.getStartDate());
		courseResponse.setEndDate(course.getEndDate());
		courseResponse.setCapacity(course.getCapacity());
		courseResponse.setRemaining(course.getRemaining());
		return courseResponse;
	}
}
