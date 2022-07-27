package laborant.report.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laborant.report.business.abstracts.LaborantService;
import laborant.report.core.results.DataResult;
import laborant.report.core.results.Result;
import laborant.report.core.results.SuccessDataResult;
import laborant.report.core.results.SuccessResult;
import laborant.report.dataAccess.LaborantDao;
import laborant.report.entities.Laborant;

@Service
public class LaborantManager implements LaborantService{

	private LaborantDao laborantDao;

	@Autowired
	public LaborantManager(LaborantDao laborantDao) {
		super();
		this.laborantDao = laborantDao;
	}

	@Override
	public DataResult<List<Laborant>> getAll() {
		return new SuccessDataResult<List<Laborant>>(this.laborantDao.findAll(),"Başarıyla listelendi.");
	}

	@Override
	public Result add(Laborant laborant) {
		this.laborantDao.save(laborant);
		return new SuccessResult("Başarıyla eklendi");
	}
	
	
}
