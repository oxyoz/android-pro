package com.oz.m;

public class StudentInfo  extends Entity{
	private String examineeNumber;
	private String stuName;
	private String flag;
	private String schoolId;
	private String attendProfessional;
	private String idCard;
	private String tel;
	private String idNumber;
	private String voluntarily;
	private String sex;
	private String property;
	private String province;
	private String netherlands;
	private String county;
	private String schoolName;
	private String enteringTime;
	
	
	public String getEnteringTime() {
		return enteringTime;
	}

	public void setEnteringTime(String enteringTime) {
		this.enteringTime = enteringTime;
	}

	//缺省构造函数
	public StudentInfo() {

	}

	
	//默认省内添加设置参数
		public StudentInfo(String examineeNumber,
				String stuName,
				String flag,
				String schoolId,
				String attendProfessional,
				String idCard,
				String tel,
				String idNumber,
				String voluntarily,
				String sex,
				String property) {

			this.examineeNumber = examineeNumber;
			this.stuName = stuName;
			this.flag = flag;
			this.schoolId = schoolId;
			this.attendProfessional = attendProfessional;
			this.idCard = idCard;
			this.tel = tel;
			this.idNumber = idNumber;
			this.voluntarily = voluntarily;
			this.sex = sex;
			this.property = property;
		}
		
	
	
	//其他省内添加设置参数
	public StudentInfo(String examineeNumber,
			String stuName,
			String flag,
			String schoolId,
			String attendProfessional,
			String idCard,
			String tel,
			String idNumber,
			String voluntarily,
			String sex,
			String property,
			String province,
			String netherlands,
			String county) {

		this.examineeNumber = examineeNumber;
		this.stuName = stuName;
		this.flag = flag;
		this.schoolId = schoolId;
		this.attendProfessional = attendProfessional;
		this.idCard = idCard;
		this.tel = tel;
		this.idNumber = idNumber;
		this.voluntarily = voluntarily;
		this.sex = sex;
		this.property = property;
		this.province = province;
		this.netherlands = netherlands;
		this.county = county;
	}
	
	//省外添加设置参数
		public StudentInfo(
				String examineeNumber,
				String stuName,
				String schoolName,
				String attendProfessional,
				String idCard,
				String tel,
				String idNumber,
				String voluntarily,
				String sex,
				String property,
				String province,
				String netherlands,
				String county) {

			this.examineeNumber = examineeNumber;
			this.stuName = stuName;
			this.attendProfessional = attendProfessional;
			this.idCard = idCard;
			this.tel = tel;
			this.idNumber = idNumber;
			this.voluntarily = voluntarily;
			this.sex = sex;
			this.property = property;
			this.province = province;
			this.netherlands = netherlands;
			this.county = county;
			this.schoolName = schoolName;
		}
	
		
		//修改用户信息设置参数
		public StudentInfo(
				 String examineeNumber,
				 String stuName,
				 String idCard,
				 String sex,
				 String schoolName,
				 String tel,
				 String attendProfessional,
				 String property,
				 String enteringTime,
				 String voluntarily) {

			this.examineeNumber = examineeNumber;
			this.stuName = stuName;
			this.attendProfessional = attendProfessional;
			this.idCard = idCard;
			this.tel = tel;
			this.voluntarily = voluntarily;
			this.sex = sex;
			this.property = property;
			this.schoolName = schoolName;
			this.enteringTime=enteringTime;
		}

		
	//把所有参数设为null
	public void setEmptyAllProperty() {

		this.examineeNumber = null;
		this.stuName = null;
		this.flag = null;
		this.schoolId = null;
		this.attendProfessional = null;
		this.idCard = null;
		this.tel = null;
		this.idNumber = null;
		this.voluntarily = null;
		this.sex = null;
		this.property = null;
		this.province = null;
		this.netherlands = null;
		this.county = null;
		this.schoolName = null;
		
	}
	
	
	public String getExamineeNumber() {
		return examineeNumber;
	}

	public void setExamineeNumber(String examineeNumber) {
		this.examineeNumber = examineeNumber;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getAttendProfessional() {
		return attendProfessional;
	}

	public void setAttendProfessional(String attendProfessional) {
		this.attendProfessional = attendProfessional;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getVoluntarily() {
		return voluntarily;
	}

	public void setVoluntarily(String voluntarily) {
		this.voluntarily = voluntarily;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getNetherlands() {
		return netherlands;
	}

	public void setNetherlands(String netherlands) {
		this.netherlands = netherlands;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	
	
	
	
}
