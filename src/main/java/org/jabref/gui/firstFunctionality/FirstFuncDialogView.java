package org.jabref.gui.firstFunctionality;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.jabref.gui.ClipBoardManager;
import org.jabref.gui.DialogService;
import org.jabref.gui.util.BaseDialog;
import org.jabref.gui.util.ControlHelper;
import org.jabref.logic.l10n.Localization;
import com.airhacks.afterburner.views.ViewLoader;

import org.jabref.logic.serpapiAPI.SearchAPI;
import org.jabref.serpapiAPI.searchClasses.SerpApiSearchException;


public class FirstFuncDialogView extends BaseDialog<Void> {

    @FXML
    private TextArea textAreaInput;
    @FXML
    private TextArea textAreaReport;
    @FXML
    private ButtonType copyReportButton;
    @FXML
    private ButtonType researchButton;
    @FXML
    private ButtonType clearResearch;

    @Inject
    private DialogService dialogService;
    @Inject
    private ClipBoardManager clipBoardManager;

    private String idToResearch;

    public FirstFuncDialogViewModel viewModel;

    public FirstFuncDialogView() {
        this.setTitle(Localization.lang("First functionality"));
        idToResearch = "";

        ViewLoader.view(this).load().setAsDialogPane(this);

        ControlHelper.setAction(copyReportButton, getDialogPane(), event -> copyReportToClipboard());
        ControlHelper.setAction(researchButton, getDialogPane(), event -> {
            try {
                researchId();
            } catch (SerpApiSearchException e) {
                dialogService.notify(Localization.lang("Insert a valid Google Scholar ID"));
            }
        });
        ControlHelper.setAction(clearResearch, getDialogPane(), event -> clearFields());
    }

    public FirstFuncDialogViewModel getViewModel() {
        return viewModel;
    }

    @FXML
    private void initialize() {
        viewModel = new FirstFuncDialogViewModel(dialogService);
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
        if (textAreaInput.getText().equals(""))
            dialogService.notify(Localization.lang("Don't forget to insert an ID"));
        else {
            idToResearch = textAreaInput.getText();
            SearchAPI tmp = new SearchAPI(idToResearch);
            for (String elem : tmp.primeiraFuncionalidade()) {
                textAreaReport.appendText(elem);
            }
            dialogService.notify(Localization.lang("Research completed"));
        }
    }

    @FXML
    private void clearFields() {
        textAreaInput.setText("");
        textAreaReport.setText("");
    }
}
