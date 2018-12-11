package controllers;

import actors.protocol.ColorProtocol;
import akka.actor.ActorRef;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;

public class ColorController extends Controller {

    private ActorRef colorActor;

    @Inject
    public ColorController(@Named("colorActor") ActorRef colorActor) {
        this.colorActor = colorActor;
    }

    public CompletionStage<Result> colorme() {
        return FutureConverters.toJava(ask(colorActor, new ColorProtocol.GetColor(), 1000))
                .thenApply(response -> ok(((ColorProtocol.ColorResponse)response).getColor().toString()));
    }
}
