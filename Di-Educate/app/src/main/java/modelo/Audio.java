package modelo;

import java.util.ArrayList;

/**
 * Created by cristian on 18/01/17.
 */

public class Audio {
    private ArrayList<String> urls =new ArrayList<>();

    public ArrayList<String> getAudios() {
        return urls;
    }

    public void setAudios(String respuesta) {
        urls.add(respuesta);
    }

}
