import scala.io.StdIn.readLine

@main
def main(): Unit = {
  //println("Hello world!")
//  val xs = List(1,2,3,4,5,6)
//  val xsM = xs.map(_ * 3)
//  val xsM = xs.filter(_ < 3);
//  val xsM = xs.find(_ > 3)
//  val xsM = xs.takeWhile(_ > 3);
//  println(xsM)
  val li2a = List.range(1, 10)
  val li2b = List.range(start=1, end=20, step=3);
  val li2c = List.fill(5)("foo")
  val li2d = List.fill(2,2)("foo") //defining multidimensional List List.fill(3,3,3,3)("element to be filled")
  val li2e = List.tabulate(5)(n=>n*2)
  val li2f = List.tabulate(2,2)((n1, n2) => n1+n2);
  val li2g = List.tabulate(3,3)((n1, n2) => n1+n2); //created multidimensional List with defined function

  val li3 = List(10, 20, 30, 40, 10)
  val li3a = li3.distinct
  val li3b = li3.drop(2)
  val li3c = li3.dropRight(2)
  val li3d = li3.dropWhile(_ <= 30)

  val a = List(10, 20, 30, 40, 10) // List(10, 20, 30, 40, 10)
  a.distinct // List(10, 20, 30, 40)
  a.drop(2) // List(30, 40, 10)
  a.dropRight(2) // List(10, 20, 30)
  a.dropWhile(_ < 25) // List(30, 40, 10)
  a.filter(_ < 25) // List(10, 20, 10)
  a.filter(_ > 100) // List()
  a.find(_ > 20) // Some(30)
  a.head // 10
  a.headOption // Some(10)
  a.init // List(10, 20, 30, 40)
  a.intersect(List(19, 20, 21)) // List(20)
  a.last // 10
  a.lastOption // Some(10)
  a.map(_ * 2) // List(20, 40, 60, 80, 20)
  a.slice(2, 4) // List(30, 40)
  a.tail // List(20, 30, 40, 10)
  a.take(3) // List(10, 20, 30)
  a.takeRight(2) // List(40, 10)
  a.takeWhile(_ < 30) // List(10, 20)
  a.filter(_ < 30).map(_ * 10) // List(100, 200, 100)

  val li5 = List("atomic", "oxygen")
  val li5a = li5.map(_.toUpperCase())
  val li5b = li5.flatMap(_.toUpperCase())
  val xs = Map("a" -> List(11,111), "b" -> List(22,222)).flatMap(_._2)

  val nums = List(10, 5, 8, 1, 7)
  nums.sorted // List(1, 5, 7, 8, 10)
  nums.sortWith(_ < _) // List(1, 5, 7, 8, 10)
  nums.sortWith(_ > _) // List(10, 8, 7, 5, 1)

  val a1 = 1                 // immutable variable
  val a2 = List(1, 2, 3) // List is immutable
  val a3 = Map(1 -> "one") // Map is immutable


  def numAsString(num:Int) = num match
    case 1 | 3 | 5 | 7 | 9 => "odd"
    case 2 | 4 | 6 | 8 | 10 => "even"
    case _ => "too big"

  def isTruthy(a: Matchable) = a match
    case 0 | "" => false
    case _ => true

//  hello()
//  helloInteractive()

  //Variables and Data Types

    //  val	: Creates an immutable variable—like final in Java. You should always create a variable with val, unless there’s a reason you need a mutable variable.
    //  var : Creates a mutable variable, and should only be used when a variable’s contents will change over time.
    //  val x: Int = 1 // explicit
    //  val x = 1 // implicit; the compiler infers the type
    //  val x: Int = 1
    //  val s: String = "a string"
    //  val p: Person = Person("Richard")

  //  Built - in data types
      //    val b: Byte = 1
      //    val i: Int = 1
      //    val l: Long = 1
      //    val s: Short = 1
      //    val d: Double = 2.0
      //    val f: Float = 3.0
      //Int and Double are the default numeric types, you typically create them without explicitly declaring the data type:
        //  val x = 1_000L // val x: Long = 1000
        //  val y = 2.2D // val y: Double = 2.2
        //  val z = 3.3F // val z: Float = 3.3
        //  val name = "Bill" // String
        //  val c = 'a' // Char
      // String : Scala strings are similar to Java strings, but they have two great additional features:
                //They support string interpolation and It’s easy to create multiline strings
        //  val firstName = "John"
        //  val mi = 'C'
        //  val lastName = "Doe"
        //println(s"Name: $firstName $mi $lastName")   // "Name: John C Doe"
        //  println(s"2 + 2 = ${2 + 2}") // prints "2 + 2 = 4"
        //  val x = -1
        //  println(s"x.abs = ${x.abs}") // prints "x.abs = 1"
      //  Multiline strings
  val quote =
    """The essence of Scala:
                 Fusion of functional and object-oriented
                 programming in a typed setting."""
  println(quote)

}
def hello(): Unit = println("Hello, World!")

def helloInteractive(): Unit =
  println("Please enter your name:")
  val name = readLine()
  println("Hello, " + name + "!")