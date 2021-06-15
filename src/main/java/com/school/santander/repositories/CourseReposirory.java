package com.school.santander.repositories;

import com.school.santander.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseReposirory extends MongoRepository<Course, String> {
    List<Course> findByName(String name);
    List<Course> findByTag(String tag);
}
