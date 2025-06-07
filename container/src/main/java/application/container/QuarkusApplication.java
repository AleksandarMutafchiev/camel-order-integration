package application.container;

import io.quarkus.runtime.Quarkus;
import java.util.TimeZone;

@io.quarkus.runtime.annotations.QuarkusMain
public class QuarkusApplication {

  public static void main(String... args) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    System.setProperty("user.timezone", "UTC");
    Quarkus.run(args);
  }
}
