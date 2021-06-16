package com.school.santander.services.impls;

import com.school.santander.exceptions.BadRequestException;
import com.school.santander.exceptions.NotFoundException;
import com.school.santander.models.Course;
import com.school.santander.repositories.CourseRepository;
import com.school.santander.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    @Override
    public Course save(Course course) {
        if(repository.checkUniqueValues(course) == null) {
            course.setTag(
                    course.getTag().replaceAll("\\s", "_").toLowerCase(Locale.ROOT));

            repository.save(course);

        } else {
            throw new BadRequestException("It is not possible to save the course, this course already " +
                    "exists in the database.");
        }
        return course;
    }

    @Override
    public Course findById(String id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "It was not possible to find the customer using the past data."));

        return course;
    }

    @Override
    public Page<Course> findByNameContains(String name) {
        String nameReplace = name.replaceAll("\\_", " ");
        List<Course> courses = repository.findByNameContains(nameReplace);

        if(courses.isEmpty())
            throw new NotFoundException("It was not possible to query the courses" +
                    " that contain the last name.");

        return new PageImpl<>(courses);
    }

    @Override
    public Page<Course> findByTag(String tag) {
        String tagReplace = tag.replaceAll("\\_", " ").toLowerCase(Locale.ROOT);
        List<Course> courses = repository.findByTag(tagReplace);

        if(courses.isEmpty())
            throw new NotFoundException("It was not possible to consult the courses with the tag " +
                    "assigned to the search.");

        return new PageImpl<>(courses);
    }

    @Override
    public Page<Course> findAll() {
        List<Course> cousers = repository.findAll();

        if(cousers.isEmpty())
            throw new NotFoundException("No results were found for the query.");

        return new PageImpl<>(cousers);
    }

    @Override
    public void updateById(String courseId, Course courseReplace) {
        if(courseId.isEmpty() || courseId.isBlank())
            throw new BadRequestException("It is not possible to perform the operation without " +
                    "providing the data required to perform this procedure.");

        repository.updateById(courseId, courseReplace);
    }

    @Override
    public void deleteById(String courseId) {
        repository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Unable to delete the course, it was not " +
                        "found in the database."));

        repository.deleteById(courseId);
    }
}
