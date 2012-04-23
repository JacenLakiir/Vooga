package core.keyconfiguration;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/**
 * 
 * @author Hui Dong
 *
 */
public abstract class KeyAdapter implements JsonDeserializer<Key>, JsonSerializer<Key> {
    private String keyElementName;
    private Gson gson;
    private Map<String, Class<? extends Key>> keyRegistry;
    private Map<Class<?extends Key>, String> keyMap;

    public KeyAdapter(String elementName) {
        keyElementName = elementName;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = gsonBuilder.create();
        keyRegistry = new HashMap<String, Class<? extends Key>>();
        keyMap = new HashMap<Class<?extends Key>, String>();
        registerKeys();
    }

    protected void registerKey(String key, Class<? extends Key> keyClass) {
        keyRegistry.put(key, keyClass);
        keyMap.put(keyClass, key);
    }
    
    public abstract void registerKeys();

    @Override
    public Key deserialize(JsonElement json, Type type,
            JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject keyObject = json.getAsJsonObject();
            JsonElement keyTypeElement = keyObject.get(keyElementName);
            Class<? extends Key> keyInstanceClass = keyRegistry
                    .get(keyTypeElement.getAsString());
            Key key = gson.fromJson(json, keyInstanceClass);
            return key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    


    @Override
    public JsonElement serialize(Key key, Type type,
            JsonSerializationContext context) {
        try {
            JsonElement element = gson.toJsonTree(key, Key.class);
            return element;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
