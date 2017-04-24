/**
 * Created by Filip on 14.03.2017.
 */

import crawler.*;
import Monitor.*;

import crawler.ConsoleLogger;
import crawler.Crawler;
import crawler.MailLogger;
import crawler.MainController;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class Main extends Application{
    private static String[] arguments;
    private MainController controller;
    public static void main( String[] args ) throws Exception
    {
        arguments = args;
        launch(args);
        /*Student b = new Student();
        Crawler c1=new Crawler();
        MailLogger mailLog= new MailLogger();
        ConsoleLogger conLog=new ConsoleLogger();

        c1.addListener(mailLog);

        try {
            c1.run();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int threadsNumber;
        System.out.println("How many threads would you like to start");
        Scanner in = new Scanner(System.in);
        threadsNumber=in.nextInt();

        URL tab[] = new URL[10];
        tab[0]=new URL("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt");

        for(int i=1;i<10;i++)
            tab[i]=new URL("http://google.pl/"+i);

        Monitor monitor = new Monitor(tab,threadsNumber);

        Thread close = new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(30000);
                }catch (InterruptedException e){
                }
                monitor.cancel();
            }
        };
        try{
            close.start();
            monitor.start();
        }catch (MonitorException ex){
        }
    }

    /*
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        MAIN UŻYWANY NA POTRZEBY PROJEKTU UŻYWAJĄCEGO FXML


    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2




    @Override
    public void start(Stage primaryStage) throws Exception{
        Crawler c1=new Crawler();
        MailLogger mailLog= new MailLogger();
        ConsoleLogger conLog=new ConsoleLogger();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("xmlfs/main.fxml"));

        try
        {
            loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        Parent root = loader.getRoot();
        controller=loader.getController();

        primaryStage.setTitle("Crawler");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(600);
        primaryStage.show();

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                c1.addListener(conLog);
                c1.addListener(controller);
                //c1.addListener(tabPane);
                //c1.addListener(mailLog);
                c1.run();
                return null;
            }
        };
        try {
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
    */

    /*
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

       MAIN NA POTRZEBY projektu używającego javafx

       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


     @Override
    public void start(Stage primaryStage) throws Exception {
        Crawler c1=new Crawler();
        MailLogger mailLog= new MailLogger();
        ConsoleLogger conLog=new ConsoleLogger();


        Scene scene = new Scene(new VBox(), 400, 400);

        //CustomMenuBar customBar = new CustomMenuBar();
        MenuBar customBar = new MenuBar();

        //CustomTabPane tabPane =new CustomTabPane();
        TabPane tabPane = new TabPane();


        ((VBox) scene.getRoot()).setVgrow(tabPane,Priority.ALWAYS);
        ((VBox)scene.getRoot()).getChildren().addAll(customBar,tabPane);

        scene.setFill(Color.BLUE);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(550);
        primaryStage.show();


        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                c1.addListener(conLog);
                c1.addListener(tabPane);
                c1.addListener(mailLog);
                c1.run();
                return null;
            }
        };
        try {
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
        */
    }

