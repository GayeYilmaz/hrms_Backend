package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService{
	private CandidateDao candidateDao;
	

	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;
	}


	@Override
	public List<Candidate> getAll() {
		// TODO Auto-generated method stub
		return this.candidateDao.findAll();
	}


	@Override
	public void add(Candidate candidate) {
		// TODO Auto-generated method stub
		this.candidateDao.save(candidate);
		
	}

}
