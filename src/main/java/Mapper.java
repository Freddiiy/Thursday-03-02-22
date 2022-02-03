import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private final Connection connection;

    public Mapper() {
        connection = DBconnector.connection();
    }

    public List<User> getNames() {
        String sql = "SELECT fname, lname FROM usertable";
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            int x = 0;
            while (resultSet.next()) {
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                users.add(new User(fname, lname));
                if (users.get(x).getName().equals(fname + " " + lname)) {
                    break;
                }
            }
            if (users.isEmpty()) return null;

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByName(String fname, String lname) {
        String sql = "SELECT fname, lname, phone, address FROM usertable WHERE fname = ? AND lname = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String fnameFromDb = resultSet.getString("fname");
                String lnameFromDb = resultSet.getString("lname");
                String phoneFromDb = resultSet.getString("phone");
                String addressFromDb = resultSet.getString("address");

                return new User(fnameFromDb, lnameFromDb, phoneFromDb, addressFromDb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editDetails(String fname, String lname, String phone, String address) {
        String sql = "UPDATE usertable SET phone = ?, address = ? WHERE fname = ? AND lname = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, address);
            ps.setString(3, fname);
            ps.setString(4, lname);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}