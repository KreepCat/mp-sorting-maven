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
    sort(values, 0, values.length-1);
  } // sort(T[])

  public void sort(T[] values, int lb, int ub) {
    if (lb>=ub) {
      return;
    }
    int pivotPos = (int) (Math.random() * (ub - lb)+1) + lb;
    int[] bounds = partition(values, pivotPos, lb, ub);
    sort(values, lb, bounds[0]-1);
    sort(values, bounds[1], ub);
  } // sort(T[])

  public int[] partition(T[] values, int pivotPos, int lb, int ub) {
    T pivot = values[pivotPos];
    int l = lb;
    int e = lb;
    int g = ub;
    while (g >= e) {
      int pivotComp = order.compare(values[e], pivot);
      if (pivotComp == 0) {
        e++;
      } else if (pivotComp > 0) {
        swap(values, e, g);
        g--;
      } else {
        swap(values, e, l);
        l++;
        e++;
      } // if/else
    } // for
    int[] toReturn = {l, e-1};
    return toReturn;
  } // partition(T[],int)

  /**
   * Swap two values in an array.
   */
  static void swap(Object[] arr, int i, int j) {
    Object tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  } // swap(Object[], int, int)


  public static void main(String[] args) {
    Integer[] toSort = {1, 2, 3, 6, 4, 6, 9, 1, 4};
    int tb = toSort.length-1;
    int lb = 0;
    for (int n = 0; n < 6; n++) {

      int rand = ((int) (Math.random() * (tb - lb+1))) + lb;
      System.err.println("randpos: " + rand + " Random Number " + toSort[rand] + " lb: " + lb + " tb: " + tb);
      int[] bounds =
          new Quicksorter<Integer>((x, y) -> x.compareTo(y)).partition(toSort, rand, lb, tb);

      System.err.println("bounds[0]: " + bounds[0] + " bounds[1]: " + bounds[1]);
      for (Integer toPrint : toSort) {
        System.err.println(toPrint);
      }
      tb = bounds[0]-1;


    }

  }
} // class Quicksorter
