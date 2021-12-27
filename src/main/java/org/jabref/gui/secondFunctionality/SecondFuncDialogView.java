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

    //Text box that will contain the first ID to be researched.
    @FXML private TextArea textAreaInput1;

    //Text box that will contain the second ID to be researched.
    @FXML private TextArea textAreaInput2;

    //This text box will contain the result of the comparison between the
    //information of the two Google Scholar users which IDs we´re giving
    @FXML private TextArea textAreaReport;
    @FXML private ButtonType copyReportButton;
    @FXML private ButtonType researchButton;
    @FXML private ButtonType clearResearch;

    @Inject private DialogService dialogService;
    @Inject private ClipBoardManager clipBoardManager;

    //First id whose information will be fetched and then compared.
    private String idToResearch1;

    //Second id whose information will be fetched and then compared.
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

        /**
         * If the user tries to start the research with an invalid ID, he will be notified of such thing, being notified to insert
         * valid IDs.
         */
        ControlHelper.setAction(researchButton, getDialogPane(), event -> {
            try {
                researchId();
            } catch (SerpApiSearchException e) {
                dialogService.notify(Localization.lang("Insert valid Google Scholar IDs"));
            }
        });
        ControlHelper.setAction(clearResearch, getDialogPane(), event -> clearFields());
    }

    /**
     * @return the viewModel to this functionality.
     */
    public SecondFuncDialogViewModel getViewModel() {
        return viewModel;
    }

    /**
     * Simple initialization method.
     */
    @FXML
    private void initialize() {
        viewModel = new SecondFuncDialogViewModel(dialogService);
        this.setResizable(false);
    }

    /**
     * This method copies the result of a search (contents of the textAreaReport text box) to the users clipboard.
     * After that, notifies the user that the action was a success or not.
     */
    @FXML
    private void copyReportToClipboard() {
        clipBoardManager.setContent(textAreaReport.getText());
        dialogService.notify(Localization.lang("Copied report to clipboard"));
    }

    /**
     * This method will search the given ID and, if it corresponds to a Google Scholar user,
     * later we´ll be able to get its information, else, the user will be notified of that.
     * If the user tries to research the id "" (empty string), the app will also remind the user
     * to insert an ID.
     * After the reserach is complete, the message "Research completed" will appear.
     * @throws SerpApiSearchException if the given the ID is not a valid Google Scholar ID.
     */
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

    /**
     * This method clears all the text box fields, both input and output ones, in order to "clean" the current search.
     * Simply sets them to "";
     */
    @FXML
    private void clearFields() {
        textAreaInput1.setText("");
        textAreaInput2.setText("");
        textAreaReport.setText("");
    }
}
