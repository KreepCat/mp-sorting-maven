package edu.grinnell.csc207.sorting;

import static java.lang.reflect.Array.newInstance;
import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Alexander Pollock
 */

public class PollockAlexanderSort<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public PollockAlexanderSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Sort the value between two indecies.
   *
   * @param values the values to sort.
   * @param lb the lower bound to sort.
   * @param ub the upper bound to sort.
   */
  public void sort(T[] values, int lb, int ub) {
    if (lb >= ub) {
      return;
    } // if
    int midpoint = lb + (ub - lb) / 2;
    sort(values, lb, midpoint);
    sort(values, midpoint + 1, ub);
    int lowbound = findLoc(values, values[midpoint + 1], lb, midpoint);
    int highbound = findLoc(values, values[midpoint], midpoint + 1, ub);
    merge(values, lowbound, highbound, midpoint);
  } // sort(T[],int,int)

  /**
   * Merge the values between two points.
   *
   * @param values the array to merge values from
   * @param lb the lower bound of the values to merge
   * @param ub the upper bound of the values to merge (inclusive)
   * @param midpoint the midpoint of the values to merge
   *
   * @post the values between lb and ub are merged.
   */
  @SuppressWarnings({"unchecked"})
  public void merge(T[] values, int lb, int ub, int midpoint) {
    T[] helper = (T[]) newInstance((values[0]).getClass(), (ub - lb + 1));
    int indexLow = lb;
    int indexHigh = midpoint + 1;

    for (int i = 0; i < helper.length; i++) {
      if (indexLow > midpoint) {
        helper[i] = values[indexHigh];
        indexHigh++;
      } else if (indexHigh > ub) {
        helper[i] = values[indexLow];
        indexLow++;
      } else {
        int comparator = order.compare(values[indexLow], values[indexHigh]);
        if (comparator <= 0) {
          helper[i] = values[indexLow];
          indexLow++;
        } else {
          helper[i] = values[indexHigh];
          indexHigh++;
        } // if/else
      } // if/else
    } // for
    for (int i = 0; i < helper.length; i++) {
      values[lb + i] = helper[i];
    } // for
  } // merge(T[],int,int,int)

  /**
   * Find where a value would go in a sorted array.
   *
   * @pre values is sorted between lb and ub
   *
   * @param values the array of values
   * @param val the value we are trying to find the location of
   * @param lb the lower bound of places to search
   * @param ub the upper bound of places to search
   * @return the location that the value goes between
   */

  public int findLoc(T[] values, T val, int lb, int ub) {
    if (lb == ub) {
      return lb;
    } // if
    int mid = (lb + ub) / 2;
    int comparison1 = order.compare(values[mid], val);
    int comparison2 = order.compare(values[mid + 1], val);

    if (comparison1 < 0 && comparison2 > 0) {
      return mid;
    } else if (comparison1 > 0 && comparison2 > 0) {
      return findLoc(values, val, lb, mid);
    } // if/else
    return findLoc(values, val, mid + 1, ub);
  } // findLoc(T[],T,int,int)
} // class Quicksorter

