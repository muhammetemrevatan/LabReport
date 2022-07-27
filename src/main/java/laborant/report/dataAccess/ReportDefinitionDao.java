package laborant.report.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laborant.report.entities.ReportDefinition;

@Repository
public interface ReportDefinitionDao extends JpaRepository<ReportDefinition, Integer>{
	
/*
	 	> * Var olan bir rapor üzerinde değişiklik yapılabilmeli 	--> Update Rapor için
		  * Var olan tüm raporların detayları incelenebilmeli		–-> Getall ile tüm detayları göster. List şeklinde gelmeli. Tüm raporları görüntüle.
		> * Var olan bir rapor silinebilmeli 						--> Delete rapor
*/
	
	
}
