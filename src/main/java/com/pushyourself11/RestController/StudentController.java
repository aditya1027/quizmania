package com.pushyourself11.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pushyourself11.RestController.ExceptionHandler.NotFoundException;
import com.pushyourself11.Service.StudentService;
import com.pushyourself11.StudentEntities.AttemptedQuizDetails;
import com.pushyourself11.StudentEntities.Student;
import com.pushyourself11.StudentEntities.StudentDetails;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> findAll() {
		List<Student> s = studentService.findAll();

		return new ResponseEntity<List<Student>>(s, HttpStatus.OK);

	}

	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable int studentId) {
		Student thestudent = studentService.findById(studentId);

		if (thestudent == null) {
			throw new RuntimeException("Not found");
		}

		return thestudent;
	}

	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student theStudent) {
		studentService.save(theStudent);

		return theStudent;
	}

	@DeleteMapping("/students/{studentID}")
	public String deleteStudent(@PathVariable int studentID) {
		Student theStudent = studentService.findById(studentID);

		if (theStudent == null) {
			throw new RuntimeException("Not found");
		}

		studentService.deleteById(studentID);

		return "Deleted user - " + studentID;
	}

	@GetMapping("/student/studentdetails/{studentId}")
	public ResponseEntity<StudentDetails> getTeacherDetails(@PathVariable int studentId) {
		Student student = studentService.findById(studentId);

		if (student == null) {
			throw new NotFoundException("Student id not found - " + studentId);
		}

		StudentDetails sd = student.getStudentDetails();
		return new ResponseEntity<StudentDetails>(sd, HttpStatus.OK);
	}

	@PutMapping("student/details/{studentId}")
	public ResponseEntity<StudentDetails> updateStudentDetails(@PathVariable int studentId,
			@RequestBody StudentDetails studentDetails) {
		Student student = studentService.findById(studentId);

		if (student == null) {
			throw new NotFoundException("Teacher id not found - " + studentId);
		}

		studentService.updateStudentDetails(studentId, studentDetails);

		return new ResponseEntity<StudentDetails>(student.getStudentDetails(), HttpStatus.OK);
	}

	@PostMapping("student/details/{studentId}")
	public ResponseEntity<StudentDetails> addStudentDetails(@PathVariable int studentId,
			@RequestBody StudentDetails studentDetails) {
		Student student = studentService.findById(studentId);

		if (student == null) {
			throw new NotFoundException("Teacher id not found - " + studentId);
		}

		studentService.updateStudentDetails(studentId, studentDetails);

		return new ResponseEntity<StudentDetails>(student.getStudentDetails(), HttpStatus.OK);
	}

	@PostMapping("student/quiz/{studentId}")
	public ResponseEntity<AttemptedQuizDetails> addQuizToStudent(@PathVariable int studentId,
			@RequestBody AttemptedQuizDetails attemptedQuizDetails) {

		studentService.addAttemptedQuizDetails(studentId, attemptedQuizDetails);

		return new ResponseEntity<AttemptedQuizDetails>(attemptedQuizDetails, HttpStatus.OK);

	}

	@GetMapping("student/quiz/{studentId}")
	public ResponseEntity<List<AttemptedQuizDetails>> getAllQuizzesAttemptedByStudent(@PathVariable int studentId) {
		List<AttemptedQuizDetails> listOfAttemptedQuizzes = studentService.getAttemptedQuizDetails(studentId);

		if (listOfAttemptedQuizzes.size() == 0) {
			return new ResponseEntity<List<AttemptedQuizDetails>>(listOfAttemptedQuizzes, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<AttemptedQuizDetails>>(listOfAttemptedQuizzes, HttpStatus.OK);

	}

	@GetMapping("student/quiz/{studentId}/{quizId}")
	public ResponseEntity<List<AttemptedQuizDetails>> getQuizAttemptedDetails(@PathVariable int studentId,
			@PathVariable int quizId) {
		List<AttemptedQuizDetails> listOfAttemptedQuizzes = studentService.getAttemptedQuizDetails(studentId);

		if (listOfAttemptedQuizzes.size() == 0) {
			return new ResponseEntity<List<AttemptedQuizDetails>>(listOfAttemptedQuizzes, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<AttemptedQuizDetails>>(listOfAttemptedQuizzes, HttpStatus.OK);

	}
}
