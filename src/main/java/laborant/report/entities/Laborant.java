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
@Table(name = "laborant")
public class Laborant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "FullName alanı boş geçilemez")
	@Size(min = 4, max = 50, message = "fullName alanı 4-50 karakter arasında olmalıdır")
	@Column(name = "full_name")
	private String fullName;
	
	@NotBlank(message = "hospitalIdentityNo alanı boş geçilemez")
	@Size(min = 7, max = 7, message = "hospitalIdentityNo alanı 7 karakterden oluşmalıdır")
	@Column(name = "hospital_identity_no")
	private String hospitalIdentityNo;
}
