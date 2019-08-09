package com.example.data.repository

import com.example.data.createUser
import com.example.data.mappers.AddressMapper
import com.example.data.mappers.CompanyMapper
import com.example.data.mappers.GeoMapper
import com.example.data.mappers.UserMapper
import com.example.data.remote.RemoteApis
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as _when

class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl

    private val mockApi = mock<RemoteApis>()

    private val mapper = UserMapper(AddressMapper(GeoMapper()), CompanyMapper())

    private val remoteItem = createUser()

    private val remoteList = listOf(remoteItem)

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(mockApi, mapper)
    }

    @Test
    fun getUsers() {
        _when(mockApi.getUsers()).thenReturn(Single.just(remoteList))

        val test = repository.get().test()

        verify(mockApi).getUsers()

        test.assertValue(mapper.mapToDomain(remoteList))
    }
}