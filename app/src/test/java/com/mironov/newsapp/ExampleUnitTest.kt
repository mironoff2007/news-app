package com.mironov.newsapp

import com.mironov.newsapp.domain.DateUtil
import org.junit.Assert.*
import org.junit.Test
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        println(DateUtil.getPreviousDayDate(2))
        assertEquals(4, 2 + 2)
    }
}
