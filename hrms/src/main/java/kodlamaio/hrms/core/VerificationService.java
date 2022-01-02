package kodlamaio.hrms.core;

import java.rmi.RemoteException;

public interface VerificationService {
	boolean verify(String firstName,String lastName,String idendityNumber,int birtYear);

}
