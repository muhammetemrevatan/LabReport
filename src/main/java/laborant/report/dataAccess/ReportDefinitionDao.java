package laborant.report.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import laborant.report.entities.ReportDefinition;

@Repository
public interface ReportDefinitionDao extends JpaRepository<ReportDefinition, Integer>{
	
/*
	 	> * Var olan bir rapor üzerinde değişiklik yapılabilmeli 	--> Update Rapor için
		  * Var olan tüm raporların detayları incelenebilmeli		–-> Getall ile tüm detayları göster. List şeklinde gelmeli. Tüm raporları görüntüle.
		> * Var olan bir rapor silinebilmeli 						--> Delete rapor
*/
	//rep inner join Laborant lab on rep.laborant = lab.id inner join Patient pat on rep.patient = pat.id where lab.fullName like %:keyword% or pat.patientTc like %:keyword% or pat.fullName like %:keyword%
	@Query("from ReportDefinition rep inner join Laborant lab on rep.laborant = lab.id inner join Patient pat on rep.patient = pat.id where lab.fullName like %:keyword% or pat.patientTc like %:keyword% or pat.fullName like %:keyword%")
	List<ReportDefinition> findByKeyword(@Param("keyword") String keyword);  
}
