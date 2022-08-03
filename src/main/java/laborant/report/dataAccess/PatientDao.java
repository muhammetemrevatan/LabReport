package laborant.report.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import laborant.report.entities.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer>{

	Patient getById(int patientId);
	
	@Query("SELECT p FROM Patient p WHERE p.fullName LIKE %?1%")
	List<Patient> find(String keyword);
}
