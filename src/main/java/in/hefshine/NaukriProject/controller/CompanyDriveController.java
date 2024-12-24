package in.hefshine.NaukriProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import in.hefshine.NaukriProject.module.CompanyDrive;
import in.hefshine.NaukriProject.module.RecruiterDashboard;
import in.hefshine.NaukriProject.repository.CompanyDriveRepository;
import in.hefshine.NaukriProject.repository.RecruiterDashboardFetch;
import in.hefshine.NaukriProject.service.CompanyDriveService;

@RestController
@RequestMapping("/companydrives")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyDriveController {

    @Autowired
    private CompanyDriveService companyDriveService;

    @Autowired
    private RecruiterDashboardFetch recruiterDashboardFetch;

    @Autowired
    private CompanyDriveRepository companyDriveRepository;

    @GetMapping("/getAllCompany")
    public List<RecruiterDashboard> getAllCompanyDrives() {
        return recruiterDashboardFetch.findAll();
    }
    @GetMapping("/getappliedCompany")
    public List<CompanyDrive> getappliedCompany() {
        return companyDriveRepository.findAll();
    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<String> applyToCompany(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        companyDriveService.applyToCompany(id, file);
        System.out.println("Applied to company successfully!");
        return ResponseEntity.ok("Applied to company successfully!");
    }

    @PostMapping("/reject/{company}")
    public ResponseEntity<String> rejectCompany(@PathVariable String company) {
        companyDriveService.rejectCompany1(company);
        System.out.println("Rejected company successfully!");
        return ResponseEntity.ok("Rejected company successfully!");
    }
    
}

