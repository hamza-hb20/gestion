package DAO;

import entities.Client;
import entities.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientDAO{
    Connexion cnx;

    public ClientDAO() {
        cnx = new Connexion();
    }

    @Override
    public void add(Client c) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "insert into clients(nom,prenom,tel,email) values (?,?,?,?)";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getTel());
            pst.setString(4, c.getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "delete from clients where id = ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getOne(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "select * from clients where id= ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            Client c;
            if (rs.next())
                return new Client(rs.getLong(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> ClientList = new ArrayList<Client>();
        PreparedStatement pst = null;
        ResultSet rs;

        try {
            String sql = "select * from Clients";
            pst = cnx.getConnexion().prepareStatement(sql);
            rs = pst.executeQuery();
            Client c;
            while (rs.next()) {
                c = new Client(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                ClientList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ClientList;
    }

    @Override
    public void update(Client c) {
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "update clients set nom = ?, prenom =  ?, tel = ?, email = ? where id = ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getTel());
            pst.setString(4, c.getEmail());
            pst.setLong(5, c.getId());
            System.out.println("in update method for clients ====> "+pst.toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAll(String tel) {
        List<Client> ClientList = new ArrayList<Client>();
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            String sql = "select * from Clients where tel like ?";
            pst = cnx.getConnexion().prepareStatement(sql);
            pst.setString(1, tel + "%");
            rs = pst.executeQuery();
            Client c;
            while (rs.next()) {
                c = new Client(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                ClientList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ClientList;
    }
}
