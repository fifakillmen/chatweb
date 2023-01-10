package dal;

import model.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class messageDBcontext extends DBcontext {
//    public ArrayList<model.message> getMessages(ArrayList<model.conversation> conversations) {
//        ArrayList<model.message> messages = new ArrayList<>();
//        for (model.conversation conversation : conversations
//        ) {
//            try {
//                String sql = "select top(20) mid,Sender,Receiver,[message],[message_type],[create_at],[read_at],[co_id] from [Message]" +
//                        " where co_id= ? \n" +
//                        "  order by create_at desc";
//                PreparedStatement stm = connection.prepareStatement(sql);
//                stm.setInt(1, conversation);
//                ResultSet rs = stm.executeQuery();
//                while (rs.next()) {
//                    model.message m = new message();
//                    m.setMid(rs.getInt("mid"));
//                    m.setSender(rs.getInt("Sender"));
//                    m.setReceiver(rs.getInt("Receiver"));
//                    m.setMessage_data(rs.getString("message"));
//                    m.setMessage_type(rs.getString("message_type"));
//                    m.setCreate_at(rs.getTimestamp("create_at"));
//                    m.setRead_at(rs.getTimestamp("read_at"));
//                    m.setCo_id(rs.getInt("co_id"));
//                    messages.add(m);
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return messages;
//    }
}


