package com.csmtech.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Course implements Serializable {
	private Long courseId;
	private String courseName;
	private Double price;
}
