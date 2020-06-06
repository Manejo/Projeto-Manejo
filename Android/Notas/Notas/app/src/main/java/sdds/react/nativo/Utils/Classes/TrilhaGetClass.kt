package sdds.react.nativo.Utils.Classes

class TrilhaGetClass{

    var id : String = ""
    var capacidade : Int
    var nome : String
    var dificuldade : String
    var status : String
    var coordenadas : String

    constructor(id : String, capacidade : Int, nome : String, dificuldade : String, status : String, coordenadas : String) {
        this.id = id
        this.capacidade = capacidade
        this.nome = nome
        this.dificuldade = dificuldade
        this.status = status
        this.coordenadas = coordenadas
    }
}