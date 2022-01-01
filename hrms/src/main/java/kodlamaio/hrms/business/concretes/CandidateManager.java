package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.EmailVerificationService;
import kodlamaio.hrms.core.VerificationService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService{
	private CandidateDao candidateDao;
	private EmailVerificationService emailVerificationService;
	private VerificationService verificationService;
	

	@Autowired
	public CandidateManager(CandidateDao candidateDao,EmailVerificationService emailVerificationService,VerificationService verificationService) {
		super();
		this.candidateDao = candidateDao;
		this.emailVerificationService = emailVerificationService;
		this.verificationService = verificationService;
	}


	@Override
	public List<Candidate> getAll() {
		// TODO Auto-generated method stub
		return this.candidateDao.findAll();
	}


	@Override
	public void add(Candidate candidate) {
		// TODO Auto-generated method stub
		if(isNull(candidate)==false) {
			System.out.println("All fields are obligatory!");
		}
		else if(checkEmail(candidate.getEmail())==false) {
			System.out.println("Email is already used!");
		}
		else if(checkIdentityNumber(candidate.getIdentityNumber())==false) {
			System.out.println("Identity number already used");
		}
		else {
			if(this.verificationService.verify(candidate.getFirstName(), candidate.getLastName(), candidate.getIdentityNumber(), candidate.getBirthYear())==true) {
				if(this.emailVerificationService.emailVerifcation(candidate.getEmail())==true) {
					this.candidateDao.save(candidate);
					System.out.println(candidate.getFirstName()+" "+candidate.getLastName()+" registered!");
				}
				}
			}
			
		
		
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
	
	
}
