package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.VerificationCode;
import kodlamaio.hrms.entities.concretes.VerificationCodeCandidate;

public interface VerificationCodeCandidateDao extends JpaRepository<VerificationCodeCandidate,Integer>{
	//VerificationCodeCandidate getByVerificationCodeCandidate(String code);
	//boolean existdByVerificationCode(String code);

}
