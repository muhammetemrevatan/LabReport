package laborant.report.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laborant.report.entities.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, Integer>{

}
