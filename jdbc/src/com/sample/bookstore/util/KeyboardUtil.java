package com.sample.bookstore.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 키보드의 입력값을 읽어오는 기능을 제공하는 유틸클래스다.  
 * @author JHTA
 *
 */
public class KeyboardUtil {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static String nextString() throws IOException {
		String text = reader.readLine();
		return text;
	}
	public static int nextInt() throws NumberFormatException, IOException {
		return Integer.parseInt(nextString());
		
	}
	public static long nextLong() throws NumberFormatException, IOException {
		return Long.parseLong(nextString());
	}
	public static double nextDouble() throws NumberFormatException, IOException {
		return Double.parseDouble(nextString());
	}
	public static void close() throws IOException{
		reader.close();
	}
	
	
}
