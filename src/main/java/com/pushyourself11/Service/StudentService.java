package com.pushyourself11.Service;

import java.util.List;

import com.pushyourself11.StudentEntities.AttemptedQuizDetails;
import com.pushyourself11.StudentEntities.Student;
import com.pushyourself11.StudentEntities.StudentDetails;


public interface StudentService {
	
	public List<Student> findAll();
	
	public Student findById(int theid);
	
	public void save(Student student);
	
	public void deleteById(int theid);
	
	public StudentDetails updateStudentDetails(int studentId  , StudentDetails studentDetails);
	
	/*-------------------------------------------------------------------------------------------------	*/
	
	public List<AttemptedQuizDetails> getAttemptedQuizDetails(int studentId);
	
	public List<AttemptedQuizDetails> addAttemptedQuizDetails(int studentId , AttemptedQuizDetails attemptedQuizDetails);
	
	public List<AttemptedQuizDetails> attemptedQuizDetails(int studentId , int quizId);
}
