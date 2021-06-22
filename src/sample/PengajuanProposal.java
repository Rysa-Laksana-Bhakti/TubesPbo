package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.sound.midi.Patch;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PengajuanProposal {
    FileChooser milih = new FileChooser();
    Patch lokasi = "D:\\";

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
    private Button btn_submitAll;

    @FXML
    private Label tfCV;

    @FXML
    private Label tfPortofolio;

    public PengajuanProposal() throws IOException {
    }

    @FXML
    void uploadCV(ActionEvent event) {
        btn_UploadCV.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadCV.getParent().getScene().getWindow());
            if(file != null){
                String namaFile = file.getName();
                Path lokasiFile = lokasi.resolve

                //File menulis = new File(lokasi);
                try {
                    Files.copy(file.toPath(), menulis.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tfCV.setText(file.getPath());
                System.out.println(file.getName());

            }
        });
    }

    @FXML
    void uploadPortofolio(ActionEvent event) {
        btn_UploadPortofolio.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadPortofolio.getParent().getScene().getWindow());
            if(file !=null){
                tfPortofolio.setText(file.getPath());

                System.out.println(file.getAbsoluteFile());
            }
        });
    }

    @FXML
    void submitAll(ActionEvent event) {
        btn_submitAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }


}
