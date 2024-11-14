package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   * @param lb the lower bound of things to partition.
   * @param ub the upper bound of things to partition.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */

  public void sort(T[] values, int lb, int ub) {
    if (lb >= ub) {
      return;
    } // if
    int pivotPos = (int) (Math.random() * (ub - lb) + 1) + lb;
    int[] bounds = partition(values, pivotPos, lb, ub);
    sort(values, lb, bounds[0] - 1);
    sort(values, bounds[1], ub);
  } // sort(T[],int,int)

  /**
   * Partitions the array between values. A solution to the DNF problem.
   *
   * @param values an array to partition.
   * @param pivotPos the pivot location.
   * @param lb the lower bound of things to partition.
   * @param ub the upper bound of things to partition.
   *
   * @return two bounds, where the less than and equal to bounds are.
   * @post the array has been partitioned
   */
  public int[] partition(T[] values, int pivotPos, int lb, int ub) {
    T pivot = values[pivotPos];
    int l = lb;
    int e = lb;
    int g = ub;
    while (g >= e) {
      int pivotComp = order.compare(values[e], pivot);
      if (pivotComp < 0) {
        swap(values, e, l);
        l++;
        //e++;
      } else if (pivotComp > 0) {
        swap(values, e, g);
        g--;
        e--;
      } // if/else
      e++;
    } // for
    int[] toReturn = {l, e - 1};
    return toReturn;
  } // partition(T[],int,int,int)

  /**
   * Swap two values in an array (copied from the tools).
   *
   * @param arr the array with the values to swap.
   * @param i one of the locations to swap.
   * @param j the other location to swap.
   *
   * @post the values have been swapped.
   */
  static void swap(Object[] arr, int i, int j) {
    Object tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  } // swap(Object[], int, int)
} // class Quicksorter
