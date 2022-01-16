package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;


import kodlamaio.hrms.core.utilities.verifications.EmailVerificationService;
import kodlamaio.hrms.core.utilities.verifications.EmployeeVerificationService;

import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeEmployerDao;

import kodlamaio.hrms.entities.concretes.Employer;

import kodlamaio.hrms.entities.concretes.VerificationCode;

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
		if(isNull(employer)==false) {
			return new ErrorResult("All fields are obligatory!");
			
		}
		if(checkEmail(employer.getEmail())==false) {
			return new ErrorResult("Email is already used!");
			
		}else {
			this.employerDao.save(employer);
			VerificationCode verificationCode = this.emailVerificationService.sendEmail(employer).getData();
			//this.verificationCodeCandidateDao.save(verificationCode,candidate.getId());
			
		}
		
			
	
		return new ErrorResult("There is an error control your fields!");
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
