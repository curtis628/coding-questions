package net.tcurt.sandbox.interviews;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TerrainTest {
  Terrain terrain = new Terrain();

  /**
   * When placing 1+ waters at basin in index 2, it will always look like the following:
   *
   * <pre>
   *  +
   *  + + w + +
   *  + + + + +
   * </pre>
   */
  @Test
  void waterStaysInBasin() {
    int[] expected = new int[] {2, 1, 1, 1, 1};
    int[] waterAmountsToTry = new int[] {1, 100};
    for (int waterAmount : waterAmountsToTry) {
      int[] input = new int[] {2, 1, 0, 1, 1};
      int[] actual = terrain.generateTerrain(input, waterAmount, 2);
      assertThat(actual).isEqualTo(expected);
    }
  }

  /**
   * Placing water where it will flow out.
   *
   * <pre>
   * Input:  [2, 1, 2, 1, 0, 1], >=2, 2
   * Output: [2, 2, 2, 1, 0, 1]
   *  + w +
   *  + + + + w +
   *  + + + + + +
   * </pre>
   */
  @Test
  void waterFlows_basic() {
    int[] expected = new int[] {2, 2, 2, 1, 1, 1};
    int[] waterAmountsToTry = new int[] {2, 100};
    for (int waterAmount : waterAmountsToTry) {
      int[] input = new int[] {2, 1, 2, 1, 0, 1};
      int[] actual = terrain.generateTerrain(input, waterAmount, 2);
      assertThat(actual).isEqualTo(expected);
    }
  }

  /**
   * Complex test that continually adds water to index 3.
   *
   * <pre>
   * Starting Input:  [5, 1, 3, 0, 3, 0, 2]
   *  +
   *  +
   *  +   +   +
   *  +   +   +   +
   *  + + +   +   +
   *  + + + + + + +
   * </pre>
   *
   * After dumping 7+ waters at index 3... it should be:
   *
   * <pre>
   *  +
   *  +
   *  + w + w +
   *  + w + w + w +
   *  + + + w + w +
   *  + + + + + + +
   * </pre>
   */
  @Test
  void waterFlows_complex() {
    int[] input = new int[] {5, 1, 3, 0, 3, 0, 2};

    // 3 waters at ndx=3
    int[] expected = new int[] {5, 1, 3, 3, 3, 0, 2};
    int[] actual = terrain.generateTerrain(input, 3, 3);
    assertThat(actual).isEqualTo(expected);

    // now 2 more waters...
    expected = new int[] {5, 3, 3, 3, 3, 0, 2};
    actual = terrain.generateTerrain(input, 2, 3);
    assertThat(actual).isEqualTo(expected);

    // now 2 more waters...
    expected = new int[] {5, 3, 3, 3, 3, 2, 2};
    actual = terrain.generateTerrain(input, 2, 3);
    assertThat(actual).isEqualTo(expected);

    // any more water just flows right out without changing terrain
    actual = terrain.generateTerrain(input, 100, 3);
    assertThat(actual).isEqualTo(expected);
  }
}
