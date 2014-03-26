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
     */
	public Solmu(){
		arvot.add(null);
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

    /**
     * Etsii syötettyä avainta vastaavan arvon solmusta ja
     * palauttaa sen indeksin solmussa.
     * @param avain
     * @return
     */
    public int etsiArvo(Integer avain) {
        for (int j=0; j<3; j++) {
            if (arvot.get(j)==null)
                break;
            else if (arvot.get(j)==avain)
                return j;
        }
        return -1;
    }

    /**
     * Lisää uuden arvon Solmuun.
     *
     * Oletetaan, että solmu ei ole täysi.
     *
     * Tekee vertailuja nykyisiin arvoihin ja
     * asettaa uuden oikeaan paikkaan näiden perusteella.
     * @param uusiArvo
     * @return
     */
    public int lisaaArvo(Integer uusiArvo) {
        koko++;

        for (int j=2; j>=0; j--) {
            if (arvot.get(j)==null)
                continue;
            else {
                Integer muistiArvo = arvot.get(j);
                if (uusiArvo < muistiArvo)
                    arvot.set(j+1,muistiArvo);
                else {
                    arvot.set(j+1,uusiArvo);
                    return j+1;
                }
            }
        }

        arvot.set(0,uusiArvo);
        return 0;
    }

    /**
     * Poistaa Solmun suurimman arvon ja palauttaa sen
     * @return Integer poistettu arvo
     */
    public Integer poistaArvo() {
        Integer valiaikainen = arvot.get(koko-1);
        arvot.set(koko-1,null);
        koko--;
        return valiaikainen;
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
