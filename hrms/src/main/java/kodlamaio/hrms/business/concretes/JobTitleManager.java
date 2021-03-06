package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
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
	public DataResult<List<JobTitle>> getAll() {
		
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(),"Data listed!");
	}



	@Override
	public Result add(JobTitle jobTitle) {
		if(checkJobTitle(jobTitle.getTitle())==true) {
			this.jobTitleDao.save(jobTitle);
			return new SuccessResult(jobTitle.getTitle()+" added.");
			
		}
			
			
		return new ErrorResult("Job title is already in database.");
		
	}
	
	public boolean checkJobTitle(String title) {
		List<JobTitle> jobTitleInDb = this.jobTitleDao.findAll();
		for(JobTitle jobTitle : jobTitleInDb) {
			if(jobTitle.getTitle().equalsIgnoreCase(title)){
				return false;
			}
		}
		return true;
		
	}

}
