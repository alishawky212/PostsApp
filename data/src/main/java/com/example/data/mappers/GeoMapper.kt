package com.example.data.mappers

import com.example.data.remotemodels.RemoteGeo
import com.example.domain.models.Geo
import javax.inject.Inject

class GeoMapper @Inject constructor() {
    fun mapToDomain(entity: RemoteGeo): Geo = Geo(lat = entity.lat, lng = entity.lng)
}