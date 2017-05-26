package org.invoice.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCutting {
	public void ImageCuttingCode(String inputimage,String outputimage) throws IOException{
		File imageFile=new File(inputimage);
		File outImageFile=new File(outputimage);
		BufferedImage img=ImageIO.read(imageFile);
		BufferedImage outImg=img.getSubimage((int)(0.1719*img.getWidth()), (int)(0.0539*img.getHeight()), (int)(0.1868*img.getWidth()), (int)(0.0748*img.getHeight()));
		ImageIO.write(outImg, "jpg", outImageFile);
	}
	public void ImageCuttingID(String inputimage,String outputimage) throws IOException{
		File imageFile=new File(inputimage);
		File outImageFile=new File(outputimage);
		BufferedImage img=ImageIO.read(imageFile);
		BufferedImage outImg=img.getSubimage((int)(0.7001*img.getWidth()), (int)(0.0502*img.getHeight()), (int)(0.1263*img.getWidth()), (int)(0.0731*img.getHeight()));
		ImageIO.write(outImg, "jpg", outImageFile);
	}
	public void ImageCuttingCount(String inputimage,String outputimage) throws IOException{
		File imageFile=new File(inputimage);
		File outImageFile=new File(outputimage);
		BufferedImage img=ImageIO.read(imageFile);
		BufferedImage outImg=img.getSubimage((int)(0.46*img.getWidth()), (int)(0.4081*img.getHeight()), (int)(0.0762*img.getWidth()), (int)(0.0475*img.getHeight()));
		ImageIO.write(outImg, "jpg", outImageFile);
	}
	public void ImageCuttingUnitPrice(String inputimage,String outputimage) throws IOException{
		File imageFile=new File(inputimage);
		File outImageFile=new File(outputimage);
		BufferedImage img=ImageIO.read(imageFile);
		BufferedImage outImg=img.getSubimage((int)(0.5424*img.getWidth()), (int)(0.4081*img.getHeight()), (int)(0.0752*img.getWidth()), (int)(0.0475*img.getHeight()));
		ImageIO.write(outImg, "jpg", outImageFile);
	}
	public void ImageCuttingTax(String inputimage,String outputimage) throws IOException{
		File imageFile=new File(inputimage);
		File outImageFile=new File(outputimage);
		BufferedImage img=ImageIO.read(imageFile);
		BufferedImage outImg=img.getSubimage((int)(0.7961*img.getWidth()), (int)(0.4081*img.getHeight()), (int)(0.1096*img.getWidth()), (int)(0.0475*img.getHeight()));
		ImageIO.write(outImg, "jpg", outImageFile);
	}
	public  static void main(String args[]) throws IOException {
		ImageCutting imageCutting=new ImageCutting();
		imageCutting.ImageCuttingCode("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test1.jpg");
		imageCutting.ImageCuttingID("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test2.jpg");
		imageCutting.ImageCuttingCount("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test3.jpg");
		imageCutting.ImageCuttingUnitPrice("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test4.jpg");
		imageCutting.ImageCuttingTax("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test5.jpg");
	}
}
