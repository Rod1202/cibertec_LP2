package org.cibertec.pe.repository;

import org.cibertec.pe.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IDetalleRepository extends JpaRepository<DetalleVenta, Integer> {

}
