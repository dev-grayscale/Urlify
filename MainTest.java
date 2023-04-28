import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MainTest {

  @Test
  void urlifyV1() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV1(null, 0));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV1("".toCharArray(), 0));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV1("    ".toCharArray(), 0));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV1("test".toCharArray(), -2));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV1("test".toCharArray(), 0));

    Assertions.assertTrue(Arrays.equals("Mr%20John%20Smith".toCharArray(), Main.urlifyV1("Mr John Smith    ".toCharArray(), 13)));
    Assertions.assertTrue(Arrays.equals("Hello%20there".toCharArray(), Main.urlifyV1("Hello there  ".toCharArray(), 11)));
    Assertions.assertTrue(Arrays.equals("%20Hi%20".toCharArray(), Main.urlifyV1(" Hi     ".toCharArray(), 4)));
    Assertions.assertTrue(Arrays.equals("%20%20Hi".toCharArray(), Main.urlifyV1("  Hi    ".toCharArray(), 4)));
    Assertions.assertTrue(Arrays.equals("%20%20%20%20Hi%20%20%20%20".toCharArray(), Main.urlifyV1("    Hi                    ".toCharArray(), 10)));
    Assertions.assertTrue(Arrays.equals("%20%20Yes%20%20".toCharArray(), Main.urlifyV1("  Yes          ".toCharArray(), 7)));
    Assertions.assertTrue(Arrays.equals("Hello%20there%20%20".toCharArray(), Main.urlifyV1("Hello there        ".toCharArray(), 13)));
    Assertions.assertTrue(Arrays.equals(
      "This%20is%20a%20sentence%20to%20check%20if%20spaces%20are%20accurately%20replaced%20and%20everything%20is%20working%20reliably".toCharArray(),
      Main.urlifyV1(
        "This is a sentence to check if spaces are accurately replaced and everything is working reliably                              ".toCharArray(),
        96
      ))
    );
  }

  @Test
  void urlifyV2() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV2(null, 0));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV2("".toCharArray(), 0));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV2("    ".toCharArray(), 0));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV2("test".toCharArray(), -2));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Main.urlifyV2("test".toCharArray(), 0));

    Assertions.assertTrue(Arrays.equals("Mr%20John%20Smith".toCharArray(), Main.urlifyV2("Mr John Smith    ".toCharArray(), 13)));
    Assertions.assertTrue(Arrays.equals("Hello%20there".toCharArray(), Main.urlifyV2("Hello there  ".toCharArray(), 11)));
    Assertions.assertTrue(Arrays.equals("%20Hi%20".toCharArray(), Main.urlifyV2(" Hi     ".toCharArray(), 4)));
    Assertions.assertTrue(Arrays.equals("%20%20Hi".toCharArray(), Main.urlifyV2("  Hi    ".toCharArray(), 4)));
    Assertions.assertTrue(Arrays.equals("%20%20%20%20Hi%20%20%20%20".toCharArray(), Main.urlifyV2("    Hi                    ".toCharArray(), 10)));
    Assertions.assertTrue(Arrays.equals("%20%20Yes%20%20".toCharArray(), Main.urlifyV2("  Yes          ".toCharArray(), 7)));
    Assertions.assertTrue(Arrays.equals("Hello%20there%20%20".toCharArray(), Main.urlifyV2("Hello there        ".toCharArray(), 13)));
    Assertions.assertTrue(Arrays.equals(
      "This%20is%20a%20sentence%20to%20check%20if%20spaces%20are%20accurately%20replaced%20and%20everything%20is%20working%20reliably".toCharArray(),
      Main.urlifyV2(
        "This is a sentence to check if spaces are accurately replaced and everything is working reliably                              ".toCharArray(),
        96
      ))
    );
  }
}
