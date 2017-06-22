package org.invoice.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HSLFilter extends AbstractBufferedImageOp {  //增加对比度和亮度
    public final static double c1o60  = 1.0 / 60.0;  
    public final static double c1o255 = 1.0 / 255.0;  
    private double hue;  
    private double saturation;  
    private double lightness;  
      
    public HSLFilter() {  
        
    }  
      
    public double getHue() {  
        return hue;  
    }  
  
    public void setHue(double hue) {  
        while (hue < 0.0) {  
            this.hue += 360;  
        }  
        while (hue >= 360.0) {  
            this.hue -= 360;  
        }  
    }  
  
    public double getSaturation() {  
        return saturation;  
    }  
  
    public void setSaturation(double saturation) {  
        if((saturation >= -100.0) && (saturation <= 100.0)) {  
            this.saturation = saturation;  
        }  
    }  
  
    public double getLightness() {  
        return lightness;  
    }  
  
    public void setLightness(double lightness) {  
        if((lightness >= -100.0) && (lightness <= 100.0)) {  
            this.lightness = lightness;  
        }  
    }  
    
    public static BufferedImage  ReadBufferedImage(String imgPath) {
    	BufferedImage image = null;
        try{ 
        	image = ImageIO.read(new FileInputStream(imgPath));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return image;
	}
    public static boolean WriteImage(String imgPath, BufferedImage dest) {
    	File file = new File (imgPath);
    	String format = "jpg";
    	try{
    		ImageIO.write(dest,format,file);//将图片保存为jpeg格式的文件file。也可以保存为jpg格式
    		return true;
    	}
    	catch (Exception e) {
    		//System.out.println(e.getMessage());
    		return false;
		}
    	
	}
    
      
  
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {  
    	
        int width = src.getWidth();  
        int height = src.getHeight();  
        double sat = 127.0d * saturation / 100.0d;  
        double lum = 127.0d * lightness / 100.0d;  
        if ( dest == null )  
            dest = createCompatibleDestImage( src, null );  
  
        int[] inPixels = new int[width*height];  
        int[] outPixels = new int[width*height];  
        getRGB( src, 0, 0, width, height, inPixels );  
        double min, max, dif, sum;  
        double f1, f2;  
        int index = 0;  
        double h, s, l;  
        double v1, v2, v3, h1;  
        for(int row=0; row<height; row++) {  
            int ta = 0, tr = 0, tg = 0, tb = 0;  
            for(int col=0; col<width; col++) {  
                index = row * width + col;  
                ta = (inPixels[index] >> 24) & 0xff;  
                tr = (inPixels[index] >> 16) & 0xff;  
                tg = (inPixels[index] >> 8) & 0xff;  
                tb = inPixels[index] & 0xff;  
                  
                // convert to HSL space  
                min = tr;  
                if (tg < min)   
                    min = tg;  
                if (tb < min)   
                    min = tb;  
                max = tr;  
                f1 = 0.0;  
                f2 = tg - tb;  
                if (tg > max) {  
                   max = tg;  
                   f1 = 120.0;  
                   f2 = tb - tr;  
                }  
                if (tb > max) {  
                   max = tb;  
                   f1 = 240.0;  
                   f2 = tr - tg;  
                }  
                dif = max - min;  
                sum = max + min;  
                l = 0.5 * sum;  
                if (dif == 0) {  
                    h = 0.0;  
                    s = 0.0;  
                }   
                else if(l < 127.5) {  
                    s = 255.0 * dif / sum;  
                }  
                else {  
                    s = 255.0 * dif / (510.0 - sum);  
                }  
  
                h = (f1 + 60.0 * f2 / dif);  
                if (h < 0.0) {   
                    h += 360.0;  
                }  
                if (h >= 360.0) {  
                    h -= 360.0;  
                }  
                  
                // Apply transformation.  
                h = h + hue;  
                if( h >= 360.0) {  
                    h = h - 360.0;  
                }  
                s = s + sat;  
                if( s < 0.0) {  
                    s = 0.0;  
                }  
                if( s > 255.0) {  
                    s = 255.0;  
                }  
                l = l + lum;  
                if( l < 0.0) {  
                    l = 0.0;  
                }  
                if( l > 255.0) {  
                    l = 255.0;  
                }  
                  
                // conversion back to RGB space here!!  
                if (s == 0) {  
                   tr = (int)l;  
                   tg = (int)l;  
                   tb = (int)l;  
                } else {  
                  
                   if (l < 127.5) {  
                      v2 = c1o255 * l * (255 + s);  
                   } else {  
                      v2 = l + s - c1o255 * s * l;  
                   }  
                     
                   v1 = 2 * l - v2;  
                   v3 = v2 - v1;  
                   h1 = h + 120.0;  
                   if (h1 >= 360.0)   
                       h1 -= 360.0;  
                     
                   if (h1 < 60.0) {  
                      tr = (int)(v1 + v3 * h1 * c1o60);  
                   }  
                   else if (h1 < 180.0) {  
                      tr = (int)v2;  
                   }  
                   else if (h1 < 240.0) {  
                      tr = (int)(v1 + v3 * (4 - h1 * c1o60));  
                   }  
                   else {  
                      tr = (int)v1;  
                   }  
                     
                   h1 = h;  
                   if (h1 < 60.0) {  
                      tg = (int)(v1 + v3 * h1 * c1o60);  
                   }  
                   else if (h1 < 180.0) {  
                      tg = (int)v2;  
                   }   
                   else if (h1 < 240.0) {  
                      tg = (int)(v1 + v3 * (4 - h1 * c1o60));  
                   }  
                   else {  
                      tg = (int)v1;  
                   }  
                     
                   h1 = h - 120.0;  
                   if (h1 < 0.0) {  
                       h1 += 360.0;  
                   }  
                   if (h1 < 60.0) {  
                      tb = (int)(v1 + v3 * h1 * c1o60);  
                   }  
                   else if (h1 < 180.0) {  
                      tb = (int)v2;  
                   }  
                   else if (h1 < 240.0) {  
                      tb = (int)(v1 + v3 * (4 - h1 * c1o60));  
                   }  
                   else {  
                      tb = (int)v1;  
                   }  
                }  
                outPixels[index] = (ta << 24) | (tr << 16) | (tg << 8) | tb;  
            }  
        }  
  
        setRGB( dest, 0, 0, width, height, outPixels );  
        
        return dest;  
    }  
    public static void main(String args[]) throws IOException{
    	ImageCutting imageCutting=new ImageCutting();
    	imageCutting.ImageCuttingCode("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test1.jpg");
		imageCutting.ImageCuttingID("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test2.jpg");
		imageCutting.ImageCuttingCount("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test3.jpg");
		imageCutting.ImageCuttingUnitPrice("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test4.jpg");
		imageCutting.ImageCuttingTax("001C.jpg","/Users/hanzhengbo/Desktop/zzs/test5.jpg");
    	for(int i=1;i<=5;i++){
    	    String readimgPath = "/Users/hanzhengbo/Desktop/zzs/test"+ i +".jpg";
    	    String writeimgPath="/Users/hanzhengbo/Desktop/zzs/test"+ i +"_des.jpg";
    	    String ZoomimgPath="/Users/hanzhengbo/Desktop/zzs/test"+ i +"_zoo.jpg";
    	
    	    BufferedImage src = ReadBufferedImage(readimgPath);
    	
    	    HSLFilter hslFilter=new HSLFilter();
    	    BufferedImage dest = hslFilter.filter(src, null);
    	    WriteImage(writeimgPath, dest);
    	    if(WriteImage(writeimgPath, dest)) {
    	    	// successful
    	    	NarrowImage narrowImage=new NarrowImage();
    	    	narrowImage.zoomImage(writeimgPath);
    	    	narrowImage.writeHighQuality(narrowImage.zoomImage(writeimgPath), ZoomimgPath);
    	    	OCR test = new OCR();
    	    	//test.ImageRecognition(writeimgPath);
    	    }
    	    System.out.println(" ");
    	}
    }
  
}  