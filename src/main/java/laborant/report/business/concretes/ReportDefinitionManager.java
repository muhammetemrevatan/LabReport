package laborant.report.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laborant.report.business.abstracts.ReportDefinitionService;
import laborant.report.dataAccess.LaborantDao;
import laborant.report.dataAccess.PatientDao;
import laborant.report.dataAccess.ReportDefinitionDao;
import laborant.report.entities.Laborant;
import laborant.report.entities.Patient;
import laborant.report.entities.ReportDefinition;

@Service
public class ReportDefinitionManager implements ReportDefinitionService{

	private ReportDefinitionDao reportDefinitionDao;
	private LaborantDao laborantDao;
	private PatientDao patientDao;
	
	@Autowired
	public ReportDefinitionManager(ReportDefinitionDao reportDefinitionDao, LaborantDao laborantDao,
			PatientDao patientDao) {
		super();
		this.reportDefinitionDao = reportDefinitionDao;
		this.laborantDao = laborantDao;
		this.patientDao = patientDao;
	}

	@Override
	public List<ReportDefinition> getAll() {
		return this.reportDefinitionDao.findAll();
	}

	@Override
	public void add(ReportDefinition reportDefinition) {
		this.reportDefinitionDao.save(reportDefinition);
	}

	/*
	@Override
	public void update(ReportDefinition reportDefinition) {
		
		ReportDefinition reportDefinitions = reportDefinitionDao.getReferenceById(reportDefinition.getId());
		Laborant newLaborant = laborantDao.getById(reportDefinition.getLaborant().getId());
		Patient newPatient = patientDao.getById(reportDefinition.getPatient().getId());
		reportDefinitions.setLaborant(newLaborant);
		reportDefinitions.setPatient(newPatient);
		reportDefinitions.setDiagnosisDetail(reportDefinition.getDiagnosisDetail());
		reportDefinitions.setDiagnosisTitle(reportDefinition.getDiagnosisTitle());
		reportDefinitions.setReportDate(reportDefinition.getReportDate());
		reportDefinitions.setReportImage(reportDefinition.getReportImage());
		this.reportDefinitionDao.save(reportDefinitions);
	}
	*/

	@Override
	public void delete(int reportDefinitionId) {
		ReportDefinition reportDefinition = reportDefinitionDao.getReferenceById(reportDefinitionId);
		this.reportDefinitionDao.delete(reportDefinition);
	}

	@Override
	public List<Laborant> getAllLaborant() {
		 
		return this.laborantDao.findAll();
	}

	@Override
	public List<Patient> getAllPatient() {
		return this.patientDao.findAll();
	}
	
	@SuppressWarnings("deprecation")
	public ReportDefinition getReportDefinition(int id) {
		return this.reportDefinitionDao.getById(id);
	}
	
	
	
}
