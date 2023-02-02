package br.com.wk.forum.service

import br.com.wk.forum.model.Curso
import br.com.wk.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(
                id = 1,
                nome = "Wkerlyson",
                email = "wk@email.com"
        )

        usuarios = listOf(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter {
            c -> c.id == id
        }.findFirst().get()
    }
}