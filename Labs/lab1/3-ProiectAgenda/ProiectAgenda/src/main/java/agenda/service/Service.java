package agenda.service;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.repository.interfaces.RepositoryContact;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Service {
    private RepositoryActivity activityRep;
    private RepositoryContact contactRep;

    public Service(RepositoryActivity activityRep, RepositoryContact contactRep) {
        this.activityRep = activityRep;
        this.contactRep = contactRep;
    }

    public void afisActivitate(BufferedReader in, User user) {
        try {
            System.out.printf("Afisare Activitate: \n");
            System.out.printf("Data(format: mm/dd/yyyy): ");
            String dateS = in.readLine();
            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(dateS.split("/")[2]),
                    Integer.parseInt(dateS.split("/")[0]) - 1,
                    Integer.parseInt(dateS.split("/")[1]));
            Date d = c.getTime();

            System.out.println("Activitatile din ziua " + d.toString() + ": ");

            List<Activity> act = activityRep
                    .activitiesOnDate(user.getName(), d);
            for (Activity a : act) {
                System.out.printf("%s - %s: %s - %s with: ", a.getStart()
                        .toString(), a.getDuration().toString(), a
                        .getDescription());
                for (Contact con : a.getContacts())
                    System.out.printf("%s, ", con.getName());
                System.out.println();
            }
        } catch (IOException e) {
            System.out.printf("Eroare de citire: %s\n" + e.getMessage());
        }
    }

    public void adaugActivitate( BufferedReader in, User user) {
        try {
            System.out.printf("Adauga Activitate: \n");
            System.out.printf("Descriere: ");
            String description = in.readLine();
            System.out.printf("Start Date(format: mm/dd/yyyy): ");
            String dateS = in.readLine();
            System.out.printf("Start Time(hh:mm): ");
            String timeS = in.readLine();
            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(dateS.split("/")[2]),
                    Integer.parseInt(dateS.split("/")[0]) - 1,
                    Integer.parseInt(dateS.split("/")[1]),
                    Integer.parseInt(timeS.split(":")[0]),
                    Integer.parseInt(timeS.split(":")[1]));
            Date start = c.getTime();

            System.out.printf("End Date(format: mm/dd/yyyy): ");
            String dateE = in.readLine();
            System.out.printf("End Time(hh:mm): ");
            String timeE = in.readLine();

            c.set(Integer.parseInt(dateE.split("/")[2]),
                    Integer.parseInt(dateE.split("/")[0]) - 1,
                    Integer.parseInt(dateE.split("/")[1]),
                    Integer.parseInt(timeE.split(":")[0]),
                    Integer.parseInt(timeE.split(":")[1]));
            Date end = c.getTime();

            Activity act = new Activity(user.getName(), start, end,
                    new LinkedList<Contact>(), description);

            activityRep.addActivity(act);

            System.out.printf("S-a adugat cu succes\n");
        } catch (IOException e) {
            System.out.printf("Eroare de citire: %s\n" + e.getMessage());
        }

    }

    public void adaugContact(BufferedReader in) {

        try {
            System.out.printf("Adauga Contact: \n");
            System.out.printf("Nume: ");
            String name = in.readLine();
            System.out.printf("Adresa: ");
            String adress = in.readLine();
            System.out.printf("Numar de telefon: ");
            String telefon = in.readLine();

            contactRep.addContact(name,adress,telefon);

            System.out.printf("S-a adugat cu succes\n");
        } catch (IOException e) {
            System.out.printf("Eroare de citire: %s\n" + e.getMessage());
        } catch (InvalidFormatException e) {
            if (e.getCause() != null)
                System.out.printf("Eroare: %s - %s\n" + e.getMessage(), e
                        .getCause().getMessage());
            else
                System.out.printf("Eroare: %s\n" + e.getMessage());
        }

    }
}
