package br.com.wk.forum.service

import br.com.wk.forum.dto.AtualizacaoTopicoForm
import br.com.wk.forum.dto.NovoTopicoForm
import br.com.wk.forum.dto.TopicoView
import br.com.wk.forum.exception.NotFoundException
import br.com.wk.forum.mapper.TopicoFormMapper
import br.com.wk.forum.mapper.TopicoViewMapper
import br.com.wk.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private var topicos: List<Topico> = ArrayList(),
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Tópico não encontrado"
        ) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map {
            t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter {
            t -> t.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form);
        topico.id = topicos.size.toLong().inc()
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.stream().filter {
            t -> t.id == form.id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        val topicoAtualizado = Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                autor = topico.autor,
                curso = topico.curso,
                respostas = topico.respostas,
                status = topico.status,
                dataCriacao = topico.dataCriacao
        )
        topicos = topicos.minus(topico).plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter {
            t -> t.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        topicos = topicos.minus(topico)
    }

}