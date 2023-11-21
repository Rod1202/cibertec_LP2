package org.cibertec.pe.repository;

import org.cibertec.pe.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer>{

}
