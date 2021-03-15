package com.example.demo.backbase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.backbase.model.CourseRequest;
import com.example.demo.backbase.model.CourseResponse;
import com.example.demo.backbase.persistence.CoursesRepository;
import com.example.demo.backbase.persistence.entity.Courses;
import com.example.demo.backbase.service.mapper.CourseServiceMapper;

@Service
public class CoursesService {

	@Autowired
	CoursesRepository coursesRepository;

	@Autowired
	CourseServiceMapper courseServiceMapper;

	public void saveOrUpdate(Courses courses) {
		coursesRepository.save(courses);
	}

	public CourseResponse createCourse(CourseRequest courseRequest) {
		Courses courses = courseServiceMapper.mapCourse(courseRequest);
		coursesRepository.save(courses);
		CourseResponse courseResponse = courseServiceMapper.mapCourseResponse(courses);
		return courseResponse;
	}

	public List<Courses> getAllCoursesByTitle(String courseTitle) {
		List<Courses> courses = new ArrayList<Courses>();
		coursesRepository.findAll().stream().filter((c) -> c.getTitle().contains(courseTitle))
				.forEach(courses1 -> courses.add(courses1));
		return courses;
	}

	public List<Optional<Courses>> getCoursesByTitle(String courseTitle) {
		return coursesRepository.findCourseByTitle(courseTitle);
	}

	public Courses getCoursesById(int id) {
		return coursesRepository.findById(id).get();
	}
}