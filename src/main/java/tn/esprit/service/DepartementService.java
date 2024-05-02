package tn.esprit.service;

import tn.esprit.entities.Departement;
import tn.esprit.tools.DBconnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementService implements ICrud<Departement>{
    Connection cnx2;
    public DepartementService() {
        cnx2 = DBconnexion.getInstance().getCnx();
    }

    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `departement` WHERE `id` ="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    @Override
    public void ajouterEntite(Departement p) {
        String req1 = "INSERT INTO `departement`( `headmaster_id`, `name`, `description`) VALUES (?,?,?)";
        try {
            PreparedStatement st = cnx2.prepareStatement(req1);
            st.setInt(1, p.getHeadmaster_id());
            st.setString(2, p.getName());
            st.setString(3,p.getDescription());
            st.executeUpdate();
            System.out.println("departement ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Departement> afficherEntite() {
        return null;
    }


    public List<String> afficherEntites() {
        List<String> deps = new ArrayList<>();
        String req3 = "SELECT * FROM departement";
        try {
            Statement stm = cnx2.createStatement();
            ResultSet rs = stm.executeQuery(req3);
            while (rs.next()) {
                Departement p = new Departement();
                p.setId(rs.getInt("id"));
                p.setHeadmaster_id(rs.getInt("headmaster_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                deps.add(p.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return deps;
    }

    @Override
    public void modifierEntite(Departement p) {
        String requet = "UPDATE departement SET name=?, description=? WHERE id =?";
        try {
            PreparedStatement st = cnx2.prepareStatement(requet);
            st.setString(1, p.getName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getId());
            System.out.println(p.getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Modification réussie");
            } else {
                System.out.println("Aucune modification effectuée. Vérifiez l'ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimerEntite(Departement p) {
        String requet = "DELETE FROM departement WHERE id =?";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requet);
            pst.setInt(1, p.getId());  // Assuming getDepartementId() returns the Departement ID
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Suppression réussie");
            } else {
                System.out.println("Aucune suppression effectuée. Vérifiez l'ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet Getall() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `departement`";
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }

    public ResultSet search(String s) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `departement` where name like '%"+s+"%'";
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }

    public ResultSet sort() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `departement` ORDER BY name";
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }
}

