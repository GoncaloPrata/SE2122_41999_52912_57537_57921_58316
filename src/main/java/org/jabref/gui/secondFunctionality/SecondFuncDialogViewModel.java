package org.jabref.gui.secondFunctionality;

import javafx.beans.property.ReadOnlyStringWrapper;
import org.jabref.gui.AbstractViewModel;
import org.jabref.gui.DialogService;
import org.jabref.gui.desktop.JabRefDesktop;
import org.jabref.logic.l10n.Localization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class SecondFuncDialogViewModel extends AbstractViewModel {

    private static final String EXPLANATION_URL = "https://github.com/GoncaloPrata/SE2122_41999_52912_57537_57921_58316/blob/main/Project/Phase%202/Sprint%201/Jo%C3%A3o%20Silva/1st%20functionality";
    private final Logger logger = LoggerFactory.getLogger(SecondFuncDialogViewModel.class);
    private final ReadOnlyStringWrapper heading = new ReadOnlyStringWrapper();
    private final DialogService dialogService;

    public SecondFuncDialogViewModel(DialogService dialogService) {
        this.dialogService = Objects.requireNonNull(dialogService);

        heading.set("Second Functionality");
    }

    public String getHeading() {
        return heading.get();
    }

    public void openExplanation() {
        openWebsite(EXPLANATION_URL);
    }

    private void openWebsite(String url) {
        try {
            JabRefDesktop.openBrowser(url);
        } catch (IOException e) {
            dialogService.showErrorDialogAndWait(Localization.lang("Could not open website."), e);
            logger.error("Could not open default browser.", e);
        }
    }
}
