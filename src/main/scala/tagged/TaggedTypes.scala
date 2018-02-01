package tagged

object TaggedTypes {

  class User
  class Checkin

  type Tagged[U] = { type Tag = U }
  type @@[T, U] = T with Tagged[U]

  class Tagger[U] {
    def apply[T](t: T): T @@ U = t.asInstanceOf[T @@ U]
//    def apply[T](t: T): @@[T, U] = t.asInstanceOf[@@[T, U]]
  }

  def tag[T] = new {
    def apply[U](u : U) : U @@ T = u.asInstanceOf[U @@ T]
  }

//  def tag[U] = new Tagger[U]
  def tag[U](i: Int): Int @@ U = i.asInstanceOf[Int @@ U]
  def tag[U](l: Long): Long @@ U = l.asInstanceOf[Long @@ U]
  def tag[U](i: Double): Double @@ U = i.asInstanceOf[Double @@ U]

  def fetch[A](id: Int @@ A): A = null.asInstanceOf[A]

  def main(args: Array[String]): Unit = {
    val id = tag[Checkin](1)

    fetch[Checkin](id)
//    fetch[User](id)

    val ids = tag[User](1) :: tag[User](2) :: Nil

    val users: List[Int @@ User] = ids
//    val checkins: List[Int @@ Checkin] = ids

    tag[Checkin]
  }




}
