package mywork

object Basics2 extends App {

  //defining a value
  val variable:Int = 42 //this is a constant

  val aBoolean = false //type is optional

  val aString = "I love Scala"
  val aComposedString = "I" + " " + "Love" + " " + "Scala" //string concatenating
  val anInterPolatedString = s"The meaning of life is $variable"




  //defining a function with single expression
  def myfunction(x:Int, y:String) : String = y + " " + x

  //defining functions with code blocks

  def myfunction2(x:Int, y:String) : String = {
    y + " " + x
  }

  //recursive function

  def factorial(n:Int) : Int  = {
    if(n <= 1) 1
    else n * factorial(n-1)
  }

  //In scala we do not use loops or iterations, instead we use recursion

  //the unit return type
  //this is void return type === >>> no meaningful value
  println("This is a unit function") //this is just do something and does not return anything
  //type of SIDE EFFECTS

  def myUnitReturningFunction() : Unit = {
    println("I am Unit return type")
  }

  //returns type unit

  val theUnit = () //this open close parenthesis is also returns type unit(void in other languages)

}
