package sdds.react.nativo.Utils.Interfaces

import com.study.lmav.testandorecyclerview.Classes.Trilha
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("trilhas")
    fun getTrilhas() : Call<List<Trilha>>

}