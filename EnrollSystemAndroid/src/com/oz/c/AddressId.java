package com.oz.c;

public class AddressId {
	//ʡid
	private String pId = null;
	//����id
	private String nId = null;
	//����id
	private String cId = null;
	//ѧУid
	private String schoolId = null;
	
	private AddressId() {
		
	}
	
	private static AddressId addressId = null;
	
	
	public void setAddressIdEmpty()
	{
		
		addressId = null;
		
	}
	
	public static AddressId getInstance()
	{
		
		if(addressId == null)
		{
			
			addressId = new AddressId();
			
		}
		
		return addressId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getnId() {
		return nId;
	}

	public void setnId(String nId) {
		this.nId = nId;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public static AddressId getAddressId() {
		return addressId;
	}

	public static void setAddressId(AddressId addressId) {
		AddressId.addressId = addressId;
	}
	
	
	
	
}
