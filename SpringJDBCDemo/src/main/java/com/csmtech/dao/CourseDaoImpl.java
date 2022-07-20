package com.csmtech.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csmtech.domain.Course;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Course> getAllCourses() {
		
		return jdbcTemplate.query("select course_id, course_name,price from course", (r,Integer)->{
			Course course = new Course();
			course.setCourseId(r.getLong(1));
			course.setCourseName(r.getString(2));
			course.setPrice(r.getDouble(3));

			return course;
		});
	}

}
