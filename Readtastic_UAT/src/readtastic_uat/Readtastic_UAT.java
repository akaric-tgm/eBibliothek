package readtastic_uat;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Readtastic_UAT {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(Search.class);

        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
