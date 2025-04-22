package org.example.controleacesso.services;

import org.example.controleacesso.entities.Usuario;
import org.example.controleacesso.repositories.UsuarioRepository;
import org.example.controleacesso.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String autenticar(String nome, String senha){
        Usuario usuario=usuarioRepository.findByNome(nome);
        String token=null;
        if(usuario!=null && usuario.getSenha().equals(senha)){
            token= JWTTokenProvider.getToken(nome, ""+usuario.getNivel());
        }
        return token;
    }
}
