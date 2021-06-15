package com.school.santander.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Course {
    @Id private String id; // (find - sim)
    private String name; // (find - sim)
    private String description; // (find - não)
    private String tag; // (find - sim)
    private double workload; // (find - não)
    @DBRef private Customer[] teachers; // (find - sim)(registry - sim)
    @DBRef private Customer[] students; // (find - sim)(registry - sim)
}
