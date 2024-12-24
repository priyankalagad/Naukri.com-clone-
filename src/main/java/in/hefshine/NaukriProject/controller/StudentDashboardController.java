package in.hefshine.NaukriProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.hefshine.NaukriProject.module.StudentDash;
import in.hefshine.NaukriProject.repository.StudentRepository;

@CrossOrigin
@RestController
@RequestMapping("/studentsDash")
public class StudentDashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/register")
    public StudentDash registerStudent(@RequestBody StudentDash studentdash) {
        return studentRepository.save(studentdash);
    }

    @GetMapping
    public List<StudentDash> getAllStudentDash() {
        return studentRepository.findAll();
    }
}