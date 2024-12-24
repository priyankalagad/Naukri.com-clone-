package in.hefshine.NaukriProject.module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentDash {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String name;
	public String email;
	public String education;
	public String marks10;
	public String marks12;
	public String gradmarks;
	public String graduYear;
	public String dob;
	public String skills;
	public String experience;
}
