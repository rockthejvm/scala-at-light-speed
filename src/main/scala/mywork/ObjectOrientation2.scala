package mywork

object ObjectOrientation2 extends App{

  //all attributed and methods are by default public
  //can use private and protected as access specifiers


  //class and instance
  class Animal{
    //define fields
    val age : Int = 0
    //define methods
    def eat() = println("I am eating")
  }
  val anAnimal = new Animal()

  //inheritance
  class Dog extends Animal

  //pass values to a class
  class Cat(val name:String) extends Animal //constructor definition
  //putting a val before the argument name will save the constructor argument as a member of the class
  //constructor arguments are not FIELDS

  val aDeclaredAnimal = new Cat("saliya")
  aDeclaredAnimal.eat()

  //abstract class
  abstract class WalkingAnimal {
    val haslegs = true
    def walk() : Unit//abstract function need to be override in child classes
  }

  //interfaces
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
  aCrock eat anAnimal //infix notation = object method argument (format)
  aCrock ?! "what if we could fly?"

  //operators in scala are actually methods
  val basicmath = 1 + 2 //+ is an operator
  val basicmathinfunctionformat = 1.+(2) //equivalent to 1+2

  //anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat everything")
  }











}
