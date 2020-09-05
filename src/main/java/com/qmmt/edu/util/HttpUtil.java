package com.qmmt.edu.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	
	// 缓存大小决定下载速度
	public static void readStreamflush(InputStream input, OutputStream output, int content_length) throws IOException {
		if (content_length <= 0)
			return;
		int needread = content_length;
		byte[] body = null;
		while (true) {
			if (needread < 128000 && (body == null || body.length != needread)) {
				body = new byte[needread];
			} else {
				if (body == null || body.length != 128000)
					body = new byte[128000];
			}

			int haveread = input.read(body);
			if (haveread == -1) {
				output.flush();
				throw new EOFException("EOF when reading request's headers");
			}
			output.write(body, 0, haveread);
			output.flush();

			needread = needread - haveread;
			if (needread <= 0) {
				break;
			}

		}
		output.flush();
	}

	public static String getInputStr(InputStream input, int content_length) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		readStreamflush(input, bout, content_length);
		return new String(bout.toByteArray(), "UTF-8");
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendHTTPGet(String url, String param) {
		String result = "";
		InputStream in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
//					for (String key : map.keySet()) {
//						System.out.println(key + "--->" + map.get(key));
//					}
			// 定义BufferedReader输入流来读取URL的响应
			int length = conn.getContentLength();
			in = conn.getInputStream();
			result = getInputStr(in, length);		
			
//					in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//					String line;
//					while ((line = in.readLine()) != null) {
//						result += "/n" + line;
//					}
			
			conn.disconnect();

		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendHTTPPost(String url, String param) {
		OutputStream out = null;
		InputStream in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
	        conn.setRequestMethod("POST");// 提交模式
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			
			out = conn.getOutputStream();
			
			out.write(param.getBytes("UTF-8"));// 输入参数
			out.flush();
	        in=conn.getInputStream();
	        
			
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
//					for (String key : map.keySet()) {
//						System.out.println(key + "--->" + map.get(key));
//					}
			// 定义BufferedReader输入流来读取URL的响应
			int length = conn.getContentLength();

			result = getInputStr(in, length);		
			
//					in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//					String line;
//					while ((line = in.readLine()) != null) {
//						result += "/n" + line;
//					}
			
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendHTTPPost(String url, String param,Map<String,String> header) {
		OutputStream out = null;
		InputStream in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
	        conn.setRequestMethod("POST");// 提交模式
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			if(header!=null){
				Iterator iterator = header.keySet().iterator();
				while(iterator.hasNext()){
					String key = (String)iterator.next();
					conn.setRequestProperty(key, header.get(key));
				}
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			
			out = conn.getOutputStream();
			
			out.write(param.getBytes("UTF-8"));// 输入参数
			out.flush();
	        in=conn.getInputStream();
	        
			
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
//					for (String key : map.keySet()) {
//						System.out.println(key + "--->" + map.get(key));
//					}
			// 定义BufferedReader输入流来读取URL的响应
			int length = conn.getContentLength();

			result = getInputStr(in, length);		
			
//					in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//					String line;
//					while ((line = in.readLine()) != null) {
//						result += "/n" + line;
//					}
			
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String getMac() {
		String macstr = null;
		try {
			Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
			while (el.hasMoreElements()) {
				NetworkInterface netinter = el.nextElement();
				Enumeration<InetAddress> inadd = netinter.getInetAddresses();
				String ip = "127.0.0.1";
				while (inadd.hasMoreElements()) {
					InetAddress ia = inadd.nextElement();
					ip = ia.getHostAddress();
				}

				byte[] mac = netinter.getHardwareAddress();

				if (mac == null)
					continue;

				StringBuilder builder = new StringBuilder();
				for (byte b : mac) {
					builder.append(parseByte(b));
					builder.append("-");
				}
				if(builder.length()>1)
					builder.deleteCharAt(builder.length() - 1);
				macstr =  builder.toString();
				System.out.println(ip +" "+macstr);

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return macstr;
	}
	
	//dmidecode -s system-serial-number
	
	public static void getSerialNumber() throws IOException{
		String command = "dmidecode -s system-serial-number";
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
//          System.out.println("line:"+line);
          if (line.indexOf("HWaddr") > 0) {
            System.out.println(line.substring(line.indexOf("HWaddr")+6));
          }
        }
        br.close();
	}
	/**
	 * 安装CutyCapt 
	 * 参考：https://linux.cn/article-2708-1.html
	 * 
	 * @throws IOException
	 */
	public static void htmlToImage(String url,String filename) throws IOException{
		//wkhtmltoimage http://www.sohu.com sohu.jpg
		String command = "wkhtmltoimage --height 1060 --width 630 --quality 100  "+url+" "+filename;
		//String command = "xvfb-run --server-args=\"-screen 0, 1024x768x24\" CutyCapt --url=http://www.baidu.com --out=baidu.png";
        System.out.println(command);
		Process p = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
        	System.out.println(line);
        }
        br.close();
	}
	
	public static void getCmdMac() throws IOException{
		String command = "ifconfig";
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
//          System.out.println("line:"+line);
          if (line.indexOf("HWaddr") > 0) {
            System.out.println(line.substring(line.indexOf("HWaddr")+6));
          }
        }
        br.close();
	}
	
	public static String getLocalIp() {
		String ip = null;
		try {
			Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
			while (el.hasMoreElements()) {
				NetworkInterface netinter = el.nextElement();
				Enumeration<InetAddress> inadd = netinter.getInetAddresses();
				
				while (inadd.hasMoreElements()) {
					InetAddress ia = inadd.nextElement();
					ip = ia.getHostAddress();
				}

				if(ip != null && !ip.equals("127.0.0.1")){
					return ip;
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return ip;
	}
	
	//������λ��mac�м䴮
	private static String parseByte(byte b) {
		int intValue = 0;
		if (b >= 0) {
			intValue = b;
		} else {
			intValue = 256 + b;
		}
		String s = Integer.toHexString(intValue);
		if(s.length() == 1){
			return "0"+s;
		}else{
			return s;
		}
	}
	
	
	public static byte[] readFile(String path){
		byte[] b = null;
		if(!isFileExit(path))
			return b;
				
		try{
			FileInputStream fin = new FileInputStream(path);
			fin.read(b);
			fin.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	
	public static boolean isFileExit(String path){
		File f = new File(path);
		return f.exists();
	}
	
	public static void writeFile(String path,byte[] b){
		try{
			FileOutputStream fout = new FileOutputStream(path);
			fout.write(b);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main (String[] param) {
		
		System.out.println(StringUtil.getUUID());
		
		long t = System.currentTimeMillis()+360*24*60*60*1000L;
		System.out.println(t/1000);
		System.out.println(new Date(t));
		
		
		Calendar cal = Calendar.getInstance();  
        cal.setTime(new Date(System.currentTimeMillis()+7*24*3600*1000));  
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));  
       System.out.println(cal.get(Calendar.DAY_OF_WEEK));  
		
		
		
		long btime = System.currentTimeMillis();
		String result = "";
		InputStream in = null;
		try {
			String urlName = "http://access.dryork.cn/yueke/api/patient/get_patients.json";
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			conn.setConnectTimeout(600000);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("x-uid", "104349");
			conn.setRequestProperty("x-cookie", "8B3581AF86F1476E8A9E7D2EA99E2890");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
//					for (String key : map.keySet()) {
//						System.out.println(key + "--->" + map.get(key));
//					}
			// 定义BufferedReader输入流来读取URL的响应
			int length = conn.getContentLength();
			in = conn.getInputStream();
			result = getInputStr(in, length);		
			System.out.println(result);
//					in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//					String line;
//					while ((line = in.readLine()) != null) {
//						result += "/n" + line;
//					}
			
			conn.disconnect();

		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		
		
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(System.currentTimeMillis() - btime);
//		return result;
	}
	
	public static void main2 (String[] args) {
		
		
		
		
		String url = "http://localhost:8080/yueke/api/dentist/get_sysassis_list.json";
		
		String param = "begin_time=2016-04-20 12:09:46&end_time=2016-05-30 12:09:46";
		
		Map<String,String> header = new HashMap<String,String>();
		header.put("x-uid", "112001");
		header.put("x-cookie", "DAA64B38E23F4356B3A81875478830FA");
		
		System.out.println(sendHTTPPost( url,  param,header));
	}

}
