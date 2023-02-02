package br.com.wk.forum.service

import br.com.wk.forum.dto.NovoTopicoForm
import br.com.wk.forum.dto.TopicoView
import br.com.wk.forum.mapper.TopicoFormMapper
import br.com.wk.forum.mapper.TopicoViewMapper
import br.com.wk.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private var topicos: List<Topico> = ArrayList(),
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper
        ) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map {
            t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter {
            t -> t.id == id
        }.findFirst().get()

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm) {
        val topico = topicoFormMapper.map(form);
        topico.id = topicos.size.toLong().inc()
        topicos = topicos.plus(topico)
    }

}