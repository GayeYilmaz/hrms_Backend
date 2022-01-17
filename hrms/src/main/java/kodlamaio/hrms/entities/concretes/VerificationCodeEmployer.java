package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
@Table(name="verification_code_employers")
public class VerificationCodeEmployer extends VerificationCode {
	
	@Column(name="employer_id")
	private int employerId;
	
	public VerificationCodeEmployer(int id, String code, boolean isVerified, LocalDate verifiedDate, int employerId) {
		super(id, code, isVerified, verifiedDate);
		this.employerId = employerId;
	}
	

}
