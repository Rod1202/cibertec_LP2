package org.cibertec.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="articulos")
public class Articulos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdArticulo")
	private int IdArticulo;
	@Column(name = "Descripcion")
	private String Descripcion;
	@Column(name = "PrecioUnidad")
	private double PrecioUnidad;
	@Column(name = "Stock")
	private int Stock;
	@Column(name = "Estado")
	private char Estado;
	
	public Articulos() {
	
	}
	public Articulos(int idArticulo, String descripcion, double precioUnidad, int stock, char estado) {
		IdArticulo = idArticulo;
		Descripcion = descripcion;
		PrecioUnidad = precioUnidad;
		Stock = stock;
		Estado = estado;
	}
	public int getIdArticulo() {
		return IdArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		IdArticulo = idArticulo;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public double getPrecioUnidad() {
		return PrecioUnidad;
	}
	public void setPrecioUnidad(double precioUnidad) {
		PrecioUnidad = precioUnidad;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	public char getEstado() {
		return Estado;
	}
	public void setEstado(char estado) {
		Estado = estado;
	}
	
}
