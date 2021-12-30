package DAO;

import entities.Client;

import java.util.List;

public interface IClientDAO extends IDAO<Client> {

    List<Client> getAll(String nom);
}
