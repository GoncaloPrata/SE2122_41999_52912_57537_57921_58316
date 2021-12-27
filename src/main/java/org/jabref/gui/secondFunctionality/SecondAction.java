package org.jabref.gui.secondFunctionality;

import com.airhacks.afterburner.injection.Injector;
import org.jabref.gui.DialogService;
import org.jabref.gui.actions.SimpleCommand;

public class SecondAction extends SimpleCommand {

    private final SecondFuncDialogView secondFuncDialogView;

    public SecondAction() {
        this.secondFuncDialogView = new SecondFuncDialogView();
    }

    @Override
    public void execute() {
        DialogService dialogService = Injector.instantiateModelOrService(DialogService.class);
        dialogService.showCustomDialog(secondFuncDialogView);
    }

    public SecondFuncDialogView getAboutDialogView() {
        return secondFuncDialogView;
    }
}
