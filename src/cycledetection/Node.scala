package cycledetection

class Node[E](val head: E, var tail: Node[E] = null) {

  def last: Node[E] = {
    tail match {
      case null => this
      case _ => tail.last
    }
  }

  def toStream: Stream[E] = {
    head #:: (if (tail != null) tail.toStream else Stream.empty)
  }

  override def toString = tail match {
    case null => "Node(" + head + ")"
    case _ => "Node(" + head + ", ?)"
  }
}

object Node {

  def parse[E](seq: Seq[E], cycleStart: E): Node[E] = {
    val (prefix, suffix) = seq.span(_ != cycleStart)
    if (suffix.isEmpty)
      return parse(prefix)
    val cycle = parse(suffix)
    cycle.last.tail = cycle
    if (prefix.isEmpty)
      return cycle
    val node = parse(prefix)
    node.last.tail = cycle
    node
  }

  def parse[E](seq: Seq[E]) = {
    seq.foldRight(null: Node[E])((head, tail) => new Node(head, tail))
  }

  def step[E](node: Node[E]) = {
    if (node != null) node.tail else null
  }
}
