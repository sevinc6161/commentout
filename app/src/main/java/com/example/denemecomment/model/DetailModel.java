// DetailModel.java
package com.example.denemecomment.model;

import java.util.List;

public class DetailModel {
    private Detail detail;
    private Related related;


    public Detail getDetail() {
        return detail;
    }

    public Related getRelated() {
        return related;
    }


    public static class Detail {
        private String uuid;
        private String title;
        private String summary;
        private String content;
        private String type;
        private String category_name;
        private HeaderImage header_image;
        private String share_url;
        private String video_url;



        public String getTitle() {
            return title;
        }

        public String getSummary() {
            return summary;
        }

        public String getContent() {
            return content;
        }

        public String getType() {
            return type;
        }

        public String getCategory_name() {
            return category_name;
        }

        public HeaderImage getHeader_image() {
            return header_image;
        }

        public String getShare_url() {
            return share_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public String getUuid() {
            return uuid;
        }


        public static class HeaderImage {
            private String url;
            private int width;
            private int height;


            public String getUrl() {
                return url;
            }

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }
        }
    }


    public static class Related {
        private String display_text;
        private List<Item> items;

        // Getter metodları
        public String getDisplay_text() {
            return display_text;
        }

        public List<Item> getItems() {
            return items;
        }


        public static class Item {
            private int uuid;
            private String title;
            private String type;
            private String category_name;
            private MainImage main_image;
            private String json_url;
            private String detail_mini_content;


            public int getUuid() {
                return uuid;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getCategory_name() {
                return category_name;
            }

            public MainImage getMain_image() {
                return main_image;
            }

            public String getJson_url() {
                return json_url;
            }

            public String getDetail_mini_content() {
                return detail_mini_content;
            }


            public static class MainImage {
                private String url;
                private int width;
                private int height;

                // Getter metodları
                public String getUrl() {
                    return url;
                }

                public int getWidth() {
                    return width;
                }

                public int getHeight() {
                    return height;
                }
            }
        }
    }
}
