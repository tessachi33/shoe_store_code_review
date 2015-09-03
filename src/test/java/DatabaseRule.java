import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteShoesQuery = "DELETE FROM Shoes *;";
      String deleteStoresQuery = "DELETE FROM Stores *;";
      String deleteStoresShoesQuery = "DELETE FROM Stores_Shoes *;";
      con.createQuery(deleteStoresQuery).executeUpdate();
      con.createQuery(deleteShoesQuery).executeUpdate();
      con.createQuery(deleteStoresShoesQuery).executeUpdate();
    }
  }
}
