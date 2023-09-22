package com.mironov.newsapp

import com.mironov.newsapp.repository.Repository
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.repository.retrofit.NewsApi
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MocksTest {

    @get:Rule
    var testName: TestName = TestName()

    @MockK
    lateinit var apiMockk: NewsApi

    @MockK(relaxed = true)
    lateinit var repoMockk: Repository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun mockkApiTest() {
        val response = JsonResponse()
        response.status = "ok"
        every {
            apiMockk.getNews(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns Single.just(response)

        val result = apiMockk.getNews(
            pageSize = 0,
            domains = null,
            sources = null,
            language = "",
            dateFrom = "",
            dateTo = "",
            apiKey = ""
        ).blockingGet()
        assert(result!!.status == "ok")
    }

    @Test
    fun mockkRepoTest() {
        val response = JsonResponse()
        response.status = "ok"
        every {
            repoMockk.getNews(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns Single.just(response)

        val result = repoMockk.getNews(
            pageSize = 0,
            domains = "",
            sources = "",
            language = "",
            dateFrom = "",
            dateTo = "",
            apiKey = ""
        ).blockingGet()
        assert(result!!.status == "ok")
    }

    @Test
    fun mockitoApiTest() {
        val mockNewsApi = mock(NewsApi::class.java)
        val response = JsonResponse()
        response.status = "ok"
        Mockito.`when`(
            mockNewsApi.getNews(
                pageSize = anyInt(),
                domains = any(),
                sources = any(),
                language = anyString(),
                dateFrom = anyString(),
                dateTo = anyString(),
                apiKey = anyString()
            )
        ).thenReturn(Single.just(response))

        val result = mockNewsApi.getNews(
            pageSize = 0,
            domains = null,
            sources = null,
            language = "",
            dateFrom = "",
            dateTo = "",
            apiKey = ""
        ).blockingGet()
        assert(result!!.status == "ok")
    }

    @Test
    fun mockitoRepoTest() {
        //Repository should be open, mockito does not work with final classes
        //Classes are final in kotlin bu default
        val mockNewsApi = mock(Repository::class.java)
        val response = JsonResponse()
        response.status = "ok"

        Mockito.`when`(
            mockNewsApi.getNews(
                pageSize = anyInt(),
                domains = anyString(),
                sources = anyString(),
                language = anyString(),
                dateFrom = anyString(),
                dateTo = anyString(),
                apiKey = anyString()
            )
        ).thenReturn(Single.just(response))

        val result = mockNewsApi.getNews(
            pageSize = 0,
            domains = "",
            sources = "",
            language = "",
            dateFrom = "",
            dateTo = "",
            apiKey = ""
        ).blockingGet()
        assert(result!!.status == "ok")
    }
}
