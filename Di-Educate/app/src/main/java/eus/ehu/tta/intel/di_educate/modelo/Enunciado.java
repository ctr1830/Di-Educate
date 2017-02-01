package eus.ehu.tta.intel.di_educate.modelo;

import java.util.ArrayList;

/**
 * Created by cristian on 19/01/17.
 */

public class Enunciado {
    private ArrayList<String> enunciados =new ArrayList<>();

    public ArrayList<String> getEnunciados() {
        return enunciados;
    }

    public void setEnunciados(String enunciado) {
        enunciados.add(enunciado);
    }
}
