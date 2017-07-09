package main;


import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        try {
            dbService.addUser("ALex","1111","ALex","Black");
            dbService.addUser("Billy","2222","Billy","Blue");
            dbService.addUser("Bob","3333","Bob","Green");
            dbService.addUser("Join","4444","Join","Void");


//
//            System.out.println("User data set: " + dbService.getUser("ALex"));
//            System.out.println("User data set: " + dbService.getUser("Billy"));
//            System.out.println("User data set: " + dbService.getUser("Bob"));
            System.out.println("User data set: " + dbService.getAllUsers());




            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
