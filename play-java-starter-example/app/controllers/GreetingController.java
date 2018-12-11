package controllers;

import actors.GreetingActor;
import actors.protocol.GreetingProtocol;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;

@Singleton
public class GreetingController extends Controller {

    private final ActorSystem actorSystem;
    private final ActorRef greetingActor;

    @Inject
    public GreetingController(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.greetingActor = actorSystem.actorOf(GreetingActor.props());
    }


    public CompletionStage<Result> greet() {
        return FutureConverters.toJava(ask(greetingActor, new GreetingProtocol.Greet(), 1000))
                .thenApply(response -> ok(((GreetingProtocol.GreetResponse)response).getGreeting()));
    }

}
