package com.siyuan.enjoyreading.entity;

/**
 * 基于APP的系统配置文件
 */
public class SystemConfig {

    private H5Urls h5_urls;

    public H5Urls getH5_urls() {
        return h5_urls;
    }

    public void setH5_urls(H5Urls h5_urls) {
        this.h5_urls = h5_urls;
    }

    private class H5Urls {
        private WebH5 bill_complaint; //账单申诉
        private WebH5 bill_common_problem; //账单申诉

        public WebH5 getBill_complaint() {
            return bill_complaint;
        }

        public void setBill_complaint(WebH5 bill_complaint) {
            this.bill_complaint = bill_complaint;
        }

        public WebH5 getBill_common_problem() {
            return bill_common_problem;
        }

        public void setBill_common_problem(WebH5 bill_common_problem) {
            this.bill_common_problem = bill_common_problem;
        }
    }

    private class WebH5 {
        private String url;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
