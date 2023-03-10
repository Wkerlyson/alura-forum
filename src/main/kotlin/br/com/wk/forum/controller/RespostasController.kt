package br.com.wk.forum.controller

import br.com.wk.forum.dto.NovaRespostaForm
import br.com.wk.forum.model.Resposta
import br.com.wk.forum.service.RespostaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topicos/{id}/respostas")
class RespostasController(private val service: RespostaService) {

    @GetMapping
    fun listar(@PathVariable id: Long): List<Resposta>{
        return service.listar(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: NovaRespostaForm){
        service.cadastrar(dto)
    }
}