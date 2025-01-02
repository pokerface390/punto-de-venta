/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesEstaticas;

import Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccederUsuarios {

    // Método para insertar un nuevo usuario
    public boolean insertarUsuario(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, String puesto, String contrasena) {
        Connection conexion = ConexionDB.getConnection();
        try {
            String sql = "INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, correo_electronico, telefono, puesto, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidoPaterno);
            ps.setString(3, apellidoMaterno);
            ps.setString(4, correoElectronico);
            ps.setString(5, telefono);
            ps.setString(6, puesto);
            ps.setString(7, contrasena);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String obtenerContrasenaPorCorreo(String correoElectronico) {
        Connection conexion = ConexionDB.getConnection();
        try {
            String sql = "SELECT contrasena FROM Usuarios WHERE correo_electronico = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, correoElectronico);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("contrasena");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String obtenerContrasena(int usuarioId) {
        Connection conexion = ConexionDB.getConnection();
        try {
            String sql = "SELECT contrasena FROM Usuarios WHERE usuario_id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("contrasena");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Método para obtener el correo electrónico de un usuario por su ID
    public String obtenerCorreoElectronico(int usuarioId) {
        Connection conexion = ConexionDB.getConnection();
        try {
            String sql = "SELECT correo_electronico FROM Usuarios WHERE usuario_id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("correo_electronico");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Método para modificar un usuario existente
    public boolean modificarUsuario(int usuarioId, String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, String puesto, String contrasena) {
        Connection conexion = ConexionDB.getConnection();
        try {
            String sql = "UPDATE Usuarios SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, correo_electronico = ?, telefono = ?, puesto = ?, contrasena = ? WHERE usuario_id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidoPaterno);
            ps.setString(3, apellidoMaterno);
            ps.setString(4, correoElectronico);
            ps.setString(5, telefono);
            ps.setString(6, puesto);
            ps.setString(7, contrasena);
            ps.setInt(8, usuarioId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean eliminarUsuario(int usuarioId) {
        Connection conexion = ConexionDB.getConnection();
        try {
            String sql = "DELETE FROM Usuarios WHERE usuario_id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
