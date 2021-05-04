package io.github.codejunk1e.maythefourth.datasource

import androidx.paging.DataSource
import io.github.codejunk1e.maythefourth.datasource.ResourceDataSource
import io.github.codejunk1e.maythefourth.responsemodels.PeopleResponse
import kotlin.coroutines.CoroutineContext

/*
 * @author robin
 */
class ResourceDataFactory(private val context: CoroutineContext):
    DataSource.Factory<Int, PeopleResponse>() {


    override fun create(): DataSource<Int, PeopleResponse> {
        return ResourceDataSource(context)
    }
}