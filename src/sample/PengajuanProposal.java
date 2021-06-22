package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PengajuanProposal {
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
    private Button btn_submitAll;

    @FXML
    private Label tfCV;

    @FXML
    private Label tfPortofolio;

    public PengajuanProposal() throws IOException {
    }

    @FXML
    public void uploadCV(ActionEvent event) {
        btn_UploadCV.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
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
                /*if (btn_submitAll.isManaged()){

                }*/
            }
        });
    }

    @FXML
    void uploadPortofolio(ActionEvent event) {
        btn_UploadPortofolio.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadPortofolio.getParent().getScene().getWindow());
            if(file != null){
                tfPortofolio.setText(file.getPath());
                String namaPortofolio = file.getName();
                Path lokasiFilePortofolio = lokasi.resolve(namaPortofolio);
                File writePorto = new File(String.valueOf(lokasiFilePortofolio));
                try {
                    Files.copy(file.toPath(), writePorto.toPath());
                } catch (IOException e){
                    e.printStackTrace();
                }
                /*if (btn_submitAll.isManaged()){

                }*/
            }
        });
    }

    @FXML
    void submitAll(ActionEvent event) {
        btn_submitAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(btn_submitAll.isPressed());
                //System.out.println(btn_submitAll.isManaged());
            }
        });
    }


}
