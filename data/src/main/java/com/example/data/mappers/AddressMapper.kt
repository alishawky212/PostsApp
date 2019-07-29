package com.example.data.mappers

import com.example.data.remotemodels.RemoteAddress
import com.example.domain.models.Address
import javax.inject.Inject

class AddressMapper @Inject constructor(private val mapper: GeoMapper) {

    fun mapToDomain(entity: RemoteAddress): Address = Address(street = entity.street,
        suite = entity.suite,
        city = entity.city,
        zipcode = entity.zipcode,
        geo = mapper.mapToDomain(entity.geo))
}