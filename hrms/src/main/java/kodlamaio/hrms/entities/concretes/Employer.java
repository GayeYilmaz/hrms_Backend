package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@PrimaryKeyJoinColumn(name="id", referencedColumnName = "id")
@Table(name="employers")
public class Employer extends User{
	
	@NotNull()
	@Column(name="company_name")
	private String companyName;
	
	@NotNull()
	@Column(name="web_address")
	private String webAddress;
	
	@NotNull()
	@Column(name="phone_number")
	private String phoneNumber;

}
