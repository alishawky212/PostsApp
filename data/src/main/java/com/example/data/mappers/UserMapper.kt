package com.example.data.mappers

import com.example.data.remotemodels.RemoteUser
import com.example.domain.models.User
import javax.inject.Inject

class UserMapper @Inject constructor(private val addressMapper: AddressMapper,
                                     private val companyMapper: CompanyMapper) {

    fun mapToDomain(entity: RemoteUser): User = User(id = entity.id,
        name = entity.name,
        username = entity.username,
        email = entity.email,
        address = addressMapper.mapToDomain(entity.address),
        phone = entity.phone,
        website = entity.website,
        company = companyMapper.mapToDomain(entity.company))

    fun mapToDomain(list: List<RemoteUser>): List<User> = list.map { mapToDomain(it) }

}