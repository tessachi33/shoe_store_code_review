import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;


public class ShoeTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Shoe.all().size(), 0);
  }


}
