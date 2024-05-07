package com.crud.proyecto.prestamo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.proyecto.usuario.Usuario;

import jakarta.servlet.http.HttpSession;

// Controlador para la solicitud de préstamos
@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoServiceImpl prestamoService;

    private final String ESTADO_PENDIENTE = "PENDIENTE";
    private final String ESTADO_APROBADO = "APROBADO";
    private final String ESTADO_RECHAZADO = "RECHAZADO";
    private final String ESTADO_CANCELADO = "CANCELADO";

    private final Long ROL_PRESTAMISTA = 4L;
    private final Long ROL_PRESTATARIO = 5L;

    @GetMapping("/buscarPrestamos")
    @ResponseBody
    public Map<String, Object> buscarPrestamos(HttpSession session) {

        try {
            Usuario user = (Usuario) session.getAttribute("usuario");
            List<Prestamo> prestamos = new ArrayList<>();
            if (user.getRol().getId() == ROL_PRESTAMISTA) {
                prestamos = prestamoService.listarPrestamosPorIdPrestamista(user);
            } else if (user.getRol().getId() == ROL_PRESTATARIO) {
                prestamos = prestamoService.listarPrestamosPorPrestatario(user);
            }
            return prestamos.isEmpty()
                    ? Collections.singletonMap("mensaje", "No hay coincidencias")
                    : Collections.singletonMap("lista", prestamos);

        } catch (Exception e) {
            return Collections.singletonMap("msg_error", "Error de Servidor");
        }
    }

    @PostMapping("/solicitaPrestamo")
    @ResponseBody
    public Map<String, Object> solicitarPrestamo(@RequestParam String fecha_inicio,
            @RequestParam String fecha_fin, Prestamo obj, HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("usuario");
        Date fechaInicio = getFechaDate(fecha_inicio);
        Date fechaFin = getFechaDate(fecha_fin);
        obj.setFechaInicio(fechaInicio);
        obj.setFechaFin(fechaFin);
        obj.setEstado(ESTADO_PENDIENTE);
        obj.setIdPrestatario(user);
        obj.setTipoCuotas("DIARIO");
        Prestamo objSalida = prestamoService.solicitar(obj);

        try {

            return objSalida != null
                    ? Collections.singletonMap("msg_ok", "Solicitud Enviada")
                    : Collections.singletonMap("msg_error", "Error de Solicitud");

        } catch (Exception e) {
            return Collections.singletonMap("msg_error", "Error de Servidor");
        }
    }

    @PutMapping("/generarPrestamo")
    @ResponseBody
    public Map<String, Object> generarPrestamo(@RequestParam String id, @RequestParam String estado,
            HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuario");

        long idLong = Long.parseLong(id);
        Prestamo prestamo = prestamoService.obtenerPrestamoPorId(idLong);
        estado = estado.equalsIgnoreCase(ESTADO_RECHAZADO) ? ESTADO_RECHAZADO : ESTADO_APROBADO;
        prestamo.setEstado(estado);
        prestamo.setIdPrestatamista(user);
        Prestamo objSalida = prestamoService.actualizarPrestamo(idLong, prestamo);

        try {
            return objSalida != null
                    ? Collections.singletonMap("mensaje_ok", "Solicitud " + estado)
                    : Collections.singletonMap("mensaje_error", "Error de Cancelacion");

        } catch (Exception e) {
            return Collections.singletonMap("msg_error", "Error de Servidor");
        }
    }

    @PutMapping("/cancelarPrestamo")
    @ResponseBody
    public Map<String, Object> cancelarPrestamo(@RequestParam String id, HttpSession session) {

        long idLong = Long.parseLong(id);
        Prestamo prestamo = prestamoService.obtenerPrestamoPorId(idLong);
        prestamo.setEstado(ESTADO_CANCELADO);
        Prestamo objSalida = prestamoService.actualizarPrestamo(idLong, prestamo);

        try {

            return objSalida != null
                    ? Collections.singletonMap("mensaje_ok", "Solicitud Cancelada")
                    : Collections.singletonMap("mensaje_error", "Error de Cancelacion");

        } catch (Exception e) {
            return Collections.singletonMap("msg_error", "Error de Servidor");
        }
    }

    @GetMapping("/buscarMontosPrestamos")
    @ResponseBody
    public Map<String, Object> buscarMontosPrestamos(HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("usuario");
        try {
            List<MontosPrestamos> monts = new ArrayList<>();

            monts = prestamoService.listarMontosPrestamos();

            return monts.isEmpty()
                    ? Collections.singletonMap("mensaje", "No hay coincidencias")
                    : Collections.singletonMap("lista", monts);

        } catch (Exception e) {
            return Collections.singletonMap("msg_error", "Error de Servidor");
        }
    }

    public static Date getFechaDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date salida = null;
        try {
            salida = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }
}
