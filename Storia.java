package la.storia.a.bivi;

import java.util.ArrayList;
import java.util.Random;

public class Storia {
	
	private String nome;
	private int numeroParagrafi;
	private ArrayList<Paragrafo> listaParagrafi=new ArrayList<Paragrafo>();
	private Random r=new Random();
	/**
	 * costruttore sia per la creazione casuale che manuale
	 * @param nome
	 * @param numeroParagrafi
	 */
	public Storia(String nome, int numeroParagrafi) {
		this.nome=nome;
		this.numeroParagrafi=numeroParagrafi;
	}
	/**
	 * costruttore per la lettura da XML
	 */
	public Storia() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroParagrafi() {
		return numeroParagrafi;
	}

	public void setNumeroParagrafi(int numeroParagrafi) {
		this.numeroParagrafi = numeroParagrafi;
	}

	public ArrayList<Paragrafo> getListaParagrafi() {
		return listaParagrafi;
	}

	public void setListaParagrafi(ArrayList<Paragrafo> listaParagrafi) {
		this.listaParagrafi = listaParagrafi;
	}
	
	public Paragrafo getParagrafoPerId(int id) {
		return listaParagrafi.get(id);
	}
	/**
	 * crea una storia in modo casuale
	 */
	public void creaStoriaCasuale() {
		for(int i=0;i<numeroParagrafi;i++) {
			//numero opzioni scelto casualmente tra 0 e 4
			int numeroOpzioni=Math.abs(r.nextInt()%Paragrafo.MASSIME_OPZIONI_SE_CASUALE);
			Paragrafo nuovoParagrafo=new Paragrafo(i,numeroOpzioni);
			listaParagrafi.add(nuovoParagrafo);
			for(int j=0;j<numeroOpzioni;j++) {
				Opzione nuovaOpzione = new Opzione(numeroParagrafi);
				nuovoParagrafo.aggiungiOpzione(nuovaOpzione);
			}
		}
	}
	/**
	 * richiamando il metodo aggiustaParagrafo procede ad aggiusta l'intera storia.
	 * Non è tuttavia garantita la presenza di paragrafi terminali
	 */
	public void aggiustaStoria() {
		for (Paragrafo paragrafo : listaParagrafi) {
			paragrafo.aggiustaParagrafo();
		}
	}
	
	public void visualizzaStoria() {
		System.out.println(nome);
		System.out.println("----------------");
		for(int i=0;i<numeroParagrafi;i++) {
			Paragrafo paragrafo = listaParagrafi.get(i);
			paragrafo.visualizzaParagrafo();
		}
	}
	/**
	 * metodo utile quando si vuole scegliere un'opzione, senza la visualizzazione
	 * del paragrafo l'utente sarebbe disorientato
	 * @param scelto
	 */
	public void visualizzaParagrafoScelto(Paragrafo scelto) {
		scelto.visualizzaParagrafo();
	}
	/**
	 * aggiunge un paragrafo all'indice posizione, utile nella lettura da XML
	 * @param nuovo
	 * @param posizione
	 */
	public void aggiungiParagrafo(Paragrafo nuovo, int posizione) {
		listaParagrafi.add(posizione,nuovo);
	}
	/**
	 * sostituisce un paragrafo con un altro, utile nella lettura da XML
	 * @param nuovo
	 * @param posizione
	 */
	public void sostituisciParagrafo(Paragrafo nuovo, int posizione) {
		listaParagrafi.set(posizione, nuovo);
	}

}
