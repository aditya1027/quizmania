package com.pushyourself11.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pushyourself11.Entity.Options;
import com.pushyourself11.Entity.Questions;
import com.pushyourself11.Entity.Quizes;
import com.pushyourself11.Entity.Sections;
import com.pushyourself11.Entity.Teacher;
import com.pushyourself11.Entity.TeacherDetails;
import com.pushyourself11.RestController.ExceptionHandler.NotFoundException;
import com.pushyourself11.dao.OptionRepository;
import com.pushyourself11.dao.QuestionRepository;
import com.pushyourself11.dao.QuizesRepository;
import com.pushyourself11.dao.SectionRepository;
import com.pushyourself11.dao.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	private TeacherRepository teacherRepository;

	private QuizesRepository quizRepository;

	@Autowired
	private SectionRepository sectionRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private OptionRepository optionRepository;

	@Autowired
	public TeacherServiceImpl(TeacherRepository theTeacherRepository, QuizesRepository thQuizesRepository) {
		teacherRepository = theTeacherRepository;
		quizRepository = thQuizesRepository;
	}

	@Override
	public List<Teacher> findAll() {

		return teacherRepository.findAll();
	}

	@Override
	public Teacher findById(int theid) {
		if (theid < 0 || theid > (int) teacherRepository.count()) {
			throw new NotFoundException("Teacher id not found - " + theid);
		}
		Optional<Teacher> result = teacherRepository.findById(theid);

		Teacher theTeacher = null;

		if (result.isPresent()) {
			theTeacher = result.get();
		} else {
			throw new NotFoundException("Teacher id not found - " + theid);
		}

		return theTeacher;
	}

	@Override
	public void save(Teacher teacher) {

		teacherRepository.save(teacher);

	}

	@Override
	public Teacher updateTeacherDetails(int id, TeacherDetails teacherDetails) {
		Optional<Teacher> result = teacherRepository.findById(id);

		Teacher theTeacher = null;

		if (result.isPresent()) {
			theTeacher = result.get();
			theTeacher.setTeacherDetails(teacherDetails);
			teacherRepository.save(theTeacher);
		} else {
			throw new RuntimeException("Did not find the employee");
		}

		return theTeacher;

	}

	@Override
	public void deleteById(int theid) {
		teacherRepository.deleteById(theid);

	}

	@Override
	public List<Quizes> getTeacherQuizes(int teacherId) {
		List<Quizes> quizes = quizRepository.findAll();

		List<Quizes> q = new ArrayList<Quizes>();

		for (Quizes quiz : quizes) {
			if (quiz.getTeacher().getTeacherId() == teacherId) {
				q.add(quiz);
			}
		}

		if (q.size() == 0) {
			throw new RuntimeException("No quizes made by this teacher");
		}

		return q;
	}

	@Override
	public Optional<Quizes> findQuizByQuizId(int quizId) {
		return quizRepository.findById(quizId);

	}

	@Override
	public Quizes getTeacherQuizById(int quizId) {
		Optional<Quizes> res = quizRepository.findById(quizId);

		Quizes quiz;
		if (res.isPresent()) {
			quiz = res.get();
		} else {
			throw new RuntimeException("Did not find the Quiz");
		}

		return quiz;
	}

	@Override
	public Quizes addQuiz(Quizes quiz, int teacherId) {
		Optional<Teacher> res = teacherRepository.findById(teacherId);

		Teacher teacher = null;
		if (res.isPresent()) {
			teacher = res.get();
			quiz.setTeacher(teacher);
			quiz.setCreatedOn(new Date());
			quiz.setModifiedOn(new Date());
			quizRepository.save(quiz);

		} else {
			throw new NotFoundException("Did not find the Teacher with this id");
		}
		return quiz;
	}

	@Override
	public Quizes updateQuiz(int quizId, Quizes quiz) {

		quizRepository.save(quiz);

		return quiz;

	}

	@Override
	public String deleteQuizById(int quizId) {
		quizRepository.deleteById(quizId);
		return "Delete the quiz with id" + quizId;
	}

	@Override
	public Sections addSection(Sections section, int quizId) {

		Optional<Quizes> res = quizRepository.findById(quizId);
		Quizes quiz = null;
		if (res.isPresent()) {
			quiz = res.get();
			quiz.setModifiedOn(new Date());
			int draftVer = quiz.getDraftVersion();
			quiz.setDraftVersion(++draftVer);
			section.setQuiz(quiz);
			quizRepository.save(quiz);
			sectionRepository.save(section);

		} else
			throw new NotFoundException("Not found the quiz " + quizId);

		return section;

	}

	@Override
	public Sections updateSection(Sections section, int quizId) {
		Optional<Quizes> res = quizRepository.findById(quizId);
		Quizes quiz = null;
		if (res.isPresent()) {
			quiz = res.get();
			quiz.setModifiedOn(new Date());
			int draftVer = quiz.getDraftVersion();
			quiz.setDraftVersion(++draftVer);
			section.setQuiz(quiz);
			quizRepository.save(quiz);
			sectionRepository.save(section);

		} else
			throw new NotFoundException("Not found the quiz " + quizId);
		return section;
	}

	@Override
	public String deleteSectionById(int sectionId) {
		sectionRepository.deleteById(sectionId);
		return "Delete the section with id" + sectionId;
	}

	@Override
	public List<Questions> addQuestions(List<Questions> listQues, int sectionId) {
		Optional<Sections> result = sectionRepository.findById(sectionId);

		Sections section = null;
		if (result.isPresent())
			section = result.get();

		Quizes quiz = section.getQuiz();
		quiz.setModifiedOn(new Date());
		quizRepository.save(quiz);

		for (Questions q : listQues) {
			q.setSection(section);
			questionRepository.save(q);
		}
		return listQues;
	}

	@Override
	public Questions addQuestion(Questions ques) {

		questionRepository.save(ques);

		return ques;
	}

	@Override
	public List<Questions> updateQuestions(List<Questions> listQues) {
		for (Questions ques : listQues) {
			questionRepository.save(ques);

		}
		return listQues;
	}

	@Override
	public String deleteQuestionById(int questionId) {
		questionRepository.deleteById(questionId);
		return "Delete the section with id" + questionId;
	}

	@Override
	public Options addOption(Options options) {
		optionRepository.save(options);
		return options;
	}

}
