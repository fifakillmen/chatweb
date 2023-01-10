package dal;

import model.friend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class friendDbcontext extends DBcontext {
    public ArrayList<friend> getallFriend(int owner) {

        try {
            ArrayList<friend> friends = new ArrayList<>();
            String sql = "select fid,Sender,Receiver,[owner],[status] from Friends \n" +
                    "where [owner]= ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, owner);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.friend f = new friend();
                f.setFid(rs.getInt("fid"));
                f.setSender(rs.getInt("Sender"));
                f.setReciever(rs.getInt("Receiver"));
                f.setOwner(rs.getInt("owner"));
                f.setStatust(rs.getString("status"));
                friends.add(f);
            }
            return friends;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
