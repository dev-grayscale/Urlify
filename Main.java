/**
 * Write a method to replace all spaces in a string with '%20: You may assume that the string
 * has sufficient space at the end to hold the additional characters, and that you are given the "true"
 * length of the string. (Note: If implementing in Java, please use a character array so that you can
 * perform this operation in place.)
 *
 * <b>All</b> spaces, no matter the location, should be replaced up to the true length of the string.
 */
public class Main {

  /**
   * The initial approach is to iterate through all the characters looking for space, when encountered -> push all the characters with 2 positions after it,
   * allocating enough space to put the replacement (3 positions) since we already have 1 slot allocated from the space itself. Once the replacement is set,
   * we increment the <i>i</i> to start from the first not visited character and update <i>lastTrueCharPos</i> with +2 to start the switching from the new valid position.
   *
   * The runtime of this method is O(N), where N is the length of the input array.
   * The method iterates through the array once, and for each space character it calls two helper methods: pushItemsToRight and replaceSpace.
   * However, the total number of times these helper methods are called is proportional to the number of spaces in the input string,
   * which is at most N (the worst case scenario is that the entire input string consists of spaces).
   * Therefore, the overall time complexity of the method is O(N).
   *
   * Space Complexity: O(1)
   */
  public static char[] urlifyV1(char[] input, int trueLength) {
    if (input == null || input.length == 0 || trueLength <= 0) {
      throw new IllegalArgumentException("Invalid input");
    }

    int lastTrueCharPos = trueLength;
    int i = 0;

    while (i < lastTrueCharPos) {
      if (input[i] == ' ') {
        pushItemsToRight(input, i, lastTrueCharPos - 1, 2);
        replaceSpace(input, i);

        lastTrueCharPos += 2;
        i+=3;
      } else {
        i+=1;
      }
    }

    return input;
  }

  /**
   * Observing closely lead us to our second approach which is to count how many spaces are there. Once we know that
   * we start shifting characters from right to left. Until we reach the 1st space we shift with <i>spacesCount * 2</i> and decrease the spacesCount by 1 for each
   * space occurrence until it reaches 0.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public static char[] urlifyV2(char[] input, int trueLength) {
    if (input == null || input.length == 0 || trueLength <= 0) {
      throw new IllegalArgumentException("Invalid input");
    }

    int spacesCount = 0;

    for (int i = 0; i < trueLength; i++) {
      if (input[i] == ' ') {
        spacesCount++;
      }
    }

    // no processing will be done
    if (spacesCount == 0) {
      return input;
    }

    for (int i = trueLength - 1; i >= 0; i--) {
      int pos = i + (spacesCount * 2);

      if (input[i] == ' ') {
        input[pos] = '0';
        input[pos - 1] = '2';
        input[pos - 2] = '%';

        spacesCount--;
      } else {
        input[pos] = input[i];
      }

      // terminate further checks - no operation will be done anyway
      if (spacesCount == 0) {
        break;
      }
    }

    return input;
  }

  private static void pushItemsToRight(char[] data, int start, int end, int positions) {
    for (int i = end; i > start; i--) {
      data[i + positions] = data[i];
    }
  }

  private static void replaceSpace(char[] data, int pos) {
    data[pos] = '%';
    data[pos + 1] = '2';
    data[pos + 2] = '0';
  }
}
