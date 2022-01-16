package kodlamaio.hrms.core.utilities.verifications;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.VerificationCode;

public interface EmailVerificationService {
	DataResult<VerificationCode> sendEmail(User user);
         
}
