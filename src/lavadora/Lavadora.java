package lavadora;

public class Lavadora implements Lavar {
    public String tipoCarga = "";
    public boolean tieneMandos = false,
                    tieneTambor = false;


    @Override
    public void ponerMandos() {
        tieneMandos = true;
    }

    @Override
    public void ponerTambor() {
        tieneTambor = true;
    }
}
