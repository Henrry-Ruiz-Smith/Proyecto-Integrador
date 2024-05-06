package com.crud.proyecto.zona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaServiceImpl implements IZonaService {
    @Autowired
    ZonaRepository zonaRepository;

    @Override
    public List<Zona> findAll() {
        return zonaRepository.findAll();
    }

}
