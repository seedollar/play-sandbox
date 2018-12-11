package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import java.util.Optional;

public class ClientsController extends Controller {

    public Result list(Integer page) {
        return ok("Default page number: " + page);
    }

    public Result listActive(Optional<String> version) {
        return ok("Optional version parameter: " + version.orElse("no version"));
    }
}
