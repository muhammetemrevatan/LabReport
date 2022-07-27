package laborant.report.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laborant.report.business.abstracts.LoginService;
import laborant.report.core.results.DataResult;
import laborant.report.core.results.Result;
import laborant.report.core.results.SuccessDataResult;
import laborant.report.core.results.SuccessResult;
import laborant.report.dataAccess.LoginDao;
import laborant.report.entities.Login;

@Service
public class LoginManager implements LoginService{

	private LoginDao loginDao;

	@Autowired
	public LoginManager(LoginDao loginDao) {
		super();
		this.loginDao = loginDao;
	}

	@Override
	public DataResult<List<Login>> getAll() {
		return new SuccessDataResult<List<Login>>(this.loginDao.findAll(), "Başarıyla Listelendi");
	}

	@Override
	public Result add(Login login) {
		this.loginDao.save(login);
		return new SuccessResult("Başarıyla eklendi");
	}
	
	
}
