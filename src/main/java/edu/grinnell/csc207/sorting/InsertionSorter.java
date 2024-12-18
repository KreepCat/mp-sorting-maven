package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Alexander Pollock
 */

public class InsertionSorter<T> implements Sorter<T> {
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
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    for (int i = 1; i < values.length; i++) {
      insert(values, i);
    } // for
  } // sort(T[])

  /**
   * Insert an element into the first i values.
   *
   * @param values an array to sort
   * @param i the index to possibly put into an earlier part of values
   *
   * @post The value at i is inserted properly and the array has been adjusted.
   */
  public void insert(T[] values, int i) {
    for (int n = i; n > 0; n--) {
      if (order.compare(values[n - 1], values[n]) > 0) {
        swap(values, n - 1, n);
      } else {
        break;
      } // if/else
    } // for
  } // insert(T[],int)

  /**
   * Swap two values.
   *
   * @param arr the array that the values are in.
   * @param i the first value to swap.
   * @param j the second value to swap.
   */
  static void swap(Object[] arr, int i, int j) {
    Object tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  } // swap(Object[], int, int)
} // class InsertionSorter
