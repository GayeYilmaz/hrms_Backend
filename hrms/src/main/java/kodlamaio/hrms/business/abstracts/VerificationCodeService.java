package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeService {

	//Result verificationUser(String code);
	VerificationCode createVerificationCode(User user);
	
}
