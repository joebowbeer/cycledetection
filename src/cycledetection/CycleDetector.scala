package cycledetection

trait CycleDetector[E] {
  def findCycle(list: Seq[E]): Option[(Int, Int)]
}
