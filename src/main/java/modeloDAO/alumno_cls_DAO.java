/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import com.google.gson.Gson;
import modelo.alumno_cls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;






/**
 *
 * @author investigacion2018
 */
public class alumno_cls_DAO implements CRUD  {

    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    alumno_cls p=new alumno_cls();
    @Override
    public String  listar() {
        ArrayList<alumno_cls>list=new ArrayList<>();
        String sql="SELECT * FROM alumno";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                alumno_cls per=new alumno_cls();
                per.setId(rs.getInt("id"));
                per.setNombre(rs.getString("nombre"));
                per.setApellido(rs.getString("apellido"));
                per.setEdad(rs.getInt("edad"));
                per.setInstitucion(rs.getString("institucion"));
                
                
                
                /*per.setId(rs.getInt("Id"));
                per.setDni(rs.getString("DNI"));
                per.setNom(rs.getString("Nombres"));*/
                list.add(per);
            }
        } catch (Exception e) {
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
        
        //return list;
     }

    @Override
    public alumno_cls list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(alumno_cls per) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean edit(alumno_cls per) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
