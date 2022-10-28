package com.pushyourself11.Service;

import com.pushyourself11.Entity.Teacher;
import com.pushyourself11.Entity.Users;
import com.pushyourself11.StudentEntities.Student;

public interface AuthService {

	public Teacher findTeacherByEmail(Users user);
	
	public void saveCredenitals(Teacher teacher);
	
	public void saveStudentCredentials(Student student);
	
	
	public Student findStudentByEmail(Users user);
	
	
	
}
