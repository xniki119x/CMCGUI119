package erx.niki119.cmcgui119.utils.json.components;

public class JsonImage extends JsonComponent {
    public String texture;
    public JsonImage(String id, int xPos, int yPos, int width, int height, String anchor, String texture){
        super(id, xPos,yPos,width,height,anchor);
        this.texture = texture;
    }
}
