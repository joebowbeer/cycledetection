package cycledetection

trait CycleDetector[E] {
  /** Returns Some(start, length) of cycle, if found, else None. */
  def findCycle(list: Node[E]): Option[(Int, Int)]
}
