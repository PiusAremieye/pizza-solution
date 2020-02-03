package hashcode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.nio.file.Path;

/**
 * Spankie
 */
public class Spankie {
  private void Calculate(int max, int types, String[] ss) {
    System.out.printf("%d %d\n%s %s\n------------\n", max, types, ss[0], ss[1]);
    ArrayList<Integer> slices = new ArrayList<>();
    int total = 0;
    for (int i = ss.length - 1; i >= 0; i--) {
      int value = Integer.parseInt(ss[i]);
      if ((total + value) > max) {
        continue;
      } else {
        total += value;
        slices.add(i);
      }
      if (total == max) {
        break;
      }
    }
    // TODO: do something with the result
    System.out.printf("result: %s : total: %d\n", slices.toString(), total);
  }

  private void GetValues(Path p) {
    try {
      List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
      String[] maxTypes = lines.get(0).split(" ");
      String[] slices = lines.get(1).split(" ");
      Calculate(Integer.parseInt(maxTypes[0]), Integer.parseInt(maxTypes[1]), slices);
    } catch (IOException e) {
      System.err.printf("%s: %s\n", e.getClass().getSimpleName(), e.getMessage());
    }
  }

  public static void SolvePizza() {
    try {
      DirectoryStream<Path> dp = Files.newDirectoryStream(Paths.get("input"));
      Iterator<Path> it = dp.iterator();
      Spankie s = new Spankie();
      while (it.hasNext()) {
        Path p = it.next();
        // System.out.println(p.toString());
        if (p.toString().endsWith(".int"))
          s.GetValues(p);
      }
    } catch (IOException e) {
      System.err.printf("%s: %s\n", e.getClass().getSimpleName(), e.getMessage());
    }
  }

  public static void main(String[] args) {
    Spankie.SolvePizza();
  }
}