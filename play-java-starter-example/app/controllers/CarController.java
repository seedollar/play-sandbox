package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Car;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.Counter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class CarController extends Controller {

    private Counter counter;
    private List<Car> cars;


    @Inject
    public CarController(Counter counter) {
        this.counter = counter;
        this.cars = new ArrayList<>();
    }
    public Result cars() {
        int carRetrievalCount = Optional.ofNullable(session("cars-query-count")).map(Integer::valueOf).orElse(0);
        if (carRetrievalCount == 10) {
            return internalServerError("Query limit reached");
        }
        int nextCount = counter.nextCount();
        session("cars-query-count", nextCount + "");
        StringBuilder carsResponse = new StringBuilder("<h1>New Cars</h1><ul>");
        cars.forEach(car -> {
            carsResponse.append("<li>").append(car).append("</li>");
        });
        carsResponse.append("</ul>");
        return ok(carsResponse.toString()).as("text/html");
    }

    public Result resetQueryLimit() {
        counter.reset();
        session("cars-query-count", counter.nextCount() + "");
        return ok("cart-query-count RESET");
    }

    public Result create() {
        JsonNode jsonNode = request().body().asJson();

        if (jsonNode != null) {
            Car car = Json.fromJson(jsonNode, Car.class);
            cars.add(car);
            flash("message", "car successfully added!");
        }
        return redirect(routes.HomeController.index());
    }

}
