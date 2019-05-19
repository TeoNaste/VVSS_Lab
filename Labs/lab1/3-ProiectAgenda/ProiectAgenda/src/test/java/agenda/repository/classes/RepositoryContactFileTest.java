package agenda.repository.classes;

import agenda.exceptions.InvalidFormatException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryContactFileTest {
    private RepositoryContactFile repo;

    @Test
    public void testCase1ECP(){
        //TC1 valid
        try {
            repo = new RepositoryContactFile();
            assertEquals(repo.count(),4);
            repo.addContact("Nume","Adress","01234567");
            assertEquals(repo.count(),5);
        } catch (InvalidFormatException e) {
            assertTrue("Invalid format exception",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCase2ECP(){
        //TC2 nevalid
        try {
            repo = new RepositoryContactFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(repo.count(),4);
        //repo.addContact(12,"Adress","098765");
    }

    @Test
    public void testCase3ECP(){
        //TC3 valid
        try {
            repo = new RepositoryContactFile();
            assertEquals(repo.count(),4);
            repo.addContact("Name","Adress","012");
            assertEquals(repo.count(),5);
        } catch (InvalidFormatException e) {
            assertTrue("Invalid format exception",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCase4ECP(){
        //TC4 nevalid
        try {
            repo = new RepositoryContactFile();
            assertEquals(repo.count(),4);
            repo.addContact("Name","Adress","123456789");
        } catch (InvalidFormatException e) {
            assertTrue("Invalid format exception",true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assertEquals(repo.count(),4);
        }
    }

    @Test
    public void testAddContactBVA(){
        try {
            repo = new RepositoryContactFile();
            assertEquals(repo.count(),4);
            //TC1 valid
            repo.addContact("MMM","Adress","01234567");
            assertEquals(repo.count(),5);
            //TC2 nevalid
            repo.addContact("MM","Adress","098765");
            assertEquals(repo.count(),5);
            //TC7 nevalid
            repo.addContact("Name","Adress","");
            assertEquals(repo.count(),5);
            //TC9 valid
            repo.addContact("Name","Adress","01");
            assertEquals(repo.count(),6);
        } catch (InvalidFormatException e) {
            assertTrue("Invalid format exception",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}