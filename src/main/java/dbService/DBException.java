package dbService;

/**
 * Created by alex on 17.7.17.
 */
public class DBException extends Exception {
    DBException(Throwable throwable) {
        super(throwable);
    }
}
