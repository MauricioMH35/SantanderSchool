package com.school.santander.controllers;

import com.school.santander.models.Course;
import com.school.santander.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/pb/santander/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService service;

    @Operation(
            summary = "Search the course by identifier",
            description = "Value must be passed with the value of the identifier(id) of the course.",
            tags = {"Course / Public School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/find/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> findById(@PathVariable String id) {
        return new ResponseEntity<>(
                service.findById(id), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Search course by name contains",
            description = "The name to be searched must be passed as a parameter with a brace (search), and then " +
                    "the value of the name of the course to be searched with separations of the names using " +
                    "underscore (_).",
            tags = {"Course / Public School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/find/name", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Course>> findByNameContains(@RequestParam("search") String name) {
        return new ResponseEntity<>(
                service.findByNameContains(name), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Search courses that have the tag",
            description = "Look for courses where the tag is the same as that referenced in the request, this value is " +
                    "always converted to lower case and must be passed when there is separation in its name with blank " +
                    "spaces for underline(_), because this value is passed as a parameter in the url .",
            tags = {"Course / Public School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/find/tag", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Course>> findByTag(@RequestParam("search") String tag) {
        return new ResponseEntity<>(
                service.findByTag(tag), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Search all courses",
            description = "Lists all courses via pagination, using a size of 5 items per page.",
            tags = {"Course / Public School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Course>> findAll() {
        return new ResponseEntity<>(
                service.findAll(), HttpStatus.FOUND
        );
    }

}
