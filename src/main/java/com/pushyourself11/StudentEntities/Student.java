package com.pushyourself11.StudentEntities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;

	private String firstname;

	private String lastname;

	private String email;

	private String password;
	
	private String role;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private StudentDetails studentDetails;

	public Student(String firstname, String lastname, String email, String password, StudentDetails studentDetails , String role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.studentDetails = studentDetails;
		this.role = role;
	}

	/*
	 * public int getStudentId() { return studentId; }
	 * 
	 * public void setStudentId(int teacherId) { this.studentId = teacherId; }
	 * 
	 * public String getFirstname() { return firstname; }
	 * 
	 * public void setFirstname(String firstname) { this.firstname = firstname; }
	 * 
	 * public String getLastname() { return lastname; }
	 * 
	 * public void setLastname(String lastname) { this.lastname = lastname; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * 
	 * 
	 * public StudentDetails getStudentDetails() { return studentDetails; }
	 * 
	 * 
	 * 
	 * public void setStudentDetails(StudentDetails studentDetails) {
	 * this.studentDetails = studentDetails; }
	 */

}
