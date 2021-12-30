package Presentation;

import entities.Client;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ClientListWindow {
    ClientListHandler handler = new ClientListHandler(this);

    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);

    Label titleLabel = new Label("Liste des Clients");
    HBox totalHbox = new HBox();
    Label totalLabel = new Label("Total des clients");
    Label totalLabelValue = new Label("0");
    TableColumn<Client, Long> idColumn = new TableColumn<>("Id");
    TableColumn<Client, String> nomColumn = new TableColumn<>("Nom");
    TableColumn<Client, Double> prenomColumn = new TableColumn<>("Prenom");
    TableColumn<Client, Integer> telColumn = new TableColumn<>("Tel");
    TableColumn<Client, Double> emailColumn = new TableColumn<>("Email");
    TableView<Client> ClientsTableView = new TableView<>();

    ObservableList<Client> ClientObservaleList = FXCollections.observableArrayList();


    private void addComumnsToTableView(){
        ClientsTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, telColumn, emailColumn);
        ClientsTableView.setItems(ClientObservaleList);
    }

    private void addNodesToPane(){
        totalHbox.getChildren().addAll(totalLabel,totalLabelValue);
        root.getChildren().addAll(titleLabel,ClientsTableView,totalHbox);
    }

    private void updateColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        idColumn.setPrefWidth(100);
        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));
        nomColumn.setPrefWidth(250);
        prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
        prenomColumn.setPrefWidth(150);
        telColumn.setCellValueFactory(new PropertyValueFactory("tel"));
        telColumn.setPrefWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        emailColumn.setPrefWidth(150);


    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("css/style.css");
        titleLabel.getStyleClass().add("labelTitle");
        totalLabel.getStyleClass().add("labelTotal");
        totalLabelValue.getStyleClass().add("labelTotal");
        totalHbox.getStyleClass().add("boxTotal");
        ClientsTableView.getStyleClass().add("table-row-cell");
        ClientsTableView.setMinHeight(500);
        titleLabel.setMinWidth(window.getWidth());
        totalHbox.setSpacing(15);
    }

    public void setTableRow(){
        ClientsTableView.setRowFactory(
                new Callback<TableView<Client>, TableRow<Client>>() {
                    public TableRow<Client> call(TableView<Client> ClientsTableView) {
                        final TableRow<Client> row = new TableRow<>();
                        final ContextMenu contextMenu = new ContextMenu();
                        final MenuItem updateClient = new MenuItem("Modifier");
                        final MenuItem deleteClient = new MenuItem("Supprimer");
                        contextMenu.getItems().addAll(updateClient,deleteClient);
                        deleteClient.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("---->"+row.getItem());
                                handler.deleteClient(Long.valueOf(row.getItem().getId()));
                                ClientsTableView.getItems().remove(row.getItem());
                            }
                        });

                        updateClient.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("CLIENT ===>"+row.getItem());
                                Client c =new Client(row.getItem().getId(),row.getItem().getNom(),row.getItem().getPrenom(),row.getItem().getTel(),row.getItem().getEmail());

                                new formClientWindow(c);
                                window.close();

                            }
                        });

                        // only display context menu for non-null items:
                        row.contextMenuProperty().bind(
                                Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                        .then(contextMenu)
                                        .otherwise((ContextMenu)null));
                        return row;
                    }
                });
    }



    public void initwindow() {
        window.setScene(scene);
        window.setWidth(1000);
        window.setHeight(650);
        window.setTitle("Liste Clients");
        window.getIcons().add(new Image("file:icon.png"));
        window.initModality(Modality.APPLICATION_MODAL);

    }

    public ClientListWindow() {
        initwindow();
        addComumnsToTableView();
        addNodesToPane();
        updateColumns();
        addStylesToNodes();
        setTableRow();
        handler.getClients();
        handler.calculerTotal();
        window.show();
    }
}
