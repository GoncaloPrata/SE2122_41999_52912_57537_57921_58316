package org.jabref.gui.firstFunctionality;

import org.jabref.gui.DialogService;
import org.jabref.gui.actions.SimpleCommand;

import com.airhacks.afterburner.injection.Injector;

public class FirstAction extends SimpleCommand {

    private final FirstFuncDialogView firstFuncDialogView;

    public FirstAction() {
        this.firstFuncDialogView = new FirstFuncDialogView();
    }

    @Override
    public void execute() {
        DialogService dialogService = Injector.instantiateModelOrService(DialogService.class);
        dialogService.showCustomDialog(firstFuncDialogView);
    }

    public FirstFuncDialogView getAboutDialogView() {
        return firstFuncDialogView;
     }
}

