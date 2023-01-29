package erx.niki119.cmcgui119.core.utils.json;

public class Value<T> {
    T value;
    T default_value;
    public Value(T value, T default_value){
        this.value = value;
        this.default_value = default_value;
    }
    public T get(){
        if(value == null) return default_value;
        else return value;
    }
}
