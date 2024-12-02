package com.example.BlackNoise.service;

import com.example.BlackNoise.model.Usuario;
import com.example.BlackNoise.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getRol() == null) {
            usuario.setRol("usuario");
        }
        usuario.setActivo(true);
        usuario.setFechaRegistro(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(String id, Usuario usuarioDetalles) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDetalles.getNombre());
        usuario.setEmail(usuarioDetalles.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }
}
