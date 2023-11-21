package org.cibertec.pe.model;

public class VentaCompleta {
    private Venta venta;
    private DetalleVenta detalleVenta;

    public VentaCompleta() {
    }

    public VentaCompleta(Venta venta, DetalleVenta detalleVenta) {
        this.venta = venta;
        this.detalleVenta = detalleVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
}
