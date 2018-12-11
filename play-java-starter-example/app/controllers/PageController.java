package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class PageController extends Controller {

    public Result showRequestParameter(String page) {
        return ok(page);
    }
}
