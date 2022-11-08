package com.quan.gradepractice;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quan.gradepractice.Entity.AppUser;
import com.quan.gradepractice.Entity.Course;
import com.quan.gradepractice.Entity.Enrolment;
import com.quan.gradepractice.Entity.Grade;
import com.quan.gradepractice.Entity.Role;
import com.quan.gradepractice.Entity.RoleType;
import com.quan.gradepractice.Entity.Student;
import com.quan.gradepractice.Entity.StudentIdCard;
import com.quan.gradepractice.Repository.AppUserRepository;
import com.quan.gradepractice.Repository.CourseRepo;
import com.quan.gradepractice.Repository.EnrolmentRepo;
import com.quan.gradepractice.Repository.StudentRepo;

@SpringBootApplication
public class GradepracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradepracticeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepo studentRepo, CourseRepo courseRepo, EnrolmentRepo enrolmentRepo, AppUserRepository appUserRepository) {
		return args -> {
			Student quan = new Student("quan", "quandoan@gmail.com", LocalDate.of(1996, 8, 19));
			Course programming1 = new Course("programming1", "12345fc" , "ICT");
			Course programming2 = new Course("programming2", "456465bc" , "ICT");
			Grade gradeA = new Grade(6, quan, programming1);
			Enrolment enrolA = new Enrolment(quan, programming1, LocalDateTime.now().minusDays(4));
			Enrolment enrolB = new Enrolment(quan, programming2, LocalDateTime.now().minusDays(4));
			
			 
			studentRepo.save(quan);
			courseRepo.save(programming1);
			courseRepo.save(programming2);
			

			// programming1.addGradetoCourse(gradeA);
			//  courseRepo.save(programming1);

			quan.addEnrolmentToStudent(enrolA);
			quan.addEnrolmentToStudent(enrolB);
			
			studentRepo.save(quan);

			// programming2.removeEnrolmentFromCourse(enrolB);
			quan.removeEnrolmentFromStudent(enrolB);
			studentRepo.save(quan);

			StudentIdCard card1 = new StudentIdCard("09132132132ddx", quan);
			quan.addStudentIdCardToStudent(card1);
			studentRepo.save(quan);

			quan.removeStudentIdCardToStudent();
			studentRepo.save(quan);
			
			AppUser admin = new AppUser("quan_admin", "123456");
			admin.getRoles().add(new Role(RoleType.ROLE_ADMIN));
			
			appUserRepository.save(admin);

			AppUser khanhHeo = new AppUser("khanh1", "123456");
			
			khanhHeo.getRoles().add(new Role(RoleType.ROLE_USER));
			appUserRepository.save(khanhHeo);
			
		};
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
