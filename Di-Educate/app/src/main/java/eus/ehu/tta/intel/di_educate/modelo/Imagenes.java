package eus.ehu.tta.intel.di_educate.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cristian on 14/01/17.
 */

public class Imagenes implements Serializable {
    private ArrayList<String> urls =new ArrayList<>();

    public ArrayList<String> getImagenes() {
        return urls;
    }

    public void setImagenes(String respuesta) {
        urls.add(respuesta);
    }

}
