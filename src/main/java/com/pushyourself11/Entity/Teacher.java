package com.pushyourself11.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacherId;

	private String firstname;

	private String lastname;

	private String email;

	private String password;
	
	private String role; 

	@OneToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private TeacherDetails teacherDetails;
	
	private int quizesMade;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "teacher", cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Quizes> quiz;	

	public Teacher(String firstname, String lastname, String email, String password, TeacherDetails teacherDetails,
			int quizesMade, List<Quizes> quiz , String role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.teacherDetails = teacherDetails;
		this.quizesMade = quizesMade;
		this.quiz = quiz;
		this.role= role;
	}
}