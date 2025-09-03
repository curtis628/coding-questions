package net.tcurt.sandbox.interviews;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * Your task is to design a data structure which allows you to set and retrieve (key, value) pairs.
 *
 * <p>There are two cases:
 *
 * <ol>
 *   <li>The key is a string and the value is an integer.
 *   <li>The key is a string and the value is a list of strings (keys). The value of the cell is the
 *       *sum* of the value of the * keys.
 * </ol>
 *
 * Example sequence of actions:
 *
 * <pre>
 * set_value("A", 5)
 * set_value("B", 10)
 * set_sum("C", ["A", "A", "B"])
 * set_sum("D", ["C", "C", "A"])
 * get_value("A") # -> 5
 * get_value("B") # -> 10
 * get_value("C") # -> 20
 * get_value("D") # -> 45
 * set_value("A", 100) # Anything depending on "A" should update
 * get_value("D") # -> 520
 * </pre>
 *
 * Follow up: Let's say {@code getValue()} is very read-heavy - and we want to perform in {@code
 * O(1)}
 */
@Slf4j
public class KeyValueLookup {

  private Map<String, Integer> keyToPrimitiveMap = new HashMap<>();
  private Map<String, List<String>> keyToKeysLookup = new HashMap<>();

  /**
   * reverse-lookup map: for a given {@code key}, return set of all other keys that depend on {@code
   * key}. Useful when {@code setValue} or {@code setSum} is used to redefine a pre-existing value.
   */
  private Map<String, Set<String>> reverseKeyToDependentKeysLookup = new HashMap<>();

  /**
   * To support {@code O(1)} performance of {@link #getValue(String)}, we'll need to store a
   * pre-computed values here (and update it accordingly during "set" calls.
   */
  private Map<String, Integer> lookupCache = new HashMap<>();

  public void setValue(String key, int value) {
    // if it already existed in lookup, get rid of it
    if (keyToKeysLookup.containsKey(key)) {
      keyToKeysLookup.remove(key);
    }
    keyToPrimitiveMap.put(key, value);
    lookupCache.put(key, value);

    // Be sure to recompute dependent keys
    if (reverseKeyToDependentKeysLookup.containsKey(key)) {
      for (String dependentKey : reverseKeyToDependentKeysLookup.get(key)) {
        calculateValue(dependentKey);
      }
    }
  }

  public void setSum(String key, List<String> keyList) {
    if (keyToPrimitiveMap.containsKey(key)) {
      keyToPrimitiveMap.remove(key);
    }

    // need to cleanup previous data that now no longer applies
    if (keyToKeysLookup.containsKey(key)) {
      Set<String> previousKeySet = new HashSet<>(keyToKeysLookup.get(key));
      Set<String> newDependencySet = new HashSet<>(keyList);
      previousKeySet.removeAll(newDependencySet);

      for (String noLongerValid : previousKeySet) {
        Set<String> dependencies = reverseKeyToDependentKeysLookup.get(noLongerValid);
        dependencies.remove(key); // removing dependency that no longer applies
      }
    }
    keyToKeysLookup.put(key, keyList);

    // keep track of reverse-lookup map so we can know what to update if dependent keys get updated.
    for (String childKey : new HashSet<>(keyList)) {
      reverseKeyToDependentKeysLookup.computeIfAbsent(childKey, k -> new HashSet<>()).add(key);
    }

    calculateValue(key);

    // Again, be sure to recompute dependent keys
    if (reverseKeyToDependentKeysLookup.containsKey(key)) {
      for (String dependentKey : reverseKeyToDependentKeysLookup.get(key)) {
        calculateValue(dependentKey);
      }
    }
  }

  private int calculateValue(String key) {
    System.out.println("Looking up: " + key);
    if (!keyToPrimitiveMap.containsKey(key) && !keyToKeysLookup.containsKey(key)) {
      throw new IllegalStateException("Key doesn't exist: " + key);
    }

    if (keyToPrimitiveMap.containsKey(key)) {
      return keyToPrimitiveMap.get(key);
    }

    int sum = 0;
    for (String childKey : keyToKeysLookup.get(key)) {
      int childValue = getValue(childKey);
      System.out.printf("  childKey=%s --> %d\n", childKey, childValue);
      sum += getValue(childKey);
    }
    lookupCache.put(key, sum);
    return sum;
  }

  public int getValue(String key) {
    if (!lookupCache.containsKey(key)) {
      throw new IllegalStateException("Key doesn't exist: " + key);
    }
    return lookupCache.get(key);
  }
}
