package laborant.report.business.abstracts;

import java.util.List;

import laborant.report.core.results.DataResult;
import laborant.report.core.results.Result;
import laborant.report.entities.Patient;

public interface PatientService {

	DataResult<List<Patient>> getAll();
	Result add(Patient patient);
}
