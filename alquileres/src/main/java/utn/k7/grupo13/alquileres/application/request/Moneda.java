package utn.k7.grupo13.alquileres.application.request;

public enum Moneda {
    USD("USD"),
    ARS("ARS"),
    EUR("EUR"),
    CLP("CLP"),
    BRL("BRL"),
    COP("COP"),
    PEN("PEN"),
    GBP("GBP")
    ;

    private final String valor;



    Moneda (String valor) {
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }
}
