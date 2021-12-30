package Presentation;

import DAO.ClientDAO;
import entities.Client;

public class ClientAddHandler {

    formClientWindow formClient = null;
    ClientDAO pdao = new ClientDAO();

    public ClientAddHandler(formClientWindow formClient) {
        this.formClient = formClient;
    }


    
    public void addClient(){
        String nom = formClient.ClientNomTextField.getText();
        String prenom = formClient.ClientPrenomTextField.getText();
        String tel = formClient.ClientTelTextField.getText();
        String email = formClient.ClientEmailTextField.getText();


        Client client =new Client(0L,nom,prenom,tel,email);

        pdao.add(client);
    }

    public void updateClient(Long id){
        System.out.println("----ID---> "+id);
        String nom = formClient.ClientNomTextField.getText();
        String prenom = formClient.ClientPrenomTextField.getText();
        String tel = formClient.ClientTelTextField.getText();
        String email = formClient.ClientEmailTextField.getText();

        Client client =new Client(id,nom,prenom,tel,email);

        pdao.update(client);
    }


}
