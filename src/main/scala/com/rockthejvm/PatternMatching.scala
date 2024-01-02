package com.rockthejvm

object PatternMatching extends App {

  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th" // case _ === default|else
  }
  // Pattern Matching is an EXPRESSION

  // Case class decomposition
  case class Person(name: String, age: Int) // similar to data object in Kotlin

  val bob = Person("Bob", 43) // === Person.apply("Bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    //if bob matches the structure of Person(n,a) ^^
    case _ => "Something else"
  }
  //  !!! Pattern Matching is only available for Case classes for now !!!
  //    (need to do a lot of changes to use with normal classes)


  // deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    // ^^ if the tuple conforms to (band, genre) structure,
    // then let these be the constituent parts of this tuple -> so that it can be reused after =>
    case _ => "I don't know what you're talking about"
  }

  // decomposing lists
  val aList = List(1, 2, 3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing 2 on its second position"
    //^^ 4 or 2 element pattern will not match the list! It should be 3 element long and 2nd element is 2!
    case _ => "unknown list"
    // if PM doesn't match anything, it will throw a MatchError, that's why we normally add "case _"
    // PM will try all cases in sequence, so don't put "case _" in the beginning!
  }
}
