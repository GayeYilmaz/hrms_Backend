package kodlamaio.hrms.core;


import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.User;
@Service
public class EmailVerificationManager implements EmailVerificationService{

	@Override
	public boolean emailVerifcation(String email) {
		System.out.println("Verification mail send to "+email);
		return true;
	}

	
}
