package Monitor;

import crawler.Crawler;

/**
 * Created by Filip on 24.04.2017.
 */
public class CrawlerThread extends Thread{
    private final Crawler crawler;
    private final String name;
    private Thread thread;

    public CrawlerThread(Crawler crawler, String name){
        this.crawler = crawler;
        this.name = name;
    }

    @Override
    public void run(){
        try {
            crawler.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(){
        if(thread==null)
        {
            thread = new Thread(this,name);
            thread.start();
        }
    }

    public void postCancel(){
        crawler.postCancel();
    }
}
