package la.storia.a.bivi;

import java.util.LinkedList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class StoriaABivi {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String[] menuScelte=new String[]{"Gioca una storia", "Crea una storia casuale",
			"Leggi storia da XML","Riordina Storie per Titolo","Riordina Storie per numero"
					+ "di paragrafi","Visualizza Storie Presenti","Cerca una storia"};
		int scelta=-1;
		int storieXML=0;//conta le storie lette da XML che devono essere massimo 3
		System.out.println("Benvenuto nel programma Storia a Bivi");
		MyMenu mioMenu=new MyMenu("Scegli un'opzione",menuScelte);
		RaccoltaDiStorie raccolta=new RaccoltaDiStorie();
		while(scelta!=0) {
			scelta=mioMenu.scegli();
			switch(scelta) {
			case 0:
				break; //interrompe il ciclo e fa terminare il programma
			case 1:{
				try {
					raccolta.visualizzaTitoli();//visualizza solo se la raccolta non è vuota
				}catch(Exception e) {
					System.out.println("Non è presente alcuna storia, creala per favore");
					break;
				}
				int storia=-1;//è il numero della storia da giocare
				//il ciclo termina se viene inserito 0 oppure un numero corretto
				while((storia<0 || storia>raccolta.getNumeroStorie()) && storia!=0) {
					storia=InputDati.leggiIntero("Digita il numero della storia da giocare(0 per uscire): ");
				}
				if(storia==0)
					break;
				Storia daGiocare=raccolta.giocaStoria(storia-1);
				int numeroOpzione=0;
				Paragrafo scelto=daGiocare.getParagrafoPerId(0);//parte dal paragrafo 0
				while(!scelto.isTerminale()){
					daGiocare.visualizzaParagrafoScelto(scelto);
					/*
					 * l'utente può anche incappare in una storia infinita, in quel caso
					 * bisogna far terminare il programma
					 */
					numeroOpzione=InputDati.leggiInteroConMinimo("Digita il numero dell'opzione che hai scelto: ",0);
					try {//prova fino a che l'utente non inserisce un'opzione inesistente
					int paragrafoRiferito=scelto.cambiaParagrafo(numeroOpzione);
					scelto=daGiocare.getParagrafoPerId(paragrafoRiferito);
					}catch(Exception e) {
						System.out.println("Opzione inesistente, riprova");
						continue;
					}
				}
				System.out.println("Sei capitato su un paragrafo terminale e la storia è finita");
				Thread.sleep(2000);
				break;
			}
			case 2:{
				String nomeStoria=InputDati.leggiStringaNonVuota("Inserisci il nome della storia: ");
				int numeroParagrafi=InputDati.leggiInteroPositivo("Quanti paragrafi ha? ");
				Storia storia=new Storia(nomeStoria,numeroParagrafi);
				storia.creaStoriaCasuale();
				storia.aggiustaStoria();
				System.out.println("Storia creata correttamente");
				raccolta.aggiungiStoria(nomeStoria, storia);
				break;
			}
			case 3:{
				storieXML++;
				try {//legge le storie fintantoché ce ne sono disponibili
				LeggiXML lettoreStorie=new LeggiXML();
				Storia storiaLetta=lettoreStorie.leggiStoria("./src/PgAr2018_Story_2."+storieXML+".xml");
				raccolta.aggiungiStoria(storiaLetta.getNome(), storiaLetta);
				System.out.println("Storia letta e aggiunta alla lista con successo :D");
				}catch(Exception e) {
					System.out.println("Non ci sono più storie in formato XML, creale tu :)");
				}
				break;
			}
			case 4:{
				LinkedList<Storia> storie=raccolta.getRaccoltaDiStorie();
				if(storie.size()>0) {
				storie=raccolta.quickSortTitoli(storie);
				raccolta.setRaccoltaDiStorie(storie);
				System.out.println("Storie sistemate in ordine alfabetico");
				Thread.sleep(1000);
				}
				else {
					System.out.println("Non hai ancora inserito alcuna storia");
					Thread.sleep(1000);
				}
				break;
			}
			case 5:{
				LinkedList<Storia> storie = raccolta.getRaccoltaDiStorie();
				if(storie.size()>0) {
				storie=raccolta.quickSortNumeroParagrafi(storie);
				raccolta.setRaccoltaDiStorie(storie);
				System.out.println("Storie ordinate per numero di paragrafi");
				Thread.sleep(1000);
				}
				else {
					System.out.println("Non hai ancora inserito alcuna storia");
					Thread.sleep(1000);
				}
				break;
			}
			case 6:{
				try {
				raccolta.visualizzaTitoli();
				}catch(Exception e) {
					System.out.println("Non sono presenti storie, creane almeno una :)");
				}
				break;
			}
			case 7:{
				String cercata=InputDati.leggiStringa("Inserisci il titolo della storia cercata: ");
				System.out.println(raccolta.cercaStoria(cercata));
				Thread.sleep(1000);
				break;
			}
			default:{
				System.out.println("Hai selezionato un'opzione inesistente, riprova");
				break;
			}
			}
		}
		System.out.println("Grazie per aver giocato alla Storia a Bivi :)");
	}

}
