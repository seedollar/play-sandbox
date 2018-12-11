package actors.protocol;

public final class GreetingProtocol {

    public static final class Greet {}

    public static final class GreetResponse {

        private final String greeting;

        public GreetResponse(String greeting) {
            this.greeting = greeting;
        }

        public String getGreeting() {
            return greeting;
        }
    }
}
