import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;


public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();

      model.put("brands", Brand.all());
      model.put("template", "templates/index.vtl");
      model.put("stores", Store.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brands", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Brand> brands = Brand.all();
    model.put("brands", brands);
    model.put("template", "templates/brands.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stores", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Store> stores = Store.all();
    model.put("stores", stores);
    model.put("template", "templates/stores.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

get("/brands/:id", (request,response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  int id = Integer.parseInt(request.params("id"));
  Brand brand = Brand.find(id);
  model.put("brand", brand);
  model.put("allStores", Store.all());
  model.put("template", "templates/brand.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

get("/stores/:id", (request,response) ->{
  HashMap<String, Object> model = new HashMap<String, Object>();
  int id = Integer.parseInt(request.params("id"));
  Store store = Store.find(id);
  model.put("store", store);
  model.put("allBrands", Brand.all());
  model.put("template", "templates/store.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

post("/brands", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  String name = request.queryParams("sName");
  Brand newBrand = new Brand(name);
  newBrand.save();
  response.redirect("/brands");
  return null;
});

post("/stores", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  String name = request.queryParams("sName");
  Store newStore = new Store(name);
  newStore.save();
  response.redirect("/stores");
  return null;
});

post("/add_brands", (request, response) -> {
  int brandId = Integer.parseInt(request.queryParams("brand_id"));
  int storeId = Integer.parseInt(request.queryParams("store_id"));
  Store store = Store.find(storeId);
  Brand brand = Brand.find(brandId);
  store.addBrand(brand);
  response.redirect("/stores/" + storeId);
  return null;
});

post("/add_stores", (request, response) -> {
  int brandId = Integer.parseInt(request.queryParams("brand_id"));
  int storeId = Integer.parseInt(request.queryParams("store_id"));
  Store store = Store.find(storeId);
  Brand brand = Brand.find(brandId);
  brand.addStore(store);
  response.redirect("/brands/" + brandId);
  return null;
});

     //use if I can later
    // get("/new", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("brand", Brand.all());
    //   model.put("template", "templates/storeform.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

  // post("/store_form", (request, response) -> {
  //   Map<String, Object> model = new HashMap<String, Object>();
  //   model.put("template", "templates/index.vtl");
  //   String name = request.queryParams("sName");
  //   int brand_id = Integer.parseInt(request.queryParams("brand_id"));
  //   Store newStore = new Store(name);
  //   newStore.save();
  //   model.put("stores", Store.all());
  //   model.put("brand", Brand.all());
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // get("/store/:id", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     model.put("store", Store.find(Integer.parseInt(request.params(":id"))));
  //     model.put("template", "templates/index.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  // post("/store/:id/delete", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Store store = Store.find(Integer.parseInt(request.params(":id")));
  //   store.delete();
  //   model.put("stores", Store.all());
  //   model.put("template", "templates/index.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // get("/store/:id/update", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   model.put("store", Store.find(Integer.parseInt(request.params(":id"))));
  //   model.put("template", "templates/storeformupdate.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // post("/store/update/:id", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Store store = Store.find(Integer.parseInt(request.params(":id")));
  //   String name = request.queryParams("sName");
  //   store.update(name);
  //   model.put("template", "templates/index.vtl");
  //   model.put("stores", Store.all());
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
    }
}
