package org.invoice.ocr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Binary {
	public static String test() throws IOException{
	String path = "111111.jpg";
	File file = new File(path);

	FileInputStream fis = new FileInputStream(file);
	byte[] b = new byte[fis.available()];
	StringBuilder str = new StringBuilder();//不建议用String

	 fis.read(b);

	for(byte bs:b)
	   {
	    str.append(Integer.toBinaryString(bs));//转换为二进制
	   }
	//System.out.println(str.toString());
	return str.toString();
	}
	public static void main(String args[]) throws IOException{
		test();
	}
}
