package com.crud.proyecto.cuotas.service;

import java.util.List;

import com.crud.proyecto.cuotas.entity.Cuota;
import com.crud.proyecto.prestamo.entity.Prestamo;

public interface ICuotaService {

    List<Cuota> registrarCuotas(Prestamo objPrestamo);
}
