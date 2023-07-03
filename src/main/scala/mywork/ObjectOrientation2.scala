package mywork

object ObjectOrientation2 extends App{

//===================================================================
  //all attributed and methods are by default public
  //can use private and protected as access specifiers


  //class
  class Animal{
    //define fields
    val age : Int = 0
    //define methods
    def eat() : Unit = println("I am eating")
  }

  //creating an instance
  val anAnimal = new Animal()

//===================================================================

  //inheritance
  class Dog extends Animal{

  }

  //if you have nothing to add in dog class, you can leave it blank
  //class dog extends Animal


//===================================================================


  //constructor
  //pass values to a class
  class Cat(val name:String) extends Animal //constructor definition
  //constructor arguments are not FIELDS
  //putting "val" before the argument name will save the constructor argument as a member of the class

  //subtype polymorphism
  val aDeclaredAnimal = new Cat("saliya")
  aDeclaredAnimal.eat()//the most derived method will be called at runtime.

//===================================================================

  //abstract class
  abstract class WalkingAnimal {
    val haslegs = true //by default public
    def walk() : Unit//abstract function need to be override in child classes
  }

//===================================================================

  //interfaces -> ultimate abstract type
  //trait  = interface
  trait Carnivore {
    def eat(animal : Animal) : Unit
  }

  trait Philosopher{
    def ?!(thought : String) : Unit //valid method name
  }

  //single - class inheritance , multi trait "mixing"

  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal : Animal): Unit = println("I  am eating you animal")

    override def ?!(thought : String) : Unit = println(s"I was thinking : $thought")

  }

  var aCrock = new Crocodile()
  aCrock.eat(anAnimal)
  aCrock eat anAnimal //infix notation = object method argument (format), only available for methods with one argument
  aCrock ?! "what if we could fly?"

//===================================================================

  //operators in scala are actually methods
  val basicmath = 1 + 2 //+ is an operator
  val basicmathinfunctionformat = 1.+(2) //equivalent to 1+2

//===================================================================

  //anonymous classes

  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat everything")
  }

  /*creating a new anonymous class and assign it a variable
  * what you tell the compiler
  * class Carnivore_anonymous_1234 extends Carnivore{
  *       override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat everything");
  * }
  *
  *   val dinosaur = Carnivore_anonymous_1234
  * */


//===================================================================

  //singleton object

  //the only instance of the MySingleton type
  //can contain any variable or any method
  object MySingleton1{
    val mySpecialValue = 53278
    def mySpecialMethod(): Int = 5327
  }

  MySingleton1.mySpecialMethod();

//===================================================================

  //apply() method

  //the apply method is a special method that allows an object to be
  // invoked as if it were a function.

  //you can add the apply method to any object or class in Scala.

  object MySingleton2 {
    val mySpecialValue = 53278

    def mySpecialMethod(): Int = 5327
    def apply(x:Int) : Int = x + 1
  }

  MySingleton2.apply(65); // == MySingleton2(65);
  MySingleton2(65); //refer to apply method


//===================================================================

  //companions - companion object

  /*
  * A companion object in Scala is an object that’s declared in the same file as a class,
  * , and has the same name as the class.
  *
  * For instance, when the following code is saved in a file named Pizza.scala, the Pizza object is considered to be a companion object to the Pizza class:

  class Pizza {
  }

  object Pizza {
  }
  *
  * benefits->
  *
  * a companion object and its class can access each other’s private members .
  * singleton Animal and instances of Animal are two different types.
  *both class and the companion object should be in the same file.
  * */

  //there is a class called Animal in this file
  //the companion object of the class Animal
  object Animal{



  }



//===================================================================

  //case classes

  /*
  * case classes = lightweight data structures with some boilerplate
  * - sensible equals and hash code
  * - serialization
  * - companion with apply
  * - pattern matching
  * */









}
