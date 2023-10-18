package org.cibertec.pe.interfacesServices;

import java.util.List;
import java.util.Optional;

import org.cibertec.pe.modelo.Articulos;

public interface IArticuloService {
	public List<Articulos> Listado();
	public Optional<Articulos> ver(int id);
	public int grabar(Articulos a);
	public void suprimir(int id);
}
