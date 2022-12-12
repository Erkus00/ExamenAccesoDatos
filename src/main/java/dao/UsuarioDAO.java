package dao;

import models.Usuario;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    private Connection connection;

    /* Completar consultas */
    static final String INSERT_QUERY = "";
    static final String LIST_QUERY = "";
    static final String GET_BY_DNI = "";

    // Parametros de Conexion
    private static final String URL = "jdbc:mysql://localhost:3306/";
    // Contraseña
    private static final String PASS = "Erkus00";

    // Usuario
    private static final String USSER = "root";

    //Base de Datos
    private static final String DATABASE = "examen";


    public void connect() {
        try {
            connection = DriverManager.getConnection(URL + DATABASE, USSER, PASS);
            System.out.println("Conexion realizada con exito a '" + DATABASE + "'");
//            System.out.println("Base de datos conectada");
        } catch (Exception ex) {
            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }
    }

    public void close() {
        try {
            connection.close();
            System.out.println("Base de datos cerrada con Exito");
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");
        }
    }

    public void save(Usuario user) throws SQLException {

        /**
         * Completar código para guardar un usuario 
         * Este método no debe generar el id del usuario, ya que la base
         * de datos es la que lo genera.
         * Una vez obtenido el id del usuario tras la consulta sql,
         * hay que modificarlo en el objeto que se pasa como parámetro 
         * de forma que pase a tener el id correcto.
         */

        String sql_query = "INSERT INTO usuario(nombre,apellidos,dni) VALUES (?,?,?)";
        try (PreparedStatement pst = connection.prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getDni());
            pst.executeUpdate();

            ResultSet generated_keys = pst.getGeneratedKeys();
            if (generated_keys.next()) {
                Integer id = generated_keys.getInt(1);
                user.setId(Long.valueOf(id));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        if (user.getId() != null) {
            System.out.println("Usuario nuevo añadido con la Id :" + user.getId());
        }

    }

    public ArrayList<Usuario> list() throws SQLException {

        var salida = new ArrayList<Usuario>(0);
        Usuario user;
        String sql_query="SELECT DISTINCT(id) FROM usuario";
        try(PreparedStatement pst=connection.prepareStatement(sql_query)){
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                user=infoUsuario(rs.getLong("id"));
                salida.add(user);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }


        return salida;
    }

    // Metodo de Apoyo para obtener un Usuario
    Usuario infoUsuario(Long id){
        Usuario us = new Usuario();
        String sql_query="SELECT * FROM usuario WHERE id=?";
        try(PreparedStatement pst=connection.prepareStatement(sql_query)){
            pst.setLong(1,id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                us.setId(id);
                us.setNombre(rs.getString("nombre"));
                us.setApellidos(rs.getString("apellidos"));
                us.setDni(rs.getString("dni"));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return us;
    }

    public Usuario getByDNI(String dni) {

        Usuario salida = new Usuario();

        /**
         * Completar código para devolver el usuario que tenga ese dni.
         * Si no existe, se debe devolver null
         */
        String sql_query = "SELECT DISTINCT id FROM usuario WHERE dni=?";
        try(PreparedStatement pst = connection.prepareStatement(sql_query)) {
            pst.setString(1,dni);

            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                Usuario user_temp = infoUsuario(rst.getLong("id"));
                if(user_temp!=null){
                    salida=user_temp;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return salida;
    }
}
