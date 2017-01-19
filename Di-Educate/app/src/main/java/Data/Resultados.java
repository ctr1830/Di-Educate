package Data;

import java.util.ArrayList;

/**
 * Created by cristian on 19/01/17.
 */

public class Resultados {

    private boolean result=false;
    private ArrayList<String> resultados =new ArrayList<>();

    public ArrayList<String> getResultados() {
        return resultados;
    }

    public void setResultados(String resultado) {
        resultados.add(resultado);
    }
}
