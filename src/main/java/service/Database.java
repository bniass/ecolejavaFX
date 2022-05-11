package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    Connection cnx = null;
    PreparedStatement pstmt = null;
    private static Database db;
    public static Database getInstance() throws Exception {
        if(db == null){
            db = new Database();
        }
        return db;
    }
    // constructeur qui établit la cnnexion à la base de données
    private Database() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ugb","root","");
        }
        catch(Exception ex){
            throw ex;
        }
    }
    // désactiver l'autocomit des requete sql
    public void beginTransaction()throws Exception{
        try {
            cnx.setAutoCommit(false);
        }catch (Exception ex){
            throw ex;
        }
    }
    //commit des transactions
    public void commit()throws Exception{
        try {
            cnx.commit();
        }catch (Exception ex){
            throw ex;
        }
    }
    //rollback des transactions
    public void rollback()throws Exception{
        try {
            cnx.rollback();
        }catch (Exception ex){
            throw ex;
        }
    }
    // activer l'autocomit des requete sql
    public void endTransaction()throws Exception{
        try {
            cnx.setAutoCommit(true);
        }catch (Exception ex){
            throw ex;
        }
    }

    //préparation de la requete
    public void prepareStatement(String sql) throws Exception{
        try{
            pstmt = cnx.prepareStatement(sql);
        }
        catch(Exception ex){
            throw ex;
        }
    }
    //ajout des paramètres de la requete
    public void addParameters(Object[] parameters) throws Exception{
        try{
            for (int i=0; i < parameters.length; i++) {
                pstmt.setObject((i+1), parameters[i]);
            }
        }
        catch(Exception ex){
            throw ex;
        }
    }
    //Executer des requete MAJ
    public int executeUpdate() throws Exception{
        int n = 0;
        try{
            return pstmt.executeUpdate();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    // executer des requetes selection
    public ResultSet executeQuery() throws Exception{
        ResultSet rs = null;
        try{
            return pstmt.executeQuery();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    // fermer la connexion
    public void CloseConnection() throws Exception{
        try{
            if(!pstmt.isClosed())
                pstmt.close();
            if(!cnx.isClosed())
                cnx.close();
        }
        catch(Exception ex){
            throw ex;
        }
    }

}
