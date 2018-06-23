package com.silence.music.bean.zhihu;

import java.util.List;

public class SectionListBean {

    /**
     * timestamp : 1527289198
     * stories : [{"images":["https://pic3.zhimg.com/v2-eca4ef2340b1c9ca65f2c2eb0fddedfa.jpg"],"date":"20180614","display_date":"6 月 14 日","id":9686469,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-20563b378f0f11772aafc423beed2c66.jpg"],"date":"20180613","display_date":"6 月 13 日","id":9686308,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-cedb4c8b4bcb16baa4367e6219c0b5ae.jpg"],"date":"20180612","display_date":"6 月 12 日","id":9686189,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic4.zhimg.com/v2-a395e6bbb84e536e1d1d3f2fbef46b4f.jpg"],"date":"20180611","display_date":"6 月 11 日","id":9686048,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic1.zhimg.com/v2-bfceaf23e4cd81bf0f44a5dc3dd518c0.jpg"],"date":"20180610","display_date":"6 月 10 日","id":9686022,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic2.zhimg.com/v2-0731122f223f4efc9191483952d07a05.jpg"],"date":"20180609","display_date":"6 月 9 日","id":9685946,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-cc871c6ccd6573c409e3e93c56b8d7d2.jpg"],"date":"20180608","display_date":"6 月 8 日","id":9685604,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-46fffa72acd94301e4130b056f83ad8a.jpg"],"date":"20180607","display_date":"6 月 7 日","id":9685349,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic2.zhimg.com/v2-690ac2748d2377486ce3fdd27f29f91d.jpg"],"date":"20180606","display_date":"6 月 6 日","id":9685533,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic2.zhimg.com/v2-919a19e1458c89d0ffd39ca895a7fc79.jpg"],"date":"20180605","display_date":"6 月 5 日","id":9685387,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-41db6234997e4688d718275c9b13adee.jpg"],"date":"20180604","display_date":"6 月 4 日","id":9685162,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic1.zhimg.com/v2-89b5a74ba3c50d87b038d1c996225234.jpg"],"date":"20180603","display_date":"6 月 3 日","id":9685145,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic1.zhimg.com/v2-9b90af4fa02648892c819d0ea87fd21c.jpg"],"date":"20180602","display_date":"6 月 2 日","id":9685136,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic4.zhimg.com/v2-d7179e77693c23df6bc674eaa73a2b87.jpg"],"date":"20180601","display_date":"6 月 1 日","id":9684852,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-88b11eb71488d615f4044b6bf594d4e6.jpg"],"date":"20180531","display_date":"5 月 31 日","id":9684623,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-2086e25d117b6a517854e5a00d692596.jpg"],"date":"20180530","display_date":"5 月 30 日","id":9684394,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic4.zhimg.com/v2-a7bcc87549b0ea80659c5e267b92512b.jpg"],"date":"20180529","display_date":"5 月 29 日","id":9684408,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic2.zhimg.com/v2-84d410961f1ba6a2b552ec581778b5f1.jpg"],"date":"20180528","display_date":"5 月 28 日","id":9684345,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic2.zhimg.com/v2-76da47577b5ccf0541a26b86e1642d49.jpg"],"date":"20180527","display_date":"5 月 27 日","id":9684328,"title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic4.zhimg.com/v2-5819fe4ff5d5245ba7e5c631550539a3.jpg"],"date":"20180526","display_date":"5 月 26 日","id":9684141,"title":"瞎扯 · 如何正确地吐槽"}]
     * name : 瞎扯
     */

    private int timestamp;
    private String name;
    private List<StoriesBean> stories;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic3.zhimg.com/v2-eca4ef2340b1c9ca65f2c2eb0fddedfa.jpg"]
         * date : 20180614
         * display_date : 6 月 14 日
         * id : 9686469
         * title : 瞎扯 · 如何正确地吐槽
         */

        private String date;
        private String display_date;
        private int id;
        private String title;
        private List<String> images;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDisplay_date() {
            return display_date;
        }

        public void setDisplay_date(String display_date) {
            this.display_date = display_date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
