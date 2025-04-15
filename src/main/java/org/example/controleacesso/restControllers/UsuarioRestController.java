package org.example.controleacesso.restControllers;
import org.example.controleacesso.entities.Usuario;
import org.example.controleacesso.repositories.UsuarioRepository;
import org.example.controleacesso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;
@RestController
@RequestMapping("apis/user")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<Object> getUsuarios(){
        List<Usuario> usuarioList = usuarioService.getAll();
        if (usuarioList.isEmpty())
            return ResponseEntity.badRequest().body("Sem dado cadastrados");
        return ResponseEntity.ok(usuarioList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable(name = "id") Long id){
        Usuario usuario = usuarioService.getById(id);
        if (usuario == null)
            return ResponseEntity.badRequest().body("Usuario não encontrado");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        return ResponseEntity.badRequest().body("erro ao cadastrar o novo usuário");
    }

    @PutMapping
    public ResponseEntity<Object> updUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        return ResponseEntity.badRequest().body("erro ao alterar o novo usuário");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delUsuario(@PathVariable Long id){
        if (usuarioService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body("Erro ao apagar usuário");
    }

//    @GetMapping("get-by-name/{name}")
//    public ResponseEntity<Object> getUsuarioNome(@PathVariable(name = "name") String nome){
//        Usuario usuario;
//        usuario = usuarioRepo.findByNome(nome);
//        if (usuario == null)
//            return ResponseEntity.badRequest().body("Usuario não encontrado");
//        return ResponseEntity.ok(usuario);
//
//    }
}
