package mx.edu.utez.cotizacion.controller.service;

import mx.edu.utez.cotizacion.controller.dto.RequestEnvioDTO;
import mx.edu.utez.cotizacion.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.cotizacion.controller.dto.RequestRentaDTO;
import mx.edu.utez.cotizacion.controller.exception.ReglaNegocioException;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public double cotizarEnvio(RequestEnvioDTO payload) {
        double volumen = payload.getLargoCm() * payload.getAnchoCm() * payload.getAltoCm();

        if (volumen > 1000000) {
            throw new ReglaNegocioException("No se aceptan paquetes con volumen mayor a 1,000,000 cm^3");
        }


        double costo = 80;

        costo = costo + payload.getPesoKg() * 12;

        if (volumen > 50000) {
            costo = costo + 100;

        }

        if (payload.getTipoEnvio().equals("EXPRESS")) {
            costo = costo * 1.40;

        } else if (payload.getTipoEnvio().equals("MISMO_DIA")) {
            costo = costo * 1.70;

        }



        if (payload.getValorDeclarado() > 10000) {
            costo = costo + payload.getValorDeclarado() * 0.02;

        }

        return redondear(costo);
    }

    public double cotizarRenta(RequestRentaDTO payload) {
        if (payload.getTipoVehiculo().equals("CAMIONETA") && payload.getEdadConductor() < 25) {
            throw new ReglaNegocioException("No se puede rentar una CAMIONETA si el conductor es menor de 25 años");

        }


        double costoDiario = 0;

        switch (payload.getTipoVehiculo()) {
            case "COMPACTO" -> costoDiario = 550;
            case "SEDAN" -> costoDiario = 700;
            case "SUV" -> costoDiario = 950;
            case "CAMIONETA" -> costoDiario = 1200;

        }

        double costoRenta = costoDiario * payload.getDiasRenta();


        int kmIncluidos = payload.getDiasRenta() * 100;
        int kmAdicionales = payload.getKilometrosEstimados() - kmIncluidos;
        double cargoKm = 0;
        if (kmAdicionales > 0) {
            cargoKm = kmAdicionales * 4;

        }


        double cargoEdad = 0;

        if (payload.getEdadConductor() >= 18 && payload.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKm) * 0.15;

        }

        double seguro = 0;
        if (payload.isSeguroCompleto()) {
            seguro = 180 * payload.getDiasRenta();

        }

        double descuento = 0;
        if (payload.getDiasRenta() >= 7) {
            descuento = costoRenta * 0.10;

        }

        double total = costoRenta - descuento + cargoKm + cargoEdad + seguro;
        return redondear(total);
    }

    public double cotizarHospedaje(RequestHospedajeDTO payload) {

        double costoNoche = 0;
        int capacidad = 0;

        switch (payload.getTipoHabitacion()) {
            case "INDIVIDUAL" -> { costoNoche = 700; capacidad = 1; }
            case "DOBLE" -> { costoNoche = 1100; capacidad = 2; }
            case "SUITE" -> { costoNoche = 1800; capacidad = 4; }

        }

        if (payload.getNumeroHuespedes() > capacidad) {
            throw new ReglaNegocioException("La habitación " + payload.getTipoHabitacion()
                    + " solo permite " + capacidad + " persona(s)");

        }

        int noches = payload.getNumeroNoches();
        double costoHospedaje = costoNoche * noches;
        double ajusteTemporada = 0;

        if (payload.getTemporada().equals("BAJA")) {
            ajusteTemporada = -(costoHospedaje * 0.10);
        } else if (payload.getTemporada().equals("ALTA")) {
            ajusteTemporada = costoHospedaje * 0.25;
        }

        double desayuno = 0;
        if (payload.isIncluyeDesayuno()) {
            desayuno = payload.getNumeroHuespedes() * noches * 150;
        }

        double estacionamiento = 0;
        if (payload.isIncluyeEstacionamiento()) {
            estacionamiento = noches * 100;
        }

        double descuento = 0;
        if (noches >= 7) {
            descuento = (costoHospedaje + ajusteTemporada) * 0.08;
        }

        double subtotal = costoHospedaje + ajusteTemporada - descuento + desayuno + estacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        return redondear(total);
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
