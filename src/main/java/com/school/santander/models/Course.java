package com.school.santander.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Schema(description = "This is the course identifier",
            example = "String of characters that make up the sequence")
    @Id
    private String id;

    @Schema(description = "This is the name of the course",
            example = "Analysis and systems development")
    @NotEmpty(message = "The course name field cannot be empty")
    private String name;

    @Schema(description = "This is the course description.",
            example = "A brief description to explains how the course works, its main areas of expertise and its importance in humanitarian development.")
    private String description;

    @Schema(description = "This is the simplified area of action/tag",
            example = "It can briefly determine in just one word the course's area of expertise, as an example a Systems Analysis and Development course can be said as a technology tag. It must always be in a lower case to facilitate handling among other applications.")
    private String tag;

    @Schema(description = "This is the course load",
            example = "The course load can determine to the student whether the course meets some of the expected conditions. This attribute to the course should be directed towards total scale values to total the sum of all hours that the course offers.")
    private double workload;
}
