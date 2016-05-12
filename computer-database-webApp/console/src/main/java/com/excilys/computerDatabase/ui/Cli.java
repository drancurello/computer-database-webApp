package com.excilys.computerDatabase.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.mapper.ComputerDTOMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;


@Component
public class Cli {
	
	private static final String URL = "http://localhost:8080/computer-database-restService/";
	
	private static Client client = ClientBuilder.newClient();	
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void sessionCommand() throws NumberFormatException, ConnectionException, SQLException {
		String str = "";
		
		while (!str.equals("exit")) {
			System.out.println(
					" 1-liste des ordinateurs\n 2-liste des fabriquants\n 3-paginer les ordinateurs\n 4-ajouter un ordinateur\n 5-chercher un ordinateur\n 6-mettre a jour un ordinateur\n 7-supprimer un ordinateur\n 8-supprimer une compagnie\n exit pour quitter ");
			str = sc.nextLine();

			switch (str) {
			case "1":
				listAllComputer();
				break;
			case "2":
				listAllCompanies();
				break;
			case "3":
				listComputerByPage();
				break;
			case "4":
				addComputer();
				break;
			case "5":
				findComputer();
				break;
			case "6":
				updateComputer();
				break;
			case "7":
				deleteComputer();
				break;
			case "8":
				deleteCompany();
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
		WebTarget webTarget = client.target(URL + "computers");
		
		Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();
		
		List<ComputerDTO> computersList = response.readEntity(new GenericType<List<ComputerDTO>>(){});
		
		for (ComputerDTO computer : computersList) {
			System.out.println(computer.toString());
		}
	}

	public static void listAllCompanies() {
		
		WebTarget webTarget = client.target(URL + "companies");
		
		Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();			
		
		List<Company> companyList = response.readEntity(new GenericType<List<Company>>(){});
		
		for (Company company : companyList) {
			System.out.println(company.toString());
		}
	}

	public static void listComputerByPage() {
		
		List<ComputerDTO> computers = new ArrayList<>();
		int pageNumber = 0;
		
		System.out.println("Combien d'ordinateurs voulez vous afficher ?");
		String nbEntries = sc.nextLine();
		while (Integer.parseInt(nbEntries) < 0) {
			System.out.println("Veuillez rentrer un nombre d'ordinateurs à afficher valide ");
			nbEntries = sc.nextLine();
		}
		System.out.println("Quelle page souhaitez-vous voir ?");
		String nPage = sc.nextLine();
		while (Integer.parseInt(nPage) < 1) {
			System.out.println("Veuillez rentrer un numero de page valide ");
			nPage = sc.nextLine();
		}
		
		pageNumber = Integer.parseInt(nPage);

		String rep = "";

		WebTarget webTarget = client.target(URL + "page/" + pageNumber + "/" + nbEntries);
		Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();			
		
		computers = response.readEntity(new GenericType<List<ComputerDTO>>(){});

		for (ComputerDTO c : computers) {
			System.out.println(c.toString());
		}

		while (!rep.equals("5")) {
			System.out.println(
					"1-page precedente, 2-page suivante, 3-changer le nombre d'ordinateur affiches, 4-changer de page, 5-quitter");
			rep = sc.nextLine();

			switch (rep) {
			case "1":
				pageNumber = pageNumber - 1;
				webTarget = client.target(URL + "page/" + pageNumber + "/" + nbEntries);
				response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();			
				
				computers = response.readEntity(new GenericType<List<ComputerDTO>>(){});
				for (ComputerDTO c : computers) {
					System.out.println(c.toString());
				}
				break;
			case "2":
				pageNumber = pageNumber + 1;
				webTarget = client.target(URL + "page/" + pageNumber + "/" + nbEntries);
				response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();			
				
				computers = response.readEntity(new GenericType<List<ComputerDTO>>(){});
				for (ComputerDTO c : computers) {
					System.out.println(c.toString());
				}
				break;
			case "3":
				System.out.println("Combien d'ordinateur voulez-vous afficher par page?");
				nbEntries = sc.nextLine();
				webTarget = client.target(URL + "page/" + 1 + "/" + nbEntries);
				response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();			
				
				computers = response.readEntity(new GenericType<List<ComputerDTO>>(){});

				for (ComputerDTO c : computers) {
					System.out.println(c.toString());
				}
				break;
			case "4":
				System.out.println("Quel page voulez-vous consulter ?");
				nPage = sc.nextLine();
				webTarget = client.target(URL + "page/" + nPage + "/" + nbEntries);
				response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();			
				
				computers = response.readEntity(new GenericType<List<ComputerDTO>>(){});

				for (ComputerDTO c : computers) {
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
			if (!aIntro.isEmpty()) {
				try {
					aComputer.setIntroduced(aIntro);
				} catch (DateTimeParseException e) {
					System.out.println("mauvais format");
					aIntro = null;
				}		
			}
		}
		String aDisc = null;
		while (aDisc == null) {
			System.out.println("Entrez une date de fin de commercialisation au format yyyy-mm-dd : ");
			aDisc = sc.nextLine();
			if (!aDisc.isEmpty()) {
				try {
					if (!aIntro.isEmpty()) {
						if (LocalDate.parse(aDisc).isBefore(LocalDate.parse(aIntro))) {
							System.out.println("La date de fin doit etre apres la date d'introduction");
							aDisc = null;
						}
					}
					aComputer.setDiscontinued(aDisc);
				} catch (DateTimeParseException e) {
					System.out.println("Date au mauvais format : yyyy-mm-dd");
					aDisc = null;
				}
			}
		}
		
		System.out.println("Entrez l'id d'un fabricant : ");
		String aManufacturer = sc.nextLine();
		if (!aManufacturer.equals("")) {
			aComputer.setCompanyId(Long.parseLong(aManufacturer));
		}
		
		WebTarget webTarget = client.target(URL + "insert");
		webTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(aComputer, MediaType.APPLICATION_JSON), ComputerDTO.class);		
		
		System.out.println("ordinateur ajoute");
	}

	public static void findComputer() {
		
		String id;
		
		System.out.println("Entrez l'id de l'ordinateur a chercher : ");
		id = sc.nextLine();
		
		WebTarget webTarget = client.target(URL + "computer/" + id);
		Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();
		
		ComputerDTO fComputer = new ComputerDTO();
		
		fComputer = response.readEntity(ComputerDTO.class);
		
		if (fComputer == null) {
			System.out.println("L'ordinateur recherché n'est pas dans la base");
		} else {
			System.out.println(fComputer.toString());
		}
	}

	public static void updateComputer() {
		System.out.println("Entrez l'id du computer a modifier : ");
		String uId = sc.nextLine();
		System.out.println("Entrez un nouveau nom pour l'ordinateur : ");
		String uName = sc.nextLine();
		ComputerDTO upComputer = new ComputerDTO();
		upComputer.setName(uName);
		upComputer.setId(Integer.parseInt(uId));
		String uIntro = null;

		while (uIntro == null) {
			System.out.println("Entrez une nouvelle date d introduction : ");
			uIntro = sc.nextLine();

			if (!uIntro.isEmpty()) {
				try {
					upComputer.setIntroduced(uIntro);
				} catch (DateTimeParseException e) {
					System.out.println("date au mauvais format : yyyy-mm-dd");
					uIntro = null;
				}
			}
		}

		String uDisc = null;
		while (uDisc == null) {
			System.out.println("Entrez une nouvelle date de fin de commercialisation : ");
			uDisc = sc.nextLine();

			if (!uDisc.isEmpty()) {
				try {
					if(!uIntro.isEmpty()) {
						if (LocalDate.parse(uDisc).isBefore(LocalDate.parse(uIntro))) {
							System.out.println("La date de fin doit etre apres la date d'introduction");
							uDisc = null;
							upComputer.setDiscontinued(null);
						}
					}
				upComputer.setDiscontinued(uDisc);
				} catch (DateTimeParseException e) {
					System.out.println("Date au mauvais format : yyyy-mm-dd");
					uDisc = null;
				}
				
			}
		}
		System.out.println("Entrez l'id d'un nouveau fabricant : ");
		String uManufacturer = sc.nextLine();
		if (!uManufacturer.isEmpty()) {
			upComputer.setCompanyId(Long.parseLong(uManufacturer));

		}
		
		WebTarget webTarget = client.target(URL + "merge");
		webTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(upComputer, MediaType.APPLICATION_JSON), ComputerDTO.class);		
		
		System.out.println("L'ordinateur a bien ete midifie");
	}

	public static void deleteComputer() {
		String id;
		System.out.println("Id de l'ordinateur a supprimer : ");
		id = sc.nextLine();
		
		WebTarget webTarget = client.target(URL + "delete/" + id);

		Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();
				
		int sup = response.readEntity(Integer.class);
		
		if (sup == 1) {
			System.out.println("ordinateur supprimé");
		} else {
			System.out.println("L'ordinateur entre n'existe pas dans la base");
		}
	}
	
	public static void deleteCompany() throws NumberFormatException, ConnectionException, SQLException {
		String id;
		System.out.println("Id de la compagnie a supprimer : ");
		id = sc.nextLine();
		
		WebTarget webTarget = client.target( URL + "deleteCompany/" + id);

		Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();
				
		int sup = response.readEntity(Integer.class);
		
		if (sup == 1) {
			System.out.println("compagnie supprimé");
		} else {
			System.out.println("La compagnie entre n'existe pas dans la base");
		}
	}

}
