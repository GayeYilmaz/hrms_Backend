package kodlamaio.hrms.core;

import kodlamaio.hrms.entities.concretes.Employer;


public interface EmployeeVerificationService {
	boolean isVerifiedByEmployee(Employer employer);
     
}
