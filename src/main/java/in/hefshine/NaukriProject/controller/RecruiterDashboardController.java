package in.hefshine.NaukriProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.hefshine.NaukriProject.module.RecruiterDashboard;
import in.hefshine.NaukriProject.repository.RecruiterRepository;

@CrossOrigin
@RestController
@RequestMapping("/recruitersDash")
public class RecruiterDashboardController {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @PostMapping("/register")
    public RecruiterDashboard registerRecruiter(@RequestBody RecruiterDashboard recruiterDashboard) {
        System.out.println("Received request: " + recruiterDashboard);
        return recruiterRepository.save(recruiterDashboard);
    }


    @GetMapping
    public List<RecruiterDashboard> getAllRecruiters() {
        return recruiterRepository.findAll();
    }
}
