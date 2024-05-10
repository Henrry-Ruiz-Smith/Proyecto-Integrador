package com.crud.proyecto.prestamo.entity;

import java.util.Date;

import com.crud.proyecto.usuario.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;
    private double montoDiario;

    private int nroCuotas;
    private String tipoCuotas;
    private String estado;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaFin; 

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrestatario")
    private Usuario idPrestatario;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrestatamista")
    private Usuario idPrestatamista;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date fechaRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date fechaActualizacion;
}
