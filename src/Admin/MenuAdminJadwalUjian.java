package Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Main.DaftarUjian;
import Main.mysqlconnect;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class MenuAdminJadwalUjian implements Initializable {
    Connection conn = null;
    ResultSet rs = null;
    Statement pst = null;
    PreparedStatement pstt = null;

    @FXML
    private TableView<DaftarUjian> tvJadwalUjian;

    @FXML
    private TableColumn<DaftarUjian, Integer> colID;

    @FXML
    private TableColumn<DaftarUjian, String> colNama;

    @FXML
    private TableColumn<DaftarUjian, String> colNim;

    @FXML
    private TableColumn<DaftarUjian, String> colEmail;

    @FXML
    private TableColumn<DaftarUjian, String> colJadwal;

    @FXML
    private TableColumn<DaftarUjian, String> colNilai;

    @FXML
    private Button btn_back;

    @FXML
    private DatePicker tfWaktuJadwal;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfId;

    @FXML
    private Button btn_hapus;

    @FXML
    private Button btn_submitAll;

    @FXML
    private Button btn_lihatNilaiP;

    @FXML
    private Button btn_lihatLaporan;

    @FXML
    void back(ActionEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlClass/MenuAdmin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @FXML
    void hapus(ActionEvent event) {
        btn_hapus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = mysqlconnect.ConnectDb();
                String sql = "delete from daftarujian where ID=? ";
                try {
                    pstt = conn.prepareStatement(sql);
                    pstt.setString(1,tfId.getText());
                    pstt.execute();
                    showIDujian();
                    JOptionPane.showMessageDialog(null, "Data telah dihapus");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data tidak dihapus"+" "+e);
                }
            }
        });

    }

    @FXML
    void lihatLaporan(ActionEvent event) {
        btn_lihatLaporan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    conn = mysqlconnect.ConnectDb();

                    String id = tfId.getText();
                    String query = "SELECT * FROM daftarujian where ID = "+id;
                    pst = conn.createStatement();
                    rs = pst.executeQuery(query);
                    while(rs.next()){
                        String file = String.valueOf(rs.getString("Laporan"));
                        String encodeNama = file.replace(" ", "%20");
                        Desktop.getDesktop().browse(new URL("file:///D:/"+encodeNama).toURI());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    @FXML
    void lihatNilaiPer(ActionEvent event) {
        btn_lihatNilaiP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    conn = mysqlconnect.ConnectDb();

                    String id = tfId.getText();
                    String query = "SELECT * FROM daftarujian where ID = "+id;
                    pst = conn.createStatement();
                    rs = pst.executeQuery(query);
                    while(rs.next()){
                        String file = String.valueOf(rs.getString("NilaiPerusahaan"));
                        String encodeNama = file.replace(" ", "%20");
                        Desktop.getDesktop().browse(new URL("file:///D:/"+encodeNama).toURI());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

    }



    public ObservableList<DaftarUjian> getDaftarUjianList(){
        ObservableList<DaftarUjian>DaftarUjianList= FXCollections.observableArrayList();
        conn = mysqlconnect.ConnectDb();
        String query = "SELECT * FROM DaftarUjian";
        try{
            pst = conn.createStatement();
            rs = pst.executeQuery(query);
            DaftarUjian daftarUjian;
            while(rs.next()){
                daftarUjian = new DaftarUjian(rs.getInt("ID"), rs.getString("Nama"), rs.getString("NIM"), rs.getString("Email"),
                        rs.getString("waktuUjian"),rs.getString("Nilai"));
                DaftarUjianList.add(daftarUjian);
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return  DaftarUjianList;
    }

    public void showIDujian(){
        ObservableList<DaftarUjian> list = getDaftarUjianList();

        colID.setCellValueFactory(new PropertyValueFactory<DaftarUjian, Integer>("ID"));
        colNama.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("Nama"));
        colNim.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("NIM"));
        colEmail.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("Email"));
        colJadwal.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("waktuUjian"));
        colNilai.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("Nilai"));

        tvJadwalUjian.setItems(list);
    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
        DaftarUjian daftarMahasiswa = tvJadwalUjian.getSelectionModel().getSelectedItem();
        tfId.setText(""+daftarMahasiswa.getID());
        tfNama.setText(daftarMahasiswa.getNama());

            btn_submitAll.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    conn = mysqlconnect.ConnectDb();
                    String sql = "UPDATE daftarujian set waktuUjian=? where ID=? ";
                    try {
                        pstt = conn.prepareStatement(sql);
                        pstt.setString(1, tfWaktuJadwal.getValue().toString());
                        pstt.setString(2, tfId.getText());
                        pstt.execute();
                        showIDujian();
                        JOptionPane.showMessageDialog(null, "Data telah disimpan");

                        try {
                            String query = "SELECT * FROM daftarujian where ID = " + tfId.getText();

                            pst = conn.createStatement();
                            rs = pst.executeQuery(query);
                            while (rs.next()) {
                                Properties properties = new Properties();
                                properties.put("mail.smtp.auth", "true");
                                properties.put("mail.smtp.starttls.enable", "true");
                                properties.put("mail.smtp.host", "smtp.gmail.com");
                                properties.put("mail.smtp.port", "587");

                                String email = "email.noreply.bot@gmail.com";
                                String pass = "TubesMenyenangkan100%";
                                String penerima = String.valueOf(rs.getString("Email"));
                                ;

                                Session session = Session.getInstance(properties, new Authenticator() {
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(email, pass);
                                    }
                                });

                                try {
                                    Message message = prepareMessage(session, email, penerima);
                                    Transport.send(message);
                                    JOptionPane.showMessageDialog(null, "Terimakasih");
                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Data tidak disimpan"+" "+e);
                    }
                }
            });


        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showIDujian();

    }

    private Message prepareMessage(Session session, String email, String penerima) throws SQLException, MessagingException {
        Message message = new MimeMessage(session);
        try {
            conn = mysqlconnect.ConnectDb();

            String query = "SELECT * FROM daftarujian where ID = " + tfId.getText();

            pst = conn.createStatement();
            rs = pst.executeQuery(query);
            while (rs.next()) {
                String nama = String.valueOf(rs.getString("Nama"));
                String nim = String.valueOf(rs.getString("NIM"));
                String waktu = String.valueOf(rs.getString("waktuUjian"));

                message.setFrom(new InternetAddress(email));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(penerima));
                message.setSubject("Pengumuman Ujian");
                message.setText("Dengan ini diberitahukan kepada "+nama+" dengan NIM "+nim+" untuk mengikuti ujian pada "+waktu);
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
