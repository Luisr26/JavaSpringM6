package com.example.usuarios.Application.UseCase;

import com.example.usuarios.dominio.modelo.Usuario;
import com.example.usuarios.dominio.puerto.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ActualizarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public ActualizarUsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    public Optional<Usuario> ejecutar(UUID id, String name, String cargo, String telefono) {
        Optional<Usuario> usuarioExistente = usuarioRepositoryPort.buscarPorId(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            // Actualizamos los campos (suponiendo que el modelo Usuario es mutable o
            // creamos uno nuevo)
            // Como Usuario tiene setters, podemos usarlos. O crear uno nuevo con el mismo
            // ID.
            // Vamos a crear uno nuevo para mantener inmutabilidad si fuera el caso, o usar
            // setters.
            // Viendo el modelo Usuario, tiene setters.

            // Sin embargo, para ser más limpios, podemos crear una nueva instancia con los
            // datos actualizados y el mismo ID
            Usuario usuarioActualizado = new Usuario(id, name, cargo, telefono);
            return Optional.of(usuarioRepositoryPort.guardar(usuarioActualizado));
        }

        return Optional.empty();
    }
}
