package com.pichs.xsql

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun AAA() {

        val list = mutableListOf<String>("1", "2", "3")

        println(list.toTypedArray().contentToString())
    }
}