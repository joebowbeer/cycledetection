package cycledetection

object Main {

  def main(args: Array[String]): Unit = {
    def node = Node.parse("ABC", 'B')
    println(node)
    println(node.toStream.take(5).mkString)
    new Floyd().findCycle(node) match {
      case None => println("No loop")
      case Some((start, length)) =>
        println("start = " + start + ", length = " + length)
    }
  }
}
