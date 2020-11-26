package com.psq.train.util;

/**
 * RequestPushDTO.java
 * Description: 请求推送的DTO
 *
 * @author Peng Shiquan
 * @date 2020/11/10
 */
public class RequestPushDTO {
    private String sipAction;
    private String cameraNum;
    private String user;
    private int videoType;
    private Command command;

    public String getSipAction() {
        return sipAction;
    }

    public void setSipAction(String sipAction) {
        this.sipAction = sipAction;
    }

    public String getCameraNum() {
        return cameraNum;
    }

    public void setCameraNum(String cameraNum) {
        this.cameraNum = cameraNum;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public static class Command {
        private String serverip;
        private Integer serverport;
        private String pubName;
        private String authInfo;
        private Video video;

        public Command() {
        }

        public Command(String serverip, Integer serverport, String pubName, String authInfo, Video video) {
            this.serverip = serverip;
            this.serverport = serverport;
            this.pubName = pubName;
            this.authInfo = authInfo;
            this.video = video;
        }

        public Video getVideo() {
            return video;
        }

        public void setVideo(Video video) {
            this.video = video;
        }

        public String getServerip() {
            return serverip;
        }

        public void setServerip(String serverip) {
            this.serverip = serverip;
        }

        public Integer getServerport() {
            return serverport;
        }

        public void setServerport(Integer serverport) {
            this.serverport = serverport;
        }

        public String getPubName() {
            return pubName;
        }

        public void setPubName(String pubName) {
            this.pubName = pubName;
        }

        public String getAuthInfo() {
            return authInfo;
        }

        public void setAuthInfo(String authInfo) {
            this.authInfo = authInfo;
        }
    }

    public static class Video {
        private int width;
        private int height;
        private int bitrate;
        private int iframe;
        private int framerate;

        public Video() {
        }

        public Video(int width, int height, int bitrate, int iframe, int framerate) {
            this.width = width;
            this.height = height;
            this.bitrate = bitrate;
            this.iframe = iframe;
            this.framerate = framerate;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getIframe() {
            return iframe;
        }

        public void setIframe(int iframe) {
            this.iframe = iframe;
        }

        public int getFramerate() {
            return framerate;
        }

        public void setFramerate(int framerate) {
            this.framerate = framerate;
        }
    }
}

