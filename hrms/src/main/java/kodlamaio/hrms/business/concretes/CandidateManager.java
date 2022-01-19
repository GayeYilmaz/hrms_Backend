package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifications.EmailVerificationService;
import kodlamaio.hrms.core.utilities.verifications.VerificationService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeCandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.VerificationCode;
import kodlamaio.hrms.entities.concretes.VerificationCodeCandidate;

@Service
public class CandidateManager implements CandidateService{
	private CandidateDao candidateDao;
	private EmailVerificationService emailVerificationService;
	private VerificationCodeCandidateDao verificationCodeCandidateDao;
	private VerificationService verificationService;
	private VerificationCodeDao verificationCodeDao;

	@Autowired
	public CandidateManager(CandidateDao candidateDao,EmailVerificationService emailVerificationService,VerificationCodeCandidateDao verificationCodeCandidateDao
			,VerificationService verificationService,VerificationCodeDao verificationCodeDao) {
		super();
		this.candidateDao = candidateDao;
		this.emailVerificationService = emailVerificationService;
		this.verificationCodeCandidateDao = verificationCodeCandidateDao;
		this.verificationService=verificationService;
		this.verificationCodeDao =  verificationCodeDao;
	}


	@Override
	public DataResult<List<Candidate>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Candidate>>( this.candidateDao.findAll(),"Data listed");
	}


	@Override
	public Result add(Candidate candidate) {

		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(candidate.getEmail());
		if(isNull(candidate)==false) {
			return new ErrorResult("All fields are obligatory!");
			
		}
		if(!checkPassword(candidate.getPassword(),candidate.getPasswordRepeat()) ){
			return new ErrorResult("Repeated password is wrong!");
		}
		if(checkEmail(candidate.getEmail())==false  || m.find()==false) {
			return new ErrorResult("Email is already used or the format of email is wrong!");
			
		}
		if(!((getByIdentityNumber(candidate.getIdentityNumber()).getData())==null)) {
			return new ErrorResult("Identity number already used");
			
		}
		else {
			if(this.verificationService.verify(candidate.getFirstName(), candidate.getLastName(), candidate.getIdentityNumber(), candidate.getBirthYear())==true) {
				this.candidateDao.save(candidate);
				Candidate canDB = candidateDao.getByIdentityNumber(candidate.getIdentityNumber());
				VerificationCode verificationCode = this.emailVerificationService.sendEmail(candidate).getData();
				VerificationCodeCandidate verificationCodeCandidate = 
						new VerificationCodeCandidate(0,verificationCode.getCode(),false,null,canDB.getId());
				this.verificationCodeCandidateDao.save(verificationCodeCandidate);
				return new SuccessResult("Check your email for the validation code to complete your registeration!");
				}
			}
			
		return new ErrorResult("These informations are not belong any person!");
		
	}
	

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identiyNumber) {
		// TODO Auto-generated method stub
		
		return new SuccessDataResult<Candidate>(this.candidateDao.getByIdentityNumber(identiyNumber));
	}

	//Check fields if they are null
	public  boolean isNull(Candidate candidate) {
		//Check first name
		if(candidate.getFirstName().equals("")) {
			return false;
		}
		//Check last name
		if(candidate.getLastName().equals("")) {
			return false;
		}
		//Check identity number
		if(candidate.getIdentityNumber().equals("")) {
			return false;
		}
		//Check email
		if(candidate.getEmail().equals("")) {
			return false;
		}
		//Check password
		if(candidate.getPassword().equals("")) {
			return false;
		}
		//Check birth year
		if(candidate.getBirthYear()==0) {
			return false;
		}
		
		return true;	
	}
	
	//Checks email if it is already used
	public  boolean checkEmail(String email) {
		List<Candidate> candidateInDb = this.candidateDao.findAll();
		for(Candidate candidate : candidateInDb) {
			if(candidate.getEmail().equals(email)){
				return false;
			}
		}
		return true;

	}
	
	//Checks identity number if it is already used
	public  boolean checkIdentityNumber(String identityNumber) {
		List<Candidate> candidateInDb = this.candidateDao.findAll();
		for(Candidate candidate : candidateInDb) {
			if(candidate.getIdentityNumber().equals(identityNumber)){
				return false;
			}
		}
		return true;

	}


	@Override
	public Result verifyByEmail(String code) {
		
		if(!this.verificationCodeDao.existsByCode(code)) {
			return new ErrorResult("You did wrong verification!");
		}
		VerificationCode newVerificationCode = verificationCodeDao.findByCode(code);
		if(this.verificationCodeDao.getOne(newVerificationCode.getId()).isVerified()) {
			return new ErrorResult("Verification done already!");
		}
		LocalDate todayDate =(LocalDate.now());
		newVerificationCode.setVerifiedDate(todayDate);
		newVerificationCode.setVerified(true);
	    this.verificationCodeDao.save(newVerificationCode);
	
		return new SuccessResult("Verification done!");
	}

	//Check the two password
	public boolean checkPassword(String password,String password_repeat) {
		if(password.equals(password_repeat)){
			return true;
		}
		return false;
	}


	
	
}
