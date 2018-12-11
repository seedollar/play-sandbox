package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.UUID;

public class PetController extends Controller {

    public Result pets() {
        return ok("<ul><li>Cat</li><li>Dog</li><li>Parrot</li><li>Fish</li></ul>").as("text/html");
    }

    public Result pet() {
        response().setHeader("x-tagged", UUID.randomUUID().toString());
        return ok("a pet");
    }

    public Result petMode() {
        response().setCookie(Http.Cookie.builder("owner", "JohnArbuckel").build());
        response().setCookie(Http.Cookie.builder("life-expectancy", "max20years")
                .withMaxAge(Duration.ofSeconds(60))
                .withPath("/tmp/pet/cookies")
                .build());
        return ok("Garfield with owner cookie");
    }
}
