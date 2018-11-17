package la.storia.a.bivi;
import java.util.*;
/**
 * la classe opzione rappresenta l'opzione presente all'interno dei paragrafi
 * @author Axel Mastroianni
 * @version 1.1
 *
 */
public class Opzione {
	
	private int idParagrafoPuntato;
	private String descrizione;
	private Random r=new Random();
	/**
	 * costruttore per la creazione di una storia casuale
	 * @param numeroParagrafi
	 */
	public Opzione(int numeroParagrafi) {
		this.idParagrafoPuntato=Math.abs(r.nextInt()%numeroParagrafi);
		this.descrizione="Collegati a "+idParagrafoPuntato;
	}
	/**
	 * costruttore vuoto per l'xml
	 */
	public Opzione() {
		
	}
	/**
	 * costruttore per la creazione di una storia manuale
	 * @param idParagrafoPuntato
	 * @param descrizione
	 */
	public Opzione(int idParagrafoPuntato, String descrizione) {
		this.idParagrafoPuntato=idParagrafoPuntato;
		this.descrizione=descrizione;
	}

	public int getIdParagrafoPuntato() {
		return idParagrafoPuntato;
	}

	public void setIdParagrafoPuntato(int idParagrafoPuntato) {
		this.idParagrafoPuntato = idParagrafoPuntato;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return descrizione;
	}
	
	

}
