package com.example.map_mobile.mpis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value="mpisJsonService")
public class MpisJsonService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static String BASE_URL = "https://www.kotsa.or.kr";
	
	public String getPostData(String urls,  Map  param) throws IOException {
		
		//URL url = new URL ("https://www.kotsa.or.kr/mpis/pub/eai/list/box2.do?");
		URL url = new URL (BASE_URL+ urls);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json"); 
		con.setDoOutput(true);
		
		
		
		//JSON String need to be constructed for the specific resource. 
		//We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
		//String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
		
	 
		//String info = "sGpsX=127.02449487578878&eGpsX=127.02805955786961&sGpsY=37.637235998908054&eGpsY=37.64117701348559";
		
		//info = "buldSn=14692";
		
		StringBuffer buf = new StringBuffer();
		
		Iterator<String> iterate = param.keySet().iterator();
		
		while(iterate.hasNext()) {
			String p = iterate.next();
			buf.append(p);
			buf.append("=");
			buf.append(param.get(p));
			buf.append("&");
		}
		
		
		try(OutputStream os = con.getOutputStream()){
			byte[] input = buf.toString().getBytes("utf-8");
			os.write(input, 0, input.length-1);		
			System.out.println(new String(input));
		}

		int code = con.getResponseCode();
		System.out.println(code);
		StringBuilder response = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
			
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println();
		}
		return response.toString();
	}

}
