package com.school.santander.services.impls;

import com.school.santander.models.Course;
import com.school.santander.models.Customer;
import com.school.santander.repositories.CourseReposirory;
import com.school.santander.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseReposirory reposirory;

    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public Course findById(String id) {
        return null;
    }

    @Override
    public Page<Course> findByNameContains(String name) {
        return null;
    }

    @Override
    public Page<Course> findByTag(String tag) {
        return null;
    }

    @Override
    public Page<Course> findAll() {
        return null;
    }

    @Override
    public Page<Customer> listRegistryTeachersCourse(String courseId) {
        return null;
    }

    @Override
    public Page<Customer> listRegistryStudentsCourse(String courseId) {
        return null;
    }

    @Override
    public Customer registryTeacherInCourse(String courseId, Customer teacher) {
        return null;
    }

    @Override
    public Customer registryStudentInCourse(String courseId, Customer student) {
        return null;
    }

    @Override
    public void updateById(String courseId, Course courseReplace) {

    }

    @Override
    public void deleteById(String courseId) {

    }
}
