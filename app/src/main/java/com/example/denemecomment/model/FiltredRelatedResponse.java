package com.example.denemecomment.model;

import java.util.List;


public class FiltredRelatedResponse {
    private List<DetailModelResponse> uuid;
    private List<DetailModelResponse> content;


    public List<DetailModelResponse> getUuid() {
        return uuid;
    }

    public void setUuid(List<DetailModelResponse> uuid) {
        this.uuid = uuid;
    }
}
