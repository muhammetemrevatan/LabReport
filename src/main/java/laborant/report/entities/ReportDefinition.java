package laborant.report.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report_definition")
public class ReportDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "Rapor Detayları alanı boş geçilemez")
	@Size(max=200, message = "diagnosisDetail alanı maksimum 200 karakterli olabilir")
	@Column(name = "diagnosis_detail") 
	private String diagnosisDetail;
	
	@NotBlank(message = "Rapor Başlığı alanı boş geçilemez")
	@Size(max=50, message = "diagnosisDetail alanı maksimum 50 karakterli olabilir")
	@Column(name = "diagnosis_title")
	private String diagnosisTitle; 
	
	@NotBlank(message = "Rapor Tarihi alanı boş geçilemez")
	@Size(max=50, message = "diagnosisDetail alanı maksimum 50 karakterli olabilir")
	@Column(name = "report_date")
	private String reportDate;
	
	@NotBlank(message = "Rapor Resmi alanı boş geçilemez")
	@Size(max=200, message = "diagnosisDetail alanı maksimum 200 karakterli olabilir")
	@Column(name = "report_image")
	private String reportImage;
	
	@ManyToOne
	@JoinColumn(name = "laborant_id")
	private Laborant laborant;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
}
