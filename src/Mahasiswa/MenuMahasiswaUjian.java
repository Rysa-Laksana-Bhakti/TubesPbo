package Mahasiswa;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Main.mysqlconnect;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuMahasiswaUjian {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    FileChooser milih = new FileChooser();
    Path lokasi = Paths.get("D:\\");

    @FXML
    private Label tfNperusahaan;

    @FXML
    private Label tfLaporan;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfNim;

    @FXML
    private TextField tfEmail;

    @FXML
    private AnchorPane menuMahasiswa;

    @FXML
    private Button btn_uploadNilaiPerusahaan;

    @FXML
    private Button btn_uploadLaporan;

    @FXML
    private Button btn_submitAll;

    @FXML
    private Button btn_back;

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
    void submitAll(ActionEvent event) {
        btn_submitAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = mysqlconnect.ConnectDb();
                String sql = "insert into daftarujian (Nama,NIM,Email,NilaiPerusahaan,Laporan) values (?,?,?,?,?)";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, tfNama.getText());
                    pst.setString(2, tfNim.getText());
                    pst.setString(3, tfEmail.getText());
                    pst.setString(4, tfNperusahaan.getText());
                    pst.setString(5, tfLaporan.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Data telah disimpan");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data tidak disimpan"+" "+e);
                }
            }
        });

    }

    @FXML
    void uploadLaporan(ActionEvent event) {
        btn_uploadLaporan.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.setInitialDirectory(new File("D:\\"));
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_uploadLaporan.getParent().getScene().getWindow());
            if(file != null){
                tfLaporan.setText(file.getPath());
                String namaCV = file.getName();
                Path lokasiFileCV = lokasi.resolve(namaCV);
                File writeCV = new File(String.valueOf(lokasiFileCV));
                try {
                    Files.copy(file.toPath(), writeCV.toPath());
                } catch (IOException e){
                    e.printStackTrace();
                }
                tfLaporan.setText(file.getName());
            }
        });

    }

    @FXML
    void uploadNilaiPerusahaan(ActionEvent event) {
        btn_uploadNilaiPerusahaan.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.setInitialDirectory(new File("D:\\"));
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_uploadNilaiPerusahaan.getParent().getScene().getWindow());
            if(file != null){
                tfNperusahaan.setText(file.getPath());
                String namaCV = file.getName();
                Path lokasiFileCV = lokasi.resolve(namaCV);
                File writeCV = new File(String.valueOf(lokasiFileCV));
                try {
                    Files.copy(file.toPath(), writeCV.toPath());
                } catch (IOException e){
                    e.printStackTrace();
                }
                tfNperusahaan.setText(file.getName());
            }
        });
    }

}
