package agenda.repository.classes;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.repository.interfaces.RepositoryContact;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RepositoryActivityFileTest {
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

    @Test
    public void TC1(){
        Calendar c = Calendar.getInstance();
        c.set(2019, 3 - 1, 16);
        Date start = c.getTime();

        c.set(2019,3-1,11);
        Date duration = c.getTime();

        Activity activity = new Activity("act1",start,duration,contacts,"desc1");
        assertFalse(repAct.addActivity(activity));
    }

    @Test
    public void TC2(){
        Calendar c = Calendar.getInstance();
        c.set(2019, 3 - 1, 11);
        Date start = c.getTime();

        c.set(2019,3-1,16);
        Date duration = c.getTime();

        Activity activity = new Activity("act2",start,duration,contacts,"desc2");
        assertTrue(repAct.addActivity(activity));
    }

    @Test
    public void TC3(){
        //add act2
        Calendar c = Calendar.getInstance();
        c.set(2019, 3 - 1, 11);
        Date start2 = c.getTime();
        c.set(2019,3-1,16);
        Date duration2 = c.getTime();
        Activity activity2 = new Activity("act2",start2,duration2,contacts,"desc2");
        repAct.addActivity(activity2);

        //check act 3
        c.set(2019, 3 - 1, 24);
        Date start = c.getTime() ;

        c.set(2019,3-1,25);
        Date duration = c.getTime();

        Activity activity = new Activity("act3",start,duration,contacts,"desc3");
        assertTrue(repAct.addActivity(activity));
    }

    @Test
    public void TC4(){
        Calendar c = Calendar.getInstance();
        //add act 2
        c.set(2019, 3 - 1, 11);
        Date start2 = c.getTime();
        c.set(2019,3-1,16);
        Date duration2 = c.getTime();
        Activity activity2 = new Activity("act2",start2,duration2,contacts,"desc2");
        repAct.addActivity(activity2);

        //add act 3
        c.set(2019, 3 - 1, 24);
        Date start3 = c.getTime() ;
        c.set(2019,3-1,25);
        Date duration3 = c.getTime();
        Activity activity3 = new Activity("act3",start3,duration3,contacts,"desc3");
        assertTrue(repAct.addActivity(activity3));

        //check act 4
        c.set(2019, 3 - 1, 15);
        Date start = c.getTime();

        c.set(2019,3-1,16);
        Date duration = c.getTime();

        Activity activity = new Activity("act4",start,duration,contacts,"desc4");
        assertFalse(repAct.addActivity(activity));
    }

    @Test
    public void TC5(){
        Calendar c = Calendar.getInstance();
        //add act 2
        c.set(2019, 3 - 1, 11);
        Date start2 = c.getTime();
        c.set(2019,3-1,16);
        Date duration2 = c.getTime();
        Activity activity2 = new Activity("act2",start2,duration2,contacts,"desc2");
        repAct.addActivity(activity2);

        //add act 3
        c.set(2019, 3 - 1, 24);
        Date start3 = c.getTime() ;
        c.set(2019,3-1,25);
        Date duration3 = c.getTime();
        Activity activity3 = new Activity("act3",start3,duration3,contacts,"desc3");
        assertTrue(repAct.addActivity(activity3));

        //check act 5
        c.set(2019, 3 - 1, 20);
        Date start = c.getTime();

        c.set(2019,3-1,21);
        Date duration = c.getTime();

        Activity activity = new Activity("act5",start,duration,contacts,"desc5");
        assertTrue(repAct.addActivity(activity));
    }
}