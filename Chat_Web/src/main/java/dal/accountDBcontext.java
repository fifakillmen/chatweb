package dal;

import model.account;
import model.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class accountDBcontext extends DBcontext {
    public boolean accountIsExist(String email) {
        try {
            String sql = "select email from Account where email like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
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

    public model.account loginCheck(String email, String password) {
        try {
            String sql = "select [uid],[email], [password] from Account where [email] like ? and [password] like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                model.account account = new account();
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                model.user user = new user();
                user.setUid(rs.getInt("uid"));
                account.setUser(user);
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int insertAccount(model.account account, model.user user) {
        try {
            String sql = "\n" +
                    "INSERT INTO [dbo].[Account]\n" +
                    "           ([email]\n" +
                    "           ,[password]\n" +
                    "           ,[uid])\n" +
                    "     VALUES\n" +
                    "           ( ? \n" +
                    "           , ? \n" +
                    "           , ? )";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getEmail());
            stm.setString(2, account.getPassword());
            stm.setInt(3, user.getUid());
            int result = stm.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
