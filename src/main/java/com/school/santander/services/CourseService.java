package com.school.santander.services;

import com.school.santander.models.Course;
import com.school.santander.models.Customer;
import org.springframework.data.domain.Page;

public interface CourseService {
    Course save(Course course);
    Course findById(String id);
    Page<Course> findByNameContains(String name);
    Page<Course> findByTag(String tag);
    Page<Course> findAll();
    Page<Customer> listRegistryTeachersCourse(String courseId);
    Page<Customer> listRegistryStudentsCourse(String courseId);
    Customer registryTeacherInCourse(String courseId, Customer teacher);
    Customer registryStudentInCourse(String courseId, Customer student);
    void updateById(String courseId, Course courseReplace);
    void deleteById(String courseId);
}
