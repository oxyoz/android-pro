package com.oz.c;

public class AddressId {
	//省id
	private String pId = null;
	//地区id
	private String nId = null;
	//城市id
	private String cId = null;
	//学校id
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
