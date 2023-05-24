package com.entendimiento.entendimiento.repositorio;

import com.entendimiento.entendimiento.entidad.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario,Long> {
}
