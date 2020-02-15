package com.example.newsapp.repos

import com.example.newsapp.koin.networkRequestModule
import com.example.newsapp.koin.reposModule
import com.example.newsapp.koin.retrofitModule
import com.example.newsapp.retrofit.ResponseWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject
import org.koin.test.KoinTest


class NewsRepositoryTest : KoinTest {

    private val newsRepository: NewsRepository by inject()

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUp() {
            startKoin {
                modules(listOf(retrofitModule, networkRequestModule, reposModule))
            }
        }

        @AfterClass
        @JvmStatic
        fun close() {
            stopKoin()
        }

    }


    // Test Api response is success or not.
    /**
     * Note: For this test Comment EspressoIdleResource Counter in getPopularArticles() function
     * */
    @Test
    fun getPopularArticles() = runBlocking {
        newsRepository.getPopularArticles().collect {
            if (it !is ResponseWrapper.Loading) {
                assert(it is ResponseWrapper.Success)
            }
        }

    }
}