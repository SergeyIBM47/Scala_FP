package cats

/**
  * A Semigroup for some given type A has a single operation (which we will call combine),
  * which takes two values of type A, and returns a value of type A.
  * This operation must be guaranteed to be associative
  *
  * Example:
  * <pre>
  *   import cats.implicits._
  *   Semigroup[Int].combine(1, 2) should be( 3 )
  *   Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6)) should be( List(1, 2, 3, 4, 5, 6) )
  *   Semigroup[Option[Int]].combine(Option(1), Option(2)) should be( Option(3) )
  *   Semigroup[Option[Int]].combine(Option(1), None) should be( Option(1) )
  * </pre>
  */
object _001_SemigroupExample extends App {
  import cats.implicits._
  val semigroup = Semigroup[Int ⇒ Int]
    .combine({ (x: Int) ⇒
      x + 1
    }, { (x: Int) ⇒
      x * 10
    })
  println(semigroup.apply(6))


  val aMap = Map("foo" → Map("bar" → 5))
  val anotherMap = Map("foo" → Map("bar" → 6))
  val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(aMap, anotherMap)

  println(combinedMap("foo"))


}
