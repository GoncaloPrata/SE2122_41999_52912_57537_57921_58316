package org.jabref.gui.secondFunctionality;

import com.airhacks.afterburner.views.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import org.jabref.gui.ClipBoardManager;
import org.jabref.gui.DialogService;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;
import org.jabref.logic.serpapiAPI.SearchAPI;
import org.jabref.serpapiAPI.searchClasses.SerpApiSearchException;

import javax.inject.Inject;


public class SecondFuncDialogView extends BaseDialog<Void> {

    @FXML private TextArea textAreaInput1;
    @FXML private TextArea textAreaInput2;
    @FXML private TextArea textAreaReport;
    @FXML private ButtonType copyReportButton;
    @FXML private ButtonType researchButton;
    @FXML private ButtonType clearResearch;

    @Inject private DialogService dialogService;
    @Inject private ClipBoardManager clipBoardManager;

    private String idToResearch1;
    private String idToResearch2;

    public SecondFuncDialogViewModel viewModel;

    public SecondFuncDialogView() {
        this.setTitle(Localization.lang("Second functionality"));
        idToResearch1 = "";
        idToResearch2 = "";

        ViewLoader.view(this)
                .load()
                .setAsDialogPane(this);

        ControlHelper.setAction(copyReportButton, getDialogPane(), event -> copyReportToClipboard());
        ControlHelper.setAction(researchButton, getDialogPane(), event -> {
            try {
                researchId();
            } catch (SerpApiSearchException e) {
                dialogService.notify(Localization.lang("Insert valid Google Scholar IDs"));
            }
        });
        ControlHelper.setAction(clearResearch, getDialogPane(), event -> clearFields());
    }

    public SecondFuncDialogViewModel getViewModel() {
        return viewModel;
    }

    @FXML
    private void initialize() {
        viewModel = new SecondFuncDialogViewModel(dialogService);
        this.setResizable(false);
    }

    @FXML
    private void copyReportToClipboard() {
        clipBoardManager.setContent(textAreaReport.getText());
        dialogService.notify(Localization.lang("Copied report to clipboard"));
    }

    @FXML
    private void researchId() throws SerpApiSearchException {
        textAreaReport.setText("");
        if(textAreaInput1.getText().equals(""))
            dialogService.notify(Localization.lang("Don't forget to insert an ID"));
        else{
            idToResearch1 = textAreaInput1.getText();
            idToResearch2 = textAreaInput2.getText();
            SearchAPI user1 = new SearchAPI(idToResearch1, idToResearch2);

            for (String elem: user1.segundaFuncionalidade()) {
                textAreaReport.appendText(elem);
            }

            dialogService.notify(Localization.lang("Research completed"));
        }
    }

    @FXML
    private void openExplanation() {
        viewModel.openExplanation();
    }

    @FXML
    private void clearFields() {
        textAreaInput1.setText("");
        textAreaInput2.setText("");
        textAreaReport.setText("");
    }
}
