package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MensagemController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/api/usuarios")
    public List<Usuario> listarUsuariosApi() {
        return repository.findAll();
    }

    @PostMapping("/api/usuarios")
    public Usuario cadastrar(@RequestBody Usuario novo) {
        novo.setEmail(novo.getNome().toLowerCase().replace(" ", "") + "@ads2026.com");
        return repository.save(novo);
    }

    @DeleteMapping("/api/usuarios/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PutMapping("/api/usuarios/{id}")
    @CrossOrigin(origins = "*")
    public Usuario editar(@PathVariable Long id, @RequestBody Usuario dadosNovos) {
        // usuário atual banco
        Usuario usuarioDoBanco = repository.findById(id).get();

        // 
        usuarioDoBanco.setNome(dadosNovos.getNome());

        // Gerador de email
        usuarioDoBanco.setEmail(dadosNovos.getNome().toLowerCase().replace(" ", "") + "@ads2026.com");

        // salva no mysql
        return repository.save(usuarioDoBanco);
    }
}
