package laborant.report.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "fullName alanı boş geçilemez")
	@Size(min=4,max=50, message = "fullName alanı 4-50 karakter arasında olmalıdır")
	@Column(name = "full_name")
	private String fullName;
	
	@NotBlank(message = "patientTc boşa geçilemez")
	@Size(min=11,max=11, message = "Tc alanı 11 karakterli olmalıdır.")
	@Column(name = "patient_tc")
	private String patientTc;
}
