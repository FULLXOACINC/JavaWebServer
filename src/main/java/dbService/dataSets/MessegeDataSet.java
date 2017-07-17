package dbService.dataSets;

import java.sql.Timestamp;

/**
 * Created by alex on 17.7.17.
 */
public class MessegeDataSet {
    final String message;
    final Timestamp time;


    public MessegeDataSet(String message, Timestamp time) {
        this.message = message;
        this.time = time;
    }



    public String getMessage() {
        return message;
    }

    public java.util.Date getDate() {
        return time;
    }

    @Override
    public String toString() {
        return  " , Message:\" " + message + '\"' +
                " , Time: " + time + '\'' ;
    }
}
