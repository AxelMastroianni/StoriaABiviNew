package la.storia.a.bivi;
import java.util.*;
public class Paragrafo {
	
	public static final int MASSIME_OPZIONI_SE_CASUALE=5;
	private int id;
	private int numeroOpzioni;
	private String descrizione;
	private LinkedList<Opzione> opzioni=new LinkedList<Opzione>();
	/**
	 * costruttore per la creazione di un paragrafo casuale
	 * @param id
	 * @param numeroOpzioni
	 */
	public Paragrafo(int id, int numeroOpzioni) {
		this.id=id;
		this.descrizione="Paragrafo "+this.id;
		this.numeroOpzioni=numeroOpzioni;
	}
	/**
	 * costruttore per la creazione di un paragrafo manuale
	 * @param id
	 * @param numeroOpzioni
	 * @param descrizione
	 */
	public Paragrafo(int id, int numeroOpzioni, String descrizione) {
		this.id=id;
		this.numeroOpzioni=numeroOpzioni;
		this.descrizione=descrizione;
	}
	/**
	 * costruttore per la lettura da XML
	 */
	public Paragrafo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroOpzioni() {
		return numeroOpzioni;
	}

	public void setNumeroOpzioni(int numeroOpzioni) {
		this.numeroOpzioni = numeroOpzioni;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LinkedList<Opzione> getOpzioni() {
		return opzioni;
	}

	public void setOpzioni(LinkedList<Opzione> opzioni) {
		this.opzioni = opzioni;
	}
	
	public void aggiungiOpzione(Opzione opzione) {
		opzioni.add(opzione);
	}
	/**
	 * visualizza il paragrafo tramite le sue opzioni e la sua descrizione
	 */
	public void visualizzaParagrafo() {
		System.out.println(descrizione+". Letta la descrizione scegli una tra queste opzioni: ");
		for(int i=0;i<opzioni.size();i++) {
			Opzione opzione=opzioni.get(i);
			System.out.println(i+": "+opzione.toString()); //visualizza in ordine le opzioni
		}
		System.out.println("--------------------------");
	}
	/**
	 * verifica se un paragrafo è terminale
	 * @return
	 */
	public boolean isTerminale() {
		if(opzioni.isEmpty())
			return true;
		return false;
	}
	/**
	 * metodo molto utile quando si crea una storia casuale. Provvede ad eliminare in un
	 * paragrafo le opzioni che puntano ad uno stesso paragrafo oppure al paragrafo che le
	 * contiene. Ad esempio il paragrafo 1 contiene un'opzione che punta ad esso. Questa opzione
	 * sarà eliminata
	 */
	public void aggiustaParagrafo() {
		for(int i=0;i<opzioni.size();i++) {
			Opzione analizzata=opzioni.get(i);
			for(int j=0;j<opzioni.size();j++) {
				Opzione doppiona=opzioni.get(j);
				if(i!=j && analizzata.getIdParagrafoPuntato()==doppiona.getIdParagrafoPuntato()) {
					opzioni.remove(doppiona);
					numeroOpzioni--;
					j--; //altrimenti alcune opzioni sono saltate
				}
			}
			if(analizzata.getIdParagrafoPuntato()==id) {
				opzioni.remove(analizzata);
				numeroOpzioni--;
				i--;//così non salta le opzioni nel caso ne becchi una che punta al paragrafo che la origina
			}
		}
	}
	/**
	 * ritorna l'opzione scelta dall'utente. Se non presente lancia una Nullptr
	 * che sarà gestita nel main
	 * @param numeroOpzione
	 * @return
	 * @throws NullPointerException
	 */
	private Opzione scegliOpzione(int numeroOpzione) throws NullPointerException {
		if(numeroOpzione<numeroOpzioni)
			return opzioni.get(numeroOpzione);
		throw new NullPointerException();
	}
	/**
	 * l'utente sceglie il numero dell'opzione. Se questo numero è inesistente viene
	 * ritoranta una nullptr exception
	 * @param numeroOpzione
	 * @return
	 */
	public int cambiaParagrafo(int numeroOpzione) throws NullPointerException {
		Opzione scelta=scegliOpzione(numeroOpzione);
		int paragrafoPuntato=scelta.getIdParagrafoPuntato();
		return paragrafoPuntato;
	}

}
