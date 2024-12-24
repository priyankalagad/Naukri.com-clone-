package in.hefshine.NaukriProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.hefshine.NaukriProject.module.LoginRegister;

@Repository
public interface userRepo extends JpaRepository<LoginRegister, Integer> {
    LoginRegister findByEmail(String email);
    LoginRegister findByMobileNumber(String mobileNumber);
    int countByMobileNumber(String mobileNumber);
}
