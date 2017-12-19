package vn.iotech.firebasechat;

/**
 * Created by ap on 12/19/2017.
 */

public class Message {
    private String mUser;
    private String mMessage;
    private boolean isMe = false;

    public String getUser() {
        return mUser;
    }

    public void setUser(String mUser) {
        this.mUser = mUser;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}
