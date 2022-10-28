package com.pushyourself11.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pushyourself11.StudentEntities.AttemptedQuizDetails;
import com.pushyourself11.StudentEntities.Student;
import com.pushyourself11.StudentEntities.StudentDetails;
import com.pushyourself11.dao.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {

		List<Student> s = studentRepository.findAll();

		if (s.size() == 0)
			return null;

		return s;
	}

	@Override
	public Student findById(int theid) {
		Optional<Student> result = studentRepository.findById(theid);

		Student theStudent = null;

		if (result.isPresent()) {
			theStudent = result.get();
		} else {
			throw new RuntimeException("Did not find the employee");
		}

		return theStudent;
	}

	@Override
	public void save(Student student) {

		studentRepository.save(student);

	}

	@Override
	public void deleteById(int theid) {
		studentRepository.deleteById(theid);

	}

	@Override
	public StudentDetails updateStudentDetails(int studentId, StudentDetails studentDetails) {
		Optional<Student> result = studentRepository.findById(studentId);

		Student student = null;

		if (result.isPresent()) {
			student = result.get();
			student.setStudentDetails(studentDetails);
			studentRepository.save(student);
		} else {
			throw new RuntimeException("Did not find the student");
		}

		return student.getStudentDetails();
	}

	/*----------------------------------------------------------------------------------------------------------------------*/

	@Override
	public List<AttemptedQuizDetails> addAttemptedQuizDetails(int studentId,
			AttemptedQuizDetails attemptedQuizDetails) {

		Optional<Student> result = studentRepository.findById(studentId);

		Student student = null;
		List<AttemptedQuizDetails> listAttemptedQuiz = null;

		if (result.isPresent()) {
			student = result.get();
			StudentDetails studentDetails = student.getStudentDetails();
			attemptedQuizDetails.setStudentDetails(studentDetails);
			
			
			 listAttemptedQuiz = studentDetails.getQuizDetails();
			listAttemptedQuiz.add(attemptedQuizDetails);

			studentDetails.setQuizDetails(listAttemptedQuiz);
			student.setStudentDetails(studentDetails);;
			studentRepository.save(student);
		} else {
			throw new RuntimeException("Did not find the student");
		}

		return listAttemptedQuiz;

	}

	@Override
	public List<AttemptedQuizDetails> getAttemptedQuizDetails(int studentId) {

		Optional<Student> result = studentRepository.findById(studentId);

		Student student = null;
		List<AttemptedQuizDetails> listAttemptedQuiz = null;

		if (result.isPresent()) {
			student = result.get();
			StudentDetails studentDetails = student.getStudentDetails();
			listAttemptedQuiz = studentDetails.getQuizDetails();

		} else {
			throw new RuntimeException("Did not find the student");
		}

		return listAttemptedQuiz;
	}

	@Override
	public List<AttemptedQuizDetails> attemptedQuizDetails(int studentId, int quizId) {

		Optional<Student> result = studentRepository.findById(studentId);

		Student student = null;
		
		List<AttemptedQuizDetails> list = new ArrayList<>();

		if (result.isPresent()) {
			student = result.get();
			StudentDetails studentDetails = student.getStudentDetails();
			List<AttemptedQuizDetails> listAttemptedQuiz = studentDetails.getQuizDetails();
			
			for(AttemptedQuizDetails quizAttempDetails : listAttemptedQuiz ) {
				if(quizAttempDetails.getQuizId() == quizId) {
					list.add(quizAttempDetails);
				}
			}
			
		} else {
			throw new RuntimeException("Did not find the student");
		}

		return list;
	}

}
