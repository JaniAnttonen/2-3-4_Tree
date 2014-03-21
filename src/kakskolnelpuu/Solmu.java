package kakskolnelpuu;
import java.util.LinkedList;


/**
 * Solmu-luokka, jota käytetään 2-3-4 -puun yksittäisen solmun ylläpitoon.
 * @author Jani Anttonen
 */
public class Solmu {

	private LinkedList<Object> arvot;
	private Solmu isa;

    /**
     * Solmun konstruktori, käytetään useimmissa tapauksissa.
     * @param isasolmu
     * @param arvo
     */
	public Solmu(Solmu isasolmu, Object arvo){
		arvot = new LinkedList<Object>();
		arvot.add(arvo);
		isa = isasolmu;
	}

    /**
     * Konstruktori, joka luo puun juurisolmun.
     * @param arvo
     */
	public Solmu(Object arvo){
		arvot = new LinkedList<Object>();
		arvot.add(arvo);
		isa = null;
	}

    /**
     * Konstruktori, jota käytetään tyhjän null-solmun luomiseen.
     * @param isasolmu
     */
    public Solmu(Solmu isasolmu){
        arvot = new LinkedList<Object>();
        arvot.add(null);
        isa = isasolmu;
    }



    // Setterit ja getterit //

    public LinkedList<Object> getArvot() {
		return arvot;
	}

	public void setArvot(LinkedList<Object> arvot) {
		this.arvot = arvot;
	}

	public Solmu getIsa() {
		return isa;
	}

	public void setIsa(Solmu isa) {
		this.isa = isa;
	}
}
