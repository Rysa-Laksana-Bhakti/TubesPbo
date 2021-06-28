package Mahasiswa;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MenuMahasiswaProposal {
    Connection conn = null;
    PreparedStatement pst = null;
    FileChooser milih = new FileChooser();
    Path lokasi = Paths.get("D:\\");

    @FXML
    private AnchorPane menuProposal;

    @FXML
    private Button btn_UploadCV;

    @FXML
    private Button btn_UploadPortofolio;

    @FXML
    private TextArea fieldAlamatPKN;

    @FXML
    private DatePicker fieldWaktuPKN;

    @FXML
    private DatePicker fieldWaktuPKN2;

    @FXML
    private Button btn_submitAll;

    @FXML
    private Label tfCV;

    @FXML
    private Label tfPortofolio;

    @FXML
    private Button btn_back;

    @FXML
    private TextArea AnggotaKelompok;

    public MenuMahasiswaProposal(){
    }

    @FXML
    void uploadCV(ActionEvent event) {
        btn_UploadCV.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.setInitialDirectory(new File("D:\\"));
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadCV.getParent().getScene().getWindow());
            if(file != null){
                tfCV.setText(file.getPath());
                String namaCV = file.getName();
                Path lokasiFileCV = lokasi.resolve(namaCV);
                File writeCV = new File(String.valueOf(lokasiFileCV));
                try {
                    Files.copy(file.toPath(), writeCV.toPath());
                } catch (IOException e){
                    e.printStackTrace();
                }
                tfCV.setText(file.getName());
            }
        });
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlClass/MenuMahasiswa.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @FXML
    void uploadPortofolio(ActionEvent event) {
        btn_UploadPortofolio.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadPortofolio.getParent().getScene().getWindow());
            if(file != null){
                tfPortofolio.setText(file.getPath());
                String namaPorto = file.getName();
                Path lokasiFilePorto = lokasi.resolve(namaPorto);
                File writePorto = new File(String.valueOf(lokasiFilePorto));
                try {
                    Files.copy(file.toPath(), writePorto.toPath());
                } catch (IOException e){
                    e.printStackTrace();
                }
                tfPortofolio.setText(file.getName());
            }
        });
    }

    @FXML
    void submitAll(ActionEvent event) {
        btn_submitAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = mysqlconnect.ConnectDb();
                String sql = "insert into dataMahasiswa (anggotaKel,alamatKel,waktuAwal,waktuAkhir,namaFileCV,namaFilePorto) values (?,?,?,?,?,?)";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, AnggotaKelompok.getText());
                    pst.setString(2, fieldAlamatPKN.getText());
                    pst.setString(3, fieldWaktuPKN.getValue().toString());
                    pst.setString(4, fieldWaktuPKN2.getValue().toString());
                    pst.setString(5, tfCV.getText());
                    pst.setString(6, tfPortofolio.getText());
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Data telah disimpan");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data tidak disimpan");
                }

                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                String email = "email.noreply.bot@gmail.com";
                String pass = "TubesMenyenangkan100%";
                String penerima = "irfan64bit@gmail.com";

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, pass);
                    }
                });

                Message message = prepareMessage(session, email, penerima);
                try {
                    Transport.send(message);
                    JOptionPane.showMessageDialog(null, "Terimakasih");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            private Message prepareMessage(Session session, String email, String penerima){
                Message message = new MimeMessage(session);
                try {
                    message.setFrom(new InternetAddress(email));
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(penerima));
                    message.setSubject("Pengajuan Proposal Mahasiswa");
                    BodyPart isiEmail = new MimeBodyPart();
                    isiEmail.setText(AnggotaKelompok.getText());

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(isiEmail);

                    isiEmail = new MimeBodyPart();
                    String namaPorto = "D:\\"+tfPortofolio.getText();
                    DataSource porto = new FileDataSource(namaPorto);
                    isiEmail.setDataHandler(new DataHandler(porto));
                    isiEmail.setFileName(tfPortofolio.getText());
                    multipart.addBodyPart(isiEmail);

                    isiEmail = new MimeBodyPart();
                    String namaCV = "D:\\"+tfCV.getText();
                    DataSource cv = new FileDataSource(namaCV);
                    isiEmail.setDataHandler(new DataHandler(cv));
                    isiEmail.setFileName(tfCV.getText());
                    multipart.addBodyPart(isiEmail);

                    message.setContent(multipart);
                    return message;
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
