package kakskolnelpuu;
import java.util.ArrayList;

/**
 * Solmu-luokka, jota k�ytet��n 2-3-4 -puun yksittäisen solmun yll�pitoon.
 * @author Jani Anttonen
 */
public class Solmu {

	private Solmu isa;
	private ArrayList<Solmu> lapset = new ArrayList<Solmu>();
	private ArrayList<Integer> arvot = new ArrayList<Integer>();

	private static int testinumero = 2;

	/**
	 * Lis�� solmun lapsen
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
		lapset.remove(indeksi);
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
	 * asettaa uuden arvon oikeaan paikkaan n�iden perusteella.
	 * @param uusiArvo
	 * @return
	 */
	public int lisaaArvo(Integer uusiArvo) {

		if(arvot.size()==0){
			arvot.add(uusiArvo);
			return 0;
		}
		else if(arvot.size()==1){

			if(uusiArvo<arvot.get(0)){

                // Siirtää vanhan ja suuremman arvon oikealle listassa.
                arvot.add(1, arvot.get(0));

                // Ylikirjoitetaan vanha arvo indeksissä 0 uudella arvolla.
				arvot.set(0, uusiArvo);

                // Derp
				return 0;
			}
			else arvot.add(uusiArvo);
			return 1;
		}
		else {
			int isompiVanhaArvo = arvot.get(1);
			int pienempiVanhaArvo = arvot.get(0);
			if (uusiArvo>isompiVanhaArvo){
				arvot.add(uusiArvo);
				return 2;
			}
			else if(uusiArvo<isompiVanhaArvo && uusiArvo > pienempiVanhaArvo){
				arvot.add(isompiVanhaArvo);
				arvot.set(0, pienempiVanhaArvo);
				arvot.set(1, uusiArvo);
				return 1;
			}
			else {
				arvot.add(isompiVanhaArvo);
				arvot.set(1, pienempiVanhaArvo);
				arvot.set(0, uusiArvo);
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
		return lapset.isEmpty();
	}

	public Integer annaArvo(int indeksi) {
		return arvot.get(indeksi);
	}

	public boolean onkoTaysi(){
		if (arvot.size()+1 < lapset.size() && lapset.size()<=4){
			return false;
		}
		return true;
	}
}
