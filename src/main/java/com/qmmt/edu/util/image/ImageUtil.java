/**
* @author wenghongbo
* @version Created on 上午10:24:22
*/ 

package com.qmmt.edu.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class ImageUtil {

	private final static int PNG=0x8950;
	private final static int JPG=0xffd8;
	private final static int GIF=0x4749;
	private final static int BMP=0x424d;
	private final static int WBMP=0x0000;
	private final static int UNKNOW=-1;
	private final static float Quality=0.8f;
    
	/**判断图片类型*/
	private static int whatType(byte[] b){
		switch (Integer.valueOf(Integer.toHexString(b[0]& 0xFF)+Integer.toHexString(b[1] & 0xFF),16)) {
			case JPG:return JPG;
			case GIF:return GIF;
			case PNG:return PNG;
			case BMP:return BMP;
			case WBMP:return WBMP;
			default:return UNKNOW;
		}
	}
	
	/**判断图片类型*/
	public static String getType(byte[] b){
		switch (Integer.valueOf(Integer.toHexString(b[0]& 0xFF)+Integer.toHexString(b[1] & 0xFF),16)) {
			case JPG:return "jpg";
			case GIF:return "gif";
			case PNG:return "png";
			case BMP:return "bmp";
			case WBMP:return "wemp";
			default:return "jpg";
		}
	}
	
	/**
	 * 按指定宽度缩�?
	 * 如果图片宽度不足指定宽度则直接返�?
	 * **/
	public static byte[] scaleImg(byte[] imgdata,int w)throws Exception{
		int imgtype=whatType(new byte[]{imgdata[0],imgdata[1],imgdata[2],imgdata[3]});
		if(imgtype==-1)throw new Exception("unknow image type.");
		BufferedImage image=null;
		try {
			image=ImageIO.read(new ByteArrayInputStream(imgdata));
		} catch (Exception e) {
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new ByteArrayInputStream(imgdata));  
			image = decoder.decodeAsBufferedImage(); 
		}
		int[] s=cypherWH(w,-1,image);
		Image img=image.getScaledInstance(s[0], s[1], Image.SCALE_SMOOTH);
		
		BufferedImage newImage=new BufferedImage(s[0], s[1],BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(img, 0, 0, null);
		JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(newImage);
        jparam.setQuality(Quality, true);
        ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
        encoder.encode(newImage);
        return imageStream.toByteArray();
	}
	
	/**
	 * 按指定宽度缩放，并且指定图片质量
	 * 如果图片宽度不足指定宽度则直接返�?
	 * **/
	public static byte[] scaleImgQuality(byte[] imgdata,int w,float quality)throws Exception{
		int imgtype=whatType(new byte[]{imgdata[0],imgdata[1],imgdata[2],imgdata[3]});
		if(imgtype==-1)throw new Exception("unknow image type.");
		BufferedImage image=null;
		try {
			image=ImageIO.read(new ByteArrayInputStream(imgdata));
		} catch (Exception e) {
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new ByteArrayInputStream(imgdata));  
			image = decoder.decodeAsBufferedImage(); 
		}
		int[] s=cypherWH(w,-1,image);
		Image img=image.getScaledInstance(s[0], s[1], Image.SCALE_SMOOTH);
		
		BufferedImage newImage=new BufferedImage(s[0], s[1],BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(img, 0, 0, null);
		JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(newImage);
        jparam.setQuality(quality, true);
        ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
        encoder.encode(newImage);
        return imageStream.toByteArray();
	}
	
	/**
	 * 按指定宽度缩�?
	 * 如果图片宽度不足指定宽度则直接返�?
	 * **/
	public static ImageBean scaleImgBean(byte[] imgdata,int w)throws Exception{
		int imgtype=whatType(new byte[]{imgdata[0],imgdata[1],imgdata[2],imgdata[3]});
		if(imgtype==-1)throw new Exception("unknow image type.");
		BufferedImage image=null;
		try {
			image=ImageIO.read(new ByteArrayInputStream(imgdata));
		} catch (Exception e) {
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new ByteArrayInputStream(imgdata));  
			image = decoder.decodeAsBufferedImage(); 
		}
		int[] s=cypherWH(w,-1,image);
		Image img=image.getScaledInstance(s[0], s[1], Image.SCALE_SMOOTH);
		
		BufferedImage newImage=new BufferedImage(s[0], s[1],BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(img, 0, 0, null);
		JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(newImage);
        jparam.setQuality(Quality, true);
        ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
        encoder.encode(newImage);
        return new ImageUtil().new ImageBean(s[0],s[1],imageStream.toByteArray());
	}
	
	/**
	 * 以中间为原点，截取并缩放
	 * **/
	public static byte[] imageFormatJpg(byte[] imgdata,int w,int h)throws Exception{

			int imgtype=whatType(new byte[]{imgdata[0],imgdata[1],imgdata[2],imgdata[3]});
			if(imgtype==-1)throw new Exception("unknow image type.");
			BufferedImage image=null;
			try {
				image=ImageIO.read(new ByteArrayInputStream(imgdata));
			} catch (Exception e) {
				JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new ByteArrayInputStream(imgdata));  
				image = decoder.decodeAsBufferedImage(); 
			}
			int width=image.getWidth(),height=image.getHeight(),x=0,y=0;
			int sub=Math.abs(width-height);
			if(sub>10 && w!=-1 && h!=-1){
				if(width>height){
					x=sub/2;
					width=height;
				}else{
					y=sub/2;
					height=width;
				}
				image=image.getSubimage(x,y,width,height);
			}
			int[] s=cypherWH(w,h,image);
			Image img=image.getScaledInstance(s[0], s[1],Image.SCALE_SMOOTH);
			BufferedImage newImage=new BufferedImage(s[0], s[1],BufferedImage.TYPE_INT_RGB);
			newImage.getGraphics().drawImage(img, 0, 0, null);
			JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(newImage);
	        jparam.setQuality(Quality, true);
	        ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
	        encoder.encode(newImage);
	        return imageStream.toByteArray();
	}
	
	
	/**
	 * 以中间为原点，截取并缩放
	 * **/
	public static ImageBean imageFormat(byte[] imgdata,int w,int h)throws Exception{

			int imgtype=whatType(new byte[]{imgdata[0],imgdata[1],imgdata[2],imgdata[3]});
			if(imgtype==-1)throw new Exception("unknow image type.");
			BufferedImage image=null;
			try {
				image=ImageIO.read(new ByteArrayInputStream(imgdata));
			} catch (Exception e) {
				JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new ByteArrayInputStream(imgdata));  
				image = decoder.decodeAsBufferedImage(); 
			}
			int width=image.getWidth(),height=image.getHeight(),x=0,y=0;
			int sub=Math.abs(width-height);
			if(sub>10 && w!=-1 && h!=-1){
				if(width>height){
					x=sub/2;
					width=height;
				}else{
					y=sub/2;
					height=width;
				}
				image=image.getSubimage(x,y,width,height);
			}
			int[] s=cypherWH(w,h,image);
			Image img=image.getScaledInstance(s[0], s[1],Image.SCALE_SMOOTH);
			BufferedImage newImage=new BufferedImage(s[0], s[1],BufferedImage.TYPE_INT_RGB);
			newImage.getGraphics().drawImage(img, 0, 0, null);
			JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(newImage);
	        jparam.setQuality(Quality, true);
	        ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
	        encoder.encode(newImage);
	        return new ImageUtil().new ImageBean(s[0],s[1],imageStream.toByteArray());
	}
	
	/**
	 * �?,0点为�?��，进行截取和缩放
	 * **/
	public static byte[] subImage(byte[] imgdata,int w,int h)throws Exception{
		int imgtype=whatType(new byte[]{imgdata[0],imgdata[1],imgdata[2],imgdata[3]});
		if(imgtype==-1)throw new Exception("unknow image type.");
	//	BufferedImage image=ImageIO.read(new ByteArrayInputStream(imgdata));
		BufferedImage image=null;
		try {
			image=ImageIO.read(new ByteArrayInputStream(imgdata));
		} catch (Exception e) {
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new ByteArrayInputStream(imgdata));  
			image = decoder.decodeAsBufferedImage(); 
		}
		int width=image.getWidth(),height=image.getHeight(),x=0,y=0;
		BufferedImage newImage=null;
		if(width>w || height>h){
			//int sub=Math.abs(width-height);
			if(w!=-1 && h!=-1){
				if(width>height){
					width=height;
				}else{
					height=width;
				}
			}else{
				int[] wh=cypherWH(w,h,image);
				w=wh[0];
				h=wh[1];
			}
			image=image.getSubimage(x,y,width,height);
			Image img=image.getScaledInstance(w, h,Image.SCALE_SMOOTH);
			newImage=new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
			newImage.getGraphics().drawImage(img, 0, 0, null);
		}else{
			newImage=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			newImage.getGraphics().drawImage(image, 0, 0, null);
		}
	   JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(newImage);
       jparam.setQuality(Quality, true);
       ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
       encoder.encode(newImage);
       return imageStream.toByteArray();
	}
	
    /**
     * @param int width 缩放的宽�?
     * @param int heigth 缩放的高�?
     * @param int subWidth 裁剪的宽�?
     * @param int subHeigth 裁剪的高�?
     * */
/*    private static void scaledImage(BufferedImage image,int width,int height,int subWidth,int subHeight)throws Exception{
    	BufferedImage newImage=image.
    	JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(newImage);
        jparam.setQuality(0.8f, true);
        ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
        encoder.encode(newImage);
        //return imageStream.toByteArray();
    }*/
    
    private static int[] cypherWH(int width,int height,Image image){
		
		if(width>0 && height>0){//高不�?1，直接返�?
			return new int[]{width,height};
		}else{
			int[] s={image.getWidth(null),image.getHeight(null)};
			if(width==-1 && height==-1)return s;
			if(s[0]>width){//图片宽度大于指定的宽�?
				s[1]=(int)Math.round((s[1] * width *1.0/ s[0]));
				s[0]=width;
			}
			return s;
		}
    }
    
    /**
     * 获取图片宽高
     * @return int[] [0]=widht,[1]=height 
     * @throws Exception 不能识别图片类型
     * */
    public static int[] getWH(byte[] data) throws Exception{
		int imgtype=whatType(new byte[]{data[0],data[1],data[2],data[3]});
		if(imgtype==-1)throw new Exception("unknow image type.");
		BufferedImage image=ImageIO.read(new ByteArrayInputStream(data));
		return new int[]{image.getWidth(),image.getHeight()};
    }
    
	private static final int FONT_SIZE=13;
	private static final int LINE_SPAC=FONT_SIZE+5;
	private static final int WORD_SPAC=FONT_SIZE+2;
	private static final int MARGIN=5;
	
	
    /**
     * 将文件转为图�?
     * @throws IOException 
     * */
    public static byte[] txtToImage(String txt) throws IOException{
    	int width=440;
    	ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
    	int lineMaxWord=Math.round((width-MARGIN)/WORD_SPAC);
		int enteNum=txt.split("\\n").length;
		int count=txt.length();
		int height=(count/lineMaxWord+enteNum)*LINE_SPAC+LINE_SPAC;
   
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // 设定背景�?
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
       
        //设定字体
        g.setFont(new Font("宋体",Font.PLAIN,FONT_SIZE));
        //画边�?
        g.setColor(Color.BLACK);
        g.drawRect(0,0,width-1,height-1);
        //字体颜色
        g.setColor(Color.BLACK);
        
        int y=LINE_SPAC;
        int x=WORD_SPAC*2+MARGIN;
        String str=null;
        int i=0;
        while(count>0){
        	str=txt.substring(i, i+1);
        	if(x+WORD_SPAC >width){
        		y+=LINE_SPAC;
        		x=MARGIN;
        	}
    		if(str.matches("[\\n]")){
        		y+=LINE_SPAC;
        		x=MARGIN;
            	i++;
            	count--;
            	continue;
    		}
        	g.drawString(str,x,y);
        	x+=WORD_SPAC;
        	i++;
        	count--;
        }
        g.setColor(new Color(0,0,0,0.2f));
        g.setFont(new Font("Dialog",Font.BOLD,14));
        g.drawString("http://w.com.cn", width-180, height-5);
        g.dispose();
        ImageIO.write(image, "jpg", imageStream);
    	return imageStream.toByteArray();
    }
    
    
    
    /**缩放图片，最大宽高为500*400
     * 
		从原图上截取的宽度最�?00px，高度最�?00px的图�?
		a. 若原图本身宽度超�?00px，等比例缩放图片直到宽度�?00px，缩放后若高度小�?00px，则保持缩放后高度；
		b. 若原图本身宽度超�?00px，等比例缩放图片直到宽度�?00px，缩放后若高度大�?00px（长图片），则截取图片从�?��方算起共400px显示�?
		c. 若图片本身宽度等�?00px，但高度超过400px，则无需缩放，直接截取图片从�?��方开始算起共400px显示�?
     * 
     * */
    public static ImageBean scaledImg500x400(byte[] imgdata,int w,int h)throws Exception{
		int imgtype=whatType(new byte[]{imgdata[0],imgdata[1],imgdata[2],imgdata[3]});
		if(imgtype==-1)throw new Exception("unknow image type.");
    	BufferedImage image=ImageIO.read(new ByteArrayInputStream(imgdata));
    	int width=image.getWidth();
    	int height=image.getHeight();
    	
    	if(width>w){//等比例缩放至500�?
    		height=(int)Math.round((height * w *1.0/ width));
    		Image img=image.getScaledInstance(w, height, Image.SCALE_SMOOTH);
    		image=new BufferedImage(w, height,BufferedImage.TYPE_INT_RGB);
    		image.getGraphics().drawImage(img, 0, 0, null);
    	}
    	if(height>h){
    		image=image.getSubimage(0, (height-h)/2, w, h);
    	}
/*    	if(width>w-3 && Math.abs(width-w)<=2){//按等�?00处理
    		if(height>h){
    			image=image.getSubimage(0, 0, width, h);
    		}
    	}*/
       if(imgtype!=PNG){
     	   JPEGEncodeParam jparam=JPEGCodec.getDefaultJPEGEncodeParam(image);
           jparam.setQuality(Quality, true);
           ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
           JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(imageStream,jparam);   
           encoder.encode(image);
           return new ImageUtil().new ImageBean(image.getWidth(),image.getHeight(),imageStream.toByteArray());
       }else{
    	   ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
    	   ImageIO.write(image, "png", imageStream);
    	   return new ImageUtil().new ImageBean(image.getWidth(),image.getHeight(),imageStream.toByteArray());
       }
    }
    
    
    
    public class ImageBean{
    	int width;
    	int height;
    	byte[] data;
    	
    	public ImageBean(int w,int h,byte[] d){
    		width=w;
    		height=h;
    		data=d;
    	}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public byte[] getData() {
			return data;
		}
    	
    }
    
    public static byte[] getImage(String imgUrl){
		try {
			int n = -1;
			byte[] temp = new byte[4096];  
			/*将网络资源地�?���?即赋值给url*/  
			URL url = new URL(imgUrl);
			/*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/  
			HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//DataInputStream in = new DataInputStream(connection.getInputStream());
			while((n=bis.read(temp))>0){
				baos.write(temp, 0, n);
			}
			bis.close();
			byte[] img = baos.toByteArray();
			baos.close();
			return img;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeImage(byte[] img){
		try {
			int n = -1;
			byte[] temp = new byte[4096];  
			ByteArrayInputStream baos = new ByteArrayInputStream(img);
			BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(new File("d://img.jpg")));
			while((n=baos.read(temp))>0){
				bis.write(temp, 0, n);
			}
			baos.close();
			bis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static void pluralToOne(File[] files) throws IOException{
    	BufferedImage ImageNew = new BufferedImage(440, 528, BufferedImage.TYPE_INT_ARGB);
    	//BufferedImage ImageNew = new BufferedImage(44, 22, BufferedImage.TYPE_INT_ARGB);
    	BufferedImage ImageRead=null;
    	int[] ImageArrayTmp =null;
    	int x=0,y=0;
    	for(File file:files){
    		if(x==20){
    			y+=22;
    			x=0;
    		}
    		ImageRead = ImageIO.read(file); 
    		ImageArrayTmp = ImageRead.getRGB(0, 0, 22 , 22, null,0, 22);
    		ImageNew.setRGB(x*22, y, 22, 22, ImageArrayTmp, 0, 22);// 设置部分的RGB
    		ImageRead.flush();
    		x++;
    	}
    	ImageIO.write(ImageNew, "png",new File("c:/new.png"));// 写图�?
    }
    
    
    public static void pressText(String targetImg) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
           
            g.drawImage(src, 0, 0,width,height, null);
            int fontSize=width<450?10:width/2/10;
            g.setColor(new Color(225,225,225));
            g.setFont(new Font("Dialog",Font.BOLD,fontSize));
            g.drawString("http://w.com.cn", width<450?0:width/3,height-10);
            g.dispose();
            FileOutputStream out = new FileOutputStream("d:/NewImage/tu5.jpg");
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public final static byte[] pressImage(byte[] imgdata,String type){
    	try{
    		BufferedImage image=ImageIO.read(new ByteArrayInputStream(imgdata));
            int width = image.getWidth();
            int height = image.getHeight();
            if(width<290 || height<120 || "gif".equals(type.toLowerCase())){
            	return imgdata;
            }else{
        		image=pressImage(image);
        		ByteArrayOutputStream imageStream=new ByteArrayOutputStream();
        		ImageIO.write(image, type, imageStream);
        		return imageStream.toByteArray();
            }
    	}catch(Exception e){
    		return imgdata;
    	}
    }
    
    
    public static final File WATER_IMAGE=new File(ImageUtil.class.getResource("/").getPath()+"/water_image.png");
    //public static final File WATER_IMAGE=new File("d:/NewImage/water_image.png");
    
    public final static BufferedImage pressImage(BufferedImage targetImg){
/*    	try{
    		if(WATER_IMAGE.exists()){
	    		Image waterImage = ImageIO.read(WATER_IMAGE);
	            int wideth = targetImg.getWidth(null);
	            int height = targetImg.getHeight(null);
	            BufferedImage image = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
	            Graphics2D g = image.createGraphics();
	            g.drawImage(targetImg, 0, 0, wideth, height, null);
	
	            //水印文件
	           
	            int wideth_biao = waterImage.getWidth(null);
	            int height_biao = waterImage.getHeight(null);
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1.0f));
	            g.drawImage(waterImage, (wideth - wideth_biao)-10,(height - height_biao)-10, wideth_biao, height_biao, null);
	            g.dispose();
	            return image;
    		}else{
    			return targetImg;
    		}
    	}catch(Exception e){
    		return targetImg;
    	}*/
    	return targetImg;
    }
    
    public static void main(String args[]) throws Exception {
    	
    	File file=new File("d:/104624_1448467360.png");
		   FileInputStream in = new FileInputStream(file);
		   byte ch[]=new byte[in.available()];
		   in.read(ch);
		   in.close();
		   //byte[] ob = subImage(ch,160,160);
		   
		   byte[] ob= ImageUtil.scaleImg(ch,630);
		   
		   FileOutputStream fout  = new FileOutputStream("d:/11111.jpg");
		   fout.write(ob);
		   fout.close();
		   
		 
/*    	BufferedImage image=pressImage(ImageIO.read(new File("d:/NewImage/012.jpg")));
        FileOutputStream out = new FileOutputStream("d:/NewImage/gray_5.jpg");
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
        out.close();*/
    	//System.out.println(ImageUtil.class.getResource("/").getPath());
    }
    
}
