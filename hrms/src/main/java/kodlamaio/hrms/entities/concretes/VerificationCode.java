package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="verification_codes")
public class VerificationCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore
	private int id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="is_verified")
	private boolean isVerified;
	
	@Column(name="verified_date")
	private LocalDate verifiedDate; 
	
	//@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY,optional=false)
	//@JoinColumn(name="user_id",referencedColumnName="id",nullable=false)
	//@JsonIgnore
	//private User user;

}
