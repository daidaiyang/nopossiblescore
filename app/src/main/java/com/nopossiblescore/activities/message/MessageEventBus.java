package com.nopossiblescore.activities.message;

public class MessageEventBus {

    private int eventCode;//0  返回按钮   1更换界面到详情  2更换到list界面

    private int position;

    public MessageEventBus(int eventCode) {
        this.eventCode = eventCode;
    }

    public MessageEventBus(int eventCode, int position) {
        this.eventCode = eventCode;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }
}
