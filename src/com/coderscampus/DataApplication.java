package com.coderscampus;


	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;

import com.coderscampus.Student;

	public class DataApplication {
	    public static void main(String[] args) {
	        try (BufferedReader reader = new BufferedReader(new FileReader("src\\student-master-list.csv"))) {
	            String line;
	            Student[] students = new Student[100]; 
	            int studentCount = 0;

	           
	            reader.readLine();

	            while ((line = reader.readLine())!= null) {
	                String[] columns = line.split(",");
	                int id = Integer.parseInt(columns[0]);
	                String name = columns[1];
	                String course = columns[2];
	                int grade = Integer.parseInt(columns[3]);

	                Student student = new Student(id, name, course, grade);
	                students[studentCount++] = student;
	            }

	            
	            processStudents(students, studentCount);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void processStudents(Student[] students, int studentCount) {
	        Student[] course1 = new Student[100];
	        Student[] course2 = new Student[100];
	        Student[] course3 = new Student[100];
	        int course1Count = 0;
	        int course2Count = 0;
	        int course3Count = 0;

	        for (int i = 0; i < studentCount; i++) {
	            Student student = students[i];
	            if (student.getCourse().startsWith("COMPSCI")) {
	                course1[course1Count++] = student;
	            } else if (student.getCourse().startsWith("APMTH")) {
	                course2[course2Count++] = student;
	            } else if (student.getCourse().startsWith("STAT")) {
	                course3[course3Count++] = student;
	            }
	        }

	        
	        sortStudentsByGrade(course1, course1Count);
	        sortStudentsByGrade(course2, course2Count);
	        sortStudentsByGrade(course3, course3Count);

	        
	        writeCourseFile(course1, course1Count, "course1.csv");
	        writeCourseFile(course2, course2Count, "course2.csv");
	        writeCourseFile(course3, course3Count, "course3.csv");
	    }

	    private static void sortStudentsByGrade(Student[] students, int count) {
	        for (int i = 0; i < count; i++) {
	            for (int j = i + 1; j < count; j++) {
	                if (students[i].getGrade() < students[j].getGrade()) {
	                    
	                    Student temp = students[i];
	                    students[i] = students[j];
	                    students[j] = temp;
	                }
	            }
	        }
	    }

	    private static void writeCourseFile(Student[] students, int count, String fileName) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
	            writer.write("Student ID,Student Name,Course,Grade\n");

	            for (int i = 0; i < count; i++) {
	                Student student = students[i];
	                writer.write(String.format("%d,%s,%s,%d\n", student.getId(), student.getName(), student.getCourse(), student.getGrade()));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}