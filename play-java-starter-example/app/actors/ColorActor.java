package actors;

import actors.protocol.ColorProtocol;
import akka.actor.AbstractActor;
import akka.actor.Props;
import util.Color;

public class ColorActor extends AbstractActor {

    public static Props props() {
        return Props.create(ColorActor.class, ColorActor::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ColorProtocol.GetColor.class, response -> {
                    getSender().tell(new ColorProtocol.ColorResponse(Color.BLUE), getSelf());
                }).build();
    }
}
