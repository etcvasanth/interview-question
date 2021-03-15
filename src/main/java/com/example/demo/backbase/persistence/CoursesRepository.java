package com.example.demo.backbase.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.backbase.persistence.entity.Courses;


@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
	
	List<Optional<Courses>> findCourseByTitle(String title);
}
