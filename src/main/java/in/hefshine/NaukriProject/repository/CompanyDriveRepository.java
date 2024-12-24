package in.hefshine.NaukriProject.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.hefshine.NaukriProject.module.CompanyDrive;

@Repository
public interface CompanyDriveRepository extends JpaRepository<CompanyDrive, Long> {
	void deleteByCompany(String company); 
}

