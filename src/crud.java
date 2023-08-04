import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class crud {
    private JPanel registro;
    private JTextField codigot;
    private JTextField cedulat;
    private JTextField nombret;
    private JTextField fechat;
    private JButton buscarcod;
    private JButton buscarPorNombreButton;
    private JButton buscarPorSignoButton;
    private JComboBox signot;
    private JButton borrarElPresenteRegistroButton;
    private JButton actualizarElPresenteRegistroButton;
    private JButton ingresarElPresenteRegistroButton;
    private JButton limpiarElFormularioButton;
    private JLabel codigoL;
    private JLabel cedulaL;
    private JLabel nombreL;
    private JLabel fechal;
    private JLabel signoL;
    static final String DB_URL = "jdbc:mysql://localhost/POOPRUEBA2";
    static final String USER = "root";
    static final String PASS = "root_bas3";

    public crud() {
        estudiante Est1 = new estudiante(0, 0,"","","");
        limpiarElFormularioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigot.setText(" ");
                cedulat.setText(" ");
                nombret.setText(" ");
                fechat.setText(" ");
                signot.setSelectedItem("...");
            }
        });
        ingresarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Est1.setCod(Integer.parseInt(codigot.getText()));
                Est1.setCed(Integer.parseInt(cedulat.getText()));
                Est1.setNombre(nombret.getText());
                Est1.setFecha(fechat.getText());
                Est1.setSigno(signot.getSelectedItem().toString());

                try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASS))
                {
                    String SQL_QUERY = "INSERT INTO est_prueba2 (codigo_est, cedula, nombre, fecha_nac, signo) VALUES (?,?,?,?,?)";
                    try(PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY)){
                        pstmt.setInt(1,Est1.getCod());
                        pstmt.setInt(2,Est1.getCed());
                        pstmt.setString(3,Est1.getNombre());
                        pstmt.setString(4,Est1.getFecha());
                        pstmt.setString(5,Est1.getSigno());

                        int filasInsertadas = pstmt.executeUpdate();
                        if (filasInsertadas > 0){
                            JOptionPane.showMessageDialog(registro,"Estudiante registrado con exito.");
                        } else {
                            JOptionPane.showMessageDialog(registro,"Error al registrar al estudiante.");
                        }
                    }

                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        buscarcod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cod = Integer.parseInt(codigot.getText());
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String SQL_Query_select = "SELECT * FROM est_prueba2 WHERE codigo_est = ?";
                    try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                        pstm.setInt(1,cod);
                        try (ResultSet rs = pstm.executeQuery()){
                            if (rs.next()){
                                int cedula = rs.getInt("cedula");
                                String nombre = rs.getString("nombre");
                                String fechanac= rs.getString("fecha_nac");
                                String signoz=rs.getString("signo");


                                cedulat.setText(String.valueOf(cedula));
                                nombret.setText(nombre);
                                fechat.setText(fechanac);
                                signot.setSelectedItem(signoz);

                                JOptionPane.showMessageDialog(registro,"Estudiante encontrado");
                            } else {
                                JOptionPane.showMessageDialog(registro,"No existe o no se encontro");
                                codigot.setText(" ");
                                cedulat.setText(" ");
                                nombret.setText(" ");
                                fechat.setText(" ");
                                signot.setSelectedItem("...");
                            }
                        }
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombret.getText();
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String SQL_Query_select = "SELECT * FROM est_prueba2 WHERE nombre like ?";
                    try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                        pstm.setString(1,nombre);
                        try (ResultSet rs = pstm.executeQuery()){
                            if (rs.next()){
                                int codigo= rs.getInt("codigo_est");
                                int cedula = rs.getInt("cedula");
                                nombre = rs.getString("nombre");
                                String fechanac= rs.getString("fecha_nac");
                                String signoz=rs.getString("signo");

                                codigot.setText(String.valueOf(codigo));
                                cedulat.setText(String.valueOf(cedula));
                                nombret.setText(nombre);
                                fechat.setText(fechanac);
                                signot.setSelectedItem(signoz);

                                JOptionPane.showMessageDialog(registro,"Estudiante encontrado");
                            } else {
                                JOptionPane.showMessageDialog(registro,"No existe o no se encontro");
                                codigot.setText(" ");
                                cedulat.setText(" ");
                                nombret.setText(" ");
                                fechat.setText(" ");
                                signot.setSelectedItem("...");
                            }
                        }
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        buscarPorSignoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sigz= signot.getSelectedItem().toString();
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String SQL_Query_select = "SELECT * FROM est_prueba2 WHERE signo like ?";
                    try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                        pstm.setString(1,sigz);
                        try (ResultSet rs = pstm.executeQuery()){
                            if (rs.next()){
                                int codigo= rs.getInt("codigo_est");
                                int cedula = rs.getInt("cedula");
                                String nombre = rs.getString("nombre");
                                String fechanac= rs.getString("fecha_nac");

                                codigot.setText(String.valueOf(codigo));
                                cedulat.setText(String.valueOf(cedula));
                                nombret.setText(nombre);
                                fechat.setText(fechanac);

                                JOptionPane.showMessageDialog(registro,"Estudiante encontrado");
                            } else {
                                JOptionPane.showMessageDialog(registro,"No existe o no se encontro");
                                codigot.setText(" ");
                                cedulat.setText(" ");
                                nombret.setText(" ");
                                fechat.setText(" ");
                                signot.setSelectedItem("...");
                            }
                        }
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        actualizarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cod = Integer.parseInt(codigot.getText());
                int cedula = Integer.parseInt(cedulat.getText());
                String nombre = nombret.getText();
                String fechanac= fechat.getText();
                String sigz= signot.getSelectedItem().toString();

                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)){
                    String SQL_QUERY_UPDATE = "UPDATE est_prueba2 SET cedula=?, nombre=?, fecha_nac=?, signo=?  WHERE codigo_est=?";
                    try(PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY_UPDATE)){
                        pstmt.setInt(1,cedula);
                        pstmt.setString(2,nombre);
                        pstmt.setString(3,fechanac);
                        pstmt.setString(4,sigz);
                        pstmt.setInt(5,cod);

                        int filasActualizadas = pstmt.executeUpdate();
                        if (filasActualizadas>0){
                            JOptionPane.showMessageDialog(registro,"Estudiante Actualizado");
                            codigot.setText(" ");
                            cedulat.setText(" ");
                            nombret.setText(" ");
                            fechat.setText(" ");
                            signot.setSelectedItem("...");
                        } else {
                            JOptionPane.showMessageDialog(registro,"Error al actualizar !");
                            codigot.setText(" ");
                            cedulat.setText(" ");
                            nombret.setText(" ");
                            fechat.setText(" ");
                            signot.setSelectedItem("...");
                        }
                    }
                }catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        borrarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cod = Integer.parseInt(codigot.getText());

                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)){
                    String SQL_QUERY_DELETE = "DELETE FROM est_prueba2 WHERE codigo_est=?";
                    try(PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY_DELETE)){
                        pstmt.setInt(1,cod);
                        int filasEliminadas = pstmt.executeUpdate();
                        if (filasEliminadas > 0){
                            JOptionPane.showMessageDialog(registro,"Estudiante eliminado con exito!");
                            codigot.setText(" ");
                            cedulat.setText(" ");
                            nombret.setText(" ");
                            fechat.setText(" ");
                            signot.setSelectedItem("...");
                        } else {
                            JOptionPane.showMessageDialog(registro,"No se encontro el estudiante !");
                        }
                    }

                }catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("crud");
        frame.setContentPane(new crud().registro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
