package utn.k7.grupo13.alquileres.service;

import utn.k7.grupo13.alquileres.domain.Alquiler;
import utn.k7.grupo13.alquileres.domain.Tarifa;



public interface TarifaService {
    public double calcularTarifa(Alquiler alquiler, Tarifa tarifa);
    public Tarifa getTarifa();
}
