package in.hefshine.NaukriProject.controller;

import java.util.Collections;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.hefshine.NaukriProject.module.LoginRegister;
import in.hefshine.NaukriProject.repository.userRepo;
import in.hefshine.NaukriProject.service.EmailService;

@RestController
@CrossOrigin
@RequestMapping("/naukri")
public class StudentController {
    @Autowired
    private userRepo userRepository;

    @Autowired
    private EmailService emailService;

    
    
    @PostMapping("/reg")
    public ResponseEntity<Object> postMethodName1(@RequestBody LoginRegister lr) {
        System.out.println("I am creating " + lr.username);

        LoginRegister existingUser = userRepository.findByEmail(lr.email);
        if (existingUser != null) {
            System.out.println("Error: Email already exists for email: " + lr.email);
            return new ResponseEntity<>("Error: Email already exists", HttpStatus.CONFLICT);
        }

        // Check if mobile already exists
        if (userRepository.countByMobileNumber(lr.mobileNumber) > 0) {
            System.out.println("Error: Mobile already exists for Mobile: " + lr.mobileNumber);
            return new ResponseEntity<>("Error: Mobile already exists", HttpStatus.CONFLICT);
        }

        // Generate a random password
        int password = 1000 + new Random().nextInt(9000);
        lr.password = String.valueOf(password);

        // Save the user
        LoginRegister savedUser = userRepository.save(lr);
        String subject = "Registration Confirmation";
        String body = "Dear " + lr.username +
            ",\n\nThank you for registering with NaukriProject. Your account has been successfully created.";

        // Send confirmation email
        emailService.sendSimpleEmail(lr.email, subject, body, password);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRegister loginData) {
        System.out.println("Received login request: Email/Mobile = " + loginData.emailOrMobile + ", Password = " + loginData.password);
        
        LoginRegister user = null;

        // Determine whether the input is an email or a mobile number
        if (loginData.emailOrMobile.contains("@")) {
            // Login using email
            user = userRepository.findByEmail(loginData.emailOrMobile);
        } else {
            // Login using mobile number
            user = userRepository.findByMobileNumber(loginData.emailOrMobile);
        }

        // Check if user exists and password matches
        if (user != null && user.password.equals(loginData.password)) {
            System.out.println("Login successful for user: " + loginData.emailOrMobile);
//            return new ResponseEntity<>(Collections.singletonMap("message", "Login successful"), HttpStatus.OK);
            return new ResponseEntity<>(loginData.username, HttpStatus.OK);
        } else {
            System.out.println("Invalid credentials for user: " + loginData.emailOrMobile);
            return new ResponseEntity<>(Collections.singletonMap("message", "Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
}
