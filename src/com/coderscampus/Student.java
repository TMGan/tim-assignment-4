package com.coderscampus;

public class Student {

	private int id;
	private String name;
	private String course;
	private int grade;
	
	
	public Student(int id, String name, String course, int grade) {
		this.id = id;
		this.name=name;
		this.course=course;
		this.grade=grade;
		
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getCourse() {
		return course;
	}
	
	public int getGrade() {
		return grade;
	}
	
}
