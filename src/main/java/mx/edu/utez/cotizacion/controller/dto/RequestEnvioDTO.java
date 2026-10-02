package mx.edu.utez.cotizacion.controller.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
public class RequestEnvioDTO {

    @NotBlank(message = "El codigo postal es obligatorio")
    @Pattern(regexp = "^\\d{5}$", message = "El codigo postal debe tener 5 digitos")
    private String codigoPostal;

    @DecimalMax(value = "50", message = "no se aceptan paquetes de mas de 50 kg")
    private double pesoKg;

    @DecimalMax(value = "150", message = "Ninguna dimension puede ser mayor a 150 cm")
    private double largoCm;

    @DecimalMax(value = "150", message = "Ninguna dimension puede ser mayor a 150 cm")
    private double anchoCm;

    @DecimalMax(value = "150", message = "Ninguna dimension puede ser mayor a 150 cm")
    private double altoCm;


    @NotBlank(message = "El tipo de envio es obligatorio")
    @Pattern(regexp = "ESTANDAR|EXPRESS|MISMO_DIA", message = "El tipo de envío debe ser estandar express o mismo_dia")
    private String tipoEnvio;

    @DecimalMin(value = "0", message = "El valor declarado no puede ser negativo")
    private double valorDeclarado;
}
