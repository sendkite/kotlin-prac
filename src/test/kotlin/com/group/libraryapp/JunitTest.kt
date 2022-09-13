package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class JunitTest {

    companion object { // static 으로 선언하는 것과 동일
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            println("모든 테스트 시작 전")
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            println("모든 테스트 종료 후")
        }
    }

    @Test
    fun test1() {
        println("test 1")
    }

    @Test
    fun test2() {
        println("test 2")
    }
}