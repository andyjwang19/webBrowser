import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.web.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.net.URL;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

public class Main extends Application {


    public static ArrayList<tab> tabList = new ArrayList<tab>();
    public static ArrayList<Button> tabButtonList = new ArrayList<Button>();
    final static GridPane TabController = new GridPane();
    final static VBox box = new VBox();
    final static Button newTab = new Button("+");
    final static HBox tabs = new HBox();
    final static WebView view = new WebView();
    final static TextField textfield = new TextField();
    final static Button button = new Button("  Go  ");
    public static int currentTab = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Web Content");

        WebEngine eng = view.getEngine();
        tabList.add(new tab("https://www.apple.com","Apple.com"));
        eng.load("https://www.apple.com");
        tabs.getChildren().addAll(tabList);
        tabs.getChildren().add(newTab);
        newTab.setOnAction(e -> {
            tabs.getChildren().add(tabs.getChildren().size()-2, new Button("https://www.apple.com"));
            tabList.add(new Button("https://www.apple.com"));
            eng.load("https://www.apple.com");
        });




        button.setOnAction(e -> {
            try {
                URL u = new URL(textfield.getText());
                u.toURI();
                eng.load(textfield.getText());
                tabList.get(currentTab).setText(textfield.getText());
                tabs.getChildren().set(currentTab, new Button(textfield.getText()));

            }
            catch(Exception e1) {
                eng.load("http://www.google.com/search?q=" + textfield.getText() + "&tbm=isch");
                tabs.getChildren().set(currentTab, new Button("google.com"));
                tabList.get(currentTab).setText("google.com");
            }

        });

        for(int i = 0; i<tabList.size(); i++) {
            int j = i;
            tabList.get(i).setOnAction(e -> {
                currentTab = j;
                eng.load(tabList.get(j).getText());
                System.out.println("hippo");
            });

        }

        GridPane.setConstraints(textfield,0,0);
        GridPane.setConstraints(button, 1,0);


        TabController.getChildren().addAll(button, textfield);

        box.getChildren().addAll(TabController, tabs, view);

        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


}
