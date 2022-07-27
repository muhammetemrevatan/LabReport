package laborant.report.business.abstracts;

import java.util.List;

import laborant.report.entities.Laborant;
import laborant.report.entities.Patient;
import laborant.report.entities.ReportDefinition;

public interface ReportDefinitionService {

	List<ReportDefinition> getAll();
	List<Laborant> getAllLaborant();
	List<Patient> getAllPatient();
	void add(ReportDefinition reportDefinition);
	//void update(ReportDefinition reportDefinition);
	void delete(int reportDefinitionId);
	ReportDefinition getReportDefinition(int id);
	
} 
