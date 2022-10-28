package com.pushyourself11.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pushyourself11.Entity.Teacher;
import com.pushyourself11.Entity.Users;
import com.pushyourself11.RestController.ExceptionHandler.NotFoundException;
import com.pushyourself11.StudentEntities.Student;
import com.pushyourself11.dao.TeacherRepository;
import com.pushyourself11.dao.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher findTeacherByEmail(Users user) {

		Optional<Users> result = userRepository.findByEmail(user.getEmail());

		Users dbUser = null;

		if (result.isPresent()) {
			dbUser = result.get();
			if (dbUser.getPassword().equals(user.getPassword()) ) {
				System.out.println("Inside else");
				Teacher teacher = teacherRepository.findByEmail(user.getEmail());

				System.out.println("Current teacher" + teacher.getEmail());
				return teacher;
			}

		}
		if (dbUser == null) {
			System.out.println("Inside findTeacherByEmail");
			throw new NotFoundException();
		}

		return null;
	}

	@Override
	public void saveCredenitals(Teacher teacher) {

		Users user = new Users(teacher.getEmail(), teacher.getPassword() , teacher.getRole());

		userRepository.save(user);

	}
	
	@Override
	public void saveStudentCredentials(Student student) {
		Users user = new Users(student.getEmail() , student.getPassword() , student.getRole());
		userRepository.save(user);
		
	}

	@Override
	public Student findStudentByEmail(Users user) {
		/*
		 * Users dbUser = userRepository.findByEmail(user.getEmail());
		 * 
		 * if (dbUser == null) { throw new NotFoundException(); }
		 * 
		 * if (dbUser.getPassword() == user.getPassword()) { return
		 * dbUser.getUserType(); }
		 */

		return null;
	}

	

}
