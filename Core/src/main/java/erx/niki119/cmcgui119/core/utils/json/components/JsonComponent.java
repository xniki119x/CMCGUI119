package erx.niki119.cmcgui119.core.utils.json.components;

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