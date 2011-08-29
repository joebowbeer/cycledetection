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
    assertEquals(Some((2,0)), search("*B"))
  }

  @Test
  def testABCB = {
    assertEquals(Some((2,1)), search("A*C"))
  }

  @Test
  def testABCDEFGHDEFGH = {
    assertEquals(Some((5,3)), search("ABC*EFGH"))
  }

  @Test
  def testAA = {
    assertEquals(Some((1,0)), search("*"))
  }

  @Test
  def testABB = {
    assertEquals(Some((1,1)), search("A*"))
  }

  def search(s: Iterable[Char]): Option[(Int,Int)] = {
    detector.findCycle(Node.parse(s.toList))
  }
}
