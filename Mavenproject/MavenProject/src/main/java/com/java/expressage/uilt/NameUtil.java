package com.java.expressage.uilt;

public class NameUtil {
//转换变量命名风格
	public static String convert2Caml(String cat) {
		String[] temp = cat.split("_");
		StringBuffer verSb = new StringBuffer();
		for(int i = 0 ; i < temp.length ; i++) {
			if (i == 0) {
				verSb.append(temp[i]);
			}else {
				verSb.append(firstUpper(temp[i]));
			}
		}
		return verSb.toString();
		
		
	}
//	首字母转大写
	public static String firstUpper(String verName) {
		StringBuffer verSb = new StringBuffer();
		String firstAlp = verName.substring(0, 1).toUpperCase();
		verSb.append(firstAlp);
		verSb.append(verName.substring(1, verName.length()));
		
		return verSb.toString();
		
	}
	public static void main(String[] args) {
		String a = "aa_cc_bbbb";
		System.out.println(convert2Caml(a));
	}
}

