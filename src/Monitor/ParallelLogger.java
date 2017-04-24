package Monitor;

import crawler.CrawlerEventListener;
import crawler.Logger;
import crawler.Student;

/**
 * Created by Filip on 23.04.2017.
 */
public class ParallelLogger implements Logger, CrawlerEventListener {

    private Logger[] loggers;

    public ParallelLogger(Logger[] loggers){
        this.loggers=loggers;
    }


    @Override
    public void log(String status, Student student) {
        for(Logger l:loggers){
            l.log(status,student);
        }

    }

    @Override
    public void onStudentAdded(Student a) {
        log("add",a);
    }

    @Override
    public void onStudentDeleted(Student a) {
        log("del",a);
    }

    @Override
    public void onNoChange() {
        log("no",new Student());
    }
}
