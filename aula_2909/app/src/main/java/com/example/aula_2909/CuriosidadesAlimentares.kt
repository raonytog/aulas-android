package com.example.aula_2909

class CuriosidadesAlimentares {

    companion object {
        // Usamos 'val' em vez de 'var' e 'listOf' em vez de 'mutableListOf'
        // porque listas estáticas no companion object geralmente não devem ser alteradas (são constantes).
        private val lista_maca: List<String> = listOf(
            "maças sao boas para a saude",
            "macas sao vermelhas as vezes, depende do dia",
            "uma maca caiu na cabeca de newton"
        )

        private val lista_pizza: List<String> = listOf(
            "pizzas nao sao boas para a saude",
            "pizzas sao vermelhas as vezes, depende do dia",
            "uma pizza NAO caiu na cabeca de newton"
        )

        // Funções para acessar as listas estáticas de qualquer lugar
        fun getAboutApple(): String {
            return lista_maca.random()
        }

        fun getAboutPizza(): String {
            return lista_pizza.random()
        }

        fun getAllRandom(): String {
            return (lista_maca + lista_pizza).random()
        }
    }
}
