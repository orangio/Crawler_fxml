package Monitor;

/**
 * Created by Filip on 24.04.2017.
 */
public class MonitorException extends Exception{
    public MonitorException(String message) {
        super(message);
        System.out.println("\n"+message);
    }
}
