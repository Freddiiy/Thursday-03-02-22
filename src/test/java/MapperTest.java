import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {
    Mapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new Mapper();

        System.out.println("TESTINNNNGGGG");
        Connection con = null;
        try {
            con = DBconnector.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5,"Rolighedsvej 3");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        Connection connection = DBconnector.connection();
        try {
            String droptable = "DROP TABLE IF EXISTS `startcode_test`.`usertable`";
            connection.prepareStatement(droptable).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getNames() {

        List<User> expected = new ArrayList<>();
        expected.add(new User("Hans", "Hansen"));

        List<User> actual = mapper.getNames();

        assertEquals(expected.equals(actual), actual.equals(expected));
    }

    @Test
    void getDetails() {
        String[] expected = {"40404040", "Rolighedsvej 3"};
        String[] actual = mapper.getUserByName("Hans", "Hansen").getDetails();

        assertEquals(Arrays.equals(expected, actual), Arrays.equals(actual, expected));
    }

    @Test
    void changeDetails() {
        String[] expected = {"30303030", "Larmevej 3"};
        mapper.editDetails("Hans", "Hansen", "30303030", "Larmevej 3");

        String[] actual = mapper.getUserByName("Hans", "Hansen").getDetails();

        assertEquals(Arrays.equals(expected, actual), Arrays.equals(actual, expected));
    }
}