package io.github.codejunk1e.maythefourth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.github.codejunk1e.maythefourth.datasource.ResourceDataFactory
import io.github.codejunk1e.maythefourth.responsemodels.PeopleResponse
import kotlinx.coroutines.Dispatchers

/*
 * @author Robin
 */
class MainViewModel : ViewModel() {

    var peoplePagedList: LiveData<PagedList<PeopleResponse>>

    init {
        val peopleData =  ResourceDataFactory(Dispatchers.IO)
        peoplePagedList = LivePagedListBuilder(
            peopleData,
            PagedList.Config
                .Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build()
        ).build()
    }
}