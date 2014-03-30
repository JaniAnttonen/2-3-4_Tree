package kakskolnelpuu;
import java.util.ArrayList;


/**
 * Solmu-luokka, jota k‰ytet‰‰n 2-3-4 -puun yksitt√§isen solmun yll‰pitoon.
 * @author Jani Anttonen
 */
public class Solmu {


	private Solmu isa;
	private ArrayList<Solmu> lapset = new ArrayList<Solmu>();
	private ArrayList<Integer> arvot = new ArrayList<Integer>();

	private static int testinumero = 2;

	/**
	 * Solmun konstruktori, k‰ytet‰‰n useimmissa tapauksissa.
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
		//		arvot.add(null);
		isa = null;
	}

	/**
	 * Konstruktori, jota k√§ytet√§√§n tyhj√§n null-solmun luomiseen.
	 * @param isasolmu
	 */
	public Solmu(Solmu isasolmu){
		arvot.add(null);
		isa = isasolmu;
	}

	/**
	 * Lis‰‰ solmun lapsen
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
	 * Etsii sy√∂tetty√§ avainta vastaavan arvon solmusta ja
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
	 * Lis√§√§ uuden arvon Solmuun.
	 *
	 * Oletetaan, ett√§ solmu ei ole t√§ysi.
	 *
	 * Tekee vertailuja nykyisiin arvoihin ja
	 * asettaa uuden arvon oikeaan paikkaan n‰iden perusteella.
	 * @param uusiArvo
	 * @return
	 */
	public int lisaaArvo(Integer uusiArvo) {

		if(arvot.size()==0){
			arvot.add(uusiArvo);
			return 0;
		}
		else if(arvot.size()==1){
			int vanhaArvo=arvot.get(0);
			if(uusiArvo<vanhaArvo){
				arvot.set(0, uusiArvo);
				arvot.add(vanhaArvo);
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

		return testinumero++;
		//        Integer valiaikainen = arvot.get(0);
		//        arvot.remove(0);
		//        return valiaikainen;
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
