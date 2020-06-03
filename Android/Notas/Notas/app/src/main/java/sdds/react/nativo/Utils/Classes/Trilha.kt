package com.study.lmav.testandorecyclerview.Classes

import com.google.gson.annotations.SerializedName

data class Trilha (
    @SerializedName("id")
    var id : String,
    @SerializedName("capacidade")
    var capacidade : Int,
    @SerializedName("nome")
    var nome : String,
    @SerializedName("dificuldade")
    var dificuldade : String,
    @SerializedName("status")
    var status : String,
    @SerializedName("coordenadas")
    var coordenadas : String
)