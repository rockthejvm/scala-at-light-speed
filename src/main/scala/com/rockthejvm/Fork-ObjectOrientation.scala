package com.rockthejvm

object ObjectOrientation extends App {

  //  class
  class Animal {
    //  define fields
    val age: Int = 0

    //  define methods
    def eat() = println("I'm eating")
  }

  //  instance of Class
  val anAnimal = new Animal

  //  inheritance
  class Dog(val name: String) extends Animal //constructor definition

  val aDog = new Dog("Lassie")

  //  constructor arguments are not fields!
  aDog.name //cannot compile unless val is added to constructor "name"!

  //  subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime!

  //abstract class
  abstract class WalkingAnimal {
    // all fields and methods are public by default.
    //    Can restrict it by using 'private' or 'protected'
    //    protected val hasLegs = true
    //    private val hasLegs = true
    val hasLegs = true

    def walk(): Unit
  }

  //  interface = ultimate abstract type
  //  In Scala you can also give implementation inside interface/traits
  //  But usually Traits are used to denote characteristics of objects that you can later use in our Concrete classes!
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  //  Scala allows single-class inheritance and multi traits inheritance.
  //  In Scala, adding a trait is called as "Mixing"
  class Crocodile extends Animal with Carnivore with SomeOtherTrait with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating you, animal!") // some implementation or make the class abstract

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  trait SomeOtherTrait

  trait Philosopher {
    def ?!(thought: String): Unit //?! is a valid name
    // ? operator or method name is often used in Akka
    // ! method is used to communicate with actors asynchronously
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  /* OR methods with single argument can be used in infix notation*/
  // Infix Notation -> object method argument
  aCroc eat aDog
  aCroc ?! "yummmmyyy"

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2)
  //basicMath and anotherBasicMath are same!

  //anonymous classes:
  // create new instance of Abstract class or Trait and give definition
  // because they need a Concrete class
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur, so I can eat everything")
  }

  //singleton object

  object MySingleton //the only instance of the MySingleton type
  {
    val mySpecialValue = 32278

    def mySpecialMethod() = 5327

    def apply(x: Int): Int = x + 1 //apply is a special Scala method

    //    apply can be in any object or any class and can take any Arguments
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  //    instances of the class can call apply method as
  MySingleton(65)
  //  OR
  MySingleton(65) // automatically interpreted as a call to the Apply method
  // apply is useful in Functional Programming

  //  in the same file, you can define a Class/Trait and a Singleton Object of same name.
  //Eg:
  object Animal {
    // companions can access each other's private fields/methods
    // However, "object Animal" and instances of Animal are different things!
    val canLiveForever = false
  }
  // Here object and class/trait are called Companions and
  // "object Animal" is a companion object

  val animalCanLiveForever = Animal.canLiveForever //similar to "static" in java

  //Case classes! -> Important in Scala!
  //  Case classes = lightweight data structure with some boilerplate!
  // When Case Class is defined, compiler auto generates the following things:
  //    - sensible equals and hashcode (for inclusion into various Collections that rely on equality and hashcode )
  //    - Sensible and quick serialization because we often send these instances over the wire in Distributed application,
  //    in particular with Akka
  //    - companion with apply
  //    - pattern matching

  case class Person(name: String, age: Int)

  val bob = new Person("Bob", 54)
  //  case class can be constructed without the keyword 'new' because they have Companion with apply keyword
  val bob1 = Person("Bob", 54) // Person.apply("Bob", 54)

  //Exceptions
  try {
    //code that can throw exception
    val x: String = null
    x.length
  } catch {
    case e: Exception => "some faulty error message"
  } finally {
    //execute some code, no matter what!
  }

  // Generics
  //  Similar to templating in C++
  abstract class MyList[T] {
    def head: T

    def tail: MyList[T]
  }

  //  using a generic with a concrete type
  // List of Int type
  val aList: List[Int] = List(1, 2, 3) // List.apply(1,2,3)
  val first = aList.head
  val rest = aList.tail
  //  List of String type
  val aStringList: List[String] = List("Hello", "World!")
  val firstString = aStringList.head
  val restStrings = aStringList.tail

  //  Important pointers
  //    1. In Scala, we usually operate with IMMUTABLE values/objects.
  //      Benefits:
  //      a. works miracles in multithreaded/distributed env.
  //      b. helps making sense of the code ("reasoning about")
  val reversedList = aList.reverse // returns a NEW list
  //    2. Scala is the closest to the Object Orientated ideal
  //    There are no values/methods outside the class/object


  /*
  * When we define an object that extends App,
  * you are inheriting its main method!
  * since methods on objects are similar to Java's static methods,
  * the main method is like -> "public static void main(String[] args)" in java
  * */
}
