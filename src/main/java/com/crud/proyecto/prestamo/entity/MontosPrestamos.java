package com.crud.proyecto.prestamo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "montos_prestamos")
public class MontosPrestamos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int dias;
    private double monto;

}
