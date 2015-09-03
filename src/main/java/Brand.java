import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Brand {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Brand(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherBrand){
    if (!(otherBrand instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return this.getName().equals(newBrand.getName()) &&
             this.getId() == newBrand.getId();
    }
  }


  public static List<Brand> all() {
    String sql = "SELECT id, name FROM brands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Brand.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Brands where id=:id";
      Brand Brand = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
      return Brand;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE Brands SET name = :name) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM Brands WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addStore(Store store) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stores_brands (Store_id, brand_id) VALUES (:store_id, :brand_id)";
    con.createQuery(sql)
      .addParameter("Store_id", this.getId())
      .addParameter("brand_id", this.getId())
      .executeUpdate();
  }
}

public List<Store> getStores() {
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT Stores.* FROM brands JOIN stores_brands ON (brands.id = stores_brands.brand_id) JOIN stores ON (stores_brands.Store_id = stores.id) where brands.id= :brand_id";
       List<Store> Stores = con.createQuery(sql)
      .addParameter("brand_id", this.getId())
      .executeAndFetch(Store.class);
    return Stores;
  }
}
public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM brands WHERE id = :id;";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM stores_brands WHERE brand_id = :brandId";
      con.createQuery(joinDeleteQuery)
        .addParameter("brandId", this.getId())
        .executeUpdate();
  }
}

public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE brands SET (name) = (:name) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
