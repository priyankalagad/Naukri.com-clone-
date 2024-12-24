package in.hefshine.NaukriProject.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.hefshine.NaukriProject.module.Recruiter;
import in.hefshine.NaukriProject.module.RecruiterDashboard;
import in.hefshine.NaukriProject.repository.RecruiterDashboardFetch;
import in.hefshine.NaukriProject.repository.RecruiterRepo;
import in.hefshine.NaukriProject.service.EmailService;

@RestController
@CrossOrigin
@RequestMapping("/recruiter")
public class RecruiterController {

	@Autowired
	private RecruiterRepo recruiterRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	 private RecruiterDashboardFetch recruiterDashboardFetch;

	
	  @GetMapping("/getAllCompany")
	    public List<RecruiterDashboard> getAllCompanyDrives() {
	        return recruiterDashboardFetch.findAll();
	    }
	  
	@PostMapping("/register")
	public ResponseEntity<Object> registerRecruiter(@RequestBody Recruiter recruiter) {
		Recruiter existingRecruiter = recruiterRepository.findByEmail(recruiter.email);
		if (existingRecruiter != null) {
			return new ResponseEntity<>("Error: Email already exists", HttpStatus.CONFLICT);
		}

		// Generate a random plain-text password
		int password = 1000 + new Random().nextInt(9000);
		recruiter.password = String.valueOf(password); // Save password as plain-text (not recommended)

		// Save the recruiter
		Recruiter savedRecruiter = recruiterRepository.save(recruiter);

		// Send the plain-text password in an email (not secure)
		String subject = "Registration Confirmation";
		String body = "Dear " + recruiter.username
				+ ",\n\nThank you for registering with NaukriProject. Your account has been created. ";

		emailService.sendSimpleEmail(recruiter.email, subject, body, password);

		return new ResponseEntity<>(savedRecruiter, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> loginRecruiter(@RequestBody Recruiter recruiter) {
		if ((recruiter.email == null || recruiter.email.isEmpty())
				&& (recruiter.mobileNumber == null || recruiter.mobileNumber.isEmpty())) {
			return new ResponseEntity<>("Error: Email or Mobile Number is required", HttpStatus.BAD_REQUEST);
		}

		// Retrieve the recruiter by email or mobile number
		Recruiter existingRecruiter = null;
		if (recruiter.email != null && !recruiter.email.isEmpty()) {
			existingRecruiter = recruiterRepository.findByEmail(recruiter.email);
		} else {
			existingRecruiter = recruiterRepository.findByMobileNumber(recruiter.mobileNumber);
		}

		// Check if recruiter exists and verify the plain-text password
		if (existingRecruiter != null && recruiter.password.equals(existingRecruiter.password)) {
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		}

		return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
	}
}

