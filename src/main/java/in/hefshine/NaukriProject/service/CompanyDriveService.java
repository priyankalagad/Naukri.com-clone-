package in.hefshine.NaukriProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.hefshine.NaukriProject.module.CompanyDrive;
import in.hefshine.NaukriProject.module.RecruiterDashboard;
import in.hefshine.NaukriProject.repository.CompanyDriveRepository;
import in.hefshine.NaukriProject.repository.RecruiterDashboardFetch;
import jakarta.transaction.Transactional;

@Service
public class CompanyDriveService {

	@Autowired
	private CompanyDriveRepository companyDriveRepository;

	@Autowired
	private RecruiterDashboardFetch recruiterDashboardFetch;

	public void applyToCompany(Long recruiterId, MultipartFile resume) {
		// Fetch recruiter information from RecruiterDashboard
		RecruiterDashboard recruiterDashboard = recruiterDashboardFetch.findById(recruiterId)
				.orElseThrow(() -> new RuntimeException("Recruiter not found"));

		// Create a new CompanyDrive entity based on the recruiter data
		CompanyDrive companyDrive = new CompanyDrive();
		companyDrive.setCompany(recruiterDashboard.getCompany());
		companyDrive.setSkillsRequired(recruiterDashboard.getSkillsRequired());
		companyDrive.setJobTitle(recruiterDashboard.getJobTitle());

		// Save resume file name
		companyDrive.setResumeFileName(resume.getOriginalFilename());

		// Save the new CompanyDrive entry
		companyDriveRepository.save(companyDrive);

		System.out.println("Application successful for company: " + recruiterDashboard.getCompany());
	}

	 @Transactional
	public void rejectCompany1(String company) {
		companyDriveRepository.deleteByCompany(company);
	}


}
