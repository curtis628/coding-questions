package net.tcurt.sandbox.problems;

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

  private boolean canPlace(int[] terrain, int waterNdx) {
    if (waterNdx == 0 || waterNdx >= terrain.length - 1) {
      return false;
    }

    int leftNdx = waterNdx - 1;
    int leftTerrain = terrain[leftNdx];

    int rightNdx = waterNdx + 1;
    int rightTerrain = terrain[rightNdx];
    int currentTerrainForWaterNdx = terrain[waterNdx];

    boolean canPlace =
        leftTerrain > currentTerrainForWaterNdx && rightTerrain > currentTerrainForWaterNdx;
    return canPlace;
  }

  // + w +
  // + + + + w +
  // + + + + + +
  //
  // Input: [2,1,2,1,0,1],2,2
  // Output: [2,2,2,1,0,1]
  public int[] generateTerrain(int[] terrain, int waterAmount, int waterNdx) {

    while (waterAmount > 0) {
      if (canPlace(terrain, waterNdx)) {
        terrain[waterNdx]++;
        waterAmount--;
      } else {
        int leftNdx = waterNdx - 1;
        int leftTerrain = terrain[leftNdx];
        int currentTerrainForWaterNdx = terrain[waterNdx];

        // assumption 1+2: if boundary not high enough, flow left first
        int leftPlacementNdx = -1;
        if (leftTerrain <= currentTerrainForWaterNdx) {
          while (leftNdx >= 0 && leftPlacementNdx == -1) {
            if (canPlace(terrain, leftNdx)) {
              leftPlacementNdx = leftNdx;
              terrain[leftNdx]++;
              waterAmount--;
            }
            leftNdx--;
          }
        }

        // time to try to the right...
        if (leftPlacementNdx == -1) {
          int rightNdx = waterNdx + 1;
          int rightPlacementNdx = -1;
          while (rightNdx < terrain.length && rightPlacementNdx == -1) {
            if (canPlace(terrain, rightNdx)) {
              rightPlacementNdx = rightNdx;
              terrain[rightNdx]++;
              waterAmount--;
            }
            rightNdx++;
          }

          // couldn't place it to the right either... so all water will just flow out
          if (rightPlacementNdx == -1) {
            waterAmount = 0;
          }
        }
      }
    }

    int maxTerrain = Arrays.stream(terrain).max().getAsInt();
    for (int terrainLevel = maxTerrain; terrainLevel >= 0; terrainLevel--) {
      for (int i = 0; i < terrain.length; i++) {
        int t = terrain[i];
        String output = (t >= terrainLevel) ? "+ " : "  ";
        System.out.print(output);
      }
      System.out.println();
    }

    return terrain;
  }
}
