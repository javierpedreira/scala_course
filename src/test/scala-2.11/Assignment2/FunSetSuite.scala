package Assignment2

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import Assignment2.Funsets._
/**
  * This class is a test suite for the methods in object FunSets. To run
  * the test suite, you can either:
  *  - run the "test" command in the SBT console
  *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
    * Link to the scaladoc - very clear and detailed tutorial of FunSuite
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Operators
    *  - test
    *  - ignore
    *  - pending
    */

  /**
    * Tests are written using the "test" operator and the "assert" method.
    */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
    * For ScalaTest tests, there exists a special equality operator "===" that
    * can be used inside "assert". If the assertion fails, the two values will
    * be printed in the error message. Otherwise, when using "==", the test
    * error message will only say "assertion failed", without showing the values.
    *
    * Try it out! Change the values so that the assertion fails, and look at the
    * error message.
    */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }




  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    *   val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1") {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets {
      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection contains only common elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      val s5 = union(s2, s3)
      val s6 = intersect(s,s5)

      assert(!contains(s6, 1), "Intersect 1")
      assert(contains(s6, 2), "Intersect 2")
      assert(!contains(s6, 3), "Intersect 3")
    }
  }

  test("diff contains only the elements in a Set not present in another set ") {
    new TestSets {
      val s = union(s1, s2)
      val s5 = union(s2, s3)
      val s6 = diff(s,s5)

      assert(contains(s6, 1), "Diff 1")
      assert(!contains(s6, 2), "Diff 2")
      assert(!contains(s6, 3), "Diff 3")
    }
  }
  test("filter contains only the elements in a Set filtered by the function p ") {
    new TestSets {
      val s = union(s1, s2)
      val s5 = union(s2, s3)
      val s6 = filter(union(s5,singletonSet(4)), x => x%2 == 0)

      assert(!contains(s6, 1), "Filter 1")
      assert(contains(s6, 2), "Filter 2")
      assert(contains(s6, 4), "Filter 4")
    }
  }
  test("forall checks that all the elements in a Set holds a certain property ") {
    new TestSets {
      val s = union(singletonSet(2), singletonSet(4))
      val s5 = union(singletonSet(6), singletonSet(8))

      assert(forall(s5, x => x%2 == 0), "Forall 1")
      assert(!forall(s5, x => x%2 == 1), "Forall 1")
    }
  }
  test("exists checks that there is an element that fits p ") {
    new TestSets {
      val s = union(union(union(union(singletonSet(2), singletonSet(4)),singletonSet(6)),singletonSet(8)),singletonSet(10))

      assert(exists(s, x => x == 10), "Exists 10")
      assert(exists(s, x => x == 4), "Exists 4")
      assert(!exists(s, x => x == 5), "Doesn't Exists 5")
      assert(!exists(s, x => x == 3), "Doesn't Exists 3")
    }
  }
  test("map retrieves a new set in which all the elements are transformed ") {
    new TestSets {
      val s = union(union(union(union(singletonSet(1), singletonSet(5)),singletonSet(3)),singletonSet(7)),singletonSet(9))

      assert(forall(map(s, x => x * 2), x => x%2 == 0), "All the elements are now even")
      assert(forall(map(s, x => x * 2), x => !(x%2 != 0)), "there are no odd numbers")
    }
  }
}