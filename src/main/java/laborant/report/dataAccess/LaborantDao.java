package laborant.report.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laborant.report.entities.Laborant;

@Repository
public interface LaborantDao extends JpaRepository<Laborant, Integer>{

	Laborant getById(int laborantId);
}
