package net.tcurt.sandbox.interviews;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * Given a one dimension array representing terrain, the value represents the height. We also have a
 * bucket of water. We want to dump water in a single point of terrain. Water will collect at
 * various points of the basins in the terrain.
 *
 * <p>Write a function which takes the terrain, amount of water, dumping point and output the
 * resulting terrain
 *
 * <p>A terrain of [2, 1, 0, 1, 1] will look like
 *
 * <pre>
 * +
 * + +   + +
 * + + + + +
 * </pre>
 *
 * One example is: Amount of water = 1, Dumping index = 2
 *
 * <pre>
 * Input: [2,1,0,1,1],1,2
 *
 * +
 * + + w + +
 * + + + + +
 * </pre>
 *
 * <p>Assumptions:
 *
 * <ol>
 *   <li>if any boundary isn't high enough, flow out
 *   <li>if two adjacent earth's altitudes are the same/both sides are lower, flow to left first
 *   <li>if one side is flat/higher, the other is lower, flow to the lower side
 * </ol>
 */
@Slf4j
public class Terrain {

  private boolean canHoldWater(int[] terrain, int waterNdx) {
    // can't place water at edges...
    if (waterNdx == 0 || waterNdx == terrain.length - 1) return false;

    int current = terrain[waterNdx];
    int left = terrain[waterNdx - 1];
    int right = terrain[waterNdx + 1];

    return left > current && right > current;
  }

  // + w +
  // + + + + w +
  // + + + + + +
  //
  // Input: [2,1,2,1,0,1],2,2
  // Output: [2,2,2,1,1,1]
  public int[] generateTerrain(int[] terrain, int waterAmount, int waterNdx) {
    // print original terrain
    int max = Arrays.stream(terrain).max().getAsInt();
    for (int level = max; level >= 0; level--) {
      for (int i = 0; i < terrain.length; i++) {
        String cell = (terrain[i] >= level) ? "+ " : "  ";
        System.out.print(cell);
      }
      System.out.println();
    }

    // keep trying to place water
    while (waterAmount > 0) {
      // try at requested space first...
      if (canHoldWater(terrain, waterNdx)) {
        terrain[waterNdx]++;
        waterAmount--;
      } else {
        int current = terrain[waterNdx];
        boolean placedWater = false;

        // try left
        for (int leftNdxSearch = waterNdx - 1; leftNdxSearch > 0 && !placedWater; leftNdxSearch--) {
          if (canHoldWater(terrain, leftNdxSearch)) {
            terrain[leftNdxSearch]++;
            waterAmount--;
            placedWater = true;
          } else if (terrain[leftNdxSearch] > current) {
            break;
          }
        }

        // try right
        for (int rightNdxSearch = waterNdx + 1;
            rightNdxSearch < terrain.length && !placedWater;
            rightNdxSearch++) {
          if (canHoldWater(terrain, rightNdxSearch)) {
            terrain[rightNdxSearch]++;
            waterAmount--;
            placedWater = true;
          } else if (terrain[rightNdxSearch] > current) {
            break;
          }
        }

        // impossible to place water... so it all flows out
        if (!placedWater) {
          waterAmount = 0;
        }
      }
    }

    return terrain;
  }
}
