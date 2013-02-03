package net.myflickpicks.android.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.model.GraphUser;

public class FlickPicksParser {
	public static List<String> getSearchResults(GraphUser user)
	{
		String jsonString = "";
		List<String> results = new ArrayList<String>();
	
		try {
			jsonString = downloadString("http://myflickpicks.net/php/db/getusersmovielist.php?UserID=" + user.getId());
		} catch (IllegalStateException e1) {//user.getId().replace(" ", "%20")
			// TODO Auto-generated catch block
			results.add("ERROR: Illegal State Exception");
			return results;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			results.add("ERROR: IO Exception");
			return results;
		}
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			Iterator iter = jsonObject.keys();
			
			while (iter.hasNext())
			{
				String key = (String)iter.next();
				if (key.equalsIgnoreCase("_id") == false && key.equalsIgnoreCase("UserID") == false)
				{
					results.add(key);
				}				
			}
			
			
			return results;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			results.add("ERROR: JSON Exception");
			results.add(jsonString);
			return results;
		}
		
		
		//for (int i = 0; i < 10; i++)
		//{
			//results.add(page);
		//}
		
		//return results;
	}
	
	public static String downloadString(String inputString) throws IllegalStateException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		String website = inputString;
		HttpGet httpGet = new HttpGet(website);
		httpGet.setHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(httpGet, localContext);
		String result = "";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String currentLine = reader.readLine();
		while(currentLine != null) {
			result += currentLine + "\n";
			currentLine = reader.readLine();
		}
		
		return result;
	}

}
