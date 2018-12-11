package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class UserController extends Controller {

    public CompletionStage<Result> getUserForId(long id) {
        return CompletableFuture
                .supplyAsync(() -> this.getUserById(id))
                .thenApply(u -> ok(u.toString()));
    }

    public User getUserById(long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("tester");
        user.setActive(true);
        return user;
    }
}
