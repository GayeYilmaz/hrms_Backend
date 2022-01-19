package kodlamaio.hrms.entities.concretes;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@PrimaryKeyJoinColumn(name="id", referencedColumnName = "id")
@Table(name="employee_confirm_employers")
public class EmployeeConfirmEmployer extends EmployeeConfirm{
	
	@Column(name="employer_id")
	private int employerId;
	
	public EmployeeConfirmEmployer(int id,int employeeId,boolean isConfirmed,LocalDate confirmDate,int employerId) {
		super(id,employeeId,isConfirmed,confirmDate);
		this.employerId=employerId;
	}

}
