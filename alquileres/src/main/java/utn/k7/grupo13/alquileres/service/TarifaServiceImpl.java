package utn.k7.grupo13.alquileres.service;


import org.springframework.stereotype.Service;
import utn.k7.grupo13.alquileres.domain.Alquiler;
import utn.k7.grupo13.alquileres.domain.Estacion;
import utn.k7.grupo13.alquileres.domain.Tarifa;
import utn.k7.grupo13.alquileres.repository.TarifaRepository;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class TarifaServiceImpl implements TarifaService{
    private TarifaRepository tarifaRepository;
    private EstacionService estacionService;


    public TarifaServiceImpl(TarifaRepository tarifaRepository, EstacionService estacionService) {
        this.tarifaRepository = tarifaRepository;
        this.estacionService = estacionService;
    }


    public Tarifa getTarifa(){
        LocalDateTime fecha_actual = LocalDateTime.now();
        //Obtener tarifa segun si es normal o con descuento
        Optional<Tarifa> optionalTarifa =tarifaRepository.getTarifaByDiaMesAndMesAndAnio(fecha_actual.getDayOfMonth()
                ,fecha_actual.getMonth().getValue(),fecha_actual.getYear());
        if(optionalTarifa.isPresent()){
            return optionalTarifa.get();
        }else {
            return tarifaRepository.getTarifaByDiaSemana(fecha_actual.getDayOfWeek().getValue());

        }
    }
    public double calcularTarifa(Alquiler alquiler, Tarifa tarifa) {
        LocalDateTime fecha_actual = LocalDateTime.now();
        Duration duracion = Duration.between(alquiler.getFechaHoraRetiro(), fecha_actual);

       //cantidad de horas y minutos que duro el alquiler
       Long duracion_horas = duracion.toHours();
       Long duracion_minutos = duracion.toMinutes()- (duracion_horas*60);

       //Tarifas
       double tarifa_total = 0;



        //monto fijo
        tarifa_total += tarifa.getMontoFijoAlquiler();
        System.out.println(tarifa_total);

        //monto por hora y minutos
        if(duracion_minutos >= 31 ){
            tarifa_total += tarifa.getMontoHora() * (duracion_horas + 1);
            System.out.println(tarifa_total);


        }else {
            tarifa_total += tarifa.getMontoHora() * duracion_horas;
            tarifa_total += tarifa.getMontoMinutoFraccion() * duracion_minutos;
            System.out.println(tarifa_total);
        }

        //monto por distancia
        Estacion estacion_retiro = estacionService.getEstacionById(alquiler.getEstacionRetiro()).get();
        Estacion estacion_devolucion = estacionService.getEstacionById(alquiler.getEstacionDevolucion()).get();




        tarifa_total += calcularDistancia(
                estacion_retiro.getLatitud(),
                estacion_retiro.getLongitud(),
                estacion_devolucion.getLatitud(),
                estacion_devolucion.getLongitud())
                * tarifa.getMontoKm();
        System.out.println(tarifa_total);

        return tarifa_total;
    }

    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Conversión de grados a metros (asumiendo que 1 grado = 110,000 metros)
        double LatitudMetros = Math.abs(lat1 - lat2) * 110;
        double LongitudMetros = Math.abs(lon1 - lon2) * 110;

        // Distancia euclídea
        return Math.sqrt(Math.pow(LatitudMetros, 2) + Math.pow(LongitudMetros, 2));

    }


}
