package com.group.libraryapp.calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
}

class CalculatorTest {

    fun addTest() {
        // give
        val calculator = Calculator(5)
        // when
        calculator.add(3)
        // then
        if (calculator.number != 8) {
            throw IllegalArgumentException()
        }
    }

    fun divideException() {
        // given
        val calculator = Calculator(5)

        // when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "0으로 나눌 수 없습니다.") {
                throw IllegalArgumentException("메시지가 다릅니다.")
            }
            return
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
        throw IllegalArgumentException("기대하는 예외가 발생하지 않습니다.")
        // then
    }
}