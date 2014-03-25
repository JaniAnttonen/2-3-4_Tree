package kakskolnelpuu;
import java.util.ArrayList;


/**
 * Solmu-luokka, jota käytetään 2-3-4 -puun yksittäisen solmun ylläpitoon.
 * @author Jani Anttonen
 */
public class Solmu {

    private int koko;
    private Solmu isa;
    private ArrayList<Solmu> lapset = new ArrayList<Solmu>();
    private ArrayList<Integer> arvot = new ArrayList<Integer>();

    /**
     * Solmun konstruktori, käytetään useimmissa tapauksissa.
     * @param isasolmu
     * @param arvo
     */
	public Solmu(Solmu isasolmu, Integer arvo){
		arvot.add(arvo);
		isa = isasolmu;
	}

    /**
     * Konstruktori, joka luo puun juurisolmun.
     * @param arvo
     */
	public Solmu(Integer arvo){
		arvot.add(arvo);
		isa = null;
	}

    /**
     * Konstruktori, jota käytetään tyhjän null-solmun luomiseen.
     * @param isasolmu
     */
    public Solmu(Solmu isasolmu){
        arvot.add(null);
        isa = isasolmu;
    }

    /**
     * Lisää solmun lapsen
     * @param indeksi
     * @param lapsi
     */
    public void yhdistaLapsi(int indeksi, Solmu lapsi) {
        lapset.add(indeksi,lapsi);
        if(lapsi != null)
            lapsi.isa = this;
    }

    /**
     * Irrottaa ja palauttaa solmun lapsen
     * @param indeksi
     * @return irrotettu solmu
     */
    public Solmu irroitaLapsi(int indeksi) {
        Solmu valiaikaisSolmu = lapset.get(indeksi);
        lapset.set(indeksi, null);
        return valiaikaisSolmu;
    }



    // Setterit ja getterit //

    public ArrayList<Integer> annaArvot() {
		return arvot;
	}

	public void asetaArvot(ArrayList<Integer> arvot) {
		this.arvot = arvot;
	}

	public Solmu annaIsa() {
		return isa;
	}

	public void asetaIsa(Solmu isa) {
		this.isa = isa;
	}

    public Solmu annaLapsi(int indeksi) {
        return lapset.get(indeksi);
    }

    public boolean onkoLehti() {
        return lapset.isEmpty();
    }

    public int annaKoko() {
        return koko;
    }

    public Integer annaArvo(int indeksi) {
        return arvot.get(indeksi);
    }

    public boolean onkoTaysi()
}
