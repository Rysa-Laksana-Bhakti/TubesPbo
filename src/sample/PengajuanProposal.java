package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

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
    void submitAll(ActionEvent event) {

    }

    @FXML
    void uploadCV(ActionEvent event) {
        btn_UploadCV.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.txt)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadCV.getParent().getScene().getWindow());
            if (btn_submitAll.isPressed()){
                Writer nulis = new Writer() {
                    @Override
                    public void write(char[] cbuf, int off, int len) throws IOException {

                    }

                    @Override
                    public void flush() throws IOException {

                    }

                    @Override
                    public void close() throws IOException {

                    }
                }
            }
        });
    }

    @FXML
    void uploadPortofolio(ActionEvent event) {
        btn_UploadPortofolio.setOnAction(event1 -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.txt)", "*.pdf");
            milih.getExtensionFilters().add(extFilter);
            File file = milih.showOpenDialog(btn_UploadPortofolio.getParent().getScene().getWindow());
        });
    }


}
