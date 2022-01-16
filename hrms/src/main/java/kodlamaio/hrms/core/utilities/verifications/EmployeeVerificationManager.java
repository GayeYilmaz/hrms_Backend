package kodlamaio.hrms.core.utilities.verifications;


import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.Employer;
@Service
public class EmployeeVerificationManager implements EmployeeVerificationService {

	@Override
	public boolean isVerifiedByEmployee(Employer employer) {
		System.out.println(employer.getCompanyName()+" verified by employee");
		return true;
	}

	

	

}
