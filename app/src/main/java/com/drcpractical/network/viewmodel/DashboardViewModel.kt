package com.drcpractical.network.viewmodel

import androidx.lifecycle.ViewModel
import com.drcpractical.network.model.HomeRequest
import com.drcpractical.network.repository.DashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DashboardViewModel constructor(private val repository: DashboardRepository) : ViewModel() {

    suspend fun hopePage(request: HomeRequest) =
        withContext(Dispatchers.IO) { repository.hopePage(request) }

}