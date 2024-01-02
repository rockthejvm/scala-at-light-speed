package com.rockthejvm

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
// ^^global value is equivalent to a thread-pool (group of threads) on which we can schedule evaluation of an expression

object Advanced extends App {

  /**
   * lazy evaluation:
   * the expression is not evaluated until it's first use.
   */
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy!")
    43
  }

  val eagerValue = lazyValueWithSideEffect + 1
  // useful in infinite collections

  /**
   * "pseudo-collections": Option, Try
   */
  def methodWhichCanReturnNull(): String = "hello, Scala"

  val anOption = Option(methodWhichCanReturnNull()) // Some("hello, Scala")
  // option = "collection" which contains at most one element: Some(value) or None
  // None is a singleton object which is equivalent to null,
  // but is a regular value. So, no risk in accessing members or values.

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
  }
  //  wrap unsafe code in Option and do pattern match or use collections like map, flatmap, filter.

  def methodWhichCanThrowException(): String = throw new RuntimeException

  val aTry = Try(methodWhichCanThrowException())
  // a try === "collection" with either a value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(ex) => s"I have obtained an exception: $ex"
  }
  // can also have map, flatMap, filter options


  /**
   * Evaluate something on another thread
   * (asynchronous programming)
   */
  val aFuture = Future {
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  }
  //^^ but the main thread completed before completion of the Future. So, everything is not printed!
  // If you add thread sleep to main thread everything will be printed because thread is alive until Future executes:
  //  Thread.sleep(2000)

  // future is a "collection" which contains a value when it's evaluated
  // future is composable with map, flatMap and filter
  // Future, Try and Option types are called Monads!

  /**
   * Implicits basics
   */
  // #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1

  implicit val myImplicitInt = 46
  println(aMethodWithImplicitArgs) // aMethodWithImplicitArgs(myImplicitInt)
  // looks for Int type implicit in the code and replaces it while executing.
  // So, no need of sending any arguments!

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }

  println(23.isEven()) // new MyRichInteger(23).isEven()
  // ^^compiler thinks, hey usually this code doesn't compiler. Let me look for an implicit that matches!
  // use this carefully

}
