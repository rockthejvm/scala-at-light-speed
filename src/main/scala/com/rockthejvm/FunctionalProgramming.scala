package com.rockthejvm

object FunctionalProgramming extends App {

  // Scala is Object Oriented language
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // INVOKING bob as a function === bob.apply(43)

  /*
    Scala runs on the JVM
    Functional programming:
    In functional programming, we want to work with Functions as
    first class elements of programming. This means we want to:
    - compose functions
    - pass functions as args
    - return functions as results

    Since this was not the default expectation of JVM, Scala introduced FunctionX.
    Conclusion: FunctionX = Function1, Function2, ... Function22
    (22 is the maximum number of arguments you can pass!)
   */

  val simpleIncrementer = new Function1[Int /*input*/ , Int /*result*/ ] {
    override def apply(arg: Int): Int = arg + 1
  }
  //  We instanciated Funcion1 like a Trait!
  //  But Function1[Int, Int] means a function "Function1" that takes Int as an input and Int as a response."

  simpleIncrementer.apply(23) // returns 24
  simpleIncrementer(23) // simpleIncrementer.apply(23)
  // defined a function!

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES

  // function with 2 arguments and a String return type
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }

  stringConcatenator("I love", " Scala") // "I love Scala"
  // syntax sugars:
  // Alternative syntax that will replace these heavier boilerplate codes!

  val doubler: Int => Int = (x: Int) => 2 * x
  doubler(4) // 8
  /*
    equivalent to the much longer:

    val doubler: Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(x: Int) = 2 * x
    }
   */
  //  OR
  // val doubler: Function1[Int, Int] = (x:Int) => 2 * x
  //  "Int => Int" is equivalent  to "Function1[Int, Int]"

  //  can emit the whole type (Int => Int)
  //  val doubler = (x: Int) => 2 * x

  // Higher-Order Functions:
  // Take functions as args/return functions as results.
  //  Eg : map, flatmap, filters
  val aMappedList: List[Int] = List(1, 2, 3).map(x => x + 1) // HOF
  // note: returned list(after map) is a different list than input (List(1,2,3))
  // Alternative syntax (lambdas):
  //  .map { x=>
  //  x + 1
  //  }

  val aFlatMappedList = List(1, 2, 3).flatMap { x =>
    List(x, 2 * x)
  }
  //  flatmaps role is to concatenate the lists into a bigger list
  //  So, println(aFlatMappedList) returns: [1,2,2,4,3,6] instead of [[1,2],[2,4],[3,6]]
  // alternative syntax, same as .flatMap(x => List(x, 2 * x))

  val aFilteredList = List(1, 2, 3, 4, 5).filter(x => x <= 3) //println(aFilteredList) returns: [1,2,3]
  // alternate syntax:
  // List(1, 2, 3, 4, 5).filter(_ <= 3)
  // where (_ <= 3) is equivalent to (x => x <= 3)

  // In Scala, since everything is IMMUTABLE: we can easily chain filter, map and flatmap Operations!
  // all pairs between the numbers 1, 2, 3 and the letters 'a', 'b', 'c'
  val allPairs = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))

  // for comprehensions:
  //  makes above mapping, flat-mapping, filtering more readable.
  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  // equivalent to the map/flatMap chain above

  /**
   * Collections
   */
  // lists
  val aList = List(1, 2, 3, 4, 5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4,5) -> 0 is prepended
  val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6) -> 0 is prepended | 6 is appended

  // sequences
  //  Seq is a Trait
  val aSequence: Seq[Int] = Seq(1, 2, 3) // Seq.apply(1,2,3)
  val accessedElement = aSequence(1) // the element at index 1: 2 === aSequence.apply(1)

  // vectors: fast Seq implementation [is fast for Random Access]
  val aVector = Vector(1, 2, 3, 4, 5)
  //  Stackoverflow notes:
  //  For random access, vector is better.
  //  For head, tail access, list is better.
  //  For bulk operation, such as map, filter, vector is preferred since vector is organized with 32 elements as a chunk whereas list organized the elements with pointers to each other there is no guarantee these elements are close to each other.

  // sets = no duplicates and order is not maintained by set
  //  Primary element of a Set is to check if an element is present
  val aSet = Set(1, 2, 3, 4, 1, 2, 3) // Set(1,2,3,4)
  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5) // adds a value to set.
  val aRemovedSet = aSet - 3 // Set(1,2,4) // removes a value from set

  // ranges
  val aRange = 1 to 1000
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,6,8..., 2000)
  //  val aRange = 1 to 1000 by 2
  //  println(aRange.toList) //returns 1,3,5,7....999
  // by 2 means in steps of 2.

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps [key value pair]
  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 6437812),
    "Jane" -> 327285 // ("Jane", 327285)
  )


}
