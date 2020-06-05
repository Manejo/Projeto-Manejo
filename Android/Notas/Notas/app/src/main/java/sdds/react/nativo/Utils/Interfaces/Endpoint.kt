package sdds.react.nativo.Utils.Interfaces

import com.study.lmav.testandorecyclerview.Classes.TrilhaDataClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import sdds.react.nativo.Utils.Classes.TrilhaClass

interface Endpoint {

    @GET("trilhas")
    fun getTrilhas() : Call<List<TrilhaDataClass>>

    @POST("trilhas")
    fun postTrilha(@Body trilha:TrilhaClass) : Call<TrilhaClass>

}