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
public class RequestRentaDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @Min(value = 18, message = "No se acepta la renta si el conductor es menor de 18")
    private int edadConductor;

    @NotBlank(message = "El tipo de vehiculo es obligatorio")
    @Pattern(regexp = "COMPACTO|SEDAN|SUV|CAMIONETA", message = "El tipo de vehiculo debe ser COMPACTO SEDAN SUV o CAMIONETA")
    private String tipoVehiculo;

    @Min(value = 1, message = "La renta debe ser de por lo menos 1 dia")
    @Max(value = 30, message = "La renta no puede ser mayyor de 30 dias")
    private int diasRenta;

    @Min(value = 0, message = "Los kilometros no pueden ser negativos")
    @Max(value = 5000, message = "Los kilometros no pueden superar 5,000")
    private int kilometrosEstimados;

    private boolean seguroCompleto;
}
