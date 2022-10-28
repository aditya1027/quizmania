package com.pushyourself11.Service;

import java.util.List;
import java.util.Optional;

import com.pushyourself11.Entity.Options;
import com.pushyourself11.Entity.Questions;
import com.pushyourself11.Entity.Quizes;
import com.pushyourself11.Entity.Sections;
import com.pushyourself11.Entity.Teacher;
import com.pushyourself11.Entity.TeacherDetails;


public interface TeacherService {
	
	//Teacher functions
	
	
	public List<Teacher> findAll();
	
	public Teacher findById(int theid);
	
	public void save(Teacher teacher);
	
	public Teacher updateTeacherDetails(int id , TeacherDetails teacherDetails);
	
	public void deleteById(int theid);
	
	
	//Quiz functions
	public List<Quizes> getTeacherQuizes(int teacherId);
	
	public Quizes getTeacherQuizById( int quizId);
	
	public Optional<Quizes> findQuizByQuizId(int theid);
	
	public Quizes addQuiz(Quizes quiz, int teacherId);
	
	public Quizes updateQuiz(int quizId ,Quizes quiz);
	
	public String deleteQuizById(int quizId);
	
	//Section functions
	
	public Sections addSection(Sections section , int quizId );
	
	public Sections updateSection(Sections section , int quizId);
	
	public String deleteSectionById(int id);
	
	//Questions functions
	
	public List<Questions> addQuestions(List<Questions> listQues , int sectionId);
	
	public List<Questions> updateQuestions(List<Questions> listQues); 
	
	public String deleteQuestionById(int questionId);

	Questions addQuestion(Questions ques);
	
	Options addOption(Options option);
	
}
