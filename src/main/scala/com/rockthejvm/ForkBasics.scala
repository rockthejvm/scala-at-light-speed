package com.rockthejvm

object ForkBasics extends App {
  val meaningOfLife: Int = 42 //const int meaningOfLife = 42;

  val aBoolean = false //type is optional
  //  Common types:
  //  Int, Boolean, Char, Double, Float, String

  //  String
  val aString = "I love Scala"
  val aComposedString = "I" + " " + "love" + "" + "Scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  //  Expressions
  //  expressions = structures that can be reduced to a value
  val anExpression = 2 + 3

  //  if-expression
  val ifExpression = if (meaningOfLife < 43) 56 else 999
  val chainedIfExpression = {
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 70
    else 0
  }

  //  Code-blocks
  val aCodeBlock = {
    //definitions
    val aLocalValue = 67

    //last expression of block is the value of the code block
    aLocalValue + 3
  }

  def myFunction(x: Int, y: String): String = y + " " + x
  // OR
  //def myFunction(x: Int, y: String): String = { y + " " + x}

  //  recursive functions
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

//  In Scala we don't use loops or iteration, we use RECURSION!

//  the Unit type = no meaning value === "void" in other languages
//  We think of "Unit" as type of SIDE EFFECTS because it has no meaning for computation
  println("I love Scala") // --> no return type so, it's UNIT
  //equivalent functions in other languages that return void:
//  System.out.println, printf, print, console.log

  def myUnitReturningFunction(): Unit = {
    println("I don't love returning Unit!")
  }

//  The value returned when unit is returned is ()
  val theUnit:Unit = ()
}