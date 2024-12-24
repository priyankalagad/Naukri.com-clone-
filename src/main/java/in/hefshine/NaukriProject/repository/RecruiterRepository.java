package in.hefshine.NaukriProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.hefshine.NaukriProject.module.RecruiterDashboard;

public interface RecruiterRepository extends JpaRepository<RecruiterDashboard, Long> {
}
