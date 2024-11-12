package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    for (int i = 0; i < values.length; i++) {
      int swapLoc = select(values, i);
      T tmp = values[i];
      values[i] = values[swapLoc];
      values[swapLoc] = tmp;
    } // for
  } // sort(T[])


  /**
   * Find the smallest element in a section of the array.
   *
   * @param values an array
   * @param i the index where we start searching
   *
   * @return the location of the lowest value past i.
   */
  public int select(T[] values, int i) {
    int min = i;
    for (int n = i; n < values.length; n++) {
      if (order.compare(values[n], values[min]) < 0) {
        min = n;
      } // if
    } // for
    return min;
  } // select(T[],int)
} // class SelectionSorter
