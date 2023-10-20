package api.models.statements;

import java.util.HashMap;
import java.util.Set;

public class UpdateValue {
    private HashMap<String, Object> data = new HashMap<>();
    public UpdateValue(String val1,  Object val2) {
        data.put(val1, val2);
    }
    public UpdateValue() {
    	
    }
    public void add (String val1, Object val2) {
        data.put(val1, val2);
    }
    public Set<String> getKeys() {
        return data.keySet();
    }
    public Object get(String key) {
        return data.get(key);
    }

}
