package com.lopy.common.utils;


import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @description
 * @author Dex
 * @date 2020/09/25
 * @param
 */
public class ExceptionUtil {

    private ExceptionUtil() {}

	/**
	 * Convert error stack into string message
	 */
	public static String getStackTraceAsString(Throwable ex) {
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
}

