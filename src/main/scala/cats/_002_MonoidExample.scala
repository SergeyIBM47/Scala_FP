package cats

/**
  * Monoid extends the Semigroup type class, adding an empty method to semigroup's combine.
  * The empty method must return a value that when combined with any other instance of that type returns the other instance, i.e.
  *
  * Example:
  * <pre>
  * import cats.implicits._
  * Monoid[String].empty should be( "" )
  * Monoid[String].combineAll(List("a", "b", "c")) should be( "abc" )
  * Monoid[String].combineAll(List()) should be( "" )
  * </pre>
  */
object _002_MonoidExample extends App {
  import cats.implicits._
  val emptyMonoid: String = Monoid[String].empty
  val combineListMonoid: String = Monoid[String].combineAll(List("a", "b", "c"))
  val combineEmptyListMonoid: String = Monoid[String].combineAll(List())
  val combineAllMonoid = Monoid[Map[String, Int]].combineAll(List(Map("a" → 1, "b" → 2), Map("a" → 3)))
  val combineAllEmptyListMonoid = Monoid[Map[String, Int]].combineAll(List())
  val foldMapModoidForTuple = List(1, 2, 3, 4, 5).foldMap(i ⇒ (i, i.toString))

  println(s"Empty Monoid: $emptyMonoid")
  println(s"Combine List Monoid: $combineListMonoid")
  println(s"Combine Empty Monoid: $combineEmptyListMonoid")
  println(s"Combine All Monoid: $combineAllMonoid")
  println(s"Combine All Empty List Monoid: $combineAllEmptyListMonoid")
  println(s"Fold Map Modoid For Tuple: $foldMapModoidForTuple")

}
