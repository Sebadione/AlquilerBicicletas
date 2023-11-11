package utn.k7.grupo13.alquileres.infraestrucura.adapters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonedaRequest {

    String moneda_destino;
    Double importe;
}
