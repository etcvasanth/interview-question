package com.example.demo.backbase.service.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.backbase.model.CourseRegistrationRequest;
import com.example.demo.backbase.model.CourseRegistrationResponse;
import com.example.demo.backbase.persistence.entity.CourseRegistration;
import com.example.demo.backbase.persistence.entity.Courses;

@Component
public class CourseRegistrationServiceMapper {

	public CourseRegistration mapRegistration(CourseRegistrationRequest courseRegistrationRequest, Courses courses) {
		CourseRegistration courseRegistration = new CourseRegistration();
		courseRegistration.setCourseId(courses);
		courseRegistration.setName(courseRegistrationRequest.getName());
		courseRegistration.setRegistrationDate(courseRegistrationRequest.getRegistrationDate());
		return courseRegistration;
	}

	public CourseRegistrationResponse mapRegistrationResponse(CourseRegistration courseRegistration) {
		CourseRegistrationResponse courseRegistrationResponse = new CourseRegistrationResponse();
		courseRegistrationResponse.setId(courseRegistration.getId());
		courseRegistrationResponse.setName(courseRegistration.getName());
		courseRegistrationResponse.setCourseId(courseRegistration.getCourseId().getId());
		courseRegistrationResponse.setRegistrationDate(courseRegistration.getRegistrationDate());
		return courseRegistrationResponse;
	}

	public CourseRegistration mapRemoveUser(CourseRegistrationRequest courseRegistrationRequest, Courses courses) {
		CourseRegistration courseRegistration = new CourseRegistration();
		courseRegistration.setId(null);
		courseRegistration.setCourseId(courses);
		courseRegistration.setName(courseRegistrationRequest.getName());
		return courseRegistration;
	}

	public CourseRegistrationResponse mapRemoveUserResponse(CourseRegistration courseRegistration) {
		CourseRegistrationResponse courseRegistrationResponse = new CourseRegistrationResponse();
		courseRegistrationResponse.setId(courseRegistration.getId());
		courseRegistrationResponse.setName(courseRegistration.getName());
		courseRegistrationResponse.setCourseId(courseRegistration.getCourseId().getId());
		return courseRegistrationResponse;
	}

}
