package org.example.controleacesso.services;

import org.example.controleacesso.entities.Usuario;
import org.example.controleacesso.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<Usuario> getAll() {
        return usuarioRepo.findAll();
    }

    public Usuario getById(Long id){
        return usuarioRepo.findById(id).orElse(null);
    }
    public Usuario save(Usuario usuario)
    {
        try {
            Usuario novoUsuario = usuarioRepo.save(usuario);
            return novoUsuario;
        }catch (Exception e) {
            return null;
        }
    }
    public boolean delete(Long id){
        Usuario usuario=usuarioRepo.findById(id).orElse(null);
        try {
            usuarioRepo.delete(usuario);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
