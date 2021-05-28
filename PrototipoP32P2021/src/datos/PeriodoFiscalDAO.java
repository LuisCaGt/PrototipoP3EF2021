/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Finanzas.dominio.PeriodoFiscal;

/**
 *
 * @author OtakuGT
 */
public class PeriodoFiscalDAO extends Conexion1 {
    
    private static final String SQL_SELECT = "SELECT * FROM periodofiscal";
    private static final String SQL_INSERT = "INSERT INTO periodofiscal(Codigo_PeriodoFiscal, Fecha_inicioPF, Fecha_finPF, Estado_PeriodoFiscal) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE periodofiscal SET Codigo_PeriodoFiscal=?, Fecha_inicioPF=?, Fecha_finPF=?, Estado_PeriodoFiscal=? WHERE Codigo_PeriodoFiscal = ?";
    private static final String SQL_DELETE = "DELETE FROM periodofiscal WHERE Codigo_PeriodoFiscal=?";
    private static final String SQL_QUERY = "SELECT Codigo_PeriodoFiscal, Fecha_inicioPF, Fecha_finPF, Estado_PeriodoFiscal FROM periodofiscal WHERE Codigo_PeriodoFiscal = ?";

    Conexion1 conectar = new Conexion1();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    PeriodoFiscal PerFis = null;

    public List<PeriodoFiscal> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PeriodoFiscal PF = null;
        List<PeriodoFiscal> PerFis = new ArrayList<PeriodoFiscal>();
        try {
            conn = Conexion1.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id_PF = rs.getString("Codigo_PeriodoFiscal");
                String inicioPF = rs.getString("Fecha_inicioPF");
                String finPF = rs.getString("Fecha_finPF");
                int EstadoPF = rs.getInt("Estado_PeriodoFiscal");


                PF = new PeriodoFiscal();
                PF.setIDPerFis(id_PF);
                PF.setInicioAñoPerFis(inicioPF);
                PF.setFinAñoPerFis(finPF);
                PF.setEstadoPerFis(EstadoPF);

                PerFis.add(PF);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion1.close(rs);
            Conexion1.close(stmt);
            Conexion1.close(conn);
        }

        return PerFis;
    }

    public PeriodoFiscal query(PeriodoFiscal PeriodoFiscal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion1.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, PeriodoFiscal.getIDPerFis());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String idPF = rs.getString("Codigo_PeriodoFiscal");
                String IniPF = rs.getString("Fecha_inicioPF");
                String FinPF = rs.getString("Fecha_finPF");
                int EstadoPF = rs.getInt("Estado_PeriodoFiscal");

                PeriodoFiscal = new PeriodoFiscal();
                PeriodoFiscal.setIDPerFis(idPF);
                PeriodoFiscal.setInicioAñoPerFis(IniPF);
                PeriodoFiscal.setFinAñoPerFis(FinPF);
                PeriodoFiscal.setEstadoPerFis(EstadoPF);

                rows++;
            }
            System.out.println("Registros buscado:" + PeriodoFiscal);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion1.close(rs);
            Conexion1.close(stmt);
            Conexion1.close(conn);
        }

        return PeriodoFiscal;
    }

    public int delete(PeriodoFiscal PeriodoFiscal) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion1.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, PeriodoFiscal.getIDPerFis());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion1.close(stmt);
            Conexion1.close(conn);
        }

        return rows;
    }

    public int update(PeriodoFiscal PeriodoFiscal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion1.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, PeriodoFiscal.getIDPerFis());
            stmt.setString(2, PeriodoFiscal.getInicioAñoPerFis());
            stmt.setString(3, PeriodoFiscal.getFinAñoPerFis());
            stmt.setInt(4, PeriodoFiscal.getEstadoPerFis());
            stmt.setString(5, PeriodoFiscal.getIDPerFis());
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion1.close(stmt);
            Conexion1.close(conn);
        }

        return rows;
    }

    public int insert(PeriodoFiscal PeriodoFiscal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion1.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, PeriodoFiscal.getIDPerFis());
            stmt.setString(2,PeriodoFiscal.getInicioAñoPerFis());
            stmt.setString(3, PeriodoFiscal.getFinAñoPerFis());
            stmt.setInt(4, PeriodoFiscal.getEstadoPerFis());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            Conexion1.close(stmt);
            Conexion1.close(conn);
        }
        return 1;
    }
}