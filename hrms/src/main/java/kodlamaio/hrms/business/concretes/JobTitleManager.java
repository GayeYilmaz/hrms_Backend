package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService{
	
	private JobTitleDao jobTitleDao;
	
	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	

	@Override
	public List<JobTitle> getAll() {
		
		return this.jobTitleDao.findAll();
	}



	@Override
	public void add(JobTitle jobTitle) {
		if(checkJobTitle(jobTitle.getTitle())==true) {
			System.out.println(jobTitle.getTitle()+" added.");
			this.jobTitleDao.save(jobTitle);
		}else {
			System.out.println("Job title is already in database.");
		}
		
		
	}
	
	public boolean checkJobTitle(String title) {
		List<JobTitle> jobTitleInDb = this.jobTitleDao.findAll();
		for(JobTitle jobTitle : jobTitleInDb) {
			if(jobTitle.getTitle().equals(title)){
				return false;
			}
		}
		return true;
		
	}

}
