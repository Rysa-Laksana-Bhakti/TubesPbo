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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Main.DataMahasiswa;
import Main.mysqlconnect;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class MenuAdminDataMah implements Initializable {
    Connection conn = null;
    ResultSet rs = null;
    Statement pst = null;
    PreparedStatement pstt = null;

    @FXML
    private AnchorPane paneDatamahasiswa;
    @FXML
    private AnchorPane paneTabel;

    @FXML
    private TableView<DataMahasiswa> tvDataMahasiswa;

    @FXML
    private TableColumn<DataMahasiswa, Integer> colId;

    @FXML
    private TableColumn<DataMahasiswa, String> colNamaAnggota;

    @FXML
    private TableColumn<DataMahasiswa, String> colLokasi;

    @FXML
    private TableColumn<DataMahasiswa, String> colAwal;

    @FXML
    private TableColumn<DataMahasiswa, String> colAkhir;

    @FXML
    private TableColumn<DataMahasiswa, String> colPersetujuan;


    @FXML
    private Button btn_back;

    @FXML
    private Button btn_lihatCV;

    @FXML
    private Button btn_lihatPortofolio;

    @FXML
    private TextField taId;

    @FXML
    private TextArea taAnggotaKelompok;

    @FXML
    private TextArea taLokasi;

    @FXML
    private TextField taWaktuAwal;

    @FXML
    private TextField taWaktuAkhir;

    @FXML
    private Button btn_backward;

    @FXML
    private Button btn_forward;

    @FXML
    private TextField tfPersetujuan;

    @FXML
    private Button btn_submitAll;

    @FXML
    private Button btn_hapus;

    @FXML
    private TextField taId1;


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
    void backward(ActionEvent event) {
        if (btn_backward.isManaged()) {
            conn = mysqlconnect.ConnectDb();
            int id = Integer.parseInt(taId.getText());
            int hitung = id - 1;
            String back = String.valueOf(hitung);

            String query = "SELECT * FROM datamahasiswa where idKel = " + back;
            try {
                pst = conn.createStatement();
                rs = pst.executeQuery(query);
                while (rs.next()) {
                    taId.setText(String.valueOf(rs.getInt("idKel")));
                    taAnggotaKelompok.setText(String.valueOf(rs.getString("anggotaKel")));
                    taLokasi.setText(String.valueOf(rs.getString("alamatKel")));
                    taWaktuAwal.setText(String.valueOf(rs.getString("waktuAwal")));
                    taWaktuAkhir.setText(String.valueOf(rs.getString("waktuAkhir")));
                    tfPersetujuan.setText(String.valueOf(rs.getString("Persetujuan")));
                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    @FXML
    void forward(ActionEvent event) {
        if (btn_forward.isManaged()) {
            conn = mysqlconnect.ConnectDb();

            try {
                int id = Integer.parseInt(taId.getText());
                int hitung = id + 1;
                String next = String.valueOf(hitung);

                String query = "SELECT * FROM datamahasiswa where idKel = " + next;

                pst = conn.createStatement();
                rs = pst.executeQuery(query);
                while (rs.next()) {
                    taId.setText(String.valueOf(rs.getInt("idKel")));
                    taAnggotaKelompok.setText(String.valueOf(rs.getString("anggotaKel")));
                    taLokasi.setText(String.valueOf(rs.getString("alamatKel")));
                    taWaktuAwal.setText(String.valueOf(rs.getString("waktuAwal")));
                    taWaktuAkhir.setText(String.valueOf(rs.getString("waktuAkhir")));
                    tfPersetujuan.setText(String.valueOf(rs.getString("Persetujuan")));
                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    @FXML
    void submitAll(ActionEvent event) {
        btn_submitAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = mysqlconnect.ConnectDb();
                String sql = "UPDATE datamahasiswa set Persetujuan=? where idKel=? ";
                try {
                    pstt = conn.prepareStatement(sql);
                    pstt.setString(1, typeKeputusan.getValue().toString());
                    pstt.setString(2, taId.getText());
                    pstt.execute();
                    showID();

                    if (typeKeputusan.getValue().toString() == "Approve") {
                        try {
                            conn = mysqlconnect.ConnectDb();

                            String query = "SELECT * FROM datamahasiswa where idKel = " + taId.getText();

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
                                String penerima = String.valueOf(rs.getString("mail"));
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
                    } else {
                        JOptionPane.showMessageDialog(null, "Terimakasih");
                    }
                    JOptionPane.showMessageDialog(null, "Data telah disimpan");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data tidak disimpan" + " " + e);
                }
            }
        });
    }

    @FXML
    void lihatPortofolio(ActionEvent event) {
        btn_lihatPortofolio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    conn = mysqlconnect.ConnectDb();

                    String id = taId.getText();
                    String query = "SELECT * FROM datamahasiswa where idKel = " + id;
                    pst = conn.createStatement();
                    rs = pst.executeQuery(query);
                    while (rs.next()) {
                        String file = String.valueOf(rs.getString("namaFilePorto"));
                        String encodeNama = file.replace(" ", "%20");
                        Desktop.getDesktop().browse(new URL("file:///D:/" + encodeNama).toURI());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    void lihatCV(ActionEvent event) {
        btn_lihatCV.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    conn = mysqlconnect.ConnectDb();

                    String id = taId.getText();
                    String query = "SELECT * FROM datamahasiswa where idKel = " + id;
                    pst = conn.createStatement();
                    rs = pst.executeQuery(query);
                    while (rs.next()) {
                        String file = String.valueOf(rs.getString("namaFileCV"));
                        String encodeNama = file.replace(" ", "%20");
                        Desktop.getDesktop().browse(new URL("file:///D:/" + encodeNama).toURI());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public ObservableList<DataMahasiswa> getDataMahasiswaList() {
        ObservableList<DataMahasiswa> DataMahasiswaList = FXCollections.observableArrayList();
        conn = mysqlconnect.ConnectDb();
        String query = "SELECT * FROM DataMahasiswa";


        try {
            pst = conn.createStatement();
            rs = pst.executeQuery(query);
            DataMahasiswa datamahasiswa;
            while (rs.next()) {
                datamahasiswa = new DataMahasiswa(rs.getInt("idKel"), rs.getString("anggotaKel"), rs.getString("alamatKel"), rs.getString("waktuAwal"),
                        rs.getString("waktuAkhir"), rs.getString("Persetujuan"));
                DataMahasiswaList.add(datamahasiswa);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return DataMahasiswaList;
    }

    @FXML
    void hapus(ActionEvent event) {
        btn_hapus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = mysqlconnect.ConnectDb();
                String sql = "delete from datamahasiswa where idKel=? ";
                try {
                    pstt = conn.prepareStatement(sql);
                    pstt.setString(1, taId1.getText());
                    pstt.execute();
                    showID();
                    JOptionPane.showMessageDialog(null, "Data telah dihapus");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data tidak dihapus" + " " + e);
                }
            }
        });

    }

    public void showID() {
        ObservableList<DataMahasiswa> list = getDataMahasiswaList();

        colId.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, Integer>("idKel"));
        colNamaAnggota.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("anggotaKel"));
        colLokasi.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("alamatKel"));
        colAwal.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("waktuAwal"));
        colAkhir.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("waktuAkhir"));
        colPersetujuan.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("Persetujuan"));


        tvDataMahasiswa.setItems(list);
    }


    public void DatapaneShow() {
        paneDatamahasiswa.setVisible(true);
        paneTabel.setVisible(false);
    }

    public void TabelShow() {
        paneDatamahasiswa.setVisible(false);
        paneTabel.setVisible(true);
        taId.setText("0");
        taAnggotaKelompok.setText(null);
        taLokasi.setText(null);
        taWaktuAwal.setText(null);
        taWaktuAkhir.setText(null);
        tfPersetujuan.setText(null);

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        DataMahasiswa dataMahasiswa = tvDataMahasiswa.getSelectionModel().getSelectedItem();
        taId.setText("" + dataMahasiswa.getIdKel());
        taAnggotaKelompok.setText(dataMahasiswa.getAnggotaKel());
        taLokasi.setText(dataMahasiswa.getAlamatKel());
        taWaktuAwal.setText(dataMahasiswa.getWaktuAwal());
        taWaktuAkhir.setText(dataMahasiswa.getWaktuAkhir());
        tfPersetujuan.setText(dataMahasiswa.getPersetujuan());
        taId1.setText("" + dataMahasiswa.getIdKel());
    }

    @FXML
    private ComboBox typeKeputusan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showID();

        typeKeputusan.getItems().addAll("Tunggu", "Approve", "Decline");
    }

    private Message prepareMessage(Session session, String email, String penerima) throws SQLException, MessagingException {
        Message message = new MimeMessage(session);
        try {
            conn = mysqlconnect.ConnectDb();

            String query = "SELECT * FROM datamahasiswa where idKel = " + taId.getText();

            pst = conn.createStatement();
            rs = pst.executeQuery(query);
            while (rs.next()) {
                String anggotaKelompok = String.valueOf(rs.getString("anggotaKel"));
                String alamat = String.valueOf(rs.getString("alamatKel"));
                String waktuA = String.valueOf(rs.getString("waktuAwal"));
                String waktuB = String.valueOf(rs.getString("waktuAkhir"));
                String filePortofolio = String.valueOf(rs.getString("namaFilePorto"));
                String fileCV = String.valueOf(rs.getString("namaFileCV"));

                message.setFrom(new InternetAddress(email));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(penerima));
                message.setSubject("Pengajuan Proposal Mahasiswa");
                BodyPart isiEmail = new MimeBodyPart();
                isiEmail.setText("Anggota kelompok : \n" + anggotaKelompok + "\nLokasi : " + alamat + "\nWaktu pelaksanaan : " + waktuA + " Sampai " + waktuB);

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(isiEmail);

                isiEmail = new MimeBodyPart();
                String namaPorto = "D:\\" + filePortofolio;
                DataSource porto = new FileDataSource(namaPorto);
                isiEmail.setDataHandler(new DataHandler(porto));
                isiEmail.setFileName(filePortofolio);
                multipart.addBodyPart(isiEmail);

                isiEmail = new MimeBodyPart();
                String namaCV = "D:\\" + fileCV;
                DataSource cv = new FileDataSource(namaCV);
                isiEmail.setDataHandler(new DataHandler(cv));
                isiEmail.setFileName(fileCV);
                multipart.addBodyPart(isiEmail);

                message.setContent(multipart);
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


