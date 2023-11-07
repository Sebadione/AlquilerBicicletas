package utn.k7.grupo13.alquileres.infraestrucura.adapters;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private T data;

}
