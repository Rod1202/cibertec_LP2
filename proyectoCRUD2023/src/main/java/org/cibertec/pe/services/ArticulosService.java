package org.cibertec.pe.services;

import java.util.List;
import java.util.Optional;

import org.cibertec.pe.interfaces.IArticulos;
import org.cibertec.pe.interfacesServices.IArticuloService;
import org.cibertec.pe.modelo.Articulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticulosService implements IArticuloService{

	@Autowired
	private IArticulos data;
	
	@Override
	public List<Articulos> Listado() {
		return (List<Articulos>)data.findAll();
	}

	@Override
	public Optional<Articulos> ver(int id) {
		return data.findById(id);
	}

	@Override
	public int grabar(Articulos a) {
		int res = 0;
		Articulos ObjA = data.save(a);
		if(!ObjA.equals(null))res= 1 ;
		return res;
	}

	@Override
	public void suprimir(int id) {
		data.deleteById(id);
	}

}
