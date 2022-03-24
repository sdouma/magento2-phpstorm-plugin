/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.reference.js;

public class RequireJsReferenceRegistrarTest extends ReferenceJsFixtureTestCase {

    private static final String FIXTURE_PATH = "test.js";

    /**
     * Mapped parameters should have reference to file.
     */
    public void testMappedInjectionParameterMustHaveReference() {
        myFixture.configureByFile(getFixturePath(FIXTURE_PATH));

        assertHasReferenceToFile("app/code/Foo/Bar/view/frontend/web/js/file.js");
    }

    /**
     * Path parameters should have reference to file.
     */
    public void testPathInjectionParameterMustHaveReference() {
        myFixture.configureByFile(getFixturePath(FIXTURE_PATH));

        assertHasReferenceToFile("app/code/Foo/Bar/view/frontend/web/js/file2.js");
    }

    /**
     * The Magento resource file path parameters should have reference to file.
     */
    public void testFileInjectionParameterMustHaveReference() {
        myFixture.configureByFile(getFixturePath(FIXTURE_PATH));

        assertHasReferenceToFile("app/code/Foo/Bar/view/frontend/web/js/file.js");
    }

    /**
     * Lib resource parameters should have reference to file.
     */
    public void testLibInjectionParameterMustHaveReference() {
        myFixture.configureByFile(getFixturePath(FIXTURE_PATH));

        assertHasReferenceToFile("/lib/web/testjs.js");
    }
}
