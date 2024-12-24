package in.hefshine.NaukriProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.hefshine.NaukriProject.module.Recruiter;

@Repository
public interface RecruiterRepo extends JpaRepository<Recruiter, Integer> {
    Recruiter findByEmail(String email);
    Recruiter findByMobileNumber(String mobileNumber); // Add search by mobile
}

