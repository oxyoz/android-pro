package com.oz.enroll.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.oz.bean.StatusMessage;
import com.oz.enroll.bean.County;
import com.oz.enroll.bean.EnteringTime;
import com.oz.enroll.bean.Entity;
import com.oz.enroll.bean.InputTime;
import com.oz.enroll.bean.ItAll;
import com.oz.enroll.bean.Major;
import com.oz.enroll.bean.Netherlands;
import com.oz.enroll.bean.Privnce;
import com.oz.enroll.bean.School;
import com.oz.enroll.bean.StaffSchool;

/*
 * JsonParser json语法解析器
 * 
 * 将所有json字符串解析为相应的实例
 * 
 * */

public class JsonParser {

	public static class SearchSchool {

		// 省实体的json解析器
		public static List<Privnce> privnceParser(String jsonString)
				throws JSONException {

			List<Privnce> list = new ArrayList<Privnce>();

			if (jsonString != null) {

				JSONArray jsonArray = new JSONArray(jsonString);

				if (jsonArray != null) {

					for (int i = 0; i < jsonArray.length(); i++) {

						JSONObject jsonObject = jsonArray.getJSONObject(i);

						list.add(new Privnce(jsonObject.getString("pname"),
								jsonObject.getString("pid"), jsonObject
										.getString("pcode")));

					}

				}
			}
			return list;
		}

		// 地区的实体的json解析器
		public static List<Netherlands> netherlandsParser(String jsonString)
				throws JSONException {
			List<Netherlands> list = new ArrayList<Netherlands>();

			if (jsonString != null) {

				JSONArray jsonArray = new JSONArray(jsonString);

				if (jsonArray != null) {

					for (int i = 0; i < jsonArray.length()
							&& jsonArray.length() != 0; i++) {

						JSONObject jsonObject = jsonArray.getJSONObject(i);

						list.add(new Netherlands(jsonObject.getString("nname"),
								jsonObject.getString("nid"), jsonObject
										.getString("ncode"), jsonObject
										.getString("pid")));

					}
				}
			}

			return list;
		}

		// 地区的实体的json解析器
		public static List<County> countyParser(String jsonString)
				throws JSONException {
			List<County> list = new ArrayList<County>();

			if (jsonString != null) {

				JSONArray jsonArray = new JSONArray(jsonString);

				if (jsonArray != null) {

					for (int i = 0; i < jsonArray.length()
							&& jsonArray.length() != 0; i++) {

						JSONObject jsonObject = jsonArray.getJSONObject(i);

						list.add(new County(jsonObject.getString("cname"),
								jsonObject.getString("ccode"), jsonObject
										.getString("cid"), jsonObject
										.getString("nid")));
					}

				}
			}

			return list;
		}

		// 城市的实体的json解析器
		public static List<School> schoolParser(String jsonString)
				throws JSONException {
			List<School> list = new ArrayList<School>();

			if (jsonString != null) {

				JSONArray jsonArray = new JSONArray(jsonString);

				if (jsonArray != null) {

					for (int i = 0; i < jsonArray.length()
							&& jsonArray.length() != 0; i++) {

						JSONObject jsonObject = jsonArray.getJSONObject(i);

						list.add(new School(jsonObject.getString("schoolName"),
								jsonObject.getString("schoolCode"), jsonObject
										.getString("schoolId"), jsonObject
										.getString("cid")));

					}

				}
			}
			return list;
		}

	}

	public static class PreAddOut {

		// 获取省外添加学生信息的专业
		public static List<Major> preAddOutsizeParse(String jsonString)
				throws JSONException {
			List<Major> list = new ArrayList<Major>();

			JSONObject jsonObject = new JSONObject(jsonString);

			JSONArray jsonArray = jsonObject.getJSONArray("lsMajor");

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject object = jsonArray.getJSONObject(i);

				list.add(new Major(object.getString("isUsed"), object
						.getString("mid"), object.getString("majorCode"),
						object.getString("majorName")));

			}

			return list;
		}

	}

	public static class PreAdd {

		/*
		 * preAddParse 专业实体的json解析器
		 */

		public static List<? extends Entity> preAddParse(String jsonString,
				String type) throws JSONException {

			JSONObject jsonObject = new JSONObject(jsonString);

			if (type.equals("lsMajor")) {

				List<Major> list = null;

				list = new ArrayList<Major>();

				JSONArray jsonArray = jsonObject.getJSONArray(type);

				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject jsonMajor = jsonArray.getJSONObject(i);

					list.add(new Major(jsonMajor.getString("isUsed"), jsonMajor
							.getString("mid"),
							jsonMajor.getString("majorCode"), jsonMajor
									.getString("majorName")));

				}


				return list;

			} else if (type.equals("lsStaffSchool")) {
				List<StaffSchool> list = null;

				list = new ArrayList<StaffSchool>();

				JSONArray jsonArray = jsonObject.getJSONArray(type);

				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject jsonStaffSchool = jsonArray.getJSONObject(i);

					JSONObject jsonInputTime = jsonStaffSchool
							.getJSONObject("inputTime");

					InputTime inputTime = new InputTime(
							jsonInputTime.getString("nanos"),
							jsonInputTime.getString("time"),
							jsonInputTime.getString("minutes"),
							jsonInputTime.getString("seconds"),
							jsonInputTime.getString("hours"),
							jsonInputTime.getString("month"),
							jsonInputTime.getString("timezoneOffset"),
							jsonInputTime.getString("year"),
							jsonInputTime.getString("day"),
							jsonInputTime.getString("date"));

					list.add(new StaffSchool(jsonStaffSchool
							.getString("linkmanTel"), jsonStaffSchool
							.getString("contactId"), jsonStaffSchool
							.getString("schoolName"), jsonStaffSchool
							.getString("linkmanPost"), jsonStaffSchool
							.getString("userId"), jsonStaffSchool
							.getString("remarks"), jsonStaffSchool
							.getString("linkman"), jsonStaffSchool
							.getString("schoolId"), jsonStaffSchool
							.getString("cid"), inputTime));

				}

				return list;

			}

			return null;
		}

	}

	public static class Result {

		/*
		 * statusParser 消息状态解析器
		 */

		public static StatusMessage statusParser(String jsonString) {

			StatusMessage statusMessage = null;

			try {

				JSONObject jsonObject = new JSONObject(jsonString);

				if (jsonObject != null) {

					if (!jsonObject.isNull("status")
							&& !jsonObject.isNull("message")) {

						statusMessage = new StatusMessage();

						statusMessage.setStatus(jsonObject.getString("status"));

						statusMessage.setMessage(jsonObject
								.getString("message"));

						return statusMessage;

					}

					if (!jsonObject.isNull("state")
							&& !jsonObject.isNull("msg")) {

						statusMessage = new StatusMessage();

						statusMessage.setStatus(jsonObject.getString("state"));

						statusMessage.setMessage(jsonObject.getString("msg"));

						return statusMessage;

					}

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return statusMessage;
		}

	}

	public static class Check {

		/*
		 * checkExamineeNumberParser 考生号查询
		 */
		public static StatusMessage checkExamineeNumberParser(String jsonString) {

			StatusMessage statusMessage = null;

			if (jsonString != null) {

				try {

					JSONObject jsonObject = new JSONObject(jsonString);

					if (!jsonObject.isNull("userTel")
							&& !jsonObject.isNull("state")
							&& !jsonObject.isNull("userName")) {

						statusMessage = new StatusMessage();

						statusMessage.setStatus(jsonObject.getString("state"));

						statusMessage.setMessage("该考生号已存在，归"
								+ jsonObject.getString("userName") + "所有!"
								+ "联系电话：" + jsonObject.getString("userTel"));

						return statusMessage;
					}

				} catch (JSONException e) {

					e.printStackTrace();
				}

			}

			return statusMessage != null ? statusMessage : Result
					.statusParser(jsonString);

		}

		/*
		 * checkSchoolParser 学校查询
		 */

		public static StatusMessage checkSchoolParser(String jsonString) {

			StatusMessage statusMessage = null;

			if (jsonString != null) {
				try {

					JSONObject jsonObject = new JSONObject(jsonString);

					if (!jsonObject.isNull("userTel")
							&& !jsonObject.isNull("state")
							&& !jsonObject.isNull("userName")) {

						statusMessage = new StatusMessage();

						statusMessage.setStatus(jsonObject.getString("state"));

						statusMessage.setMessage("已经被"
								+ jsonObject.getString("userName") + "选择!"
								+ "联系电话：" + jsonObject.getString("userTel"));

						return statusMessage;

					}

				} catch (JSONException e) {

					e.printStackTrace();
				}

			}

			return statusMessage != null ? statusMessage : Result
					.statusParser(jsonString);
		}

	}

	public static class Property {

		/*
		 * propertyParser 学生性质解析器
		 */
		public static StatusMessage propertyParser(String jsonString) {

			StatusMessage statusMessage = null;

			if (jsonString != null) {

				try {

					JSONObject jsonObject = new JSONObject(jsonString);

					if (!jsonObject.isNull("isUsed")
							&& !jsonObject.isNull("property")) {

						statusMessage = new StatusMessage();

						statusMessage.setMessage(jsonObject
								.getString("property"));

						statusMessage.setStatus(jsonObject.getString("isUsed"));

					}

				} catch (JSONException e) {

					e.printStackTrace();
				}

			}

			return statusMessage != null ? statusMessage : Result
					.statusParser(jsonString);

		}

	}

	public static class ItAllParser {

		/*
		 * itAllParser 将包含所有生源信息json字符串转化为List<ItAll>对象
		 */

		public static List<ItAll> itAllParser(String jsonString)
				throws JSONException {
			List<ItAll> list = new ArrayList<ItAll>();

			ItAll itAll = null;

			EnteringTime enteringTime = null;

			JSONObject jsonObject = new JSONObject(jsonString);

			JSONArray jsonArray = jsonObject.getJSONArray("listvwf");

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject object = jsonArray.getJSONObject(i);

				JSONObject time = object.getJSONObject("enteringTime");

				enteringTime = new EnteringTime(time.getString("nanos"),
						time.getString("time"), time.getString("minutes"),
						time.getString("seconds"), time.getString("hours"),
						time.getString("month"),
						time.getString("timezoneOffset"),
						time.getString("year"), time.getString("day"),
						time.getString("date"));

				itAll = new ItAll(object.getString("examineeNumber"),
						object.getString("idCard"), object.getString("sex"),
						object.getString("schoolName"),
						object.getString("attendProfessional"),
						object.getString("voluntarily"),
						object.getString("expr1"), object.getString("tel"),
						object.getString("expr2"), enteringTime,
						object.getString("property"),
						object.getString("userIdSend"),
						object.getString("userId"), object.getString("stuId"),
						object.getString("userName"),
						object.getString("schoolId"),
						object.getString("stuName"),
						object.getString("modifyTime"),
						object.getString("idNumber"));

				list.add(itAll);
			}

			return list;
		}

	}

}
