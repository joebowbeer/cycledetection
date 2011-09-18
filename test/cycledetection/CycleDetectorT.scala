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
  def testA = {
    assertEquals(None, search("A"))
  }

  @Test
  def testABC = {
    assertEquals(None, search("ABC"))
  }

  @Test
  def testAA = {
    assertEquals(Some(0,1), search("A", 'A'))
  }

  @Test
  def testABB = {
    assertEquals(Some(1,1), search("AB", 'B'))
  }

  @Test
  def testABA = {
    assertEquals(Some(0,2), search("AB", 'A'))
  }

  @Test
  def testABCB = {
    assertEquals(Some(1,2), search("ABC", 'B'))
  }

  @Test
  def testABCDEFGHDEFGH = {
    assertEquals(Some(3,5), search("ABCDEFGH", 'D'))
  }

  def search(s: Seq[Char]): Option[(Int,Int)] = {
    println(s)
    detector.findCycle(Node.parse(s))
  }

  def search(s: Seq[Char], startCycle: Char): Option[(Int,Int)] = {
    val list = Node.parse(s, startCycle)
    println(list.toStream.take(s.length*2).mkString)
    detector.findCycle(list)
  }
}
