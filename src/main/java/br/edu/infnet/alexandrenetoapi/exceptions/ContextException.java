package br.edu.infnet.alexandrenetoapi.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class ContextException extends RuntimeException {
    public String context;

    public ContextException(String message, String context) {
        super(message);

        this.context = context;
    }

    private String getContext() {
        return this.context;
    }

    private String getLocalDataTime() {
        return LocalDateTime.now().toString();
    }

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("timestamp", getLocalDataTime());
        map.put("context", getContext());
        map.put("error", getMessage());
        return map;
    }

    public Map<String, String> getMap(String detail) {
        Map<String, String> map = getMap();
        map.put("detail", detail);
        return map;
    }
}