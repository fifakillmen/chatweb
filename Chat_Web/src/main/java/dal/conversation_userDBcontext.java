package dal;

import model.conversation_user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class conversation_userDBcontext extends DBcontext {
    public ArrayList<model.conversation_user> getConversation_userWith2userId(int uid1, int uid2) {
        ArrayList<model.conversation_user> conversationUsers = new ArrayList<>();
        try {
            String sql = "select c2.coid,c1.is_admin as[u1_isAdmin],c2.is_admin as[u2_isAdmin],c1.[uid] as[u1_id],c2.[uid] as[u2_id] \n" +
                    "  from (select coid,is_admin,[uid] from Conversation_user where [uid]= ? ) c1\n" +
                    "  join\n" +
                    "  (select coid,is_admin,[uid] from Conversation_user where [uid]= ? ) c2\n" +
                    "  on c1.coid= c2.coid";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid1);
            stm.setInt(2, uid2);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                model.conversation_user conversationUser1 = new conversation_user();
                conversationUser1.setCoid(rs.getInt("coid"));
                conversationUser1.setUid(rs.getInt("u1_id"));
                conversationUser1.setAdmin(rs.getBoolean("u1_isAdmin"));

                model.conversation_user conversationUser2 = new conversation_user();
                conversationUser2.setCoid(rs.getInt("coid"));
                conversationUser2.setUid(rs.getInt("u2_id"));
                conversationUser2.setAdmin(rs.getBoolean("u2_isAdmin"));
                conversationUsers.add(conversationUser1);
                conversationUsers.add(conversationUser2);
                return conversationUsers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
