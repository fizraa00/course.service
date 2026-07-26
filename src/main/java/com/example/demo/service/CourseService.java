package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Course not found with ID: " + id));
    }

    public Course updateCourse(Long id, Course updatedCourse) {

        Course existingCourse = getCourseById(id);

        existingCourse.setCourseCode(updatedCourse.getCourseCode());
        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setCredits(updatedCourse.getCredits());
        existingCourse.setInstructorName(
                updatedCourse.getInstructorName());

        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {

        Course course = getCourseById(id);

        courseRepository.delete(course);
    }
}