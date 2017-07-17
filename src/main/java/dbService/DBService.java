package dbService;

import dbService.dao.MessegeDAO;
import dbService.dao.UsersDAO;
import dbService.dataSets.MessegeDataSet;
import dbService.dataSets.UsersDataSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by alex on 17.7.17.
 */
public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public UsersDataSet getUser(String login) throws DBException {
        try {
            return (new UsersDAO(connection).get(login));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addUser(String login,String password,String firstName,String lastName) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(login,password,firstName,lastName);
            new MessegeDAO(connection).createMessagesTable(login);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void sendMessege(String recipient, String sender, String message) throws DBException {
        try {
            connection.setAutoCommit(false);
            new MessegeDAO(connection).sendMessage(recipient,sender,message);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public Map<String, ArrayList<MessegeDataSet>> getMesseges(String login) throws DBException {
        try {
            return (new MessegeDAO(connection).getMessages(login));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("database?").          //db name
                    append("user=root&").          //Login
                    append("password=1130324");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
