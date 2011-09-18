package cycledetection

import Node._ // step function

/**
 * Robert W. Floyd's "Tortoise and Hare" algorithm.
 */
class Floyd[E] extends CycleDetector[E]  {

  def findCycle(list: Node[E]): Option[(Int,Int)] = {
    /*
     * Find a repetition list[i] = list[2i]
     * The hare moves twice as fast as the tortoise.
     */
    var (tort, hare) = (list, list)
    do {
      tort = step(tort)
      hare = step(step(hare))
    } while (hare != null && !(hare eq tort))

    if (hare == null)
      return None

    /*
     * At this point the start of the loop is equi-distant from the current
     * tortoise position and the start of the list, so the hare is moving in
     * a circle and the tortoise, moving towards the circle from the start
     * of the list, will intersect at the beginning of the circle.
     * 
     * Find start of repetition. The hare and tortoise move at the same speed.
     */
    var start = 0
    tort = list
    while (!(tort eq hare)) {
        tort = step(tort)
        hare = step(hare)
        start += 1
    }

    /*
     * Find length of shortest cycle starting from list[start].
     * The hare moves while the tortoise stays still.
     */
    var length = 0
    hare = tort
    do {
        hare = step(hare)
        length += 1
    } while (!(hare eq tort))

    return Some(start, length)
  }
}
