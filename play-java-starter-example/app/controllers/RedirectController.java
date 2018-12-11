package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class RedirectController extends Controller {

    public Result redirect() {
        return redirect(routes.UserController.getUserForId(454L));
    }
}
