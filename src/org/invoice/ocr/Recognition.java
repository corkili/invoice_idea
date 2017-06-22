package org.invoice.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class Recognition {
	public static void main(String args[]) throws IOException{
		Recognition rec=new Recognition();
		//rec.recognition("001C.jpg");
    }
	public Map<String, Object> recognition(String image, String path) throws IOException{
		String dirName = path + "\\zzs";// 创建目录
		CreateDirectory.createDir(dirName);// 调用方法创建目录
		Map<String,Object> map=new HashedMap();
    	ImageCutting imageCutting=new ImageCutting();
    	OCR test = new OCR();
    	String string[]= {"invoiceCode","invoiceId","quantities","unitPrices","taxs"};
    	imageCutting.ImageCuttingCode(image,path + "\\zzs\\test1.jpg");
		imageCutting.ImageCuttingID(image,path + "\\zzs\\test2.jpg");
		imageCutting.ImageCuttingCount(image,path + "\\zzs\\test3.jpg");
		imageCutting.ImageCuttingUnitPrice(image,path + "\\zzs\\test4.jpg");
		imageCutting.ImageCuttingTax(image,path + "\\zzs\\test5.jpg");
    	for(int i=1;i<=2;i++){
    	    String readimgPath = path + "\\zzs\\test"+ i +".jpg";
    	    String writeimgPath = path + "\\zzs\\test"+ i +"_des.jpg";
    	    String ZoomimgPath = path + "\\zzs\\test"+ i +"_zoo.jpg";
    	
    	    BufferedImage src = HSLFilter.ReadBufferedImage(readimgPath);
    	
    	    
    	    HSLFilter hslFilter=new HSLFilter();
    	    BufferedImage dest = hslFilter.filter(src, null);
    	    HSLFilter.WriteImage(writeimgPath, dest);
    	    if(HSLFilter.WriteImage(writeimgPath, dest)) {//增加对比度 饱和度成功
    	    	// successful
    	    	NarrowImage narrowImage=new NarrowImage();
    	    	narrowImage.zoomImage(writeimgPath);
    	    	narrowImage.writeHighQuality(narrowImage.zoomImage(writeimgPath), ZoomimgPath);
    	    	
    	    	map.put(string[i-1], test.ImageRecognitionCodeID(writeimgPath));
    	    }
    	}
    	for(int i=3;i<=5;i++){
    	    String readimgPath = path + "\\zzs\\test"+ i +".jpg";
    	    String writeimgPath = path + "\\zzs\\test"+ i +"_des.jpg";
    	    String ZoomimgPath = path + "\\zzs\\test"+ i +"_zoo.jpg";
    	
    	    BufferedImage src = HSLFilter.ReadBufferedImage(readimgPath);
    		    
    	    HSLFilter hslFilter=new HSLFilter();
    	    BufferedImage dest = hslFilter.filter(src, null);
    	    HSLFilter.WriteImage(writeimgPath, dest);
    	    if(HSLFilter.WriteImage(writeimgPath, dest)) {//增加对比度 饱和度成功
    	    	// successful
    	    	NarrowImage narrowImage=new NarrowImage();
    	    	narrowImage.zoomImage(writeimgPath);
    	    	narrowImage.writeHighQuality(narrowImage.zoomImage(writeimgPath), ZoomimgPath);
    	    	
    	    	map.put(string[i-1], test.ImageRecognitionOther(writeimgPath));
    	    }
    	}
//    	for(int i=1;i<=5;i++){
//    		System.out.println(map.get(i));
//    	}
    	String deletedir = path + "\\zzs";
        boolean success = DeleteDirectory.deleteDir(new File(deletedir));
        if (success) {
            System.out.println("Successfully deleted populated directory: " + deletedir);
        } else {
            System.out.println("Failed to delete populated directory: " + deletedir);
        } 
        return map;
	}
}
