package kodlamaio.hrms.core.utilities.verifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.VerificationCode;

@Service
public class EmailVerificationManagerAdapter implements EmailVerificationService{
	private VerificationCodeService verficationCodeService;
	private VerificationCodeDao verificationCodeDao;

	@Autowired
	public EmailVerificationManagerAdapter(VerificationCodeService verficationCodeService,VerificationCodeDao verificationCodeDao) {
		super();
		this.verficationCodeService = verficationCodeService;
		this.verificationCodeDao = verificationCodeDao;
	}

	@Override
	public DataResult<VerificationCode> sendEmail(User user) {
		
	      VerificationCode verificationCode = this.verficationCodeService.createVerificationCode(user);
	     
	      
	      return new SuccessDataResult<VerificationCode>(verificationCode,"Code send to email.("+verificationCode.getCode()+")");
	}
	
	

}
