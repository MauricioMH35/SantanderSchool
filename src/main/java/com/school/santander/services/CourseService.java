package com.school.santander.services;

import com.school.santander.models.Course;
import org.springframework.data.domain.Page;

public interface CourseService {
    Course save(Course course);
    Course findById(String id);
    Page<Course> findByNameContains(String name);
    Page<Course> findByTag(String tag);
    Page<Course> findAll();
    void updateById(String courseId, Course courseReplace);
    void deleteById(String courseId);
}
