package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

public class TestPollockAlexanderSorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new PollockAlexanderSort<String>((x,y) -> x.compareTo(y));
    intSorter = new PollockAlexanderSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()
}