package cycledetection

import org.junit._
import org.junit.Assert._

abstract class CycleDetectorT {

  def detector: CycleDetector[Char]

  @Before
  def setUp: Unit = {
  }

  @After
  def tearDown: Unit = {
  }

  @Test
  def emptyList = {
    assertEquals(None, search(""))
  }

  @Test
  def testABC = {
    assertEquals(None, search("ABC"))
  }

  @Test
  def testABA = {
    assertEquals(Some((2,1)), search("*B"))
  }

  @Test
  def testABCB = {
    assertEquals(Some((2,2)), search("A*C"))
  }

  @Test
  def testABCDEFGHDEFGH = {
    assertEquals(Some((5,4)), search("ABC*EFGH"))
  }

  def search[E](seq: Seq[Char]): Option[(Int,Int)] = {
    detector.findCycle(CyclicLists.parse(seq))
  }
}
