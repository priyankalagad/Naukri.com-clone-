package in.hefshine.NaukriProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.hefshine.NaukriProject.module.RecruiterDashboard;


@Repository
public interface RecruiterDashboardFetch  extends JpaRepository<RecruiterDashboard, Long>{
	List<RecruiterDashboard> findBySkillsRequired(String skillsRequired);
}



