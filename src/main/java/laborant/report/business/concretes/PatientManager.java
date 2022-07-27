package laborant.report.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laborant.report.business.abstracts.PatientService;
import laborant.report.core.results.DataResult;
import laborant.report.core.results.Result;
import laborant.report.core.results.SuccessDataResult;
import laborant.report.core.results.SuccessResult;
import laborant.report.dataAccess.PatientDao;
import laborant.report.entities.Patient;

@Service
public class PatientManager implements PatientService{

	private PatientDao patientDao;

	@Autowired
	public PatientManager(PatientDao patientDao) {
		super();
		this.patientDao = patientDao;
	}

	@Override
	public DataResult<List<Patient>> getAll() {
		return new SuccessDataResult<List<Patient>>(this.patientDao.findAll(), "Başarıyla listelendi");
	}

	@Override
	public Result add(Patient patient) {
		this.patientDao.save(patient);
		return new SuccessResult("Başarıyla eklendi");
	}
	
}
