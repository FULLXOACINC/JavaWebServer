package dbService.dao;

import dbService.dataSets.MessegeDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

/**
 * Created by alex on 17.7.17.
 */
public class MessegeDAO {
    private Executor executor;

    public MessegeDAO(Connection connection) {
        this.executor = new Executor(connection);
    }


    public Map<String,ArrayList<MessegeDataSet>> getMessages(String login) throws SQLException {
        return executor.execQuery("select * from "+login, result -> {
            Map<String,ArrayList<MessegeDataSet>> messeges= new HashMap<String,ArrayList<MessegeDataSet>>();
            do{
                result.next();
                if(messeges.get(result.getString(1))==null){
                    ArrayList<MessegeDataSet> list =new ArrayList<MessegeDataSet>();
                    list.add(new MessegeDataSet(result.getString(2), result.getTimestamp(3)));
                    messeges.put(result.getString(1),list);
                }
                else
                    messeges.get(result.getString(1)).add(new MessegeDataSet(result.getString(2), result.getTimestamp(3)));

            }while(!result.isLast());
            return messeges;
        });
    }

    public void sendMessage(String recipient, String sender, String message) throws SQLException {
        executor.execUpdate("insert into "+recipient+" (Sender,Message,Date) values ('"+sender+ "','"+message+ "',now())");
    }

    public void createMessagesTable(String login) throws SQLException {
        executor.execUpdate("create table if not exists "+login+" (\n" +
                "    Sender varchar(255),\n" +
                "    Message varchar(255),\n" +
                "    sendDate TIMESTAMP(6) \n" +
                "    )");
    }

}
