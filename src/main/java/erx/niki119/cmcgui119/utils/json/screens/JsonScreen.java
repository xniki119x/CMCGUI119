package erx.niki119.cmcgui119.utils.json.screens;

import erx.niki119.cmcgui119.utils.json.components.JsonBase;

public class JsonScreen extends JsonBase {
    public String background;
    public String name;
    public JsonScreen(String id, String name, String background) {
        super(id);
        this.name = name;
        this.background = background;
    }
}
