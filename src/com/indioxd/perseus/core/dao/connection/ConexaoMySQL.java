/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.indioxd.perseus.core.dao.connection;

 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Huellinton.Mota
 */
public class ConexaoMySQL {
    
    private static final String urlDb = "jdbc:mysql://localhost:3306/perseus_db";
    private static final String userDb = "root";
    private static final String passDb = "root";
    
    private static Connection conn;
    
    public static Connection getConexao(){
        try {
            if(conn ==null){
                conn = DriverManager.getConnection(urlDb, userDb, passDb);
                return conn;
            }else{
                return conn;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /* TESTE
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(urlDb, userDb, passDb);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nome_usuario FROM usuario");
            while(rs.next()){
                System.out.println(rs.getString("nome_usuario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */

}
