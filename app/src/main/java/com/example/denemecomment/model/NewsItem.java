package com.example.denemecomment.model;



import java.util.List;

public class NewsItem {

    private String uuid;
    private String title;
    private String category_name;
    private String detail_mini_content;
    private String json_url;
    private MainImage main_image;



    public String getUuid() {
        return uuid;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }

    public String getDetailMiniContent() {
        return detail_mini_content;
    }

    public void setDetailMiniContent(String detail_mini_content) {
        this.detail_mini_content = detail_mini_content;
    }

    public MainImage getMainImage() {
        return main_image;
    }

    public void setMainImage(MainImage main_image) {
        this.main_image = main_image;
    }

    public String getJson_url() {
        return json_url;
    }



    public static class MainImage {
        private String url;
        private int height;
        private int width;


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
