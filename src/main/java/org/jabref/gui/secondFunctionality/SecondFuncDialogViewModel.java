package org.jabref.gui.secondFunctionality;

import javafx.beans.property.ReadOnlyStringWrapper;
import org.jabref.gui.AbstractViewModel;
import org.jabref.gui.DialogService;

import java.util.Objects;

public class SecondFuncDialogViewModel extends AbstractViewModel {

    private final ReadOnlyStringWrapper heading = new ReadOnlyStringWrapper();
    private final DialogService dialogService;

    public SecondFuncDialogViewModel(DialogService dialogService) {
        this.dialogService = Objects.requireNonNull(dialogService);

        heading.set("Second Functionality");
    }

    public String getHeading() {
        return heading.get();
    }
}
