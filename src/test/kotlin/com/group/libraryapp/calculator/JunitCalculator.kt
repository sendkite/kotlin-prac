package com.group.libraryapp.calculator

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculator {

    @Test
    fun add() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        assertThat(calculator.number).isEqualTo(8) // (확인값).함수(기대값)
    }

    @Test
    fun multiply() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(3)
        // then
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun divideExceptionTest() {
        // given
        val calculator = Calculator(5)
        // when + then
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message
        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
    }
}