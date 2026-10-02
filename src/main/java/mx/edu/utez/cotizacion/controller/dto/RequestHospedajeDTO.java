package mx.edu.utez.cotizacion.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestHospedajeDTO {


    @NotBlank(message = "El nombre del huesped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "el tipo de habitación es obligatorio")
    @Pattern(regexp = "INDIVIDUAL|DOBLE|SUITE", message = "El tipo de habitación debe ser INDIVIDUAL, DOBLE o SUITE")
    private String tipoHabitacion;

    @Min(value = 1, message = "debe ser al menos 1 noche")
    @Max(value = 30, message = "no se aceptan reservaciones de más de 30 noches")
    private int numeroNoches;

    @Min(value = 1, message = "Debe haber al menos 1 huesped")
    private int numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    @Pattern(regexp = "BAJA|REGULAR|ALTA", message = "La temporada debe ser baja, regular o alta")
    private String temporada;

    private boolean incluyeDesayuno;

    private boolean incluyeEstacionamiento;
}
