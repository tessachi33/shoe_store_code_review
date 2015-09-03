
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;


public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

  // @Test
  // public void equals_returnsTrueIfNamesAretheSame() {
  //   Store firstStore = new Store("The mall");
  //   Store secondStore = new Store("The mall");
  //   assertTrue(firstStore.equals(secondStore));
  // }
  //
  // @Test
  // public void save_savesIntoDatabase_true() {
  //   Store myStore = new Store("The mall");
  //   myStore.save();
  //   assertTrue(Store.all().get(0).equals(myStore));
  // }
  //
  // @Test
  // public void find_findStoreInDatabase_true() {
  //   Store myStore = new Store("The mall");
  //   myStore.save();
  //   Store savedStore = Store.find(myStore.getId());
  //   assertTrue(myStore.equals(savedStore));
  // }
}
