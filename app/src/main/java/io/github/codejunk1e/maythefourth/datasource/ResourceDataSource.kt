package io.github.codejunk1e.maythefourth.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.github.codejunk1e.maythefourth.responsemodels.PeopleResponse
import io.github.codejunk1e.maythefourth.webservice.StarWarsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/*
 * @author robin
 */
class ResourceDataSource(private val context: CoroutineContext):
    PageKeyedDataSource<Int, PeopleResponse>() {

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)


    override fun  loadInitial (
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PeopleResponse>
    ) {
        scope.launch {
            try{
                val response =
                    StarWarsService
                        .getInstance()
                        .getCharacters(FIRST_PAGE)

                if (response.isSuccessful) {
                    val key = if (response.body()?.next != null) FIRST_PAGE + 1 else null
                    callback.onResult(response.body()?.results as MutableList<PeopleResponse>, null, key)
                }

            } catch (e :Exception){
                Log.e("OkHttp", "Failed to fetch data!")
            }
        }

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PeopleResponse>
    ) {
        scope.launch {
            try{
                val response =
                    StarWarsService
                        .getInstance()
                        .getCharacters(params.key)

                if (response.isSuccessful) {
                    val key = if (response.body()?.next != null) params.key + 1 else null
                    callback.onResult(response.body()?.results as MutableList<PeopleResponse>, key)
                }


            } catch (e :Exception){
                Log.e("OkHttp", "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PeopleResponse>
    ) {
        scope.launch {
            try{
                val response =
                    StarWarsService
                        .getInstance()
                        .getCharacters(params.key)

                if (response.isSuccessful) {
                    val key = if (response.body()?.previous != null) params.key - 1 else null
                    callback.onResult(response.body()?.results as MutableList<PeopleResponse>, key)
                }

            } catch (e :Exception){
                Log.e("OkHttp", "Failed to fetch data!")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}