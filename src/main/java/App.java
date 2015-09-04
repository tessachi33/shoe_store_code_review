import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

//trying to add more comments to see if I can explain this to myself.

//This shows the layout
public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

//This takes you to the home page where you can click links to take you to forms to add brands/stores
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//This route takes you to a page that lists the brands and another link to add a brand
    get("/brands", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Brand> brands = Brand.all();
    model.put("brands", brands);
    model.put("template", "templates/brands.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

//This route takes you to the store version of brands
  get("/stores", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Store> stores = Store.all();
    model.put("stores", stores);
    model.put("template", "templates/stores.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


//Here you can assign a store to a particular clicked brand
get("/brands/:id", (request,response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  int id = Integer.parseInt(request.params("id"));
  Brand brand = Brand.find(id);
  model.put("brand", brand);
  model.put("allStores", Store.all());
  model.put("template", "templates/brand.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//The same goes here for adding a brand to a particular store
get("/stores/:id", (request,response) ->{
  HashMap<String, Object> model = new HashMap<String, Object>();
  int id = Integer.parseInt(request.params("id"));
  Store store = Store.find(id);
  model.put("store", store);
  model.put("allBrands", Brand.all());
  model.put("template", "templates/store.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//This allows other added brands to post to the /brands route, can I assign this to index and make pages smaller?
post("/brands", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  String name = request.queryParams("sName");
  Brand newBrand = new Brand(name);
  newBrand.save();
  response.redirect("/"); //takes you back to the table in home page!
  return null;
});

//This is the same as the post /brands but for /stores
post("/stores", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  String name = request.queryParams("sName");
  Store newStore = new Store(name);
  newStore.save();
  response.redirect("/"); //takes you back to the table in home page :)
  return null;
});

//This is the post that actually allows you to add brands with the post method
post("/add_brands", (request, response) -> {
  int brandId = Integer.parseInt(request.queryParams("brand_id"));
  int storeId = Integer.parseInt(request.queryParams("store_id"));
  Store store = Store.find(storeId);
  Brand brand = Brand.find(brandId);
  store.addBrand(brand);
  response.redirect("/stores/" + storeId);
  return null;
});

//Same as with brands but with the post method for stores
post("/add_stores", (request, response) -> {
  int brandId = Integer.parseInt(request.queryParams("brand_id"));
  int storeId = Integer.parseInt(request.queryParams("store_id"));
  Store store = Store.find(storeId);
  Brand brand = Brand.find(brandId);
  brand.addStore(store);
  response.redirect("/brands/" + brandId);
  return null;
});


//This get method to update stores
get("/stores/:id/update", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Store store = Store.find(Integer.parseInt(request.params(":id")));
        model.put("store", store);
        model.put("template", "templates/edit-store.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

//post routes to update stores from a seperate form, I would like to try to make this the same form to cut down on pages
      post("/stores/:id/update", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Store store = Store.find(Integer.parseInt(request.params(":id")));
        String name = request.queryParams("name");
        store.update(name);
        response.redirect("/stores/" + store.getId());
        return null;
      });

//This allows a user to delete a store
      post("/stores/:id/delete", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Store store = Store.find(Integer.parseInt(request.params(":id")));
        store.delete();
        response.redirect("/");
        return null;
      });

//This is getting a form to update brands just as with store, can I consalidate these too?
      get("/brands/:id/update", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Brand brand = Brand.find(Integer.parseInt(request.params(":id")));
        model.put("brand", brand);
        model.put("template", "templates/edit-brand.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

//This route actually posts the form to update the brand
       post("/brands/:id/update", (request, response) -> {
        int brandId = Integer.parseInt(request.params(":id"));
        Brand brand = Brand.find(brandId);
        String description = request.queryParams("description");
        brand.update(description);
        response.redirect("/brands/" + brandId);
        return null;
      });

//This route deletes the brands
      post("/brands/:id/delete", (request, response) -> {
        int brandId = Integer.parseInt(request.params(":id"));
        Brand brand = Brand.find(brandId);
        brand.delete();
        response.redirect("/");
        return null;
      });


     //use if I can later to consalidate pages? Didn't work the first time so I changed to the tried and true method above from our lesson


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
