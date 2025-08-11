package net.tcurt.sandbox.interviews;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SplitStaysTest {

  static Stream<Method> methodProvider() {
    return Stream.of(Method.BRUTE_FORCE, Method.BINARY_SEARCH);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case1(Method method) {
    SplitStays underTest = new SplitStays(method);
    SplitStays.Host a = new SplitStays.Host("A", List.of(1, 2, 3, 6, 7, 10, 11));
    SplitStays.Host b = new SplitStays.Host("B", List.of(3, 4, 5, 6, 8, 9, 10, 13));
    SplitStays.Host c = new SplitStays.Host("C", List.of(7, 8, 9, 10, 11));
    List<SplitStays.Host> hosts = List.of(a, b, c);

    List<List<String>> poss = underTest.splitStays(hosts, 3, 11);
    assertThat(poss).containsExactlyInAnyOrder(List.of("B", "C"));

    for (List<String> pairs : poss) {
      System.out.println(pairs);
    }
  }

  /**
   * Test case for returning multiple solutions
   *
   * <pre>
   * Host A: [6, 7, 8, 11]
   * Host B: [2, 3, 4, 5, 6, 8, 9, 10, 13]
   * Host C: [7, 8, 9, 10, 11]
   * Host D: [7, 8, 10, 11]
   * Host E: [7, 10, 11]
   * Host F: [2, 3, 4, 5]
   *
   * [startDay=2] through [endDay=8]
   *
   * Solutions: [
   *   [B, A],
   *   [B, C],
   *   [B, D],
   *   [F, A]
   * ]
   * <ol>
   * <li>{@code B: 2-6, A: 7-8}
   * <li>{@code B: 2-6, C: 7-8}
   * <li>{@code B: 2-6, D: 7-8}
   * <li>{@code F: 2-6, A: 7-8}
   * </ol>
   */
  @ParameterizedTest
  @MethodSource("methodProvider")
  void case2(Method method) {
    SplitStays underTest = new SplitStays(method);
    SplitStays.Host a = new SplitStays.Host("A", List.of(6, 7, 8, 11));
    SplitStays.Host b = new SplitStays.Host("B", List.of(2, 3, 4, 5, 6, 8, 9, 10, 13));
    SplitStays.Host c = new SplitStays.Host("C", List.of(7, 8, 9, 10, 11));
    SplitStays.Host d = new SplitStays.Host("D", List.of(7, 8, 10, 11));
    SplitStays.Host e = new SplitStays.Host("E", List.of(7, 10, 11));
    SplitStays.Host f = new SplitStays.Host("F", List.of(2, 3, 4, 5));
    List<SplitStays.Host> hosts = List.of(a, b, c, d, e, f);

    List<List<String>> poss = underTest.splitStays(hosts, 2, 8);
    assertThat(poss)
        .containsExactlyInAnyOrder(
            List.of("B", "A"), // below are only possible options
            List.of("B", "C"),
            List.of("B", "D"),
            List.of("F", "A"));
  }
}
