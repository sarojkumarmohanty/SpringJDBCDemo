package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.dao.CourseDao;
import com.csmtech.dao.CourseDaoImpl;
import com.csmtech.domain.Course;


@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Override
	public List<Course> getAllCourses() {	
		
		return courseDao.getAllCourses() ;
	}

}
