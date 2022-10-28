package com.pushyourself11.RestController;

import java.util.List;
import java.util.Optional;

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

import com.pushyourself11.Entity.Options;
import com.pushyourself11.Entity.Questions;
import com.pushyourself11.Entity.Quizes;
import com.pushyourself11.Entity.Sections;
import com.pushyourself11.Entity.Teacher;
import com.pushyourself11.Entity.TeacherDetails;
import com.pushyourself11.RestController.ExceptionHandler.NotFoundException;
import com.pushyourself11.Service.TeacherService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	

	@GetMapping("/teachers")
	public ResponseEntity<List<Teacher>> findAll() {
		List<Teacher> teachers = teacherService.findAll();
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
	}

	@GetMapping("/teachers/{teacherId}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable int teacherId) {
		Teacher theTeacher = teacherService.findById(teacherId);

		if (theTeacher == null) {
			throw new RuntimeException("Not found");
		}

		return new ResponseEntity<Teacher>(theTeacher, HttpStatus.OK);
	}

	

	@PutMapping("/teachers")
	public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher theTeacher) {
		teacherService.save(theTeacher);

		return new ResponseEntity<Teacher>(theTeacher, HttpStatus.OK);
	}

	@DeleteMapping("/teachers/{userID}")
	public ResponseEntity<String> deleteUsers(@PathVariable int teacherId) {
		Teacher theTeacher = teacherService.findById(teacherId);

		if (theTeacher == null) {
			throw new RuntimeException("Not found");
		}

		teacherService.deleteById(teacherId);

		return new ResponseEntity<String>("Deleted user - " + teacherId, HttpStatus.OK);
	}

	@GetMapping("/teachers/teacherdetails/{teacherId}")
	public ResponseEntity<TeacherDetails> getTeacherDetails(@PathVariable int teacherId) {
		Teacher tempTeacher = teacherService.findById(teacherId);

		if (tempTeacher == null) {
			throw new NotFoundException("Teacher id not found - " + teacherId);
		}

		TeacherDetails td = tempTeacher.getTeacherDetails();
		return new ResponseEntity<TeacherDetails>(td, HttpStatus.OK);
	}

	@PutMapping("teacher/details/{teacherId}")
	public ResponseEntity<Teacher> updateTeacherDetails(@PathVariable int teacherId,
			@RequestBody TeacherDetails teacherDetails) {
		Teacher tempTeacher = teacherService.findById(teacherId);

		if (tempTeacher == null) {
			throw new NotFoundException("Teacher id not found - " + teacherId);
		}

		teacherService.updateTeacherDetails(teacherId, teacherDetails);

		return new ResponseEntity<Teacher>(tempTeacher, HttpStatus.OK);
	}

	@PostMapping("teacher/details/{teacherId}")
	public ResponseEntity<Teacher> postTeacherDetails(@PathVariable int teacherId,
			@RequestBody TeacherDetails teacherDetails) {
		Teacher tempTeacher = teacherService.findById(teacherId);

		if (tempTeacher == null) {
			throw new NotFoundException("Teacher id not found - " + teacherId);
		}

		teacherService.updateTeacherDetails(teacherId, teacherDetails);

		return new ResponseEntity<Teacher>(tempTeacher, HttpStatus.OK);
	}

	@GetMapping("teacher/quiz/{teacherId}")
	public ResponseEntity<List<Quizes>> getTeacherQuizes(@PathVariable int teacherId) {
		List<Quizes> quizes = teacherService.getTeacherQuizes(teacherId);
		if (quizes.size() == 0)
			throw new RuntimeException("No quizes found");

		return new ResponseEntity<List<Quizes>>(quizes, HttpStatus.OK);
	}

	@GetMapping("/teachers/quiz/{quizId}")
	public ResponseEntity<Optional<Quizes>> getQuiz(@PathVariable int quizId) {
		Optional<Quizes> theQuiz = teacherService.findQuizByQuizId(quizId);

		if (theQuiz == null) {
			throw new NotFoundException("Quiz not found - " + quizId);
		}

		return new ResponseEntity<Optional<Quizes>>(theQuiz, HttpStatus.OK);
	}

	@GetMapping("teacher/quiz/{teacherId}/{quizId}")
	public ResponseEntity<Quizes> getTeacherQuiz(@PathVariable int teacherId, @PathVariable int quizId) {
		Quizes quiz = teacherService.getTeacherQuizById(teacherId);
		if (quiz == null) {
			throw new NotFoundException("Quiz not found - " + quizId);
		}
		return new ResponseEntity<Quizes>(quiz, HttpStatus.OK);

	}

	@PostMapping("teacher/quiz/{teacherId}")
	public ResponseEntity<Quizes> addQuiz(@RequestBody Quizes quiz, @PathVariable int teacherId) {

		teacherService.addQuiz(quiz, teacherId);

		return new ResponseEntity<Quizes>(quiz, HttpStatus.OK);
	}

	@PutMapping("teacher/quiz/{quizId}")
	public ResponseEntity<Quizes> updateQuiz(@PathVariable int teacherId, @PathVariable int quizId,
			@RequestBody Quizes quiz) {

		teacherService.updateQuiz(quizId, quiz);

		return new ResponseEntity<Quizes>(quiz, HttpStatus.OK);
	}

	@DeleteMapping("/teacher/quiz/{quizId}")
	public ResponseEntity<String> deleteQuiz(@PathVariable int quizId) {

		teacherService.deleteQuizById(quizId);
		return new ResponseEntity<String>("Deleted quiz - " + quizId, HttpStatus.OK);
	}

	@PostMapping("teacher/quiz/section/{quizId}")
	public ResponseEntity<Sections> addSection(@RequestBody Sections section, @PathVariable int quizId) {

		teacherService.addSection(section, quizId);

		return new ResponseEntity<Sections>(section, HttpStatus.OK);
	}

	@PutMapping("teacher/quiz/section/{quizId}")
	public ResponseEntity<Sections> updateQuiz(@PathVariable int quizId, @RequestBody Sections section) {

		teacherService.updateSection(section, quizId);

		return new ResponseEntity<Sections>(section, HttpStatus.OK);
	}

	@DeleteMapping("/teacher/quiz/{sectionId}")
	public ResponseEntity<String> deleteSection(@PathVariable int sectionId) {

		teacherService.deleteSectionById(sectionId);
		return new ResponseEntity<String>("Deleted section - " + sectionId, HttpStatus.OK);

	}

	@PostMapping("/teacher/quiz/questions/{sectionId}")
	public ResponseEntity<List<Questions>> addQuestions(@RequestBody List<Questions> listQues,
			@PathVariable int sectionId) {
		teacherService.addQuestions(listQues, sectionId);

		return new ResponseEntity<List<Questions>>(listQues, HttpStatus.OK);
	}

	@PostMapping("/teacher/quiz/question")
	public ResponseEntity<Questions> addQuestion(@RequestBody Questions ques) {
		teacherService.addQuestion(ques);

		return new ResponseEntity<Questions>(ques, HttpStatus.OK);
	}

	@PutMapping("/teacher/quiz/questions")
	public ResponseEntity<List<Questions>> updateQuestions(@RequestBody List<Questions> listQues) {
		teacherService.updateQuestions(listQues);

		return new ResponseEntity<List<Questions>>(listQues, HttpStatus.OK);
	}

	@DeleteMapping("/teacher/quiz/question/{questionId}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable int questionId) {

		teacherService.deleteQuestionById(questionId);

		return new ResponseEntity<String>("Deleted Question - " + questionId, HttpStatus.OK);
	}

	@PostMapping("/teacher/quiz/question/options")
	public Options addOption(@RequestBody Options options) {
		teacherService.addOption(options);

		return options;
	}

}
