package com.instabank.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.NonNull;

public class InstaClient {
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	@UniqueElements
	@Min(13)
	@Max(13)
	private long identity_Number;
	@UniqueElements
	private long mobile_Number;
	private String physical_Address;
	
	public InstaClient(String firstName, String lastName, long identity_Number, long mobile_Number,
			String physical_Address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.identity_Number = identity_Number;
		this.mobile_Number = mobile_Number;
		this.physical_Address = physical_Address;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getIdentity_Number() {
		return identity_Number;
	}
	public void setIdentity_Number(long identity_Number) {
		this.identity_Number = identity_Number;
	}
	public long getMobile_Number() {
		return mobile_Number;
	}
	public void setMobile_Number(long mobile_Number) {
		this.mobile_Number = mobile_Number;
	}
	public String getPhysical_Address() {
		return physical_Address;
	}
	public void setPhysical_Address(String physical_Address) {
		this.physical_Address = physical_Address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (identity_Number ^ (identity_Number >>> 32));
		result = prime * result + (int) (mobile_Number ^ (mobile_Number >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstaClient other = (InstaClient) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (identity_Number != other.identity_Number)
			return false;
		if (mobile_Number != other.mobile_Number)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "InstaClient [firstName=" + firstName + ", lastName=" + lastName + ", identity_Number=" + identity_Number
				+ ", mobile_Number=" + mobile_Number + ", physical_Address=" + physical_Address + "]";
	}
	
	
	
	

}
