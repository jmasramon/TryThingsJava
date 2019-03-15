package lavadora;

public class LambdedFactoriaLavadoras extends FactoriaLavadoras {
    public LambdedFactoriaLavadoras(LavadoraGeneratorLamda lamda) {
        createNewLavadora = lamda;
    }


}
