package cats

import cats.data.{Nested, OptionT}

/**
  * <pre>
  * A Functor is a ubiquitous type class involving types that have one "hole", i.e. types which have the shape F[?], such as Option, List and Future.
  * (This is in contrast to a type like Int which has no hole, or Tuple2 which has two holes (Tuple2[?,?])).
  *
  * The Functor category involves a single operation, named map:
  * def map[A, B](fa: F[A])(f: A => B): F[B]
  *
  * This method takes a function A => B and turns an F[A] into an F[B]
  * Example:
  *
  *
  * </pre>
  */
object _003_FunctorExample extends App {

  import cats._
  import cats.implicits._

  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa map f
  }

  implicit val listFunctor: Functor[List] = new Functor[List] {
    def map[A, B](fa: List[A])(f: A => B): List[B] = fa map f
  }

  //  https://github.com/non/kind-projector
  implicit def function1Functor[In]: Functor[Function1[In, ?]] =
    new Functor[Function1[In, ?]] {
      def map[A, B] (fa: In => A) (f: A => B): Function1[In, B] = fa andThen f
    }

  val source = List("Cats", "is", "awesome")
  val product: Map[String, Int] = Functor[List].fproduct(source)(_.length).toMap

  println(product.getOrElse("Cats", 0))

  val listOpt = Functor[List] compose Functor[Option]
  println(listOpt.map(List(Some(1), None, Some(3)))(_ + 1))


  // MapN
  println("")
  println("MapN")
  println("")
  println(("a".some, "b".some).mapN(_ ++ _))

  println((List(1, 2), List(3, 4), List(5, 6)).mapN((a, b, c) => println(s"$a $b $c")))

  case class Mead(name: String, honeyRatio: Double, agingYears: Double)

  println(("półtorak".some, 0.5.some, 3d.some).mapN(Mead))

  object ex4 {
    import cats.effect.IO

    type Query[T] = IO[Option[T]]

    def defineMead(qName: Query[String],
                   qHoneyRatio: Query[Double],
                   qAgingYears: Query[Double]): Query[Mead] = for {
      name <- qName
      honeyRatio <- qHoneyRatio
      agingYears <- qAgingYears
    } yield (name, honeyRatio, agingYears).mapN(Mead)

    // Or Monad Transformers
     def defineMead2(qName: Query[String],
                   qHoneyRatio: Query[Double],
                   qAgingYears: Query[Double]): Query[Mead] = (for {
      name <- OptionT(qName)
      honeyRatio <- OptionT(qHoneyRatio)
      agingYears <- OptionT(qAgingYears)
    } yield Mead(name, honeyRatio, agingYears)).value

  }

//  // Nested not work???
//  val someValue: Option[Either[Int, String]] = "a".asRight.some
//  println(Nested[Option, Either[Int, ?], String](someValue).map(a => println(a)))

  //IoR

  val iorFailure = "a".asLeft[Int]
  iorFailure.valueOr(code => code.length)





}
