package br.com.wk.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class NovaRespostaForm(
        @field:NotEmpty
        val mensagem: String,
        @field:NotNull
        val idAutor: Long,
        @field:NotNull
        val idTopico: Long
)
