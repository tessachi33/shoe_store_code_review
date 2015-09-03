
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

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Store firstStore = new Store("The mall");
    Store secondStore = new Store("The mall");
    assertTrue(firstStore.equals(secondStore));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Store newStore = new Store("The mall");
    newStore.save();
    assertTrue(Store.all().get(0).equals(newStore));
  }

  @Test
  public void find_findStoreInDatabase_true() {
    Store newStore = new Store("The mall");
    newStore.save();
    Store savedStore = Store.find(newStore.getId());
    assertTrue(newStore.equals(savedStore));
  }

  @Test
  public void update_updateStoreInfo() {
    Store savedStore = new Store("The Mall");
    savedStore.save();
    savedStore.update("Tatyana");
    assertTrue(Store.all().get(0).getName().equals("Tatyana"));
  }
  @Test
 public void delete_deletesAllStoresAndLists() {
   Store newStore = new Store("math", "133");
   newStore.save();

   Student myStudent = new Student("teresa", "june 2015");
   myStudent.save();

   newStore.addStudent(myStudent);
   newStore.delete();
   assertEquals(newStore.getStudents().size(), 0);
 }

}
