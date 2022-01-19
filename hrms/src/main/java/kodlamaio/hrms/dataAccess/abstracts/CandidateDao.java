package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Candidate;


public interface CandidateDao extends JpaRepository<Candidate,Integer>{
	//For checking if the identity number is in database
	Candidate getByIdentityNumber(String identyNumber);
	
	//For checking if the email is in database

}
