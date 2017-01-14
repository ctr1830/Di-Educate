package Business;

import org.json.JSONArray;
import org.json.JSONObject;

import Data.Respuestas;

/**
 * Created by cristian on 14/01/17.
 */

public class ObtenerDatos {

    private RestClient cliente= new RestClient("http://u017633.ehu.eus:28080/Di-EducateSERVER/rest/DiEducate");

    public Respuestas getRespuestas() throws Exception{
        Respuestas respuestas=new Respuestas();

        JSONObject json;
        json = cliente.getJson("requestRespuestas?ejercicio=1");
        JSONArray array =json.getJSONArray("respuesta");
        for (int i=0;i<array.length();i++){
            JSONObject item=array.getJSONObject(i);
            respuestas.setRespuestas(item.getString("textoRespuesta"));
            System.out.println(respuestas.getRespuestas().get(i));
        }
        return respuestas;
    }
}
