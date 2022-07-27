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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "username alanı boş geçilemez")
	@Size(min = 5, max = 20, message = "username alanı 5-20 karakter arasında olmalıdır")
	@Column(name = "username")
	private String username;
	
	@NotBlank(message = "password alanı boş geçilemez")
	@Size(min = 8, max = 20, message = "password alanı 8-20 karakter arasında olmalıdır")
	@Column(name = "password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "laborant_id")
	private Laborant laborant;
}
