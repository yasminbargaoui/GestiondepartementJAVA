package tn.esprit.service;

import tn.esprit.entities.Internship;
import tn.esprit.tools.DBconnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InternshipService implements ICrud<Internship>{
    Connection cnx2;
    public InternshipService() {
        cnx2 = DBconnexion.getInstance().getCnx();
    }

    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `internship` WHERE `id` ="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    @Override
    public void ajouterEntite(Internship p) {
        String req1 = "INSERT INTO `internship`( `period`, `startdate`, `typeinternship`, `technology`,`description`,`title`,`departement_id`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx2.prepareStatement(req1);
            st.setString(1, p.getPeriod());
            st.setString(2, p.getStartdate());
            st.setString(3,p.getTypeinternship());
            st.setString(4, p.getTechnology());
            st.setString(5, p.getDescription());
            st.setString(6, p.getTitle());
            st.setInt(7, p.getDepartement_id());
            st.executeUpdate();
            System.out.println("internship ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Internship> afficherEntite() {
        return null;
    }

    @Override
    public void modifierEntite(Internship p) {
        String requet = "UPDATE internship SET period=?, startdate=?,typeinternship=?,technology=?,description=?,title=? WHERE id =?";
        try {
            PreparedStatement st = cnx2.prepareStatement(requet);
            st.setString(1, p.getPeriod());
            st.setString(2, p.getStartdate());
            st.setString(3,p.getTypeinternship());
            st.setString(4, p.getTechnology());
            st.setString(5, p.getDescription());
            st.setString(6, p.getTitle());
            st.setInt(7, p.getId());
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
    public void supprimerEntite(Internship p) {
        String requet = "DELETE FROM internship WHERE id =?";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requet);
            pst.setInt(1, p.getId());  // Assuming getQuizId() returns the Quiz ID
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

    public ResultSet Getall(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `internship` WHERE `departement_id`="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }

}

