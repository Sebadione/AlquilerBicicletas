package utn.k7.grupo13.alquileres.application.request;

public enum Moneda {
    ARS(1),
    USD(0.0029) ,
    EUR(0.0027),
    JPY(0.43), // Yen Japones
    GBP(0.0023), // Libra Esterlina
    BRL(0.014 ), // Real Brasileño
    CLP(2.71), // Peso Chileno
    CNY(0.021), // Yuan Chino
    COP(0.011), // Peso Colombiano
    MXN(0.057), // Peso Mexicano
    PEN(0.011), // Sol Peruano
    UYU(0.12), // Peso Uruguayo
    VES(0.00000000000), // Bolivar Venezolano
    SEK(0.025); // Corona Sueca



    private final double valor;
    Moneda (double valor) {
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
}
