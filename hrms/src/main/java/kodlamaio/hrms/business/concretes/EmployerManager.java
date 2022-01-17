package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifications.EmailVerificationService;
import kodlamaio.hrms.core.utilities.verifications.EmployeeVerificationService;

import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeEmployerDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;

import kodlamaio.hrms.entities.concretes.VerificationCode;
import kodlamaio.hrms.entities.concretes.VerificationCodeCandidate;
import kodlamaio.hrms.entities.concretes.VerificationCodeEmployer;

@Service
public class EmployerManager implements EmployerService{
	private EmployerDao employerDao;
	private EmployeeVerificationService employeeVerificationService;
	private EmailVerificationService emailVerificationService;
	private VerificationCodeDao verificationCodeDao;
	private VerificationCodeEmployerDao verificationCodeEmployerDao;
	

	@Autowired
	public EmployerManager(EmployerDao employerDao,EmployeeVerificationService employeeVerificationService, 
			EmailVerificationService emailVerificationService, VerificationCodeEmployerDao verificationCodeEmployerDao,
			VerificationCodeDao verificationCodeDao) {
		super();
		this.employerDao = employerDao;
		this.employeeVerificationService=employeeVerificationService;
		this.emailVerificationService=emailVerificationService;
		this.verificationCodeDao = verificationCodeDao;
		this.verificationCodeEmployerDao = verificationCodeEmployerDao;
	}


	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Data listed!");
	}


	@Override
	public Result add(Employer employer) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(employer.getEmail());
		if(isNull(employer)==false) {
			return new ErrorResult("All fields are obligatory!");
			
		}
		if(checkEmail(employer.getEmail())==false || m.find()==false) {
			return new ErrorResult("Email is already used or the format of email is wrong!");
			
		}else {
			this.employerDao.save(employer);
			Employer empDB = employerDao.getByEmail(employer.getEmail());
			VerificationCode verificationCode = this.emailVerificationService.sendEmail(employer).getData();
			VerificationCodeEmployer verificationCodeEmployer = 
					new VerificationCodeEmployer(0,verificationCode.getCode(),false,null,empDB.getId());
			this.verificationCodeEmployerDao.save(verificationCodeEmployer);
			return new SuccessResult("Check your email for the validation code to complete your registeration!");
			//this.verificationCodeCandidateDao.save(verificationCode,candidate.getId());
			
		}
		
			
	
		
	}
	
	//Check fields if they are null
		public  boolean isNull(Employer employer) {
			//Check first name
			if(employer.getCompanyName().equals("")) {
				return false;
			}
			//Check last name
			else if(employer.getPhoneNumber().equals("")) {
				return false;
			}
			//Check identity number
			else if(employer.getWebAddress().equals("")) {
				return false;
			}
			//Check email
			else if(employer.getEmail().equals("")) {
				return false;
			}
			//Check password
			else if(employer.getPassword().equals("")) {
				return false;
			}
			else {
				return true;	
			}
			
			
			
		}
		

		//Checks email if it is already used
		public  boolean checkEmail(String email) {
			List<Employer> employerInDb = this.employerDao.findAll();
			for(Employer employer : employerInDb) {
				if(employer.getEmail().equals(email)){
					return false;
				}
			}
			return true;

		}

}
