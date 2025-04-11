package org.example.controleacesso.restControllers;

import org.example.controleacesso.entities.Usuario;
import org.example.controleacesso.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.List;

@RestController
@RequestMapping("apis/user")
public class UsuarioRestController {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @GetMapping("get-all")
    public ResponseEntity<Object> getUsuarios(){
        List<Usuario> usuarioList;
        usuarioList = usuarioRepo.findAll();
        return ResponseEntity.ok(usuarioList);
    }
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable(name = "id") Long id){
        Usuario usuario;
        usuario = usuarioRepo.findById(id).orElse(usuario = null);
        if (usuario == null)
            return ResponseEntity.badRequest().body("Usuario não encontrado");
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("get-by-name/{name}")
    public ResponseEntity<Object> getUsuarioNome(@PathVariable(name = "name") String nome){
        Usuario usuario;
        usuario = usuarioRepo.findByNome(nome);
        if (usuario == null)
            return ResponseEntity.badRequest().body("Usuario não encontrado");
        return ResponseEntity.ok(usuario);

    }
}
