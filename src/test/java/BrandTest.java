import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;


public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Brand firstBrand = new Brand("B.A.I.T.");
    Brand secondBrand = new Brand("B.A.I.T.");
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Brand myBrand = new Brand("B.A.I.T.");
    myBrand.save();
    Brand savedBrand = Brand.all().get(0);
    assertTrue(savedBrand.equals(myBrand));
  }
  
  // @Test
  // public void save_assignsIdToObject() {
  //   Brand myBrand = new Brand("B.A.I.T.");
  //   myBrand.save();
  //   Brand savedBrand = Brand.all().get(0);
  //   assertEquals(myBrand.getId(), savedBrand.getId());
  // }
  //
  // @Test
  // public void find_findsBrandInDatabase_true() {
  //   Brand myBrand = new Brand("B.A.I.T.");
  //   myBrand.save();
  //   Brand savedBrand = Brand.find(myBrand.getId());
  //   assertTrue(myBrand.equals(savedBrand));
  // }
}
