package lavadora;

public abstract class FactoriaLavadoras implements Factoria {
    LavadoraGeneratorLamda createNewLavadora;

    @Override
    public Lavadora creaLavadora() {
        Lavadora newLavadora = createNewLavadora.createNewLavadora();

        newLavadora.ponerMandos();
        newLavadora.ponerTambor();

        return newLavadora;
    }

//    abstract Lavadora createNewLavadora();
}
