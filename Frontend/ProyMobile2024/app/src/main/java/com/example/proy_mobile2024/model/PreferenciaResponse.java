package com.example.proy_mobile2024.model;

public class PreferenciaResponse {
    private String preference_id;
    private String init_point;

    public PreferenciaResponse(String preference_id, String init_point) {
        this.preference_id = preference_id;
        this.init_point = init_point;
    }

    public String getPreference_id() {
        return preference_id;
    }

    public String getInit_point() {
        return init_point;
    }

    public void setPreference_id(String preference_id) {
        this.preference_id = preference_id;
    }

    public void setInit_point(String init_point) {
        this.init_point = init_point;
    }
}
