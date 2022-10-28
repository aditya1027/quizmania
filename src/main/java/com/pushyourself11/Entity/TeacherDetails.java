package com.pushyourself11.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teacher_detail")
public class TeacherDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="profile_pic_url")
	private String profilePicUrl;
	
	
	
	public TeacherDetails() {}

	public TeacherDetails(String profilePicUrl, int quizesMade, String quiz) {
		this.profilePicUrl = profilePicUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}


	@Override
	public String toString() {
		return "TeacherDetails [id=" + id + ", profilePicUrl=" + profilePicUrl + ", quizesMade="+ "]";
	}
	
	
	
	
}
