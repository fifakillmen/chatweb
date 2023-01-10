package model;

import helperCode.datetimeHellper;

import java.sql.Timestamp;

public class message {
    private int mid;

    private int sender;
    private int receiver;

    private String message_data;
    private String message_type;

    private java.sql.Timestamp create_at;
    private java.sql.Timestamp read_at;
    private int co_id;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMessage_data() {
        return message_data.toString();
    }

    // need toString because nvarchar is not String
    public void setMessage_data(String message_data) {
        this.message_data = message_data;
    }

    public String getMessage_type() {
        return message_type.toString();
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }


    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getRead_at() {
        return read_at;
    }

    public void setRead_at(Timestamp read_at) {
        this.read_at = read_at;
    }

    public int getCo_id() {
        return co_id;
    }

    public void setCo_id(int co_id) {
        this.co_id = co_id;
    }

    public String getCreate_at_String() {
        helperCode.datetimeHellper hellper = new datetimeHellper();
        return hellper.convertTimeStam2String(create_at);
    }

    public String getRead_at_String() {
        helperCode.datetimeHellper hellper = new datetimeHellper();
        return hellper.convertTimeStam2String(read_at);
    }


}
