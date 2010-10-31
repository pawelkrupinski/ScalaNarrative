package com.youdevise.narrative.example

class Calculator {
  private var accumulator: Int = 0

  private var currentOperator: Operator = Operator.Plus

  class Operator(val char: Character, val applyFunction: (Int, Int) => Int) {
    def applyOperator(accumulator: Int, value: Int) = applyFunction(accumulator, value)
  }

  object Operator {
    val Plus = new Operator('+', _ + _)
    val Minus = new Operator('-', _ - _)
    val Equals = new Operator('=', (a, b) => a)

    def lookup(keypress: Char): Option[Operator] = {
      List(Plus, Minus, Equals).find(_.char == keypress)
    }
  }

  def press(keypress: Char): Unit = {
    if (Character.isDigit(keypress)) {
      accumulator = currentOperator.applyOperator(accumulator, Character.digit(keypress, 10))
    } else {
      currentOperator = Operator.lookup(keypress).get
    }
  }

  def read: String = {
    return String.valueOf(accumulator)
  }
}