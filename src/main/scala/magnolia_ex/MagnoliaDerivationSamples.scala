package magnolia_ex

object MagnoliaDerivationSamples extends App {

  import language.experimental.macros, magnolia._

  object co_product_trait {
    sealed trait Tree[+T]
    case class Branch[+T](left: Tree[T], right: Tree[T]) extends Tree[T]
    case class Leaf[+T](value: T) extends Tree[T]

    Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))/*.show*/



    trait Show[T] {
      def show(value: T): String
    }

    object ShowDerivation {
      type Typeclass[T] = Show[T]

      def combine[T](ctx: CaseClass[Show, T]): Show[T] = new Show[T] {
        def show(value: T): String = ctx.parameters.map { p =>
          s"${p.label}=${p.typeclass.show(p.dereference(value))}"
        }.mkString("{", ",", "}")
      }

      def dispatch[T](ctx: SealedTrait[Show, T]): Show[T] =
        new Show[T] {
          override def show(value: T): String = ctx.dispatch(value) { sub =>
            sub.typeclass.show(sub.cast(value))
          }
        }

      implicit def gen[T]: Show[T] = macro Magnolia.gen[T]
    }


  }




}
