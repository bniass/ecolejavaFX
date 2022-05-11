package service;



import model.Formateur;
import model.Matiere;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FormateurService {
    Database db;
    public FormateurService() throws Exception {
        db = Database.getInstance();
    }

    public void save(Formateur formateur) throws Exception{
        try {
            String sql = "INSERT INTO formateur values(null,?,?,?,?,?, ?)";
            //préparer la requette
            db.prepareStatement(sql);
            // passage des paramètres
            Object[] parameters = {formateur.getNom(), formateur.getPrenom(),
                    formateur.getEmail(), formateur.getSalaire(),
                    formateur.getDateNaissance(),formateur.getMatiere().getId()};
            db.addParameters(parameters);

            //executer la requette
            db.executeUpdate();
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public List<Formateur> findAll() throws Exception {
        List<Formateur> formateurs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM formateur f, matiere m where m.id = f.matiere_id";
            db.prepareStatement(sql);
            ResultSet rs = db.executeQuery();
            while (rs.next()){
                Formateur f = new Formateur();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setPrenom(rs.getString("prenom"));
                f.setEmail(rs.getString("email"));
                f.setSalaire(rs.getInt("salaire"));
                f.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
                Matiere m = new Matiere();
                m.setId(rs.getInt("matiere_id"));
                m.setLibelle(rs.getString("libelle"));
                f.setMatiere(m);
                formateurs.add(f);
            }
            rs.close();
            return formateurs;
        }catch (Exception ex){
            throw ex;
        }
    }
}
