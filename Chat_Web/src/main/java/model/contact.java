package model;

import jakarta.mail.Message;

public class contact {
    private message message;
    private user friend_user;

    private conversation conversation;

    public model.message getMessage() {
        return message;
    }

    public void setMessage(model.message message) {
        this.message = message;
    }

    public user getFriend_user() {
        return friend_user;
    }

    public void setFriend_user(user friend_user) {
        this.friend_user = friend_user;
    }

    public model.conversation getConversation() {
        return conversation;
    }

    public void setConversation(model.conversation conversation) {
        this.conversation = conversation;
    }

    public String getLastMess() {
        String result = "";
        if (friend_user.getUid() == message.getSender()) {
            result = friend_user.getUdisplayname() + " ";
        } else {
            result = "You";
        }

        if (message.getMessage_type().equals("text")) {
            if (result.equals("You")) {
                result += ": " + message.getMessage_data();
            }else {
                result =message.getMessage_data();
            }

        } else if (message.getMessage_type().equals("file")) {
            result += "sent a photo";
        } else if (message.getMessage_type().equals("")) {
            result += "sent an attachment";
        }
        return result;
    }
}
