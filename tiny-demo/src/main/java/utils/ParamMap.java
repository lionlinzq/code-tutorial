package utils;

import java.util.HashMap;

public class ParamMap extends HashMap<String, Object> {

    private static final long serialVersionUID = -298024471557969288L;

    public static ParamMap getInstance() {

        return new ParamMap();

    }

    public ParamMap add(String key, Object value) {
        if (value == null) {
            return this;
        }
        if (value instanceof String && "".equals(value)) {
            return this;
        }
        super.put(key, value);
        return this;
    }

    @Override
    public Object put(String key, Object value) {
        if (value == null) {
            return this;
        }
        if (value instanceof String && "".equals(value)) {
            return this;
        }
        return super.put(key, value);
    }

    public Object put(String key, Object value, boolean ignoreEmpty) {
        if(ignoreEmpty) {
            if (value == null) {
                return this;
            }
            if (value instanceof String && "".equals(value)) {
                return this;
            }
        }
        return super.put(key, value);
    }
}
