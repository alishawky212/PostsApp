package com.example.data.mappers

import com.example.data.remotemodels.RemoteCompany
import com.example.domain.models.Company
import javax.inject.Inject

class CompanyMapper @Inject constructor() {

    fun mapToDomain(entity: RemoteCompany): Company = Company(name = entity.name,
        catchPhrase = entity.catchPhrase,
        bs = entity.bs)
}