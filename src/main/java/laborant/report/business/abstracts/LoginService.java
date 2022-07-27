package laborant.report.business.abstracts;

import java.util.List;

import laborant.report.core.results.DataResult;
import laborant.report.core.results.Result;
import laborant.report.entities.Login;

public interface LoginService {

	DataResult<List<Login>> getAll();
	Result add(Login login);
}
