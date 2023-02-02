package br.com.wk.forum.mapper

import br.com.wk.forum.dto.NovoTopicoForm
import br.com.wk.forum.model.Topico
import br.com.wk.forum.service.CursoService
import br.com.wk.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
        private val cursoService: CursoService,
        private val usuarioService: UsuarioService
): Mapper<NovoTopicoForm, Topico> {
    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
                titulo = t.titulo,
                mensagem = t.mensagem,
                curso = cursoService.buscarPorId(t.idCurso),
                autor = usuarioService.buscarPorId(t.idAutor),
        )
    }

}
