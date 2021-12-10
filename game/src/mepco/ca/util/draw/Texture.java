package mepco.ca.util.draw;

import java.awt.*;

public class Texture {
    public final Color frontLineColor;
    public final Color backLineColor;
    public final Color frontFillColor;
    public final Color backFillColor;

    public Texture() {
        this(Color.BLACK, Color.WHITE, null, null);
    }

    public Texture(Color frontLineColor, Color frontFillColor, Color backLineColor, Color backFillColor) {
        this.frontLineColor = frontLineColor;
        this.backLineColor = backLineColor;
        this.frontFillColor = frontFillColor;
        this.backFillColor = backFillColor;
    }
}
