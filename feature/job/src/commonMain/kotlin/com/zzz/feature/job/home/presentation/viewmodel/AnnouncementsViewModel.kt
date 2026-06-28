package com.zzz.feature.job.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.util.ClaveLogger.logD
import com.zzz.core.ui.util.ClaveLogger.logE
import com.zzz.core.util.domain.Result
import com.zzz.data.remote.data.student.announcements.AnnouncementResponse
import com.zzz.data.remote.domain.student.announcements.AnnouncementSource
import com.zzz.data.remote.domain.toUIError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnnouncementsViewModel(
    private val announcementSource: AnnouncementSource
) : ViewModel() {

    private val _items = MutableStateFlow<List<AnnouncementResponse>>(emptyList())
    val items =_items.asStateFlow()

    init {

        getAll()
    }

    private fun getAll(){
        viewModelScope.launch {
            val result = announcementSource.getAnnouncements()
            when(result){
                is Result.Error -> {
                    this@AnnouncementsViewModel.logE {
                        "getAll : Error ${result.error.toUIError()}"
                    }
                }
                is Result.Success -> {
                    this@AnnouncementsViewModel.logD {
                        "getAll : Success"
                    }
                    _items.update {
                        result.data
                    }
                }
            }
        }
    }

}