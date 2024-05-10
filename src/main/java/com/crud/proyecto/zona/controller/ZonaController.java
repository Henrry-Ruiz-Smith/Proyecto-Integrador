package com.crud.proyecto.zona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.proyecto.zona.entity.Zona;
import com.crud.proyecto.zona.service.IZonaService;

@Controller
@RequestMapping("/zona")
public class ZonaController {

    @Autowired
    private IZonaService usuarioService;

    @GetMapping("/listarZonas")
    @ResponseBody
    public List<Zona> listarZonas() {
        return usuarioService.findAll();
    }

}
