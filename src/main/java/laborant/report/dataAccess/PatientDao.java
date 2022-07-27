package laborant.report.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laborant.report.entities.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer>{

	Patient getById(int patientId);
}
