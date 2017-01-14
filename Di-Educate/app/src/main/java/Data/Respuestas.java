package Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cristian on 14/01/17.
 */

public class Respuestas implements Serializable{

    private ArrayList<String> respuestas =new ArrayList<>();
    //private String texto_respuesta;

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuesta) {
        respuestas.add(respuesta);
    }

    /*public String getTexto_respuesta() {
        return texto_respuesta;
    }*/

}
