package dal;

import model.user;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDBcontext extends DBcontext {
    public boolean userIsExist(model.user user) {
        try {
            String sql = "select [name],[displayname],[dob],[address],[phone],[gender] from [User] \n" +
                    "where 1=1 and\n" +
                    "[name] like ? and\n" +
                    "[displayname] like ? and\n" +
                    "[dob] like ? and\n" +
                    "[address] like ? and\n" +
                    "[phone] like  ? and\n" +
                    "[gender] like ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUname());
            stm.setString(2, user.getUdisplayname());
            stm.setDate(3, user.getDob());
            stm.setString(4, user.getAddress());
            stm.setString(5, user.getPhone());
            stm.setBoolean(6, user.isGender());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int insertUser(model.user user) {

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [dbo].[User]\n" +
                    "           ([name]\n" +
                    "           ,[displayname]\n" +
                    "           ,[dob]\n" +
                    "           ,[address]\n" +
                    "           ,[phone]\n" +
                    "           ,[avartar]\n" +
                    "           ,[gender])\n" +
                    "     VALUES\n" +
                    "           ( ? \n" +
                    "           , ? \n" +
                    "           , ? \n" +
                    "           , ? \n" +
                    "           , ? \n" +
                    "           ,'null'\n" +
                    "           ,?)\n" +
                    "\n" +
                    "\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUname());
            stm.setString(2, user.getUdisplayname());
            stm.setDate(3, user.getDob());
            stm.setString(4, user.getAddress());
            stm.setString(5, user.getPhone());
            stm.setBoolean(6, user.isGender());
            stm.executeUpdate();
            String sql_userId = "Select @@IDENTITY as uid";
            PreparedStatement stm_uid = connection.prepareStatement(sql);
            ResultSet rs = stm_uid.executeQuery();
            int uid = -1;
            if (rs.next()) {
                uid = rs.getInt("uid");
            }
            return uid;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public model.user getUser(int uid) {
        try {
            String sql = "select [uid],[name],[displayname],[dob],[address],[phone],[gender],[avartar] from [user] " +
                    "where [uid]= ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                model.user user = new user();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getNString("name"));
                user.setUdisplayname(rs.getNString("displayname"));
                user.setDob(rs.getDate("dob"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getBoolean("gender"));
                user.setAvatar(rs.getString("avartar"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

//    public model.user getfriends_user(ArrayList<model.friend> friends) {
//        try {
//            for (model.friend f : friends
//            ) {
//                String sql = "select top [uid],[name],[displayname],[dob],[address],[phone],[gender],[avartar] from [user] " +
//                        "where [uid]= ? ";
//                PreparedStatement stm = connection.prepareStatement(sql);
//                stm.setInt(1, f.getReciever());
//                ResultSet rs = stm.executeQuery();
//                if (rs.next()) {
//                    model.user user = new user();
//                    user.setUid(rs.getInt("uid"));
//                    user.setUname(rs.getNString("name"));
//                    user.setUdisplayname(rs.getNString("displayname"));
//                    user.setDob(rs.getDate("dob"));
//                    user.setAddress(rs.getString("address"));
//                    user.setPhone(rs.getString("phone"));
//                    user.setGender(rs.getBoolean("gender"));
//                    user.setAvatar(rs.getString("avartar"));
//                    return user;
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
}
