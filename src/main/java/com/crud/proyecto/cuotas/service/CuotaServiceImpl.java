package com.crud.proyecto.cuotas.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.cuotas.entity.Cuota;
import com.crud.proyecto.cuotas.repository.CuotaRepository;
import com.crud.proyecto.prestamo.entity.Prestamo;

/**
 * CuotaServiceImpl
 */
@Service
public class CuotaServiceImpl implements ICuotaService {
    private final String ESTADO_PENDIENTE = "PENDIENTE";
    @Autowired
    CuotaRepository cuotaRepository;

    @Override
    public List<Cuota> registrarCuotas(Prestamo objPrestamo) {

        List<Cuota> listCuotas = new ArrayList<>();
        Date hoy = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(objPrestamo.getFechaInicio());

        // Iterar sobre el número de cuotas
        for (int i = 0; i < objPrestamo.getNroCuotas();) {
            // Obtener el día de la semana actual
            int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
            // Si no es sábado (día 7) ni domingo (día 1), incrementar el contador y crear
            // la cuota
            if (diaSemana != Calendar.SATURDAY && diaSemana != Calendar.SUNDAY) {

                Cuota obj = new Cuota();

                obj.setMonto(objPrestamo.getMontoDiario());
                obj.setPrestamo(objPrestamo);
                obj.setEstadoPago(ESTADO_PENDIENTE);
                obj.setFechaRegistro(hoy);
                obj.setFechaActualizacion(hoy);
                obj.setFechaVencimiento(cal.getTime());
                listCuotas.add(obj);
                i++; // Incrementar el contador solo si se agrega una cuota válida
            }
            // Avanzar un día en el calendario
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return cuotaRepository.saveAll(listCuotas);
    }

}