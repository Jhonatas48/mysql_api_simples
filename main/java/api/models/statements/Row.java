package api.models.statements;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class Row {
    private HashMap<String, Object> content = new LinkedHashMap<>();
    public Row(){}
    public void addcolumn(String name, Object content) {
        this.content.put(name, content);
    }

    public HashMap<String, Object> getColumns() {
        return content;
    }
    public Object get(String key) {
        return content.get(key);
    }
    public Set<String> getKeys() {
        return content.keySet();
    }
}
