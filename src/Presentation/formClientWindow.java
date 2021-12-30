package Presentation;

import entities.Client;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class formClientWindow {

    ClientAddHandler handler = new ClientAddHandler(this);
    VBox root = new VBox();
    HBox buttons = new HBox();
    Scene scene = new Scene(root);
    Stage window = new Stage();

    /*controls for container*/
    Label titleLabel = new Label("Nouveau Client:");

    Label ClientNomLabel = new Label("Nom:");
    TextField ClientNomTextField = new TextField();

    Label ClientPrenomLabel = new Label("Prenom:");
    TextField ClientPrenomTextField = new TextField();

    Label ClientTelLabel = new Label("Telephone:");
    TextField ClientTelTextField = new TextField();

    Label ClientEmailLabel = new Label("Email:");
    TextField ClientEmailTextField = new TextField();

    Button addClient = new Button("Ajouter");
    Button cancelClient = new Button("Annuler");



    private void initwindow(){
        window.setScene(scene);
        window.setWidth(700);window.setHeight(600);
        window.setTitle("Nouveau Client");
        window.getIcons().add(new Image("file:icon.png"));
        window.initModality(Modality.APPLICATION_MODAL);

    }

    private void addNodesToWindow(){
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(ClientNomLabel, ClientNomTextField);
        root.getChildren().addAll(ClientPrenomLabel, ClientPrenomTextField);
        root.getChildren().addAll(ClientTelLabel, ClientTelTextField);
        root.getChildren().addAll(ClientEmailLabel, ClientEmailTextField);
        buttons.getChildren().addAll(addClient,cancelClient);
        root.getChildren().add(buttons);
    }

    private void addStyleToNodes(){
        scene.getStylesheets().add("css/style.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMinWidth(window.getWidth());
        ClientNomLabel.getStyleClass().add("labelForm");
        ClientPrenomLabel.getStyleClass().add("labelForm");
        ClientTelLabel.getStyleClass().add("labelForm");
        ClientEmailLabel.getStyleClass().add("labelForm");
        root.setSpacing(15);
        buttons.setSpacing(20);

    }

    private void addEvents(){
        cancelClient.setOnAction(event -> {
            window.close();
        });
        addClient.setOnAction(event -> {
            handler.addClient();
            /*window.close();*/
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

        });
        window.setOnCloseRequest(event -> {
            event.consume();
        });
    }

    private void addEvents(Long id){
        cancelClient.setOnAction(event -> {
            window.close();
            new ClientListWindow();
        });
        addClient.setOnAction(event -> {
            handler.updateClient(id);
            window.close();
            new ClientListWindow();

        });
        /*window.setOnCloseRequest(event -> {
            event.consume();
        });*/
    }

    public formClientWindow() {
        initwindow();
        addStyleToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }

    public formClientWindow(Client c) {
        initwindow();
        ClientNomTextField.setText(c.getNom());
        ClientTelTextField.setText(c.getTel()+"");
        ClientPrenomTextField.setText(c.getPrenom()+"");
        ClientEmailTextField.setText(c.getEmail()+"");
        addClient.setText("Modifier");
        titleLabel.setText("Modifier un Client");
        window.setTitle("Modification du Client");
        addStyleToNodes();
        addNodesToWindow();
        System.out.println("in construct id==> "+c.toString());
        addEvents(c.getId());
        window.show();
    }
}
