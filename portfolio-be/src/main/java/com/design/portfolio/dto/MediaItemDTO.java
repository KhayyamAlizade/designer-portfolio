package com.design.portfolio.dto;

public class MediaItemDTO {
    private String fileName;
    private String fileType;
    private int width;
    private int height;
    private String ratioType;
    private String base64;

    public MediaItemDTO(String src, String type, String ratio) {
        this.src = src;
        this.type = type;
        this.ratio = ratio;
    }

    public String getSrc() {
        return src;
    }

    public String getType() {
        return type;
    }

    public String getRatio() {
        return ratio;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}