package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import static java.lang.reflect.Array.newInstance;


/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Alexander Pollock
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

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
    merge(values, lb, ub, midpoint);
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
} // class MergeSorter
