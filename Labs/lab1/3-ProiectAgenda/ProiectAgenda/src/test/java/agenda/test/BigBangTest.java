package agenda.test;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.repository.classes.RepositoryActivityFile;
import agenda.repository.classes.RepositoryContactFile;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.repository.interfaces.RepositoryContact;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class BigBangTest {
    private RepositoryActivity repAct;
    private RepositoryContact repCon;
    private List<Contact> contacts;

    @Before
    public void setup() throws Exception {
        contacts = new ArrayList<Contact>();
        repCon = new RepositoryContactFile();
        repAct = new RepositoryActivityFile(repCon);

        for (Activity a : repAct.getActivities())
            repAct.removeActivity(a);

    }

    //f01 - A
    @Test
    public void testCase1ECP(){
        try {
            assertEquals(repCon.count(),4);
            repCon.addContact("Numele","Adress","01234566");
            assertEquals(repCon.count(),5);
        } catch (InvalidFormatException e)
        {
            assertTrue("Invalid format exception",true);
        } catch (Exception e)
        {
            e.printStackTrace() ;
        }
    }

    //f02 - B
    @Test
    public void TC1(){
        Calendar c = Calendar.getInstance();
        c.set(2019, 3 - 1, 16);
        Date start = c.getTime();

        c.set(2019,3-1,11);
        Date duration = c.getTime();

        Activity activity = new Activity("act",start,duration,contacts,"desc");
        assertFalse(repAct.addActivity(activity));
    }

    //f03 - C
    @Test
    public void raportValid(){
        Calendar c = Calendar.getInstance();
        c.set(2019, 3 - 1, 20);
        Date start = c.getTime();

        c.set(2019,3-1,21);
        Date duration = c.getTime();

        c.set(2019,3-1,22);
        Date duration2 = c.getTime();

        Activity activity = new Activity("act1",start,duration,contacts,"desc5");
        Activity activity2 = new Activity("act1",duration,duration2,contacts,"desc6");
        assertTrue(repAct.addActivity(activity2));
        assertTrue(repAct.addActivity(activity));

        assertTrue(repAct.activitiesOnDate("act1",start).size() == 1);
        assertTrue(repAct.activitiesOnDate("act1",duration).size() == 2);
    }

    @Test
    public void integrationTest(){
        try {
            //f01
            assertEquals(repCon.count(),4);
            repCon.addContact("Nume","Adress","01234566");
            assertEquals(repCon.count(),5);
            contacts = repCon.getContacts();

            //f02
            Calendar c = Calendar.getInstance();
            c.set(2019, 3 - 1, 16);
            Date start = c.getTime();

            c.set(2019,3-1,11);
            Date duration = c.getTime();

            Activity activity = new Activity("act",start,duration,contacts,"desc");
            assertFalse(repAct.addActivity(activity));

            //f03
            c.set(2019, 3 - 1, 20);
            Date start2 = c.getTime();

            c.set(2019,3-1,21);
            Date duration2 = c.getTime();

            Activity activity2 = new Activity("act",start2,duration2,contacts,"desc");
            assertTrue(repAct.addActivity(activity));

            assertTrue(repAct.activitiesOnDate("act",start2).size() == 1);

        } catch (InvalidFormatException e)
        {
            assertTrue("Invalid format exception",true);
        } catch (Exception e)
        {
            e.printStackTrace() ;
        }
    }
}
