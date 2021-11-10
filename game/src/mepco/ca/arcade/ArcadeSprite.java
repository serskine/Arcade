package mepco.ca.arcade;

import java.awt.*;

public class ArcadeSprite extends Rectangle implements ArcadeActor {
    protected String imageUrl;

    public ArcadeSprite(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void tick(double deltaMs) {

    }

    @Override
    public void render(Graphics2D g) {
    }
}
