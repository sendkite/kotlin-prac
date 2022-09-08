package com.group.libraryapp.calculator

/**
 * 요구사항
 * 1. 계산기는 정수만 취급한다.
 * 2. 계산기가 생성될 때 숫자를 1개 받는다.
 * 최초 숫자가 기록된 이후에는 연산자 함수를 통해서 계산
 * */
class Calculator(
    var number: Int
) {

    fun add(operand: Int) { this.number += operand }

    fun minus(operand: Int) { this.number -= operand }

    fun multiply(operand: Int) { this.number *= operand }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다.")
        }
        this.number /= operand
    }
}