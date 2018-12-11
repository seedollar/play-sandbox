package actors;

import actors.protocol.GreetingProtocol;
import akka.actor.AbstractActor;
import akka.actor.Props;

public class GreetingActor extends AbstractActor {

    public static Props props() {
        return Props.create(GreetingActor.class, GreetingActor::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(GreetingProtocol.Greet.class, g -> {
            getSender().tell(new GreetingProtocol.GreetResponse("I'm greeting!"), getSelf());
        }).build();
    }

}
