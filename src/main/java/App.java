import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

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

    get("/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("brand", Brand.all());
      model.put("template", "templates/storeform.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  // post("/store_form", (request, response) -> {
  //   Map<String, Object> model = new HashMap<String, Object>();
  //   model.put("template", "templates/index.vtl");
  //   String name = request.queryParams("sName");
  //   int brand_id = Integer.parseInt(request.queryParams("brand_id"));
  //   Store myStore = new Store();
  //   myStore.save();
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
  //   Store.delete();
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
  // post("/store_update/:id", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Store store = Store.find(Integer.parseInt(request.params(":id")));
  //   String name = request.queryParams("sName");
  //   Store.update();
  //   model.put("template", "templates/index.vtl");
  //   model.put("stores", Store.all());
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
   }
}
