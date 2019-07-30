package com.example.data.mappers

import com.example.data.createUser
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class UserMapperTest {

    private lateinit var mapper: UserMapper
    
    @Before
    fun setUp() {
        mapper = UserMapper(AddressMapper(GeoMapper()), CompanyMapper())
    }

    @Test
    fun mapToDomain() {
        val remoteUser = createUser()
        
        val domainModel = mapper.mapToDomain(remoteUser)

        assertTrue(domainModel.id == remoteUser.id)
        assertTrue(domainModel.name == remoteUser.name)
        assertTrue(domainModel.username == remoteUser.username)
        assertTrue(domainModel.email == remoteUser.email)
        assertTrue(domainModel.phone == remoteUser.phone)
        assertTrue(domainModel.website == remoteUser.website)

        assertTrue(domainModel.address.street == remoteUser.address.street)
        assertTrue(domainModel.address.suite == remoteUser.address.suite)
        assertTrue(domainModel.address.city == remoteUser.address.city)
        assertTrue(domainModel.address.zipcode == remoteUser.address.zipcode)

        assertTrue(domainModel.address.geo.lat == remoteUser.address.geo.lat)
        assertTrue(domainModel.address.geo.lng == remoteUser.address.geo.lng)

        assertTrue(domainModel.company.name == remoteUser.name)
        assertTrue(domainModel.company.catchPhrase == remoteUser.company.catchPhrase)
        assertTrue(domainModel.company.bs == remoteUser.company.bs)
    }
}