package kodlamaio.hrms.core.utilities.verifications;

import kodlamaio.hrms.entities.concretes.Employer;


public interface EmployeeVerificationService {
	boolean isVerifiedByEmployee(Employer employer);
     
}
