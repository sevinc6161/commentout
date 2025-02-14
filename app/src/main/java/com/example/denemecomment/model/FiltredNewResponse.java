package com.example.denemecomment.model;

import java.util.List;


//Burada uuid ile eşleşen yer geliyor
 public class FiltredNewResponse {
        private List<NewsResponse> uuid;
        private List<NewsResponse> json_url;

        public List<NewsResponse> getUuid() {
            return uuid;
        }
        public void setUuid(List<NewsItem> featured) {
            this.uuid = uuid;
        }
    public List<NewsResponse> getJson_url() {
        return json_url;
    }

    public void setJson_url(List<NewsResponse> json_url) {
        this.json_url = json_url;
    }
}
