package cycledetection

/**
 * Robert W. Floyd's "Tortoise and Hare" algorithm.
 */
class Floyd[E] extends CycleDetector[E]  {

  def findCycle(list: Seq[E]): Option[(Int,Int)] = {
    /*
     * Find a repetition list[mu] = list[2mu]
     * The hare moves twice as fast as the tortoise.
     */
    var (tort, hare) = (list, list)
    do {
      tort = step(tort)
      hare = step(step(hare))
    } while (hare.nonEmpty && !(hare eq tort))

    if (hare.isEmpty)
      return None

    /*
     * At this point the start of the loop is equi-distant from the current
     * tortoise position and the start of the list, so the hare is moving in
     * a circle and the tortoise, moving towards the circle from the start
     * of the list, will intersect at the beginning of the circle.
     * 
     * Find the position of the first repetition of length mu.
     * The hare and tortoise move at the same speed.
     */
    var mu = 0
    tort = list
    while (!(tort eq hare)) {
        tort = step(tort)
        hare = step(hare)
        mu += 1
    }

    /*
     * Find the length of the shortest cycle starting from list[mu].
     * The hare moves while the tortoise stays still.
     */
    var lambda = 0
    hare = tort
    do {
        hare = step(hare)
        lambda += 1
    } while (!(hare eq tort))

    return Some(lambda, mu)
  }

  def step(list: Seq[E]) = {
    if (list.nonEmpty) list.tail else list
  }
}
