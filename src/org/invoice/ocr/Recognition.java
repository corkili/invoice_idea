package org.invoice.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Recognition {
	public static void main(String args[]) throws IOException{
		Recognition rec=new Recognition();
		rec.recognition("001C.jpg");
    }
	public void recognition(String image) throws IOException{
		String dirName = "zzs";// 创建目录
		CreateDirectory.createDir(dirName);// 调用方法创建目录
    	ImageCutting imageCutting=new ImageCutting();
    	imageCutting.ImageCuttingCode(image,"zzs/test1.jpg");
		imageCutting.ImageCuttingID(image,"zzs/test2.jpg");
		imageCutting.ImageCuttingCount(image,"zzs/test3.jpg");
		imageCutting.ImageCuttingUnitPrice(image,"zzs/test4.jpg");
		imageCutting.ImageCuttingTax(image,"zzs/test5.jpg");
    	for(int i = 1; i <= 5; i++){
    	    String readimgPath = "zzs/test"+ i +".jpg";
    	    String writeimgPath="zzs/test"+ i +"_des.jpg";
    	    String ZoomimgPath="zzs/test"+ i +"_zoo.jpg";
    	
    	    BufferedImage src = HSLFilter.ReadBufferedImage(readimgPath);
    	
    	    HSLFilter hslFilter = new HSLFilter();
    	    BufferedImage dest = hslFilter.filter(src, null);
    	    HSLFilter.WriteImage(writeimgPath, dest);
    	    if(HSLFilter.WriteImage(writeimgPath, dest)) {
    	    	// successful
    	    	NarrowImage narrowImage = new NarrowImage();
    	    	narrowImage.zoomImage(writeimgPath);
    	    	narrowImage.writeHighQuality(narrowImage.zoomImage(writeimgPath), ZoomimgPath);
    	    	OCR OCR = new OCR();
    	    	OCR.ImageRecognition(writeimgPath);
    	    }
    	    System.out.println(" ");
    	}
    	String deleteDir = "zzs";
        //boolean success = DeleteDirectory.deleteDir(new File(deleteDir));
		boolean success = DeleteDirectory.deleteFolder(deleteDir);
        if (success) {
            System.out.println("Successfully deleted populated directory: " + deleteDir);
        } else {
            System.out.println("Failed to delete populated directory: " + deleteDir);
        }     
	}
}
