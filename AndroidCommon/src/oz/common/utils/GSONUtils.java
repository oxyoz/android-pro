package oz.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * GSON ������
 * 
 * @author arvin
 * 
 */
public class GSONUtils {
	/**
	 * ʵ�ֵ���
	 */
	private static Gson gson = null;

	static {
		if (gson == null) {
			gson = new Gson();
		}
	}

	/**
	 * ����Ĭ�ϵĹ��췽��
	 */
	private GSONUtils() {

	}

	/**
	 * ������ת����json��ʽ
	 * 
	 * @param ts
	 * @return
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}

	/**
	 * ����cla ���͵�list����
	 * 
	 * @param s
	 * @param cla
	 * @return
	 */
	public static <T extends Object> T jsonToBeanList(String s, Class<?> cla) {

		List<Object> ls = new ArrayList<Object>();
		JSONArray ss;
		try {
			ss = new JSONArray(s);
			for (int i = 0; i < ss.length(); i++) {
				String str = ss.getString(i);
				Object a = jsonToBean(str, cla);
				ls.add(a);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (T) ls;
	}

	/**
	 * ����cla ���͵�list����
	 * 
	 * @param s
	 * @param cla
	 * @return
	 */
	public static <T extends Object> T jsonToBeanList(String s, String cla) {

		List<Object> ls = new ArrayList<Object>();
		JSONArray ss;
		try {
			ss = new JSONArray(s);
			for (int i = 0; i < ss.length(); i++) {
				String str = ss.getString(i);
				Object a = jsonToBean(str, cla);
				ls.add(a);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (T) ls;
	}

	/**
	 * ��jsonStrת����cl����
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static <T extends Object> T jsonToBean(String jsonStr, Class<?> cl) {
		Object obj = null;
		if (gson != null) {
			if (!TextUtils.isEmpty(jsonStr))
				obj = gson.fromJson(jsonStr, cl);
		}
		return (T) obj;
	}

	/**
	 * ����
	 * 
	 * @param jsonStr
	 * @param classType
	 * @param <T>
	 * @return
	 */
	public static <T extends Object> T jsonToBean(String jsonStr,
			String classType) {

		Class c = null;
		try {
			c = Class.forName(classType);
		} catch (ClassNotFoundException e) {
			c = Object.class;
			e.printStackTrace();
		}
		return jsonToBean(jsonStr, c);
	}

	public static <T extends Object> T json2b(String jsonStr, String classType) {

		if (jsonStr.trim().startsWith("[")) {
			return jsonToBeanList(jsonStr, classType);

		} else {
			return jsonToBean(jsonStr, classType);
		}

	}

	/**
	 * ���� ���� jsonStr �Զ������ɶ����������.
	 * 
	 * @param jsonStr
	 * @param classType
	 * @param <T>
	 * @return
	 */
	public static <T extends Object> T json2b(String jsonStr, Class classType) {

		if (jsonStr.trim().startsWith("[")) {
			return jsonToBeanList(jsonStr, classType);

		} else {
			return jsonToBean(jsonStr, classType);
		}

	}

	/**
	 * ��json��ʽת����map����
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> jsonToMap(String jsonStr) {
		Map<?, ?> objMap = null;
		if (gson != null) {
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
			}.getType();
			objMap = gson.fromJson(jsonStr, type);
		}
		return objMap;
	}

}
