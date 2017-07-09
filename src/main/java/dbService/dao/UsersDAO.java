package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet get(String login) throws SQLException {
        return executor.execQuery("select * from users where login='" + login+"'", result -> {
            result.next();
            return new UsersDataSet(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
        });
    }
    public List<UsersDataSet> getAll() throws SQLException {
        return executor.execQuery("select * from users ", result -> {
            List<UsersDataSet> users= new ArrayList<UsersDataSet>();
            result.next();
            while(!result.isLast()){
                users.add( new UsersDataSet(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
                result.next();
            }
            return users;
        });
    }

//    public long getUserId(String name) throws SQLException {
//        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
//            result.next();
//            return result.getLong(1);
//        });
//    }

    public void insertUser(String login,String password,String firstName,String lastName) throws SQLException {
        executor.execUpdate("insert into users (Login,Password,LastName,FirstName) values ('" + login + "','"+password+ "','"+firstName+ "','"+lastName+"')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Login varchar(255),\n" +
                "    Password varchar(255)\n" +
                "    )");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
