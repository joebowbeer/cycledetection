package cycledetection

trait CycleDetector[E] {
  def findCycle(node: Node[E]): Option[(Int, Int)]
}
