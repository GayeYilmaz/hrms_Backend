package kodlamaio.hrms.core;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisManagerAdapter implements VerificationService{

	@Override
	public boolean verify(String firstName, String lastName, String idendityNumber, int birtYear) {
		boolean result=true;
		KPSPublicSoapProxy kpsPublic =  new  KPSPublicSoapProxy();
		
		try {
			 result = kpsPublic.TCKimlikNoDogrula(Long.parseLong(idendityNumber), firstName,lastName, birtYear);
		} catch (NumberFormatException | RemoteException e) {
			// TODO Auto-generated catch block
			result=false;
			e.printStackTrace();
			
		}
		System.out.println("Doğrulama : "+(result ? "Başarılı Giriş":"Başarısız Giriş"));
		return result;
		
	}



}
