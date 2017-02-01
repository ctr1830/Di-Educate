package eus.ehu.tta.intel.di_educate.modelo;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cristian on 14/01/17.
 */

public class RestClient {
    private final static String AUTH="Authorization";
    private final String baseUrl;
    private final Map<String,String> properties =new HashMap<>();

    public RestClient(String baseUrl){
        this.baseUrl= baseUrl;
    }

    public void setHttpBasicAuth (String user, String passwd){
        String basicAuth = Base64.encodeToString(String.format("%s:%s",user,passwd).getBytes(),Base64.DEFAULT);
        properties.put(AUTH,String.format("Basic %s",basicAuth));
    }

    public String getAuthorization(){
        return properties.get(AUTH);
    }

    public void setAuthorization(String auth){
        properties.put(AUTH,auth);
    }

    public void setProperty (String name, String value){
        properties.put(name,value);
    }

    private HttpURLConnection getConnection(String path) throws IOException {
        URL url=new URL(String.format("%s/%s",baseUrl,path));
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        for (Map.Entry<String,String> property : properties.entrySet())
            conn.setRequestProperty(property.getKey(),property.getValue());
        conn.setUseCaches(false);
        return conn;
    }

    public String getString(String path) throws IOException{
        HttpURLConnection conn=null;
        try{
            conn=getConnection(path);
            BufferedReader br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String cadena = new String();
            cadena=br.readLine();
            br.close();
            return cadena;
        }finally {
            if(conn!=null){
                conn.disconnect();
            }
        }
    }

    public JSONObject getJson(String path)throws IOException, JSONException {
        return new JSONObject(getString(path));
    }

    public int postJson (final JSONObject json, String path) throws IOException{
        HttpURLConnection conn = null;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type","application/json; charset=UTF-8");
            PrintWriter pw= new PrintWriter(conn.getOutputStream());
            pw.print(json.toString());
            pw.close();
            return conn.getResponseCode();
        }
        finally {
            if(conn!=null){
                conn.disconnect();
            }
        }
    }

    public String postUserJson (final JSONObject json, String path) throws IOException{
        String alternative;
        HttpURLConnection conn = null;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type","application/json; charset=UTF-8");
            PrintWriter pw= new PrintWriter(conn.getOutputStream());
            pw.print(json.toString());
            pw.close();
            if(conn.getResponseCode()==200) {
                BufferedReader br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String cadena = new String();
                cadena=br.readLine();
                br.close();
                return cadena;
            }
            else{
                alternative=Integer.toString(conn.getResponseCode())+";null";
                return alternative;
            }
        }
        finally {
            if(conn!=null){
                conn.disconnect();
            }
        }
    }
}
