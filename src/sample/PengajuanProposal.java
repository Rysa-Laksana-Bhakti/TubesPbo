package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;

public class PengajuanProposal {
    FileChooser milih = new FileChooser();

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


    @FXML
    void submitAll(ActionEvent event) {

    }

    @FXML
    void uploadCV(ActionEvent event) {
        btn_UploadCV.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadCV.getParent().getScene().getWindow());
            if(file !=null){
                tfCV.setText(file.getPath());
            }
            if (btn_submitAll.isPressed()){

            }
        });
    }

    @FXML
    void uploadPortofolio(ActionEvent event) {
        btn_UploadPortofolio.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.txt)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadPortofolio.getParent().getScene().getWindow());
            if(file !=null){
                tfPortofolio.setText(file.getPath());
            }
        });
    }


}
