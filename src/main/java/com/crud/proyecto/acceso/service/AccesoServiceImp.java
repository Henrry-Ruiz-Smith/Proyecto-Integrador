package com.crud.proyecto.acceso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.acceso.entity.Acceso;
import com.crud.proyecto.acceso.entity.AccesoPK;
import com.crud.proyecto.acceso.repository.AccesoRepository;
import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.entity.Rol;
import com.crud.proyecto.roles.repository.RolRepository;


@Service
public class AccesoServiceImp implements IAccesoService{

	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private AccesoRepository accesoRepository;
	
	@Override
	public List<Rol> listaRol() {
		 
		return rolRepository.findAll();
	}

	@Override
	public List<Opcion> traerOpcionDeRol(int idRol) {
		return rolRepository.traerOpcionDeRol(idRol);
	}

	@Override
	public Optional<Acceso> buscaOpcion(AccesoPK ojb) {
		 
		return accesoRepository.findById(ojb);
	}

	@Override
	public Acceso insertaOpcion(Acceso ojb) {
		 
		return accesoRepository.save(ojb);
	}

	@Override
	public void eliminaOpcion(Acceso obj) {
		accesoRepository.delete(obj);
		
	}

	@Override
	public Rol login(Rol bean) {
		 
		return rolRepository.login(bean);
	}
	
	@Override
	public List<Object> getObjRol(int idRol) {
		 
		return rolRepository.getObjRol(idRol);
	}

}
