package service;



import model.Formateur;
import model.Matiere;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatiereService {
    Database db;
    public MatiereService() throws Exception {
        db = Database.getInstance();
    }

    public List<Matiere> findAll() throws Exception {
        List<Matiere> matieres = new ArrayList<>();
        try {
            String sql = "SELECT * FROM matiere";
            db.prepareStatement(sql);
            ResultSet rs = db.executeQuery();
            while (rs.next()){
                Matiere m = new Matiere();
                m.setId(rs.getInt("id"));
                m.setLibelle(rs.getString("libelle"));
                matieres.add(m);
            }
            rs.close();
            return matieres;
        }catch (Exception ex){
            throw ex;
        }
    }
}
