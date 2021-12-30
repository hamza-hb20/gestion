package Presentation;

import DAO.ClientDAO;
import entities.Client;
import entities.Produit;

import java.util.List;

public class ClientListHandler {

    ClientListWindow listWindow = null;

    ClientListHandler(){}

    ClientDAO clientDAO = new ClientDAO();

    public ClientListHandler(ClientListWindow listWindow){
        this.listWindow = listWindow;
    }


    public void getClients(){
        List<Client> liste = clientDAO.getAll();
        listWindow.ClientObservaleList.addAll(liste);
    }



    public void deleteClient(long id){
        clientDAO.delete(id);
        initTableView();
    }

    public void initTableView(){
        listWindow.ClientsTableView.getItems().clear();
        getClients();
    }
    public void calculerTotal(){
        listWindow.totalLabelValue.setText(listWindow.ClientsTableView.getItems().size()+"");
    }

}
