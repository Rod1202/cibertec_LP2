package org.cibertec.pe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_postal_destino_id")
	private Ciudad codigo_postal_destino;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_postal_origen_id")
	private Ciudad codigo_postal_origen;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Venta idVenta;
	private int cantidad;
	private Date fecha_viaje;
	private Date fecha_retorno;
	private double sub_total;
	public DetalleVenta() {
	}
	public DetalleVenta(int idDetalle, Ciudad codigo_postal_destino, Ciudad codigo_postal_origen, Venta idVenta,
			int cantidad, Date fecha_viaje, Date fecha_retorno, double sub_total) {
		this.idDetalle = idDetalle;
		this.codigo_postal_destino = codigo_postal_destino;
		this.codigo_postal_origen = codigo_postal_origen;
		this.idVenta = idVenta;
		this.cantidad = cantidad;
		this.fecha_viaje = fecha_viaje;
		this.fecha_retorno = fecha_retorno;
		this.sub_total = sub_total;
	}
	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public Ciudad getCodigo_postal_destino() {
		return codigo_postal_destino;
	}
	public void setCodigo_postal_destino(Ciudad codigo_postal_destino) {
		this.codigo_postal_destino = codigo_postal_destino;
	}
	public Ciudad getCodigo_postal_origen() {
		return codigo_postal_origen;
	}
	public void setCodigo_postal_origen(Ciudad codigo_postal_origen) {
		this.codigo_postal_origen = codigo_postal_origen;
	}
	public Venta getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Venta idVenta) {
		this.idVenta = idVenta;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha_viaje() {
		return fecha_viaje;
	}
	public void setFecha_viaje(Date fecha_viaje) {
		this.fecha_viaje = fecha_viaje;
	}
	public Date getFecha_retorno() {
		return fecha_retorno;
	}
	public void setFecha_retorno(Date fecha_retorno) {
		this.fecha_retorno = fecha_retorno;
	}
	public double getSub_total() {
		return sub_total;
	}
	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}
	
}
