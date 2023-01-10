package model;

public class friend {
    private int fid;
    private int sender;
    private int reciever;
    private int owner;
    private String statust;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReciever() {
        return reciever;
    }

    public void setReciever(int reciever) {
        this.reciever = reciever;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getStatust() {
        return statust;
    }

    public void setStatust(String statust) {
        this.statust = statust;
    }
}
