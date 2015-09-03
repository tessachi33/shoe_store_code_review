import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/shoes_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteBrandsQuery = "DELETE FROM Brands *;";
      String deleteStoresQuery = "DELETE FROM Stores *;";
      String deleteStoresBrandsQuery = "DELETE FROM Stores_Brands *;";
      con.createQuery(deleteStoresQuery).executeUpdate();
      con.createQuery(deleteBrandsQuery).executeUpdate();
      con.createQuery(deleteStoresBrandsQuery).executeUpdate();
    }
  }
}
