package cycledetection

class Node[E](val head: E, var tail: Node[E] = null) {
  def last: Node[E] = {
    tail match {
      case null => this
      case _ => tail.last
    }
  }
  override def toString = tail match {
    case null => "Node(" + head + ")"
    case _ => "Node(" + head + ", ?)"
  }
}

object Node {

  def parse(list: List[Char]): Node[Char] = {
    val (prefix, suffix) = list.span(_ != '*')
    if (suffix.isEmpty)
      return fromList(prefix)
    val cycle = fromList(suffix)
    cycle.last.tail = cycle
    if (prefix.isEmpty)
      return cycle
    val node = fromList(prefix)
    node.last.tail = cycle
    node
  }

  def step[E](node: Node[E]) = {
    if (node != null) node.tail else node
  }

  private def fromList[E](list: List[E]) = {
    list.foldRight(null: Node[E])((head, tail) => new Node(head, tail))
  }
}
