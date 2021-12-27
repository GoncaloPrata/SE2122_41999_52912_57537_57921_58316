package org.jabref.gui.firstFunctionality;

import java.util.Objects;
import javafx.beans.property.ReadOnlyStringWrapper;
import org.jabref.gui.AbstractViewModel;
import org.jabref.gui.DialogService;

public class FirstFuncDialogViewModel extends AbstractViewModel {

    private final ReadOnlyStringWrapper heading = new ReadOnlyStringWrapper();
    private final DialogService dialogService;

    public FirstFuncDialogViewModel(DialogService dialogService) {
        this.dialogService = Objects.requireNonNull(dialogService);

        heading.set("First Functionality");
    }

    public String getHeading() {
        return heading.get();
    }

}
