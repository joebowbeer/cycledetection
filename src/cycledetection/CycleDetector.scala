package cycledetection

trait CycleDetector[E] {
  def findCycle(list: Node[E]): Option[(Int, Int)]
}
