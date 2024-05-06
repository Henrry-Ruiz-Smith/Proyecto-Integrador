package com.crud.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.entity.Prestamo;
import com.crud.proyecto.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl {

    @Autowired
    private PrestamoRepository prestamoRepository;

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

    public void solicitar(Prestamo prestamo) {
        prestamoRepository.save(prestamo);
    }
}
