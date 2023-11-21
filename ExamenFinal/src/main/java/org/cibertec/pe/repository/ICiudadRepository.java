package org.cibertec.pe.repository;

import org.cibertec.pe.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad, Integer>{

}
