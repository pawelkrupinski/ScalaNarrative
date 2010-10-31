package com.youdevise.narrative.example

import jline.ConsoleReader

object ConsoleCalculator {
  def main(args: Array[String]) {
    val allowed: Array[Char] = Array[Char]('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '=')
    val calculator: Calculator = new Calculator
    System.out.println("Welcome to the calculator")
    val input: ConsoleReader = new ConsoleReader
    while (true) {
      val keypress: Char = input.readCharacter(allowed).asInstanceOf[Char]
      calculator.press(keypress)
      System.out.println("You pressed " + keypress + ". Display reads: " + calculator.read)
    }
  }
}
