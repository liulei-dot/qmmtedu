package com.wechat.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class TestMain {

	public static void main(String[] args) throws Exception {
		
		String zimu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for(int i=0;i<zimu.length();i++) {
			System.out.println(zimu.charAt(i));
			String content = "http://ke.qmmtedu.com/26engword/"+zimu.charAt(i)+".mp3";
		     String path = "/Users/lilianzhi/Documents/";
		     
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		     File file1 = new File(path,zimu.charAt(i)+".jpg");
		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1); 
		}
		
		
		
		
	     System.out.println("----");
	}

}
