package cycledetection

import scala.collection.mutable.LinkedList

object Main {

  def main(args: Array[String]): Unit = {
    val list = new LinkedList('A', new LinkedList('B', new LinkedList('C', null)))
    list.next.next.next = list
    new Floyd().findCycle(list) match {
      case None => println("No loop")
      case Some((lambda, mu)) => println("lambda = " + lambda + ", mu = " + mu)
    }
  }
}
