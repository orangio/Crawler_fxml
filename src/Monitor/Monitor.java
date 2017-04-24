package Monitor; /**
 * Created by Filip on 23.04.2017.
 */

import crawler.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Monitor {

    private final ArrayList<URL> urls;
    private final ArrayList<CrawlerThread> threads = new ArrayList<CrawlerThread>();
    private Logger[] loggers = new Logger[]{
            new ConsoleLogger(),
            new MailLogger()
    };

    private boolean isOn=true;
    private int threadsNumber;


    public Monitor(URL[] url, int number) {

        //if urls.size > number throw exception 'wrong number'

        this.urls= new ArrayList<>(Arrays.asList(url));
        this.threadsNumber=number;

    }

    public void start() throws MonitorException {
        if(threadsNumber<urls.size()) throw new MonitorException("Za duzo adresow, za malo watkow");
        int i=0;
        while(isOn&&i<urls.size()){
            URL s = urls.get(i);
            Crawler crawler = new Crawler(s);
            try {
                crawler.addListener(new ParallelLogger(loggers));

            } catch (Exception e) {
                    e.printStackTrace();
            }

            CrawlerThread thread = new CrawlerThread(crawler,"thread"+i);
            threads.add(thread);
            thread.start();
            i++;
        }
    }

    public void cancel(){
        try{
            isOn=false;
            for(CrawlerThread t:threads){
                t.postCancel();
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
