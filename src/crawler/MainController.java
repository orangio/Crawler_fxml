package crawler;

import com.sun.org.apache.xml.internal.security.Init;
import crawler.xmlfs.HistogramController;
import crawler.xmlfs.LogController;
import crawler.xmlfs.StudentsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, CrawlerEventListener {

    @FXML
    public TabPane tabPane;
    @FXML
    public StudentsController tabSController;
    public LogController tabLController;
    public HistogramController tabHController;
    @FXML
    public MenuItem exit;
    public BorderPane tabS;
    public AnchorPane tabL;
    public AnchorPane tabH;
    private ObservableList<Student> students = FXCollections.observableArrayList();


    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void about(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Example program information");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        onStudentAdded(new Student());
//        onStudentDeleted(new Student());
    }

    @Override
    public void onStudentAdded(Student a) {
        tabSController.addStudent(a);
        tabLController.addData("add",a);
        tabHController.addData(a.getMark());
        //System.out.println("lol");
    }

    @Override
    public void onStudentDeleted(Student a) {

        tabSController.delStudent(a);
        tabLController.addData("add",a);
        tabHController.delData(a.getMark());
    }

    @Override
    public void onNoChange() {

    }
}
