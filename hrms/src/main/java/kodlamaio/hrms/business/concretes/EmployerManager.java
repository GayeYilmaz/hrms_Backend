package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.EmailVerificationManager;
import kodlamaio.hrms.core.EmailVerificationService;
import kodlamaio.hrms.core.EmployeeVerificationService;
import kodlamaio.hrms.core.VerificationService;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class EmployerManager implements EmployerService{
	private EmployerDao employerDao;
	private EmployeeVerificationService employeeVerificationService;
	private EmailVerificationService emailVerificationService;
	
	

	@Autowired
	public EmployerManager(EmployerDao employerDao,EmployeeVerificationService employeeVerificationService, EmailVerificationService emailVerificationService) {
		super();
		this.employerDao = employerDao;
		this.employeeVerificationService=employeeVerificationService;
		this.emailVerificationService=emailVerificationService;
	}


	@Override
	public List<Employer> getAll() {
		// TODO Auto-generated method stub
		return this.employerDao.findAll();
	}


	@Override
	public void add(Employer employer) {
		if(isNull(employer)==false) {
			System.out.println("All fields are obligatory!");
		}
		else if(checkEmail(employer.getEmail())==false) {
			System.out.println("Email is already used!");
		}
		else {
			
            if(this.emailVerificationService.emailVerifcation(employer.getEmail())==true && this.employeeVerificationService.isVerifiedByEmployee(employer)==true ) {
            	this.employerDao.save(employer);
    			System.out.println(employer.getCompanyName()+" registered!");
            }
			
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
