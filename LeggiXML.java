package la.storia.a.bivi;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class LeggiXML {
	/**
	 * legge una storia da un XML
	 * @param filename
	 * @return
	 */
	public Storia leggiStoria(String filename) {
		try {
			
			XMLInputFactory xmlif=XMLInputFactory.newInstance();
	        XMLStreamReader xmlr = xmlif.createXMLStreamReader(filename,
	                                   new FileInputStream(filename));
	        Storia storia=new Storia();
	        int contaParagrafi=-1;//conta tutti i paragrafi, parte da -1 per evitare errori
	        int contaOpzioni=0;//conta le opzioni in un paragrafo. Ogni volta che se ne crea uno nuovo riparte da 0
	        while(xmlr.hasNext()) {
	            switch(xmlr.getEventType()) {
	            case XMLStreamConstants.START_ELEMENT:{
	            	switch(xmlr.getLocalName()) {//sulla base dei nomi dei tag fa cose diverse
	            	case "story":{//crea un oggetto Storia
	            		String titolo=xmlr.getAttributeValue(0);//esiste un metodo più efficiente?
	            		int numeroParagrafi=Integer.parseInt(xmlr.getAttributeValue(1));
	            		storia.setNome(titolo);
	            		storia.setNumeroParagrafi(numeroParagrafi);
	            		break;
	            	}
	            	case "paragraph":{//crea un oggetto Paragrafo e lo aggiunge alla storia
	            		contaParagrafi++;//ecco perché parte da -1
	            		contaOpzioni=0;
	            		Paragrafo paragrafo=new Paragrafo();
	            		int id=Integer.parseInt(xmlr.getAttributeValue(0));
	            		paragrafo.setId(id);
	            		storia.aggiungiParagrafo(paragrafo,contaParagrafi);//lo aggiunge in una certa posizione
	            		break;
	            	}
	            	case "description":{//genera la descrizione del paragrafo
	            		//approfitto del fatto che description è "figlio" di paragraph
	            		Paragrafo selezionato=storia.getParagrafoPerId(contaParagrafi);
	            		xmlr.next();//vai avanti così leggi il testo
	            		String testo=xmlr.getText();
	            		if(testo.trim().length()>0) {
	            			selezionato.setDescrizione(testo);
	            		}
	            		//siccome è stato modificato il paragrafo viene riaggiunto alla storia
	            		storia.sostituisciParagrafo(selezionato, contaParagrafi);
	            		break;
	            	}
	            	case "option":{//crea un'opzione e la aggiunge al paragrafo
	            		contaOpzioni++;
	            		Opzione opzione=new Opzione();
	            		Paragrafo paragrafo=storia.getParagrafoPerId(contaParagrafi);
	            		int linkTo=Integer.parseInt(xmlr.getAttributeValue(0));
	            		xmlr.next();//vai avanti in modo da leggere il testo
	            		String descrizione=xmlr.getText();
	            		if(descrizione.trim().length()>0)
	            			opzione.setDescrizione(descrizione);
	            		opzione.setIdParagrafoPuntato(linkTo);
	            		paragrafo.aggiungiOpzione(opzione);
	            		paragrafo.setNumeroOpzioni(contaOpzioni);
	            		//stesso discorso del case "description"
	            		storia.sostituisciParagrafo(paragrafo, contaParagrafi);
	            		break;
	            	}
	            	}
	            	break;
	            }
	            default:
	            	break;
	            }
	            xmlr.next();
	        }
	        return storia;
	    }catch(Exception e){
	    	System.err.println("Error detected");
	    	System.err.println(e.getMessage());
	    	return null;
	    }
	}

}
