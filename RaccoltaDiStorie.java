package la.storia.a.bivi;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class RaccoltaDiStorie {
	
	private Map<String,Storia> raccolta=new HashMap<String,Storia>();//utile per la ricerca per titolo
	private LinkedList<Storia> raccoltaDiStorie=new LinkedList<Storia>();
	
	public RaccoltaDiStorie() {
		
	}
	/**
	 * aggiunge una storia alla mappa e alla lista
	 * @param titolo
	 * @param storia
	 */
	public void aggiungiStoria(String titolo,Storia storia) {
		raccolta.put(titolo,storia);
		raccoltaDiStorie.add(storia);
	}
	
	public Map<String, Storia> getRaccolta() {
		return raccolta;
	}

	public void setRaccolta(Map<String, Storia> raccolta) {
		this.raccolta = raccolta;
	}

	public LinkedList<Storia> getRaccoltaDiStorie() {
		return raccoltaDiStorie;
	}

	public void setRaccoltaDiStorie(LinkedList<Storia> raccoltaDiStorie) {
		this.raccoltaDiStorie = raccoltaDiStorie;
	}

	public void visualizzaTitoli() throws Exception {
		int i=1;//li visualizza da 1 a raccoltaDiStorie.size();
		if(raccoltaDiStorie.size()>0) {
			for (Storia storia : raccoltaDiStorie) {
				System.out.println(i+"."+storia.getNome());
				i++;
			}
		}
		else
			throw new Exception();//nel caso non ci siano storie
	}
	/**
	 * fa giocare una storia sulla base del numero premuto dall'utente
	 * @param numero
	 * @return
	 * @throws NullPointerException
	 */
	public Storia giocaStoria(int numero) throws NullPointerException {
		if(numero>raccoltaDiStorie.size() || numero<0)
			throw new NullPointerException();
		else
			return raccoltaDiStorie.get(numero);
	}
	/**
	 * cerca una storia nella mappa sulla base del suo titolo, se non è presente
	 * visualizza un messaggio di errore
	 * @param titolo
	 * @return
	 */
	public String cercaStoria(String titolo) {
		if(raccolta.containsKey(titolo)) {
			Storia storia=raccolta.get(titolo);
			return storia.getNome()+" in posizione "+raccoltaDiStorie.indexOf(storia);
		}
		return "Storia inesistente, magari creala";
	}
	
	public int getNumeroStorie() {
		return raccoltaDiStorie.size();
	}
	/**
	 * quick sort per l'ordine alfabetico
	 * @param storie
	 * @return
	 */
	public LinkedList<Storia> quickSortTitoli(LinkedList<Storia> storie) {
		if(storie.size()<=1)
			return storie;
		else {
			Storia pivot=storie.get(storie.size()/2);
			LinkedList<Storia> subList1=new LinkedList<Storia>();
			LinkedList<Storia> subList2=new LinkedList<Storia>();
			for(int i=0;i<storie.size();i++) {
				if(i==storie.size()/2)
					continue;
				if(pivot.getNome().compareTo(storie.get(i).getNome())<0)//riempi i minori
					subList2.add(storie.get(i));
				else
					subList1.add(storie.get(i)); //riempi i maggiori
			}
			LinkedList<Storia> risultato=new LinkedList<Storia>();
			LinkedList<Storia> minori=quickSortTitoli(subList1);
			LinkedList<Storia> maggiori=quickSortTitoli(subList2);
			risultato.addAll(minori); risultato.add(pivot); risultato.addAll(maggiori);
			return risultato;
		}
	}
	/**
	 * quick sort per il numero di paragrafi di una storia
	 * @param storie
	 * @return
	 */
	public LinkedList<Storia> quickSortNumeroParagrafi(LinkedList<Storia> storie) {
		if(storie.size()<=1)
			return storie;
		else {
			Storia pivot=storie.get(storie.size()/2); //prendi il pivot al centro
			LinkedList<Storia> subList1=new LinkedList<Storia>();//lista con maggiori
			LinkedList<Storia> subList2=new LinkedList<Storia>();//lista con minori
			for(int i=0;i<storie.size();i++) {
				if(i==storie.size()/2)
					continue;
				if(pivot.getNumeroParagrafi()<storie.get(i).getNumeroParagrafi())
					subList2.add(storie.get(i));
				else
					subList1.add(storie.get(i));
			}
			LinkedList<Storia> risultato=new LinkedList<Storia>();
			LinkedList<Storia> minori=quickSortNumeroParagrafi(subList1);
			LinkedList<Storia> maggiori=quickSortNumeroParagrafi(subList2);
			risultato.addAll(minori); risultato.add(pivot); risultato.addAll(maggiori);
			return risultato;
		}
	}
	
	

}
