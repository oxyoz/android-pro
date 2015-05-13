package com.config.api;

/**
 * 接口集合
 * 
 * @author oz
 * 
 */

public class API {

	/*
	 * root URL
	 */
	private static String Basic_Url = "http://192.168.173.1:8080/EnrollSystem/";

	public static void setBasic_Url(String ip) {

		Basic_Url = "http://" + ip + ":8080/EnrollSystem/";

	}

	public static String getBasic_Url() {

		return Basic_Url;
	}

	/**
	 * 登录api
	 * 
	 * @author oz
	 * 
	 */
	public static final class login {

		/**
		 * 返回以Json格式的用户个人信息数据和 以Json返回添加状态，其中包括status（状态）
		 * 和message(提示消息)，status值为1， message值为“***成功”，则表示该操作成功；
		 * status值为0，message值为“***失败”， 则表示该操作失败
		 **/
		public static String login_api(String userName, String password) {

			String api = Basic_Url + "userActionAndroid.action?"
					+ "userInfo.userName=" + userName + "&"
					+ "userInfo.password=" + password;

			return api;

		}

	}

	/**
	 * 设置时间卡api
	 * 
	 * @author oz
	 * 
	 */
	public static final class setTime {

		// 返回所有时间卡的信息
		public static String showAllTime_api() {
			String api = Basic_Url + "showSetTimeAndroid.action";
			return api;
		}

		// 添加时间卡
		public static String addTime_api(String startTime, String contact,
				String endTime) {
			String api = Basic_Url + "System/addTimeAndroid.action?"
					+ "setTime.startTime=" + startTime + "&"
					+ "setTime.contact=" + contact + "&" + "setTime.endTime="
					+ endTime;
			return api;
		}

		// 添加时间卡
		public static String addTime_api() {
			String api = Basic_Url + "System/addTimeAndroid.action";

			return api;
		}

		// 时间卡更新
		public static String timeUpdate_api(String tid) {
			String api = Basic_Url + "System/System/updateSetTime.action?"
					+ "setTime.tid=" + tid;
			return api;
		}

		// 修改时间卡
		public static String updateTime_api(String startTime, String contact,
				String endTime, String year) {
			String api = Basic_Url + "System/updateTimeAndroid.action?"
					+ "setTime.startTime=" + startTime + "&"
					+ "setTime.contact=" + contact + "&" + "setTime.endTime="
					+ endTime + "&" + "setTime.endTime=" + year;
			return api;
		}

		// 修改时间卡
		public static String updateTime_api() {

			String api = Basic_Url + "System/updateTimeAndroid.action";

			return api;

		}

		// 删除时间卡
		public static String deleteTime_api(String tid) {
			String api = Basic_Url + "System/deleteSetTimeAndroid.action?"
					+ "setTime.tid=" + tid;
			return api;
		}

	}

	/**
	 * 反馈信息管理
	 * 
	 * @author oz
	 * 
	 */
	public static final class feedbackInfo {

		// 返回搜索到的反馈信息
		public static String findBySystem_api(String examineeNumber) {
			String api = Basic_Url + "findBySystemAndroid.action?"
					+ "examineeNumber=" + examineeNumber;
			return api;
		}

		// 更改生源性质
		public static String updateStuPro_api(String stuId, String property) {
			String api = Basic_Url + "updateStuProAndroid.action?" + "stuId="
					+ stuId + "&" + "property=" + property;
			return api;
		}

		// 删除反馈信息
		public static String deleteFeedback_api(String stuId) {
			String api = Basic_Url + "delfeedbackAndroid.action?" + "stuId="
					+ stuId;
			return api;
		}

	}

	/**
	 * 重置密码
	 * 
	 * @author oz
	 * 
	 */
	public static final class resetPassword {

		// 所有用户的信息
		public static String userInfoAction_api() {
			String api = Basic_Url + "System/showUserInfoActionAndroid.action";
			return api;
		}

		// 搜索单个用户信息
		public static String singleUserInfoAction_api(String userName) {

			String api = Basic_Url + "showUserInfoActionAndroid.action?"
					+ "userName=" + userName;
			return api;

		}

		// 重置密码
		public static String updatePassowrd_api(String userId) {
			String api = Basic_Url + "updatePassowrdActionAndroid.action?"
					+ "userInfo.userId=" + userId;
			return api;
		}

	}

	/**
	 * 添加学生信息
	 * 
	 * @author oz
	 * 
	 */
	public static final class addStudentInfo {

		// 查找所有的省
		public static String getPrivnce_api() {

			String api = Basic_Url + "getPrivnceAndroid.action";

			return api;

		}

		// 通过省的Id查找该省所有的地区
		public static String getNetherlands_api(String provinceId) {
			String api = Basic_Url + "getNetherlandsAndroid.action?" + "id="
					+ provinceId;
			return api;
		}

		// 通过地区Id查找该地区所有的城市
		public static String getCounty_api(String netherlandsId) {
			String api = Basic_Url + "getCountyAndroid.action?" + "id="
					+ netherlandsId;
			return api;
		}

		// 通过城市的Id查找该城市所有的学校
		public static String getSchool_api(String countyId) {
			String api = Basic_Url + "getSchoolAndroid.action?" + "id="
					+ countyId;
			return api;
		}

		// 查找所有生源信息
		public static String finditall_api() {
			String api = Basic_Url + "finditallAndroid.action";
			return api;
		}

		// 省内默认添加学生信息
		public static String addStudentInfo_api(String examineeNumber,
				String stuName, String flag, String schoolId,
				String attendProfessional, String idCard, String tel,
				String idNumber, String voluntarily, String sex, String property) {

			String api = Basic_Url + "addStudentInfoAndroid.action?"
					+ "feedbackInfo.examineeNumber=" + examineeNumber + "&"
					+ "feedbackInfo.stuName=" + stuName + "&"
					+ "feedbackInfo.flag=" + flag + "&"
					+ "feedbackInfo.schoolId=" + schoolId + "&"
					+ "feedbackInfo.attendProfessional=" + attendProfessional
					+ "&" + "feedbackInfo.idCard=" + idCard + "&"
					+ "feedbackInfo.tel=" + tel + "&"
					+ "feedbackInfo.idNumber=" + idNumber + "&"
					+ "feedbackInfo.voluntarily=" + voluntarily + "&"
					+ "feedbackInfo.sex=" + sex + "&"
					+ "feedbackInfo.property=" + property;

			return api;
		}

		// 省内默认添加学生信息
		public static String addStudentInfo_api() {

			String api = Basic_Url + "addStudentInfoAndroid.action";

			return api;
		}

		// 省内其他添加学生信息
		public static String addStudentInfo_api(String examineeNumber,
				String stuName, String flag, String schoolId,
				String attendProfessional, String idCard, String tel,
				String idNumber, String voluntarily, String sex,
				String property, String province, String netherlands,
				String county) {
			String api = Basic_Url + "addStudentInfoAndroid.action?"
					+ "feedbackInfo.examineeNumber=" + examineeNumber + "&"
					+ "feedbackInfo.stuName=" + stuName + "&"
					+ "feedbackInfo.flag=" + flag + "&"
					+ "feedbackInfo.schoolId=" + schoolId + "&"
					+ "feedbackInfo.attendProfessional=" + attendProfessional
					+ "&" + "feedbackInfo.idCard=" + idCard + "&"
					+ "feedbackInfo.tel=" + tel + "&"
					+ "feedbackInfo.idNumber=" + idNumber + "&"
					+ "feedbackInfo.voluntarily=" + voluntarily + "&"
					+ "feedbackInfo.sex=" + sex + "&"
					+ "feedbackInfo.property=" + property + "&" + "province="
					+ province + "&" + "netherlands=" + netherlands + "&"
					+ "county=" + county;

			return api;
		}

		// 省外添加学生信息
		public static String addInOutsize_api(String examineeNumber,
				String stuName, String schoolName, String attendProfessional,
				String idCard, String tel, String idNumber, String voluntarily,
				String sex, String property, String province,
				String netherlands, String county) {
			String api = Basic_Url + "addInOutsizeAndroid.action?"
					+ "feedbackInfo.examineeNumber=" + examineeNumber + "&"
					+ "feedbackInfo.stuName=" + stuName + "&" + "schoolName="
					+ schoolName + "&" + "feedbackInfo.attendProfessional="
					+ attendProfessional + "&" + "feedbackInfo.idCard="
					+ idCard + "&" + "feedbackInfo.tel=" + tel + "&"
					+ "feedbackInfo.idNumber=" + idNumber + "&"
					+ "feedbackInfo.voluntarily=" + voluntarily + "&"
					+ "feedbackInfo.sex=" + sex + "&"
					+ "feedbackInfo.property=" + property + "&" + "province="
					+ province + "&" + "netherlands=" + netherlands + "&"
					+ "county=" + county;
			return api;
		}

		// 省外添加学生信息
		public static String addInOutsize_api() {
			String api = Basic_Url + "addInOutsizeAndroid.action";

			return api;
		}

		// 获取省内添加学生信息的学校和专业
		public static String preAdd_api() {

			String api = Basic_Url + "preAddAndroid.action";
			return api;

		}

		// 获取省外添加学生信息的专业
		public static String preAddOutsize_api() {

			String api = Basic_Url + "preAddOutsizeAndroid.action";
			return api;

		}

		// 获取添加学生信息的生源性质，返回Status（0/1）、Message（property）
		public static String getProperty_api() {

			String api = Basic_Url + "preAddTimeAndroid.action";
			return api;

		}

		// 检查考生号是否已录入和是否正确,返回Status（0/1）、Message
		public static String check_api(String examineeNumber) {

			String api = Basic_Url + "checkAndroid.action?" + "examineeNumber="
					+ examineeNumber;
			return api;

		}

		// 检查学校是否被其他老师已选,返回Status（0/1）、Message
		public static String checkSchool_api(String schoolId) {

			String api = Basic_Url + "checkSchoolAndroid.action?" + "schoolId="
					+ schoolId + "&" + "property=" + "非专职招生人员";
			return api;

		}

		// 学生生源信息修改
		public static String updatef_api(String examineeNumber, String stuName,
				String idCard, String sex, String schoolName, String tel,
				String attendProfessional, String property,
				String enteringTime, String voluntarily) {

			String api = Basic_Url + "updatef.action?"
					+ "feedbackInfo.examineeNumber=" + examineeNumber + "&"
					+ "feedbackInfo.stuName=" + stuName + "&"
					+ "feedbackInfo.schoolName=" + schoolName + "&"
					+ "feedbackInfo.attendProfessional=" + attendProfessional
					+ "&" + "feedbackInfo.idCard=" + idCard + "&"
					+ "feedbackInfo.tel=" + tel + "&"
					+ "feedbackInfo.voluntarily=" + voluntarily + "&"
					+ "feedbackInfo.sex=" + sex + "&"
					+ "feedbackInfo.enteringTime=" + enteringTime + "&"
					+ "feedbackInfo.property=" + property;
			return api;

		}

	}

}
