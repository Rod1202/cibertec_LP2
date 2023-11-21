package org.cibertec.pe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="venta")
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre_comprador;
	private Date fecha_venta;
	private double monto_total;
	public Venta() {
	}
	public Venta(int id, String nombre_comprador, Date fecha_venta, double monto_total) {
		this.id = id;
		this.nombre_comprador = nombre_comprador;
		this.fecha_venta = fecha_venta;
		this.monto_total = monto_total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_comprador() {
		return nombre_comprador;
	}
	public void setNombre_comprador(String nombre_comprador) {
		this.nombre_comprador = nombre_comprador;
	}
	public Date getFecha_venta() {
		return fecha_venta;
	}
	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}
	public double getMonto_total() {
		return monto_total;
	}
	public void setMonto_total(double monto_total) {
		this.monto_total = monto_total;
	}
	
}
