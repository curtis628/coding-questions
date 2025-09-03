package net.tcurt.sandbox.interviews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import org.junit.jupiter.api.Test;

public class KeyValueLookupTest {
  private KeyValueLookup underTest = new KeyValueLookup();

  @Test
  void case1() {
    underTest.setValue("A", 5);
    underTest.setValue("B", 10);
    underTest.setSum("C", List.of("A", "A", "B"));
    underTest.setSum("D", List.of("C", "C", "A"));

    assertThat(underTest.getValue("A")).isEqualTo(5);
    assertThat(underTest.getValue("B")).isEqualTo(10);
    assertThat(underTest.getValue("C")).isEqualTo(20);
    assertThat(underTest.getValue("D")).isEqualTo(45);

    underTest.setValue("A", 100);
    assertThat(underTest.getValue("C")).isEqualTo(210);
    assertThat(underTest.getValue("D")).isEqualTo(520);

    underTest.setSum("C", List.of("A", "A"));
    // A -----------> 100
    // B -----------> 10
    // C [A, A] ----> 200
    // D [C, C, A] -> 500
    assertThat(underTest.getValue("C")).isEqualTo(200);
    assertThat(underTest.getValue("D")).isEqualTo(500);
  }

  @Test
  void keyDoesNotExist() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> underTest.getValue("notThere"))
        .withMessage("Key doesn't exist: notThere");
  }
}
