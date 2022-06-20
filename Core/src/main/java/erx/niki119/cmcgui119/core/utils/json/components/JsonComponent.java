package erx.niki119.cmcgui119.v1165.utils.json.components;

import erx.niki119.cmcgui119.v1165.utils.AnchorType;

public class JsonComponent extends JsonBase {
    public int xPos, yPos, width, height;
    public String anchor;

    public JsonComponent(String id, int xPos, int yPos, int width, int height, String anchor) {
        super(id);
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.anchor = anchor;
    }

    public void render(){

    }
}