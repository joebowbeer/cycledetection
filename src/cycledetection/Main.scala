package cycledetection

object Main {

  def main(args: Array[String]): Unit = {
    def list = List('A', '*', 'B', 'C')
    println(list)
    new Floyd().findCycle(Node.parse(list)) match {
      case None => println("No loop")
      case Some((lambda, mu)) => println("lambda = " + lambda + ", mu = " + mu)
    }
  }
}
