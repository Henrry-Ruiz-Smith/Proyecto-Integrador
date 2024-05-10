package com.crud.proyecto.zona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.zona.entity.Zona;
import com.crud.proyecto.zona.repository.ZonaRepository;

@Service
public class ZonaServiceImpl implements IZonaService {
    @Autowired
    ZonaRepository zonaRepository;

    @Override
    public List<Zona> findAll() {
        return zonaRepository.findAll();
    }

}
