package com.wechat.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	public static Set<Integer> toSet(Integer[] values) {
		Set<Integer> r = new HashSet<Integer>();
		for (Integer i : values) {
			r.add(i);
		}
		return r;
	}

	public static boolean between(Long l, Long[] range) {
		if (l >= range[0] && l <= range[1]) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean between(Integer i, Long[] range) {
		if (i >= range[0] && i <= range[1]) {
			return true;
		} else {
			return false;
		}
	}

	public static String join(String[] array, String split) {
		if (array.length == 0) {
			return "";
		} else if (array.length == 1) {
			return array[0];
		} else {
			StringBuffer sbf = new StringBuffer();
			for (int n = 0; n < array.length; n++) {
				if (n > 0) {
					sbf.append(split);
				}
				sbf.append(array[n]);
			}
			return sbf.toString();
		}
	}

	public static <T> T parseObject(String json, Class<T> clazz) {
		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static String join(List<?> list, String split) {
		if (list.size() == 0) {
			return "";
		} else if (list.size() == 1) {
			return list.get(0).toString();
		} else {
			StringBuffer sbf = new StringBuffer();
			for (int n = 0; n < list.size(); n++) {
				if (n > 0) {
					sbf.append(split);
				}
				sbf.append(list.get(n).toString());
			}
			return sbf.toString();
		}
	}

	/**
	 * json串转map
	 * 
	 * @Title: jsonToMap
	 * @Description: TODO
	 * @param jsonString
	 * @return
	 * @return: Map<String,String>
	 */
	public static Map<String, String> jsonToMap(String jsonString) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonString, type);
	}

	/**
	 * json 转 map
	 * 
	 * @Title: jsonToObjectMap
	 * @Description: TODO
	 * @param jsonString
	 * @return
	 * @return: Map<String,Object>
	 */
	public static Map<String, Object> jsonToObjectMap(String jsonString) {
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonString, type);
	}

	/**
	 * json 转 list
	 * 
	 * @Title: jsonToObjectList
	 * @Description: TODO
	 * @param jsonString
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	public static List<Map<String, Object>> jsonToObjectList(String jsonString) {
		Type type = new TypeToken<List<Map<String, Object>>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonString, type);
	}

	/**
	 * 升序排序
	 * 
	 * @param srclist
	 * @param sortkey
	 */
	public static void sortbylong(List<Map<String, Object>> srclist,
			String sortkey) {
		while (true) {
			boolean change = false;
			for (int n = 0; n < srclist.size() - 1; n++) {
				Map<String, Object> current = srclist.get(n);
				int currentsort = (Integer) current.get(sortkey);

				Map<String, Object> next = srclist.get(n + 1);
				int nextsort = (Integer) next.get(sortkey);

				if (currentsort > nextsort) {
					srclist.add(n, next);
					srclist.remove(n + 2);
					change = true;
				}
			}
			if (!change) {
				break;
			}
		}
	}

	public static Integer toInteger(Object value) {
		if (value instanceof Integer) {
			return (Integer) value;
		} else if (value instanceof Long) {
			return ((Long) value).intValue();
		} else if (value instanceof Double) {
			return ((Double) value).intValue();
		} else if (value instanceof String) {
			return Integer.parseInt((String) value);
		} else if (value instanceof Date) {
			Date _value = (Date) value;
			Long time = _value.getTime();
			return time.intValue();
		} else {
			throw new ClassCastException();
		}
	}

	public static List<Integer> toIntegerList(Object value) {
		List<Integer> ret = new ArrayList<Integer>();
		if (value instanceof Integer[]) {
			Integer[] _value = (Integer[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add((Integer) _value[n]);
			}
		} else if (value instanceof int[]) {
			int[] _value = (int[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add((int) _value[n]);
			}
		} else if (value instanceof Long[]) {
			Long[] _value = (Long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Long) _value[n]).intValue());
			}
		} else if (value instanceof long[]) {
			long[] _value = (long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Long) _value[n]).intValue());
			}
		} else if (value instanceof Double[]) {
			Double[] _value = (Double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Double) _value[n]).intValue());
			}
		} else if (value instanceof double[]) {
			double[] _value = (double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Double) _value[n]).intValue());
			}
		} else if (value instanceof String[]) {
			String[] _value = (String[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(Integer.parseInt((String) _value[n]));
			}
		} else if (value instanceof Date[]) {
			Date[] _value = (Date[]) value;
			for (int n = 0; n < _value.length; n++) {
				Long time = ((Date) _value[n]).getTime();
				ret.add(time.intValue());
			}
		} else {
			throw new ClassCastException();
		}
		return ret;
	}

	public static Long toLong(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).longValue();
		} else if (value instanceof Long) {
			return (Long) value;
		} else if (value instanceof Double) {
			return ((Double) value).longValue();
		} else if (value instanceof String) {
			return Long.parseLong((String) value);
		} else if (value instanceof Date) {
			return ((Date) value).getTime();
		} else {
			throw new ClassCastException();
		}
	}

	public static List<Long> toLongList(Object value) {
		List<Long> ret = new ArrayList<Long>();
		if (value instanceof Integer[]) {
			Integer[] _value = (Integer[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Integer) _value[n]).longValue());
			}
		} else if (value instanceof int[]) {
			int[] _value = (int[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Integer) _value[n]).longValue());
			}
		} else if (value instanceof Long[]) {
			Long[] _value = (Long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add((Long) _value[n]);
			}
		} else if (value instanceof long[]) {
			long[] _value = (long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add((Long) _value[n]);
			}
		} else if (value instanceof Double[]) {
			Double[] _value = (Double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Double) _value[n]).longValue());
			}
		} else if (value instanceof double[]) {
			double[] _value = (double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Double) _value[n]).longValue());
			}
		} else if (value instanceof String[]) {
			String[] _value = (String[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(Long.parseLong((String) _value[n]));
			}
		} else if (value instanceof Date[]) {
			Date[] _value = (Date[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Date) _value[n]).getTime());
			}
		} else {
			throw new ClassCastException();
		}
		return ret;
	}

	public static Long[] toLongArray(Object value) {
		return toLongList(value).toArray(new Long[0]);
	}

	public static Double toDouble(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).doubleValue();
		} else if (value instanceof Long) {
			return ((Long) value).doubleValue();
		} else if (value instanceof Double) {
			return (Double) value;
		} else if (value instanceof String) {
			return Double.parseDouble((String) value);
		} else {
			throw new ClassCastException();
		}
	}

	public static List<Double> toDoubleList(Object value) {
		List<Double> ret = new ArrayList<Double>();
		if (value instanceof Integer[]) {
			Integer[] _value = (Integer[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Integer) _value[n]).doubleValue());
			}
		} else if (value instanceof int[]) {
			int[] _value = (int[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Integer) _value[n]).doubleValue());
			}
		} else if (value instanceof Long[]) {
			Long[] _value = (Long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Long) _value[n]).doubleValue());
			}
		} else if (value instanceof long[]) {
			long[] _value = (long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(((Long) _value[n]).doubleValue());
			}
		} else if (value instanceof Double[]) {
			Double[] _value = (Double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add((Double) _value[n]);
			}
		} else if (value instanceof double[]) {
			double[] _value = (double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add((Double) _value[n]);
			}
		} else if (value instanceof String[]) {
			String[] _value = (String[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(Double.parseDouble((String) _value[n]));
			}
		} else {
			throw new ClassCastException();
		}
		return ret;
	}

	public static Date toDate(Object value) throws Exception {
		if (value instanceof Integer) {
			return new Date((Long) value);
		} else if (value instanceof Long) {
			return new Date((Long) value);
		} else if (value instanceof String) {
			long time = UtilDate.formatDateTimeToLong((String) value);
			return new Date(time);
		} else if (value instanceof Date) {
			return (Date) value;
		} else {
			throw new ClassCastException();
		}
	}

	public static List<Date> toDateList(Object value) throws Exception {
		List<Date> ret = new ArrayList<Date>();
		if (value instanceof Integer[]) {
			Integer[] _value = (Integer[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(new Date((Integer) _value[n]));
			}
		} else if (value instanceof int[]) {
			int[] _value = (int[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(new Date((Integer) _value[n]));
			}
		} else if (value instanceof Long[]) {
			Long[] _value = (Long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(new Date((Long) _value[n]));
			}
		} else if (value instanceof long[]) {
			long[] _value = (long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(new Date((Long) _value[n]));
			}
		} else if (value instanceof String[]) {
			String[] _value = (String[]) value;
			for (int n = 0; n < _value.length; n++) {
				long time = UtilDate.formatDateTimeToLong((String) value);
				ret.add(new Date(time));
			}
		} else if (value instanceof Date[]) {
			Date[] _value = (Date[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add((Date) _value[n]);
			}
		} else {
			throw new ClassCastException();
		}
		return ret;
	}

	public static String toString(Object value) throws Exception {
		if (value instanceof Integer) {
			return String.valueOf(value);
		} else if (value instanceof Long) {
			return String.valueOf(value);
		} else if (value instanceof String) {
			return (String) value;
		} else if (value instanceof Date) {
			return UtilDate.getDateTime(((Date) value).getTime());
		} else {
			throw new ClassCastException();
		}
	}

	public static List<String> toStringList(Object value) {
		List<String> ret = new ArrayList<String>();
		if (value instanceof Integer[]) {
			Integer[] _value = (Integer[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(String.valueOf(_value[n]));
			}
		} else if (value instanceof int[]) {
			int[] _value = (int[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(String.valueOf(_value[n]));
			}
		} else if (value instanceof Long[]) {
			Long[] _value = (Long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(String.valueOf(_value[n]));
			}
		} else if (value instanceof long[]) {
			long[] _value = (long[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(String.valueOf(_value[n]));
			}
		} else if (value instanceof Double[]) {
			Double[] _value = (Double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(String.valueOf(_value[n]));
			}
		} else if (value instanceof double[]) {
			double[] _value = (double[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(String.valueOf(_value[n]));
			}
		} else if (value instanceof String[]) {
			String[] _value = (String[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(_value[n]);
			}
		} else if (value instanceof Date[]) {
			Date[] _value = (Date[]) value;
			for (int n = 0; n < _value.length; n++) {
				ret.add(UtilDate.getDateTime(_value[n].getTime()));
			}
		} else {
			throw new ClassCastException();
		}
		return ret;
	}

	public static boolean isInt(Object value) {
		if (value instanceof Integer) {
			return true;
		} else if (value instanceof String) {
			String _value = (String) value;
			if (_value.length() == 0) {
				return false;
			} else {
				return _value.matches("-?\\d*");
			}
		} else {
			return false;
		}
	}

	public static boolean isDouble(Object value) {
		if (value instanceof Double) {
			return true;
		} else if (value instanceof String) {
			String _value = (String) value;
			if (_value.length() == 0) {
				return false;
			} else {
				return _value.matches("-?\\d+\\.\\d+");
			}

		} else {
			return false;
		}
	}

	public static Map<String, Object> obj2map(Object obj) throws Exception {

		Map<String, Object> ret = new HashMap<String, Object>();

		Field[] fields = obj.getClass().getDeclaredFields();

		for (Field field : fields) {

			if (!Modifier.isStatic(field.getModifiers())
					&& Modifier.isPublic(field.getModifiers())) {

				ret.put(field.getName(), field.get(obj));
			}
		}
		return ret;
	}

	/**
	 * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	 * 
	 * @Title: transMap2Bean
	 * @Description: TODO
	 * @param map
	 * @param obj
	 * @throws Exception
	 * @return: void
	 */
	public static void transMap2Bean(Map<String, Object> map, Object obj)
			throws Exception {

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();

		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();

			if (map.containsKey(key)) {
				Object value = map.get(key);
				// 得到property对应的setter方法
				Method setter = property.getWriteMethod();
				setter.invoke(obj, value);
			}

		}

		return;
	}

	/**
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	 * 
	 * @Title: transBean2Map
	 * @Description: TODO
	 * @param obj
	 * @return
	 * @throws Exception
	 * @return: Map<String,Object>
	 */
	public static Map<String, Object> transBean2Map(Object obj)
			throws Exception {

		if (obj == null) {

			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();

		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();

			// 过滤class属性
			if (!key.equals("class")) {
				// 得到property对应的getter方法
				Method getter = property.getReadMethod();
				Object value = getter.invoke(obj);

				map.put(key, value);
			}
		}

		return map;
	}

	public static <T> T fromJson(String json, Class<T> c) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gsonBuilder.create();
		return gson.fromJson(json, c);
	}

	public static <T> T fromJson(String json, Type type) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gsonBuilder.create();
		return gson.fromJson(json, type);
	}

	public static String toJson(Object obj) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gsonBuilder.create();
		return gson.toJson(obj);
	}

	public static String intToDoubleString(int value, int divide) {
		StringBuffer moneyString = new StringBuffer();
		moneyString.append(value / divide).append(".");
		int afterDot = value % divide;
		if (afterDot < 10) {
			moneyString.append("0").append(afterDot);
		} else {
			moneyString.append(afterDot);
		}
		return moneyString.toString();
	}
}
