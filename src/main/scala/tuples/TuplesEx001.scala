package tuples

object TuplesEx001 extends App {

//  object User extends Function3[String, String, Int, User]
  case class User(name: String, address:String, age: Int)

  val userTuple: (String, String, Int) = ("name", "address", 18)
  val user: User = (User.apply _).tupled(userTuple)

  val user2: User = User.tupled(userTuple)

  val tupleUser: (String, String, Int) = User.unapply(user).get

  val applyUser: (String, String, Int) => User = new User(_, _, _)
  val appliedUser: User = applyUser("", "", 1)

  val tupledToUser = applyUser.tupled.apply(_)
  val tupledToUser2 = applyUser.tupled
  val user3: User = tupledToUser("", "", 1)
  val a = Function.tupled(applyUser)

}


