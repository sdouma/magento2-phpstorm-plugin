package com.magento.idea.magento2plugin.actions.generation;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.magento.idea.magento2plugin.MagentoIcons;
import com.magento.idea.magento2plugin.actions.generation.dialog.NewEavAttributeDialog;
import org.jetbrains.annotations.NotNull;

public class NewEavAttributeAction extends AnAction {
    public static final String ACTION_NAME = "Magento 2 EAV Attribute";
    public static final String ACTION_DESCRIPTION = "Create a new Magento 2 EAV Attribute";

    public NewEavAttributeAction() {
        super(ACTION_NAME, ACTION_DESCRIPTION, MagentoIcons.MODULE);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final DataContext dataContext = e.getDataContext();
        final IdeView view = LangDataKeys.IDE_VIEW.getData(dataContext);
        if (view == null) {
            return;
        }

        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
        if (project == null) {
            return;
        }

        final PsiDirectory directory = view.getOrChooseDirectory();
        if (directory == null) {
            return;
        }

        NewEavAttributeDialog.open(project, directory);
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
