package mx.edu.utez.cotizacion.controller;

import jakarta.validation.Valid;
import mx.edu.utez.cotizacion.controller.dto.RequestEnvioDTO;
import mx.edu.utez.cotizacion.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.cotizacion.controller.dto.RequestRentaDTO;
import mx.edu.utez.cotizacion.controller.service.MyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping({"/cotizacion"})
public class MyController {

    private final MyService service;
    public MyController(MyService service) {
        this.service = service;
    }

    @PostMapping("/envio")
    public ResponseEntity<Map<String, Object>> cotizarEnvio(@RequestBody @Valid RequestEnvioDTO payload) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("tipoEnvio", payload.getTipoEnvio());
        respuesta.put("total", service.cotizarEnvio(payload));

        return ResponseEntity.ok(respuesta);

    }

    @PostMapping("/renta")
    public ResponseEntity<Map<String, Object>> cotizarRenta(@RequestBody @Valid RequestRentaDTO payload) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("cliente", payload.getNombreCliente());
        respuesta.put("total", service.cotizarRenta(payload));

        return ResponseEntity.ok(respuesta);

    }

    @PostMapping("/hospedaje")
    public ResponseEntity<Map<String, Object>> cotizarHospedaje(@RequestBody @Valid RequestHospedajeDTO payload) {

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("huesped", payload.getNombreHuesped());
        respuesta.put("total", service.cotizarHospedaje(payload));

        return ResponseEntity.ok(respuesta);
    }
}
