package net.tcurt.sandbox.problems;

/** From <a href="https://leetcode.com/problems/flatten-2d-vector">Leetcode 251</a> */
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class Vector2D {

  // Constructor will put all the nums into this list.
  private List<Integer> nums = new ArrayList<>();

  // Keep track of where the Iterator is up to.
  private int position = 0;

  // NOTE: this is bad solution. Iterators should have low constructor time, an minimize use of
  // auxiliary space (only track what's needed to grab next value, reusing existing data structure)
  public Vector2D(int[][] v) {
    // We need to iterate over the 2D vector, getting all the integers
    // out of it and putting them into nums (a field).
    for (int[] innerVector : v) {
      for (int num : innerVector) {
        nums.add(num);
      }
    }
  }

  public int next() {
    // In Java, we throw a NoSuchElementException when next() is called
    // on an exhausted Iterator.
    if (!hasNext()) throw new NoSuchElementException();
    // Store the number we need to return, as we still need to move position forward.
    int result = nums.get(position);
    // Move the position pointer forward by 1, so that it's ready for
    // the next call to next, and gives a correct hasNext result.
    position++;
    return result;
  }

  public boolean hasNext() {
    // There's nums left as long as position is a valid index of the list.
    return position < nums.size();
  }
}
