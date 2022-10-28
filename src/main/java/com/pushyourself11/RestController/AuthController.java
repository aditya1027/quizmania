package com.pushyourself11.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pushyourself11.Entity.LoginResponse;
import com.pushyourself11.Entity.Teacher;
import com.pushyourself11.Entity.Users;
import com.pushyourself11.RestController.ExceptionHandler.NotFoundException;
import com.pushyourself11.Service.AuthService;
import com.pushyourself11.Service.StudentService;
import com.pushyourself11.Service.TeacherService;
import com.pushyourself11.StudentEntities.Student;
import com.pushyourself11.dao.StudentRepository;
import com.pushyourself11.dao.TeacherRepository;
import com.pushyourself11.security.MyUserDetailsService;
import com.pushyourself11.utils.JwtUtil;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private JwtUtil jwtTokenUtil;
	

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users user) throws Exception, NotFoundException {
		
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (BadCredentialsException e) {
			throw new NotFoundException("enter correct details");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

		Teacher teacher = teacherRepository.findByEmail(user.getEmail());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new LoginResponse(jwt, teacher.getTeacherId(), teacher.getFirstname()));

	}

	@PostMapping("/login/student")
	public ResponseEntity<?> loginStudent(@RequestBody Users user) throws Exception, NotFoundException {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (BadCredentialsException e) {
			throw new NotFoundException("enter correct details");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

		Student theStudent = studentRepository.findByEmail(user.getEmail());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new LoginResponse(jwt, theStudent.getStudentId(), theStudent.getFirstname()));
	}

	@PostMapping("/signup/student")
	public ResponseEntity<Student> signUpStudent(@RequestBody Student theStudent) {
		
		theStudent.setStudentId(0);
		theStudent.setRole("student");
		theStudent.setPassword(passwordEncoder.encode(theStudent.getPassword()));
		
		studentService.save(theStudent);
		
		authService.saveStudentCredentials(theStudent);

		return new ResponseEntity<Student>(theStudent, HttpStatus.OK);
	}

	@PostMapping("/signup/teacher")
	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher theTeacher) {
		theTeacher.setTeacherId(0);
		theTeacher.setRole("teacher");
		theTeacher.setPassword(passwordEncoder.encode(theTeacher.getPassword()));
		
		teacherService.save(theTeacher);

		authService.saveCredenitals(theTeacher);

		return new ResponseEntity<Teacher>(theTeacher, HttpStatus.OK);
	}

}
