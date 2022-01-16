package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.VerificationCode;

@Service
public class VerificationCodeManager implements VerificationCodeService {
	private VerificationCodeDao verificationCodeDao;
	private UserDao userDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public VerificationCodeManager(VerificationCodeDao verificationCodeDao, UserDao userDao,CandidateDao candidateDao) {
		super();
		this.verificationCodeDao = verificationCodeDao;
		this.userDao = userDao;
		this.candidateDao = candidateDao;
	}

	



	@Override
	public VerificationCode createVerificationCode(User user) {
		String actCode = UUID.randomUUID().toString();
		VerificationCode Code = new VerificationCode();
		Code.setCode(actCode);
		Code.setVerifiedDate(null);
		Code.setVerified(false);
		return Code;
	}
	

}
