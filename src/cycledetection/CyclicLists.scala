package cycledetection

import scala.collection.mutable.LinkedList

object CyclicLists {
  def parse(seq: Seq[Char]) = {
    val list = new LinkedList[Char]()
    var tail = list
    var star: LinkedList[Char] = null
    seq.foreach { x =>
      tail.next = new LinkedList(x, null)
      tail = tail.next
      if (x == '*')
        star = tail
    }
    if (star ne null) {
      tail.next = star
    }
    list
  }
}
