package cycledetection

import Node._ // step function

/**
 * Richard P. Brent's algorithm, also known as the "Teleporting Tortoise".
 */
class Brent[E] extends CycleDetector[E]  {

  def findCycle(list: Node[E]): Option[(Int,Int)] = {
    /*
     * Main phase: hare searches successive powers of two while the
     * tortoise teleports to the hare's position after each pass.
     */
    var (tort, hare) = (list, list)
    var limit = 1
    var length = 0
    do {
      if (length == limit) {
        // start a new pass
        tort = hare // teleport
        limit *= 2
        length = 0
      }
      hare = step(hare)
      length += 1
    } while (hare != null && !(hare eq tort))

    if (hare == null)
      return None

    /*
     * With the tortoise starting from the head of the list and the hare
     * spotted 'length' steps ahead, advance the tortoise and hare at the
     * same speed until they meet at the start of the repetition.
     */
    var start = 0
    tort = list
    hare = list
    (0 until length) foreach {_ =>
      hare = step(hare)
    }
    while (!(tort eq hare)) {
        tort = step(tort)
        hare = step(hare)
        start += 1
    }

    return Some(start, length)
  }
}
