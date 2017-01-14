package Business;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import Data.Imagenes;
import Data.Respuestas;

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
}
