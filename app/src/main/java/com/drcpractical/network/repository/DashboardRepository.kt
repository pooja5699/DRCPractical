package com.drcpractical.network.repository

import com.drcpractical.network.APIService
import com.drcpractical.network.SafeApiRequest
import com.drcpractical.network.model.HomeRequest
import com.drcpractical.network.model.HomeResponse

class DashboardRepository(private val api: APIService) : SafeApiRequest() {

    suspend fun hopePage(request: HomeRequest): ArrayList<HomeResponse> {
        return apiRequest { api.homepage(request) }
    }
}