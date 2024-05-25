package Delivroo.JSONPagenation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONReader {
	HashMap<String,PriorityQueue<Shows>> hm=
			new HashMap<String,
			 PriorityQueue<Shows>>();

	public void readJSON() {
		// TODO Auto-generated method stub
		String url ="https://jsonmock.hackerrank.com/api/tvseries?page=";
		String json =getHTML(url);
		//System.out.println(json);
		JSONObject jsonObject=new JSONObject(json);
		int totalPages=jsonObject.getInt("total_pages");
		//System.out.println("totalPages"+totalPages);

			createMap(url,totalPages);
			
			//System.out.println(hm);


	}

	private void createMap(String url,int totalPages) {
		// TODO Auto-generated method stub
		for(int i =1;i<totalPages;i++)
		{
			String surl=url+i;
			String json=getHTML(surl);
			JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            for(int j=0;j<data.length();j++)
            {
                JSONObject show = data.getJSONObject(j);
                Shows s=new Shows(show.getString("name"), 
                		show.getDouble("imdb_rating"));
                String genere=show.getString("genre");
                createQueue(s,genere);

            }
			
		}

	}

	private void createQueue(Shows s, String genere) {
		// TODO Auto-generated method stub
		
		hm.computeIfAbsent(genere, k->new PriorityQueue<Shows>()).add(s);
		
	}

	private String getHTML(String surl) {
		// TODO Auto-generated method stub
		
		StringBuilder sb=new StringBuilder();
		URLConnection urlCon=null;
		InputStreamReader in =null;
		
		try {
			URL url =new URL(surl);
			urlCon=url.openConnection();
			if(urlCon!=null)
			{
				urlCon.setReadTimeout(60*100);
			}
			if(urlCon!=null && urlCon.getInputStream()!=null)
			{
				in =new InputStreamReader(urlCon.getInputStream(), 
						Charset.defaultCharset());
				
				BufferedReader br=new BufferedReader(in);
				if(br!=null)
				{
					int cp;
					while((cp=br.read())!=-1)
					{
						sb.append((char)cp);
					}
					br.close();
				}
				in.close();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String findShow(String genre) {
		// TODO Auto-generated method stub
		
		String name =hm.get(genre).peek().name;
		return name;
	}
	

}
