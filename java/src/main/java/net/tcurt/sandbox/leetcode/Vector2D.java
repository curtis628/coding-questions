package net.tcurt.sandbox.leetcode;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO struggled (but wasn't fully focusing)
/** From <a href="https://leetcode.com/problems/flatten-2d-vector">Leetcode 251</a> */
class Vector2D implements Iterator<Integer> {

  private int[][] v;
  private int innerNdx = 0;
  private int outerNdx = 0;

  public Vector2D(int[][] v) {
    this.v = v;
  }

  public Integer next() {
    if (!hasNext()) throw new NoSuchElementException();

    int next = v[innerNdx][outerNdx];
    if (outerNdx + 1 == v[innerNdx].length) {
      innerNdx++;
      outerNdx = 0;
    } else {
      outerNdx++;
    }

    return next;
  }

  public boolean hasNext() {
    boolean hasNext = innerNdx < v.length && outerNdx < v[innerNdx].length;
    while (innerNdx < v.length && !hasNext) {
      innerNdx++;
      outerNdx = 0;
      hasNext = innerNdx < v.length && outerNdx < v[innerNdx].length;
    }
    return hasNext;
  }
}
