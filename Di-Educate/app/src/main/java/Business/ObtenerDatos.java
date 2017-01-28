package Business;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import Data.Audio;
import Data.Enunciado;
import Data.Imagenes;
import Data.Respuestas;
import Data.Resultados;

/**
 * Created by cristian on 14/01/17.
 */

public class ObtenerDatos {

    private RestClient cliente= new RestClient("http://u017633.ehu.eus:28080/Di-EducateSERVER/rest/DiEducate");

    public Respuestas getRespuestas(int id) throws Exception{
        Respuestas respuestas=new Respuestas();

        JSONObject json;
        json = cliente.getJson("requestRespuestas?ejercicio="+id);
        JSONArray array =json.getJSONArray("respuesta");
        for (int i=0;i<array.length();i++){
            JSONObject item=array.getJSONObject(i);
            respuestas.setRespuestas(item.getString("textoRespuesta"));
            System.out.println(respuestas.getRespuestas().get(i));
        }
        return respuestas;
    }

    public Imagenes getImagenes(int id) throws Exception{
        Imagenes urls=new Imagenes();

        JSONObject json;
        json = cliente.getJson("requestImagenes?ejercicio="+id);
        JSONArray array =json.getJSONArray("imagen");
        for (int i=0;i<array.length();i++){
            JSONObject item=array.getJSONObject(i);
            urls.setImagenes(item.getString("url"));
            System.out.println(urls.getImagenes().get(i));
        }
        return urls;
    }

    public Audio getAudio(int id) throws Exception{
        Audio urls=new Audio();

        JSONObject json;
        json = cliente.getJson("requestAudios?ejercicio="+id);
        JSONArray array =json.getJSONArray("audio");
        for (int i=0;i<array.length();i++){
            JSONObject item=array.getJSONObject(i);
            urls.setAudios(item.getString("url"));
            System.out.println(urls.getAudios().get(i));
        }
        return urls;
    }

    public Enunciado getEnunciados(int id) throws Exception {
        Enunciado texto = new Enunciado();

        JSONObject json;
        json = cliente.getJson("requestEnunciados?ejercicio=" + id);
        JSONArray array = json.getJSONArray("textoEnunciado");
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);
            texto.setEnunciados(item.getString("textoEnunciado"));
            System.out.println(texto.getEnunciados().get(i));
        }
        return texto;
    }

    public Resultados getResultados(int id) throws Exception {
        Resultados resultados = new Resultados();

        JSONObject json;
        json = cliente.getJson("requestCalifications?userid=" + id);
        JSONArray array = json.getJSONArray("resultados");
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);
            resultados.setResultados(item.getString("aprobado"));
            System.out.println(resultados.getResultados().get(i));
        }
        return resultados;
    }

    public int postInfo(String userid,String ejercicio) throws Exception{
        JSONObject json=new JSONObject();
        JSONObject info=new JSONObject();

        json.put("username",userid);
        json.put("ejercicio",ejercicio);
        info.put("info",json);

        return cliente.postJson(json,"addInfo");
    }
    public int postResultados(String aprobado) throws Exception{
        JSONObject json=new JSONObject();
        JSONObject object=new JSONObject();

        json.put("aprobado",aprobado);
        object.put("aprobado",aprobado);

        return cliente.postJson(json,"addCalificaciones");
    }

    public String postRequestUser (String username, String pass) throws Exception{
        JSONObject json=new JSONObject();
        JSONObject object=new JSONObject();

        json.put("username",username);
        json.put("password",pass);
        object.put("usuario","usuario");

        return cliente.postUserJson(json,"requestUser");
    }
}
