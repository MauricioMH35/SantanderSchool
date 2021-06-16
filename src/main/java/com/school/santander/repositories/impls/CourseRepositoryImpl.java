package com.school.santander.repositories.impls;

import com.school.santander.exceptions.NotFoundException;
import com.school.santander.models.Course;
import com.school.santander.repositories.CourseRepositoryCustom;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class CourseRepositoryImpl implements CourseRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    private MongoOperations mongoOperations;

    @Override
    public Course checkUniqueValues(Course course) {
        Query query = new Query();
        List<Criteria> criterias = new ArrayList<>();

        String name = course.getName();
        String tag = course.getTag();

        if(!name.isBlank() || !name.isEmpty())
            criterias.add(Criteria.where("name").is(name));
        if(!tag.isBlank() || !tag.isEmpty())
            criterias.add(Criteria.where("tag").is(tag));

        if(!criterias.isEmpty())
            query.addCriteria(new Criteria().andOperator(criterias));

        return mongoTemplate.findOne(query, Course.class);
    }

    @Override
    public void updateById(String courseId, Course courseReplace) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(courseId));

        Course currentCourse = mongoTemplate.findOne(query, Course.class);

        if(currentCourse == null)
            throw new NotFoundException("It was not possible to find the course based on the " +
                    "data provided for the query.");

        if(!courseReplace.getName().isEmpty() || !courseReplace.getName().isBlank())
            courseReplace.setName(courseReplace.getName());

        if(!courseReplace.getDescription().isEmpty() || !courseReplace.getDescription().isBlank())
            courseReplace.setDescription(courseReplace.getDescription());

        if(!courseReplace.getTag().isEmpty() || !courseReplace.getTag().isBlank())
            courseReplace.setTag(courseReplace.getTag());

        if(courseReplace.getWorkload() == 0)
            courseReplace.setWorkload(courseReplace.getWorkload());


        courseReplace.setId(courseId);
        mongoTemplate.save(courseReplace);
    }
}
