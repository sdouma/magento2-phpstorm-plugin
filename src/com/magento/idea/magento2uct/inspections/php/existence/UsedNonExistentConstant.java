/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2uct.inspections.php.existence;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.jetbrains.php.lang.psi.elements.ClassConstantReference;
import com.jetbrains.php.lang.psi.elements.Field;
import com.jetbrains.php.lang.psi.elements.FieldReference;
import com.jetbrains.php.lang.psi.elements.impl.ClassConstImpl;
import com.magento.idea.magento2uct.inspections.UctProblemsHolder;
import com.magento.idea.magento2uct.inspections.php.UsedFieldInspection;
import com.magento.idea.magento2uct.packages.IssueSeverityLevel;
import com.magento.idea.magento2uct.packages.SupportedIssue;
import com.magento.idea.magento2uct.versioning.VersionStateManager;
import org.jetbrains.annotations.NotNull;

public class UsedNonExistentConstant extends UsedFieldInspection {

    @Override
    protected void execute(
            final Project project,
            final @NotNull ProblemsHolder problemsHolder,
            final Field field,
            final FieldReference fieldReference
    ) {
        // We do not need to check field in the constant inspection.
    }

    @Override
    protected void execute(
            final Project project,
            final @NotNull ProblemsHolder problemsHolder,
            final ClassConstImpl constant,
            final ClassConstantReference constantReference
    ) {
        if (VersionStateManager.getInstance(project).isExists(constant.getFQN())) {
            return;
        }
        final String message = SupportedIssue.USED_NON_EXISTENT_CONSTANT.getMessage(
                constant.getFQN().replace(".", "::"),
                VersionStateManager.getInstance(project).getRemovedInVersion(constant.getFQN())
        );

        if (problemsHolder instanceof UctProblemsHolder) {
            ((UctProblemsHolder) problemsHolder).setIssue(
                    SupportedIssue.USED_NON_EXISTENT_CONSTANT
            );
        }
        problemsHolder.registerProblem(constantReference, message, ProblemHighlightType.ERROR);
    }

    @Override
    protected IssueSeverityLevel getSeverityLevel() {
        return SupportedIssue.USED_NON_EXISTENT_CONSTANT.getLevel();
    }
}
