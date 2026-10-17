package com.example.aula_2909

class CuriosidadesAlimentares {

    companion object {
        // Usamos 'val' em vez de 'var' e 'listOf' em vez de 'mutableListOf'
        // porque listas estáticas no companion object geralmente não devem ser alteradas (são constantes).
        private var lista_maca: MutableList<String> = mutableListOf(
            "maças sao boas para a saude",
            "macas sao vermelhas as vezes, depende do dia",
            "uma maca caiu na cabeca de newton"
        )

        private var lista_pizza: MutableList<String> = mutableListOf(
            "pizzas nao sao boas para a saude",
            "pizzas sao vermelhas as vezes, depende do dia",
            "uma pizza NAO caiu na cabeca de newton"
        )

        private var lista_sorvete: MutableList<String> = mutableListOf(
            "sorvtes sao bons ate no frio",
            "quem nao gosta de sorvete nao e muito confiavel nao",
            "sorvetes quando caem no chao congelam 1,27 atomos"
        )

        fun getAboutApple(): String {
            return lista_maca.random()
        }

        fun getAboutPizza(): String {
            return lista_pizza.random()
        }

        fun getAboutIcecream(): String {
            return lista_sorvete.random()
        }

        fun getAllRandom(): String {
            return (lista_maca + lista_pizza + lista_sorvete).random()
        }

        fun getAll(): MutableList<String> {
            var lista_geral = (lista_maca + lista_pizza + lista_sorvete)
            return lista_geral as MutableList<String>
        }

        fun delFromAll(about: String) {
            if (lista_maca.contains(about)) { lista_maca.remove(about) }
            else if (lista_pizza.contains(about)) { lista_pizza.remove(about) }
            else if (lista_sorvete.contains(about)) { lista_sorvete.remove(about) }

            if (lista_maca.isEmpty()) lista_maca.add("Lista maca vazia")
            else if (lista_pizza.isEmpty()) lista_pizza.add("Lista pizza vazia")
            else if (lista_sorvete.isEmpty()) lista_sorvete.add("Lista sorvete vazia")
        }
    }
}
