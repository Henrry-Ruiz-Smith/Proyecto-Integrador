package com.crud.proyecto.prestamo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.prestamo.entity.MontosPrestamos;
import com.crud.proyecto.prestamo.entity.Prestamo;
import com.crud.proyecto.prestamo.repository.MontosPrestamosRepository;
import com.crud.proyecto.prestamo.repository.PrestamoRepository;
import com.crud.proyecto.usuario.entity.Usuario;

@Service
public class PrestamoServiceImpl {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private MontosPrestamosRepository montosPrestamos;

    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    public Prestamo obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Prestamo actualizarPrestamo(Long id, Prestamo prestamo) {
        prestamo.setId(id);
        return prestamoRepository.save(prestamo);
    }

    public void eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }

    public Prestamo solicitar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public List<MontosPrestamos> listarMontosPrestamos() {
        return montosPrestamos.findAll();
    }

    public List<Prestamo> listarPrestamosPorIdPrestamista(Usuario prestamista) {
        return prestamoRepository.findByIdPrestatamista(prestamista);
    }

    public List<Prestamo> listarPrestamosPorPrestatario(Usuario prestatario) {
        return prestamoRepository.findByIdPrestatario(prestatario);
    }

    public List<Prestamo> buscarPrestamosPorNombrePrestatarioYRangoFechas(String nombre, Date fechaIni,Date fechaFi) {
        return prestamoRepository.buscarPrestamosPorNombrePrestatarioYRangoFechas(nombre, fechaIni,fechaFi);
    }
}
