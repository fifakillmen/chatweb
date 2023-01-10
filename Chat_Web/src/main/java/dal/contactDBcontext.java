package dal;

import model.contact;
import model.conversation;
import model.message;
import model.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class contactDBcontext extends DBcontext {
    public ArrayList<model.contact> gettextedContacts(model.account account) {
        ArrayList<model.contact> contacts = new ArrayList<>();
        try {
            String sql = "SELECT a1.mid,\n" +
                    "       a1.sender,\n" +
                    "       a1.receiver,\n" +
                    "       a1.friend,\n" +
                    "       b1.[name],\n" +
                    "       b1.displayname," +
                    "       b1.avartar,\n" +
                    "       a1.[message],\n" +
                    "       a1.message_type,\n" +
                    "       a1.create_at,\n" +
                    "       a1.read_at,\n" +
                    "       a1.co_id\n" +
                    "FROM (\n" +
                    "         SELECT a.mid,\n" +
                    "                a.sender,\n" +
                    "                a.receiver,\n" +
                    "                b.[uid] AS friend,\n" +
                    "                a.[message],\n" +
                    "                a.message_type,\n" +
                    "                a.create_at,\n" +
                    "                a.read_at,\n" +
                    "                a.co_id\n" +
                    "         FROM (\n" +
                    "                  SELECT m.mid,\n" +
                    "                         m.Sender,\n" +
                    "                         m.Receiver,\n" +
                    "                         m.[message],\n" +
                    "                         m.message_type,\n" +
                    "                         m.create_at,\n" +
                    "                         m.read_at,\n" +
                    "                         m.co_id\n" +
                    "                  FROM [Message] m\n" +
                    "                       JOIN (\n" +
                    "                                SELECT co_id,\n" +
                    "                                       MAX(create_at) AS create_at\n" +
                    "                                FROM [Message]\n" +
                    "                                GROUP BY co_id\n" +
                    "                            ) c\n" +
                    "                            ON c.create_at = m.create_at\n" +
                    "                  WHERE m.co_id IN (\n" +
                    "                      SELECT coid\n" +
                    "                      FROM Conversation_user\n" +
                    "                      WHERE [uid] = ?\n" +
                    "                  )\n" +
                    "              ) a\n" +
                    "                  JOIN (\n" +
                    "                           SELECT [uid], coid\n" +
                    "                           FROM Conversation_user\n" +
                    "                       ) b\n" +
                    "                       ON a.co_id = b.coid\n" +
                    "         WHERE b.coid = a.co_id\n" +
                    "           AND b.[uid] != ?\n" +
                    "     ) a1\n" +
                    "          JOIN (\n" +
                    "                   SELECT *\n" +
                    "                   FROM [user]\n" +
                    "               ) b1\n" +
                    "               ON b1.[uid] = a1.friend\n" +
                    "ORDER BY a1.create_at DESC\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, account.getUser().getUid());
            stm.setInt(2, account.getUser().getUid());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                model.contact contact = new contact();
                model.user user = new user();
                model.message message = new message();
                model.conversation conversation = new conversation();
                //======================================
                user.setUid(rs.getInt("friend"));
                user.setUname(rs.getString("name"));
                user.setUdisplayname(rs.getString("displayname"));
                user.setAvatar(rs.getString("avartar"));
                //======================================
                message.setMid(rs.getInt("mid"));
                message.setSender(rs.getInt("sender"));
                message.setMessage_data(rs.getString("message"));
                message.setMessage_type(rs.getString("message_type"));
                message.setCreate_at(rs.getTimestamp("create_at"));
                message.setRead_at(rs.getTimestamp("read_at"));
                //======================================
                conversation.setCoid(rs.getInt("co_id"));

                contact.setFriend_user(user);
                contact.setMessage(message);
                contact.setConversation(conversation);
                contacts.add(contact);
            }
            return contacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
