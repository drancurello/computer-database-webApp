package com.excilys.computerDatabase.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.ComputerDTO;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		String str = "";

		while (!str.equals("exit")) {
			System.out.println(
					" 1-liste des materiels\n 2-liste des fabriquants\n 3-paginer les materiels\n 4-ajouter un materiel\n 5-chercher un materiel\n 6-mettre a jour un materiel\n 7-supprimer un materiel\n exit pour quitter ");
			str = sc.nextLine();

			switch (str) {
			case "1": 
				Main.listAllComputer();
				break;
			case "2":
				Main.listAllCompanies();
				break;
			case "3":
				Main.listComputerByPage();
				break;
			case "4":
				Main.addComputer();
				break;
			case "5":
				Main.findComputer();
				break;
			case "6":
				Main.updateComputer();
				break;
			case "7":
				Main.deleteComputer();
				break;
			case "exit":
				break;
			default:
				System.out.println("Entrez une commande valide");
			}

		}

		System.out.println("deconnexion");

		sc.close();
	}

	public static void listAllComputer() {
		List<ComputerDTO> listComputer = new ArrayList<ComputerDTO>();
		listComputer = ComputerService.findAllComputers();
		for (ComputerDTO c : listComputer) {
			System.out.println(c.toString());
		}
	}

	public static void listAllCompanies() {
		List<Company> companyList = new ArrayList<Company>();
		companyList = CompanyService.findAllCompanies();
		for (Company company : companyList) {
			System.out.println(company.toString());
		}
	}

	public static void listComputerByPage() {
		List<ComputerDTO> pListComputer = new ArrayList<ComputerDTO>();
		System.out.println("Combien de materiels vouolez vous afficher ?");
		String nbEntries = sc.nextLine();
		while (Integer.parseInt(nbEntries) < 0) {
			System.out.println("Veuillez rentrer un nombre de materiels a afficher valide ");
			nbEntries = sc.nextLine();
		}
		System.out.println("Quelle page souhaitez-vous voir ?");
		String nPage = sc.nextLine();
		while (Integer.parseInt(nPage) < 1) {
			System.out.println("Veuillez rentrer un numero de page valide ");
			nPage = sc.nextLine();
		}

		Page page = new Page(Integer.parseInt(nPage), Integer.parseInt(nbEntries));
		String rep = "";

		pListComputer = ComputerService.findPageComputers(page.getPageNumber(), page.getNbEntriesByPage());

		for (ComputerDTO c : pListComputer) {
			System.out.println(c.toString());
		}

		while (!rep.equals("5")) {
			System.out.println(
					"1-page precedente, 2-page suivante, 3-changer le nombre de materiels affiches, 4-changer de page, 5-quitter");
			rep = sc.nextLine();

			switch (rep) {
			case "1":
				page.previousPage();
				pListComputer = ComputerService.findPageComputers(page.getPageNumber(), page.getNbEntriesByPage());
				for (ComputerDTO c : pListComputer) {
					System.out.println(c.toString());
				}
				break;
			case "2":
				page.nextPage();
				pListComputer = ComputerService.findPageComputers(page.getPageNumber(), page.getNbEntriesByPage());
				for (ComputerDTO c : pListComputer) {
					System.out.println(c.toString());
				}
				break;
			case "3":
				System.out.println("Combien de materiels voulez-vous afficher par page?");
				nbEntries = sc.nextLine();
				page.setNbEntriesByPage(Integer.parseInt(nbEntries));
				page.setPageNumber(1);
				pListComputer = ComputerService.findPageComputers(page.getPageNumber(), page.getNbEntriesByPage());
				for (ComputerDTO c : pListComputer) {
					System.out.println(c.toString());
				}
				break;
			case "4":
				System.out.println("Quel page voulez-vous consulter ?");
				nbEntries = sc.nextLine();
				page.setPageNumber(Integer.parseInt(nbEntries));
				pListComputer = ComputerService.findPageComputers(page.getPageNumber(), page.getNbEntriesByPage());
				for (ComputerDTO c : pListComputer) {
					System.out.println(c.toString());
				}
				break;
			case "5":
				break;
			default:
				System.out.println("Veuillez rentrer une commande valide");
			}

		}

	}

	public static void addComputer() {
		System.out.println("Entrez un nom : ");
		String aName = sc.nextLine();
		while (aName.equals("") || aName.equals("null")) {
			System.out.println("Veuillez saisir un nom : ");
			aName = sc.nextLine();
		}
		ComputerDTO aComputer = new ComputerDTO();
		
		aComputer.setName(aName);

		String aIntro = null;
		while (aIntro == null) {
			System.out.println("Entrez une date d'introduction au format yyyy-mm-dd : ");
			aIntro = sc.nextLine();
			if (!aIntro.equals("")) {
				try {
					LocalDate.parse(aIntro);
				} catch (DateTimeParseException e) {
					System.out.println("mauvais format");
					aIntro = null;
				}
				aComputer.setIntroduced(aIntro);
			}
		}

		String aDisc = null;
		while (aDisc == null) {
			System.out.println("Entrez une date de fin de commercialisation au format yyyy-mm-dd : ");
			aDisc = sc.nextLine();

			if (!aDisc.equals("")) {
				try {
					LocalDate.parse(aDisc);
					if (LocalDate.parse(aDisc).isBefore(LocalDate.parse(aIntro))) {
						System.out.println("La date de fin doit etre apres la date d'introduction");
						aDisc = null;
						aComputer.setDiscontinued(null);
					}
				} catch (DateTimeParseException e) {
					System.out.println("Date au mauvais format : yyyy-mm-dd");
					aDisc = null;
				}
				aComputer.setDiscontinued(aDisc);
			}
		}	
		
		System.out.println("Entrez l'id d'un fabricant : ");
		String aManufacturer = sc.nextLine();
		if (!aManufacturer.equals("")) {
			Company mCompany = CompanyService.findCompany(Integer.parseInt(aManufacturer));
			aComputer.setCompanyId(mCompany.getId());
			aComputer.setCompanyName(mCompany.getName());
		}
		ComputerService.addComputer(aComputer);
		System.out.println("materiel ajoute");
	}

	public static void findComputer() {
		String id;
		System.out.println("Entrez l'id du materiel a chercher : ");
		id = sc.nextLine();
		ComputerDTO fComputer = new ComputerDTO();
		fComputer = ComputerService.findComputer(Integer.parseInt(id));
		if (fComputer == null) {
			System.out.println("Le materiel recherche n'est pas dans la base");
		} else {
			System.out.println(fComputer.toString());
		}
	}

	public static void updateComputer() {
		System.out.println("Entrez l'id du computer a modifier : ");
		String uId = sc.nextLine();
		System.out.println("Entrez un nouveau nom pour le materiel : ");
		String uName = sc.nextLine();
		ComputerDTO upComputer = new ComputerDTO();
		upComputer.setName(uName);
		upComputer.setId(Integer.parseInt(uId));
		String uIntro = null;

		while (uIntro == null) {
			System.out.println("Entrez une nouvelle date d introduction : ");
			uIntro = sc.nextLine();

			if (!uIntro.equals("")) {
				try {
					LocalDate.parse(uIntro);
				} catch (DateTimeParseException e) {
					System.out.println("date au mauvais format : yyyy-mm-dd");
					uIntro = null;
				}
				upComputer.setIntroduced(uIntro);
			}
		}

		String uDisc = null;
		while (uDisc == null) {
			System.out.println("Entrez une nouvelle date de fin de commercialisation : ");
			uDisc = sc.nextLine();

			if (!uDisc.equals("")) {
				try {
					LocalDate.parse(uDisc);
					if (LocalDate.parse(uDisc).isBefore(LocalDate.parse(uIntro))) {
						System.out.println("La date de fin doit etre apres la date d'introduction");
						uDisc = null;
						upComputer.setDiscontinued(null);
					}
				} catch (DateTimeParseException e) {
					System.out.println("Date au mauvais format : yyyy-mm-dd");
					uDisc = null;
				}
				upComputer.setDiscontinued(uDisc);
			}
		}
		System.out.println("Entrez l'id d'un nouveau fabricant : ");
		String uManufacturer = sc.nextLine();
		if (!uManufacturer.equals("")) {
			Company upCompany = CompanyService.findCompany(Integer.parseInt(uManufacturer));
			upComputer.setCompanyId(upCompany.getId());
			upComputer.setCompanyName(upCompany.getName());
			
		}
		ComputerService.updateComputer(upComputer);
		System.out.println("Le materiel a bien ete midifie");
	}

	public static void deleteComputer() {
		String id;
		System.out.println("Id du materiel a supprimer : ");
		id = sc.nextLine();
		int sup = ComputerService.deleteComputer(Integer.parseInt(id));
		if (sup == 1) {
			System.out.println("materiel supprimé");
		} else {
			System.out.println("Le materiel entre n'existe pas dans la base");
		}
	}
}