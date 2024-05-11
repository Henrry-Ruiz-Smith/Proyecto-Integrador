package com.crud.proyecto.enlaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.proyecto.usuario.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnlacesController {
	@GetMapping("/")
	public String mostrarFormularioLogin() {
		return "login";
	}

	@GetMapping("/vistaInversionista")
	public String verVistaInversionista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaInversionista";
	}

	@GetMapping("/registrarJefePrestamista")
	public String registrarJefePrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "registrarJefePrestamista";
	}

	@GetMapping("/actualizaPrestatario")
	public String actualizaPrestatario(HttpServletRequest request, @RequestParam Long id,
			@RequestParam String nom,
			@RequestParam String ape,
			@RequestParam String dni,
			@RequestParam String tel,
			@RequestParam String cor,
			@RequestParam String use,
			@RequestParam String zid) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "editarPrestatario";
	}

	@GetMapping("/actualizaPrestamista")
	public String actualizaPrestamista(HttpServletRequest request, @RequestParam Long id,
			@RequestParam String nom,
			@RequestParam String ape,
			@RequestParam String dni,
			@RequestParam String tel,
			@RequestParam String cor,
			@RequestParam String use,
			@RequestParam String zid) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "editarPrestamista";
	}

	@GetMapping("/actualizaJefePrestamista")
	public String actualizaJefePrestamista(HttpServletRequest request, @RequestParam Long id,
			@RequestParam String nom,
			@RequestParam String ape,
			@RequestParam String dni,
			@RequestParam String tel,
			@RequestParam String cor,
			@RequestParam String use,
			@RequestParam String zid) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "editarJefePrestamista";
	}

	@GetMapping("/vistaJefePrestamista")
	public String verVistaJefePrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaJefePrestamista";
	}

	@GetMapping("/vistaPrestamista")
	public String verVistaPrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaPrestamista";
	}

	@GetMapping("/vistaPrestatario")
	public String verVistaPrestatario(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaPrestatario";
	}

	// Registros

	// Registros
	@GetMapping("/registrarPrestamista")
	public String registrarPrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "registrarPrestamista";
	}

	@GetMapping("/registrarPrestatario")
	public String registrarPrestatario(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "registrarPrestatario";
	}

	@GetMapping("/singup")
	public String registrarLoginPrestatario() {

		return "registrarLoginPrestatario";
	}

	@GetMapping("/solicitarPrestamo")
	public String solicitarPrestamos(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "solicitarPrestamo";
	}

	@GetMapping("/aprobarSolicitudesPrestamo")
	public String aprobarSolicitudesPrestamo(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "aprobarSolicitudesPrestamo";
	}

}
