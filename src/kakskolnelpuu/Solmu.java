package kakskolnelpuu;
import java.util.ArrayList;

/**
 * Solmu-luokka, jota k�ytet��n 2-3-4 -puun yksittäisen solmun yll�pitoon.
 * @author Jani Anttonen
 */
public class Solmu {

    // Ei varmaan aivan välttämätön, mutta nopeuttaa käänteistä iterointia.
	private Solmu isa;

    // Listat jotka sisältävät solmun rakenteen.
	private ArrayList<Solmu> lapset = new ArrayList<Solmu>(4);
	private ArrayList<Integer> arvot = new ArrayList<Integer>(3);

	private static int testinumero = 2;

	/**
	 * Lis�� solmun lapsen
     * @pre indeksi < lapset.size() && arvot.size()==lapset.size()
	 * @param indeksi
	 * @param lapsi
	 */
	public void yhdistaLapsi(int indeksi, Solmu lapsi) {

        // Lisätään lapsisolmu määritettyyn indeksiin
        if (lapset.size()>indeksi)
		    lapset.set(indeksi,lapsi);
        else
            lapset.add(indeksi,lapsi);

        // Tyhjien lapsisolmujen tekoa (lehtisolmuille)
        if(lapsi != null)
			lapsi.asetaIsa(this);

	}

	/**
	 * Irrottaa ja palauttaa solmun lapsen
     * @pre indeksi < lapset.size()
	 * @param indeksi
	 * @return irrotettu solmu
	 */
	public Solmu irroitaLapsi(int indeksi) {

        // Tallennetaan indeksistä löytyvä lapsisolmu palautusta varten
		Solmu valiaikaisSolmu = lapset.get(indeksi);

        // Irrotetaan lapsisolmu indeksistä
		lapset.remove(indeksi);

        // Palauttaa irrotetun lapsisolmun
		return valiaikaisSolmu;

	}

	/**
	 * Etsii sy�tetty� avainta vastaavan arvon solmusta ja
	 * palauttaa sen indeksin solmussa.
     * @pre avain != null
	 * @param avain etsittävä arvo
	 * @return -1, jos arvoa ei löytynyt nykyisestä solmusta, 0 tai suurempi (indeksi), jos avainta vastaava arvo löytyi.
	 */
	public int etsiArvo(int avain) {

        // Iteroidaan arvot läpi.
        for (int j=0; j<arvot.size(); j++) {

            // Jos arvolle löytyi vastaavuus, palautetaan sen indeksi.
			if (arvot.get(j)==avain)
				return j;

		}

        // Arvoa ei löytynyt, palautetaan -1
		return -1;

	}


	/**
	 * Lisää uuden arvon Solmuun.
	 *
	 * Oletetaan, että solmu ei ole täysi.
	 *
	 * Tekee vertailuja nykyisiin arvoihin ja
	 * asettaa uuden arvon oikeaan paikkaan n�iden perusteella.
	 * @pre solmu johon lis�t��n uusi arvo on lehtisolmu ja siin� on tilaa uudelle datalle, int uusiArvo != null
	 * @post solmussa olevat arvot ovat suuruusj�rjestyksess�
	 * @param uusiArvo
	 * @return indeksi, johon arvo lisättiin
	 */
	public int lisaaArvo(Integer uusiArvo) {

        if (lapset.size()==0) {
            lapset.add(new Solmu());
        }
        lapset.add(new Solmu());

        // Jos solmu on tyhjä lehtisolmu, ei siirtoja tarvitse tehdä ennen lisäystä.
		if(arvot.size()==0){
			arvot.add(uusiArvo);
			return 0;
		}

        // Jos solmussa on yksi arvo
		else if(arvot.size()==1){

            // Jos uusi arvo on pienempi kuin solmun nykyinen arvo
			if(uusiArvo<arvot.get(0)){

                // Lisätään arvo listan alkuun. (Vanha siirtyy automaagisesti eteenpäin.)
				arvot.add(0, uusiArvo);

                // Palautetaan indeksi, johon arvo lisättiin
				return 0;

			}

            // Ei tarvitse siirtää, lisätään listan perään.
			else arvot.add(uusiArvo);

            // Palautetaan indeksi, johon arvo lisättiin.
			return 1;

		}

        // Jos solmussa on kaksi arvoa
		else {

            // Oletetaan vanhojen arvojen olevan suuruusjärjestyksessä
			Integer isompiVanhaArvo = arvot.get(1);
			Integer pienempiVanhaArvo = arvot.get(0);

            // Jos uusi arvo on suurempi kuin kumpikaan aiemmista arvoista
			if (uusiArvo>isompiVanhaArvo){

                // Lisätään arvo listan perään, koska se on suurin.
				arvot.add(uusiArvo);

                /*
                 * Indeksi 2 on suurin indeksi 2-3-4-puussa,
                 * ja tässä tapauksessa uuden arvon indeksi.
                 */
				return 2;

			}

            // Tarkistetaan onko uusi arvo "keskikokoinen"
			else if(uusiArvo > pienempiVanhaArvo){

                // Lisätään uusi arvo listan keskelle
				arvot.add(1, uusiArvo);

                // Palautetaan indeksi johon uusi arvo lisättiin.
				return 1;

			}

            // Jos uusi arvo tulee olemaan pienin solmun arvoista
			else {

                // Lisätään uusi arvo listan alkuun.
				arvot.add(0, uusiArvo);

                // Palautetaan indeksi johon uusi arvo lisättiin.
				return 0;

			}

        }
	}

	/**
	 * Poistaa Solmun suurimman arvon ja palauttaa sen
	 * @return Integer poistettu arvo
	 */
	public Integer poistaArvo() {

        /*
         * Solmu-luokka asettaa suurimman arvon oikealle (viimeiseksi),
         * joten poistetaan viimeisen indeksin arvo.
         */
		Integer valiaikainen = arvot.get(arvot.size()-1);

        // Poistetaan arvo, kun se on muistissa
		arvot.remove(arvot.size()-1);

        // Palautetaan poistettu arvo käyttäjälle.
	    return valiaikainen;

	}

    /**
     * Poistaa arvon määritetystä indeksistä
     * @return Integer poistettu arvo
     */
    public Integer poistaArvo(int IndX) {
        Integer valiaikainen = null;

        // Tarkistetaan, voiko poistoa edes tehdä määritetyssä indeksissä.
        if(IndX<=arvot.size()) {
            valiaikainen = arvot.get(IndX);
            // Poistetaan arvo, kun se on muistissa
            arvot.remove(IndX);
            return valiaikainen;
        }

        return valiaikainen;
    }


    /**
     * Tarkistaa, onko solmussa jo 3 arvoa.
     * @return true = solmussa kolme arvoa, false = solmussa vähemmän kuin kolme arvoa.
     */
    public boolean onkoTaysi(){
        if (arvot.size()==3){
            return true;
        }
        return false;
    }

	// Setterit ja getterit //

	public ArrayList<Integer> annaArvot() {
		return arvot;
	}

	public ArrayList<Solmu> annaLapset() {
		return lapset;
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
        for(Solmu lapsi : lapset){
            if(lapsi.annaArvot().size()!=0)
                return false;
        }
        return true;
	}
	

	public Integer annaArvo(int indeksi) {
		return arvot.get(indeksi);
	}

    public String toString() {

        String palautus = "";

        if (annaIsa()!=null) {
            //palautus = isa.annaArvot().toString();
            palautus += isa.toString();
            palautus += " -> ";
            int IndX = 0;
            for(Solmu skidi: isa.annaLapset()){
                if(skidi==this)
                    palautus += "lapsi nro " + IndX + " -> ";
                IndX++;
            }
        }

        for(int i=0;i<arvot.size();++i){
            palautus += lapset.get(i).annaArvot().toString();

            if (i==0)
                palautus += " / ";
            else
                palautus += " | ";

            palautus += arvot.get(i).toString();

            if (i+1==arvot.size())
                palautus += " \\ ";
            else
                palautus += " | ";
        }
        palautus += lapset.get(arvot.size()).annaArvot().toString();

        return palautus;

    }
}
