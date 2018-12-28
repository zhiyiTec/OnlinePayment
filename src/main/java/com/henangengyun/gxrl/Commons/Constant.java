package com.henangengyun.gxrl.Commons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Constant {

	public static final Float LATE_FEE_RATIO;
	public static final Boolean IS_DEBUG;
	public static final String IP;
	
	static {
		Properties p = new Properties();
		try {
			p.load(Constant.class.getClassLoader().getResourceAsStream("conf/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 第一个参数是properties里的键，第二个值是如果没有该键的话的默认值
		LATE_FEE_RATIO = Float.parseFloat(p.getProperty("LaLateFeeRatio", "0.00025"));
		IS_DEBUG = p.getProperty("IS_DEBUG").equals("1");
		IP = p.getProperty("IP");
	}
	
}
