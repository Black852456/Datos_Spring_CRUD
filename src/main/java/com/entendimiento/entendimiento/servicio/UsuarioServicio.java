package com.entendimiento.entendimiento.servicio;

import com.entendimiento.entendimiento.entidad.Usuario;
import com.entendimiento.entendimiento.repositorio.UsuarioRepositorio;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios(){
        return (List<Usuario>) repositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario encontrarUsuario(Long idUsuario){

        return repositorio.findById(idUsuario)
                .orElse(null);
    }

    @Transactional
    public void dataUpdateSave(Usuario usuario){
        repositorio.save(usuario);
    }

    @Transactional
    public void eliminar(Long id){
        repositorio.deleteById(id);
    }


}
