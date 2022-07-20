package com.csmtech.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csmtech.domain.Course;
import com.csmtech.service.CourseService;
import com.csmtech.service.CourseServiceImpl;

/*class MyCourseResultSet implements ResultSetExtractor<List<Course>> {
	@Override
	public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Course> courseList = new ArrayList<Course>();
		while (rs.next()) {
			Course course = new Course();
			course.setCourseId(rs.getLong(1));
			course.setCourseName(rs.getString(2));
			course.setPrice(rs.getDouble(3));
			courseList.add(course);
		}
		return courseList;
	}
}

class MyCourseResultSet1 implements RowMapper<Course> {
	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course = new Course();
		course.setCourseId(rs.getLong(1));
		course.setCourseName(rs.getString(2));
		course.setPrice(rs.getDouble(3));

		return course;
	}

}*/

@Controller
public class MyController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CourseService courseService;

	@GetMapping("/test")
	public void test() {
		List<SqlParameter> paramList=new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Type.CHAR));
		paramList.add(new SqlParameter(Type.DOUBLE));
		//Arrays.asList(new SqlParameter(Type.CHAR),new SqlParameter(Type.DOUBLE))
		Map<String, Object> resMap=jdbcTemplate.call(new CallableStatementCreator() {			
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs=con.prepareCall("{call course_proc(?,?)}");
				cs.setString(1, "JPA");
				cs.setDouble(2, 777);
				
				return cs;
			}
		}, paramList);
		
		/*
		 * Set<Entry<String, Object>> entrySet = resMap.entrySet();
		 * for(Entry<String,Object> x:entrySet) {
		 * System.out.println(x.getKey()+"  "+x.getValue());
		 * 
		 * }
		 */
		//System.out.println(resMap.get("c_price")+"  "+resMap.get("c_name"));
		System.out.println(resMap);
		
	}
	
	
	@RequestMapping("/getAllCourses")
	public String getAllCourses(Model model) {
		
		model.addAttribute("courseList", courseService.getAllCourses());
		return "CourseList";
		

	}
}
