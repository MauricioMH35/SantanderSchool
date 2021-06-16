package com.school.santander.repositories;

import com.school.santander.models.Course;

public interface CourseRepositoryCustom {
    Course checkUniqueValues(Course course);
    void updateById(String id, Course courseReplace);
}
