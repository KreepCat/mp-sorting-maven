package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly. Rather, you should subclass it
 * and initialize stringSorter and intSorter in a static @BeforeAll method.
 *
 * @author Alex Pollock
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the permutation and assert that it
   * equals the original.
   *
   * @param <T> The type of values in the array.
   * @param sorted The sorted array.
   * @param perm The permuted sorted array.
   * @param sorter The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm, () -> String.format("sort(%s) yields %s rather than %s",
        Arrays.toString(tmp), Arrays.toString(perm), Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably just to make sure that some
   * test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"foxtrot", "delta", "charlie", "bravo", "alpha"};
    String[] expected = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized array sorts correctly.
   */
  @Test
  public void permutedIntegersTest() {
    int SIZE = 100;
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Ensure sorting can be done multiple times on arrays in reverse.
   */
  @Test
  public void reversedIntSorting() {
    if (null == intSorter) {
      return;
    } // if
    for (int i = 0; i < 100; i++) {
      Integer[] unsorted = new Integer[i];
      Integer[] expected = new Integer[i];
      for (int n = 0; n < i; n++) {
        unsorted[i - n] = n;
        expected[n] = n;
      } // for
      assertSorts(expected, unsorted, intSorter);
    } // for
  } // reversedIntSorting

  /**
   * Ensure sorting can be done multiple times on arrays in random orders.
   */
  @Test
  public void multipleIntSorting() {
    if (null == intSorter) {
      return;
    } // if
    for (int i = 0; i < 100; i++) {
      Integer[] expected = new Integer[i];
      Integer[] original = new Integer[i];
      for (int n = 0; n < i; n++) {
        expected[n] = n;
        original[n] = n;
      } // for
      ArrayUtils.permute(original);
      assertSorts(expected, original, intSorter);
    } // for
  } // multipleIntSorting

  /**
   * Ensure that an already sorted array does not cause errors.
   */
  @Test
  public void sameObjectSort() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"foxtrot", "foxtrot", "foxtrot", "foxtrot", "foxtrot"};
    String[] expected = {"foxtrot", "foxtrot", "foxtrot", "foxtrot", "foxtrot"};
    assertSorts(expected, original, stringSorter);
  } // sameObjectSort

  /**
   * Ensure that an empty array does not cause errors.
   */
  @Test
  public void emptySorting() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {};
    String[] expected = {};
    assertSorts(expected, original, stringSorter);
  } // emptySorting

  /**
   * Ensure that an array with one object does not cause errors.
   */
  @Test
  public void singleSorting() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"1"};
    String[] expected = {"1"};
    assertSorts(expected, original, stringSorter);
  } // singleSorting

} // class TestSorter
