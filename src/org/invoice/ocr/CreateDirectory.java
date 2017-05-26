package org.invoice.ocr;

import java.io.File;

public class CreateDirectory {
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("Failed to create directory. The destination directory already exists!");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("Create directory success!" + destDirName);
			return true;
		} else {
			System.out.println("Failed to create directory!");
			return false;
		}
	}
	public static void main(String[] args) {
		String dirName = "/Users/hanzhengbo/Desktop/zzs";// 创建目录
		CreateDirectory.createDir(dirName);// 调用方法创建目录
	}
}
