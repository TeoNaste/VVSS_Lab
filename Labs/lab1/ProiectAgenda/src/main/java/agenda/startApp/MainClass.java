package agenda.startApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.exceptions.InvalidFormatException;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.repository.classes.RepositoryActivityFile;
import agenda.repository.classes.RepositoryContactFile;
import agenda.repository.classes.RepositoryUserFile;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.repository.interfaces.RepositoryContact;
import agenda.repository.interfaces.RepositoryUser;
import agenda.service.Service;

//functionalitati
//F01.	 adaugarea de contacte (nume, adresa, numar de telefon, adresa email);
//F02.	 programarea unor activitati (denumire, descriere, data, locul, ora inceput, durata, contacte).
//F03.	 generarea unui raport cu activitatile pe care le are utilizatorul (nume, user, parola) la o anumita data, ordonate dupa ora de inceput.

public class MainClass {

	public static void main(String[] args) {
		BufferedReader in = null;
		try {
			RepositoryContact contactRep = new RepositoryContactFile();
			RepositoryUser userRep = new RepositoryUserFile();
			RepositoryActivity activityRep = new RepositoryActivityFile(
					contactRep);
			Service service = new Service(activityRep,contactRep);

			User user = null;
			in = new BufferedReader(new InputStreamReader(System.in));
			while (user == null) {
				System.out.printf("Enter username: ");
				String u = in.readLine();
				System.out.printf("Enter password: ");
				String p = in.readLine();

				user = userRep.getByUsername(u);
				if (user == null || !user.isPassword(p))
					user = null;
			}

			int chosen = 0;
			while (chosen != 4) {
				printMenu();
				chosen = Integer.parseInt(in.readLine());
				try {
					switch (chosen) {
					case 1:
						service.adaugContact(in);
						break;
					case 2:
						service.adaugActivitate(in, user);
						break;
					case 3:
						service.afisActivitate(in, user);
						break;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			// List<Activity> act =
			// activityRep.activitiesByName(user.getName());
			// for(Activity a : act)
			// System.out.println(a.toString());

		} catch (Exception e) {
            System.out.println(e);
		}
		System.out.println("Program over and out\n");
	}


	private static void printMenu() {
		System.out.printf("Please choose option:\n");
		System.out.printf("1. Adauga contact\n");
		System.out.printf("2. Adauga activitate\n");
		System.out.printf("3. Afisare activitati din data de...\n");
		System.out.printf("4. Exit\n");
		System.out.printf("Alege: ");
	}
}
