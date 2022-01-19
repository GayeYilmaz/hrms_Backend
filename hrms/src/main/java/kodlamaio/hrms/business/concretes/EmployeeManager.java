package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeConfirmDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeConfirmEmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.EmployeeConfirm;
import kodlamaio.hrms.entities.concretes.EmployeeConfirmEmployer;

@Service
public class EmployeeManager implements EmployeeService{
	private EmployeeDao employeeDao;
	private EmployeeConfirmEmployerDao employeeConfirmEmployerDao;

	@Autowired
	public EmployeeManager(EmployeeDao employeeDao,EmployeeConfirmEmployerDao employeeConfirmEmployerDao) {
		super();
		this.employeeDao = employeeDao;
		this.employeeConfirmEmployerDao=employeeConfirmEmployerDao;
	}


	@Override
	public DataResult<List<Employee>> getAll() {
	
		return new SuccessDataResult<List<Employee>>( this.employeeDao.findAll(),"Data listed");
	}


	@Override
	public Result verify(int employeeId, int employerId) {
		
		if(this.employeeConfirmEmployerDao.getByEmployerId(employerId).isConfirmed()) {
			return new ErrorResult("Employer already confirmed by another employee!");
		}
		
		EmployeeConfirm employeeConfirm = this.employeeConfirmEmployerDao.getByEmployerId(employerId);
		LocalDate todayDate =(LocalDate.now());
		employeeConfirm.setConfirmDate(todayDate);
		employeeConfirm.setConfirmed(true);
		employeeConfirm.setEmployeeId(employeeId);
		EmployeeConfirmEmployer employeeConfirmEmployer =
				new EmployeeConfirmEmployer(employeeConfirm.getId(),employeeConfirm.getEmployeeId(),
						true,todayDate,employerId);
		this.employeeConfirmEmployerDao.save(employeeConfirmEmployer);
			
		
		return new SuccessResult("This employer"+employerId+"confirmed by this employee"+employeeId+"!");
	}


	@Override
	public Result add(Employee employee) {
		this.employeeDao.save(employee);
		return new SuccessResult("Employee registered!");
	}
	
	

}
