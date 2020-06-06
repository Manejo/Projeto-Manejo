package sdds.react.nativo.Utils.Interfaces

import com.study.lmav.testandorecyclerview.Classes.TrilhaDataClass
import retrofit2.Call
import retrofit2.http.*
import sdds.react.nativo.Utils.Classes.TrilhaGetClass
import sdds.react.nativo.Utils.Classes.TrilhaPostClass

interface Endpoint {

    @GET("trilhas")
    fun getTrilhas() : Call<List<TrilhaDataClass>>

    @POST("trilhas")
    fun postTrilha(@Body trilha: TrilhaPostClass) : Call<TrilhaPostClass>

    @POST("trilhas/{id}")
    fun updateTrilha(@Path("id") id: String, @Body trilha: TrilhaPostClass) : Call<TrilhaPostClass>

    @DELETE("trilhas/{id}")
    fun apagaTrilha(@Path("id") id: String, @Header("Authorization") authorization: String) : Call<TrilhaPostClass>

}