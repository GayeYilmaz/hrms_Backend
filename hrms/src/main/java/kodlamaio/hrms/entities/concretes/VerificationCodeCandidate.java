package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@PrimaryKeyJoinColumn(name="id", referencedColumnName = "id")
@Table(name="verification_code_candidates")
public class VerificationCodeCandidate extends VerificationCode {
	

	@Column(name="candidate_id")
	private int candidateId;

	public VerificationCodeCandidate(int id, String code, boolean isVerified, LocalDate verifiedDate, int candidateId) {
		super(id, code, isVerified, verifiedDate);
		this.candidateId = candidateId;
	}
	
	

}
