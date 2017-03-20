package edu.asu.diging.gilesecosystem.september.core.model;

import java.util.HashMap;
import java.util.Map;

public enum MessageType {
    ERROR("ERROR"),
    WARNING("WARNING"),
    DEBUG("DEBUG"),
    INFO("INFO");
    
    private String value;
    
    private static Map<String, MessageType> map = new HashMap<>();
    
    private MessageType(String value) {
        this.value = value;
    }
    
    static {
        for (MessageType type : values()) {
            map.put(type.getValue(), type);
        }
    }
    
    public static MessageType getByValue(String value) {
        return map.get(value);
    }
    
    public String getValue() {
        return value;
    }
    
    
}
