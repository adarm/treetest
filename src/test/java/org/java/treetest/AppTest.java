package org.java.treetest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.treetest.SpringConfig;
import org.treetest.service.TreeService;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
	    @SuppressWarnings("resource")
		ApplicationContext ctx = 
	    		new AnnotationConfigApplicationContext(SpringConfig.class);
	    TreeService s = ctx.getBean(TreeService.class);
    }
}
