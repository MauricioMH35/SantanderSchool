package com.school.santander.controllers;

import com.school.santander.models.Course;
import com.school.santander.models.Customer;
import com.school.santander.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PostMapping("/admin/courses/create")
    public ResponseEntity<Course> save(@RequestBody @Valid Course course) {
        return new ResponseEntity<>(
                service.save(course), HttpStatus.CREATED
        );
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findById(@PathVariable String id) {
        return new ResponseEntity<>(
                service.findById(id), HttpStatus.FOUND
        );
    }

    @GetMapping("/courses/find/name")
    public ResponseEntity<Page<Course>> findByNameContains(@RequestParam String name) {
        return new ResponseEntity<>(
                service.findByNameContains(name), HttpStatus.FOUND
        );
    }

    @GetMapping("/courses/find/tag")
    public ResponseEntity<Page<Course>> findByTag(@RequestParam String tag) {
        return new ResponseEntity<>(
                service.findByTag(tag), HttpStatus.FOUND
        );
    }

    @GetMapping("/courses/find/all")
    public ResponseEntity<Page<Course>>  findAll() {
        return new ResponseEntity<>(
                service.findAll(), HttpStatus.FOUND
        );
    }

    @GetMapping("/admin/courses/list/teachers/{courseId}")
    public ResponseEntity<Page<Customer>> listRegistryTeachersCourse(@PathVariable String courseId) {
        return new ResponseEntity<>(
                service.listRegistryTeachersCourse(courseId), HttpStatus.FOUND
        );
    }

    @GetMapping("/admin/courses/list/students/{courseId}")
    public ResponseEntity<Page<Customer>> listRegistryStudentsCourse(@PathVariable String courseId) {
        return new ResponseEntity<>(
                service.listRegistryStudentsCourse(courseId), HttpStatus.FOUND
        );
    }

    @PutMapping("/courses/teacher/registry/{courseId}")
    public ResponseEntity<Customer> registryTeacherInCourse(
            @PathVariable String courseId, @RequestBody @Valid Customer teacher) {
        return new ResponseEntity<>(
                service.registryTeacherInCourse(courseId, teacher), HttpStatus.FOUND
        );
    }

    @PutMapping("/courses/student/registry/{courseId}")
    public ResponseEntity<Customer> registryStudentInCourse(
            @PathVariable String courseId, @RequestBody @Valid Customer student) {
        return new ResponseEntity<>(
                service.registryStudentInCourse(courseId, student), HttpStatus.FOUND
        );
    }

    @PutMapping("/admin/courses/update/{courseId}")
    public ResponseEntity updateById(
            @PathVariable String courseId, @RequestBody @Valid Course courseReplace) {
        service.updateById(courseId, courseReplace);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/courses/delete/{courseId}")
    public ResponseEntity deleteById(@PathVariable String courseId) {
        service.deleteById(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
