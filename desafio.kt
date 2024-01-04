import java.util.*

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome:String = ""){
    override fun toString(): String { return nome }
}

data class CEducacional(var nome: String, val duracao: Int = 60, val nivel:Nivel){
    override fun toString(): String {
        val saida = "\n\t$nome Duração:$duracao min, Nivel:$nivel"
        return saida
    }
}

data class Formacao(val nome: String, val conteudos: List<CEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    infix fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }
    override fun toString(): String {
        val saida = "Nome:$nome\nConteudos:$conteudos"
        return saida
    }
}
fun main() {
    val scanner = Scanner(System.`in`)
    
    //declarados algumas formações
    val formacoes = listOf(
        Formacao(nome="kotlin", listOf(
            CEducacional("Introdução ao Kotlin", 30, Nivel.BASICO),
            CEducacional("Coroutines em Kotlin", 25, Nivel.INTERMEDIARIO),
            CEducacional("Desenvolvimento Android com Kotlin", 50, Nivel.AVANCADO)
        )),
        Formacao(nome="java", listOf(
            CEducacional("Java Básico", 30, Nivel.BASICO),
            CEducacional("Java Avançado", 25, Nivel.INTERMEDIARIO),
            CEducacional("Desenvolvimento Web com Spring", 50, Nivel.AVANCADO)
        )),
        Formacao(nome="python", listOf(
            CEducacional("Python Fundamentos", 30, Nivel.BASICO),
            CEducacional("Web Scraping com Python", 25, Nivel.INTERMEDIARIO),
            CEducacional("Machine Learning com Python", 50, Nivel.AVANCADO)
        ))
    )
    infix fun String.matricular(nomeFormacao:String){
        for(f in formacoes){
            if(f.nome.equals(nomeFormacao)){
                f.matricular(Usuario(this)) }
        }
    }
    //criado um loop para executar enquanto o usuario nao digita para sair
    while(true) {
        println("Digite uma opção:\n" +
                "'matricular' : Matricula novos alunos\n" +
                "'formacoes' : vê as formações disponiveis\n" +
                "'alunos' : Vê os alunos matriculados\n" +
                "'sair' : fecha o sistema\n")

        var frase: String = scanner.nextLine()
        var palavras = frase.split(" ")

        when (palavras[0]){
            "matricular" -> try{
                println("Digite o nome do aluno e a formação(kotlin, java, python):")
                frase = scanner.nextLine()
                palavras = frase.split(" ")
                palavras[0] matricular palavras[1]
                println("Aluno ${palavras[0]} matriculado com sucesso ao curso ${palavras[1]}")

            }catch(e:Throwable){
                println("Erro");
            }
            "formacoes" -> println("Formações disponiveis:\n$formacoes")
            "alunos" -> {
                println("Alunos matriculados:\n")
                for(f in formacoes) println("${f.nome} {${f.inscritos}\n")
            }
            "sair" -> { println("encerrando..."); break;}
            else -> println("Comando inválido!")
        }
    }
}
