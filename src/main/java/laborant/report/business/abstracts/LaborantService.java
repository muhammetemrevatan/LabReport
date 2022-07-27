package laborant.report.business.abstracts;

import java.util.List;

import laborant.report.core.results.DataResult;
import laborant.report.core.results.Result;
import laborant.report.entities.Laborant;

public interface LaborantService {

	DataResult<List<Laborant>> getAll();
	Result add(Laborant laborant);
}
