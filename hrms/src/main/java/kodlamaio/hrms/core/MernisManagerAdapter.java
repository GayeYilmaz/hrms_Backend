package kodlamaio.hrms.core;

import org.springframework.stereotype.Service;

@Service
public class MernisManagerAdapter implements VerificationService{

	@Override
	public boolean verify(String firstName, String lastName, String idendityNumber, int birtYear) {
		System.out.println(firstName +" "+lastName+" can register!");
		return true;
	}



}
