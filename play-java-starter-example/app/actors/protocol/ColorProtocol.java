package actors.protocol;

import util.Color;

public final class ColorProtocol {

    public static final class GetColor {
    }

    public static final class ColorResponse {
        private final Color color;

        public ColorResponse(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
}
