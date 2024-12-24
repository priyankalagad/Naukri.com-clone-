package in.hefshine.NaukriProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.hefshine.NaukriProject.module.StudentDash;

public interface StudentRepository extends JpaRepository<StudentDash, Integer> {

}
