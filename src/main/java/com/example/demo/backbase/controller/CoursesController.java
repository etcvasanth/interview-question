package com.example.demo.backbase.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.backbase.exception.RecordNotFoundException;
import com.example.demo.backbase.model.CourseRegistrationRequest;
import com.example.demo.backbase.model.CourseRegistrationResponse;
import com.example.demo.backbase.model.CourseRequest;
import com.example.demo.backbase.model.CourseResponse;
import com.example.demo.backbase.persistence.entity.Courses;
import com.example.demo.backbase.service.CourseRegistrationService;
import com.example.demo.backbase.service.CoursesService;

@RestController
@RequestMapping("/courses")
public class CoursesController {

	@Autowired
	CoursesService coursesService;

	@Autowired
	CourseRegistrationService courseRegistrationService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CourseResponse> createCourse(@RequestBody @Validated CourseRequest courseRequest) {
		CourseResponse courseResponse = coursesService.createCourse(courseRequest);
		return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Optional<Courses>>> searchByTitle(@RequestParam(name = "q") String title)
			throws RecordNotFoundException {
		List<Optional<Courses>> course = coursesService.getCoursesByTitle(title);
		if (course.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course title doest not exits");
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private Courses getCoursesById(@PathVariable("id") int id) {
		return coursesService.getCoursesById(id);
	}

	@PostMapping(value = "/{id}/add")
	public ResponseEntity<CourseRegistrationResponse> register(@PathVariable int id,
			@RequestBody @Validated CourseRegistrationRequest request) throws RecordNotFoundException {

		CourseRegistrationResponse register = courseRegistrationService.add(id, request);
		return new ResponseEntity<CourseRegistrationResponse>(register, HttpStatus.OK);
	}

	@PostMapping("/{courseId}/remove")
	public ResponseEntity<CourseRegistrationResponse> removeUser(@PathVariable int courseId,
			@RequestBody CourseRegistrationRequest request) throws RecordNotFoundException {
		CourseRegistrationResponse remove = courseRegistrationService.remove(courseId, request);
		return new ResponseEntity<CourseRegistrationResponse>(remove, HttpStatus.OK);
	}
}
