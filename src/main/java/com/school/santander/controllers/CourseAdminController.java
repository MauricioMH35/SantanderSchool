package com.school.santander.controllers;

import com.school.santander.models.Course;
import com.school.santander.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/admins/santander/courses")
@RequiredArgsConstructor
public class CourseAdminController {

    private final CourseService service;

    @Operation(
            summary = "Create a new course",
            description = "Creates a course from the data passed for insertion in the database.",
            tags = {"Course / Admin School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When successful when creating"),
            @ApiResponse(responseCode = "400", description = "When it is not possible to remove")
    })
    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> save(@RequestBody @Valid Course course) {
        return new ResponseEntity<>(
                service.save(course), HttpStatus.CREATED
        );
    }

    @Operation(
            summary = "Updating course information",
            description = "Updates the course data according to the information passed by the requisition.",
            tags = {"Course / Admin School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "When it is successful to update"),
            @ApiResponse(responseCode = "400", description = "When not successful when updating")
    })
    @PutMapping(value = "/update/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Course courseReplace) {
        service.updateById(id, courseReplace);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Removing the course from the database",
            description = "Removes the course through its identifier(id) from the Santander School database.",
            tags = {"Course / Admin School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "When successful to remove"),
            @ApiResponse(responseCode = "404", description = "When not to find to remove")
    })
    @DeleteMapping(value = "/delete/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable String id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
