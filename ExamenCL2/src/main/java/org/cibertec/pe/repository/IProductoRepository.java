package org.cibertec.pe.repository;

import org.cibertec.pe.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
