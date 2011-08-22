package cycledetection

/**
 * Richard P. Brent's algorithm, also known as the "Teleporting Tortoise".
 */
class Brent[E] extends CycleDetector[E]  {

  def findCycle(list: Seq[E]): Option[(Int,Int)] = {
    /*
     * Main phase: hare searches successive powers of two while the
     * tortoise teleports to the hare's position after each pass.
     */
    var (tort, hare) = (list, list)
    var limit = 1
    var lambda = 0
    do {
      if (lambda == limit) {
        // start a new pass
        tort = hare // teleport
        limit *= 2
        lambda = 0
      }
      hare = step(hare)
      lambda += 1
    } while (hare.nonEmpty && !(hare eq tort))

    if (hare.isEmpty)
      return None

    /*
     * Find position of first repetition of length lambda.
     */
    hare = list
    0 until lambda foreach {_ =>
      hare = step(hare)
    }

    /*
     * Find position of first repetition of length mu.
     * The hare and tortoise move at the same speed.
     */
    var mu = 0
    tort = list
    while (!(tort eq hare)) {
        tort = step(tort)
        hare = step(hare)
        mu += 1
    }

    return Some((lambda, mu))
  }

  def step(list: Seq[E]) = {
    if (list.nonEmpty) list.tail else list
  }
}
