package com.example.demo.backbase.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.backbase.exception.RecordNotFoundException;
import com.example.demo.backbase.model.CourseRegistrationRequest;
import com.example.demo.backbase.model.CourseRegistrationResponse;
import com.example.demo.backbase.persistence.CoursesRegistrationRepository;
import com.example.demo.backbase.persistence.entity.CourseRegistration;
import com.example.demo.backbase.persistence.entity.Courses;
import com.example.demo.backbase.service.mapper.CourseRegistrationServiceMapper;

@Service
public class CourseRegistrationService {

	@Autowired
	CoursesRegistrationRepository coursesRegistrationRepository;

	@Autowired
	CoursesService coursesService;

	@Autowired
	CourseRegistrationServiceMapper courseRegistrationServiceMapper;

	public CourseRegistrationResponse add(int id, CourseRegistrationRequest request) throws RecordNotFoundException {

		Courses courses = coursesService.getCoursesById(request.getCourseId());
		CourseRegistration register = coursesRegistrationRepository.findByName(request.getName(), courses);
		if (!Optional.ofNullable(courses).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course does not exis");
		}
		if (Optional.ofNullable(register).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already Registered for the course");
		if (courses.getRemaining() < 1)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course capacity is full");
		if (request.getRegistrationDate().isAfter(courses.getStartDate().minusDays(3)))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registration closed for the course");
		CourseRegistration courseRegistration = courseRegistrationServiceMapper.mapRegistration(request, courses);
		courseRegistration.getCourseId().setRemaining(courseRegistration.getCourseId().getCapacity() - 1);
		coursesRegistrationRepository.save(courseRegistration);
		CourseRegistrationResponse courseRegistrationResponse = courseRegistrationServiceMapper
				.mapRegistrationResponse(courseRegistration);
		return courseRegistrationResponse;
	}

	public CourseRegistrationResponse remove(int id, CourseRegistrationRequest request) throws RecordNotFoundException {

		Courses courses = coursesService.getCoursesById(request.getCourseId());
		if (!Optional.ofNullable(courses).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course does not exis");
		}
		CourseRegistrationResponse courseRegistrationResponse = new CourseRegistrationResponse();
		CourseRegistration removeUser = coursesRegistrationRepository.findByName(request.getName(), courses);
		if (Optional.ofNullable(removeUser).isPresent()) {
			if (request.getCancelDate().isAfter(courses.getStartDate().minusDays(3)))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Cancelation cannot done be after registration Date");
			removeUser.getCourseId().setRemaining(removeUser.getCourseId().getRemaining() + 1);
			coursesRegistrationRepository.delete(removeUser);
			courseRegistrationResponse = courseRegistrationServiceMapper.mapRemoveUserResponse(removeUser);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not registered for the course");
		return courseRegistrationResponse;
	}
}