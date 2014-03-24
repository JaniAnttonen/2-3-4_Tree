package kakskolnelpuu;
import java.util.Map;
import java.util.List;

/**
 * 2-3-4 -puun rakennetta ylläpitävä luokka.
 * Lisäys-, korjaus- ja poisto-operaatioiden toteutus yhteistyössä Solmu-luokan kanssa.
 *
 * @author Jani Anttonen
 */
public class Puu {

    /**
     * Muuttujat, jotka sisältävät tiedot puun syvyydestä ja solmut yhteyksineen.
     * (Solmun yhteyksien toteutus tapahtuu Solmu-luokassa)
     */
    private List<Solmu> taso;
	private Map<Integer, List<Solmu>> puu;

    /**
     * Tyhjän puun konstruktori, loput tehdään metodeilla.
     */
	public Puu() {
        taso = new List<Solmu>();
        puu = new Map<Integer, List<Solmu>>();
	}
	
    /**
     * Kommentti
     */
     
    public int etsi(Integer){
    	Solmu nykyinenSolmu = root;
    	int lapsiIndeksi;
    	while (true)
    	 {
    	 	if((lapsiIndeksi=nykyinenSolmu.etsiArvo(Integer)) != -1)
    	 		return lapsiIndeksi;
    	 	else if(nykyinenSolmu.onkoLehti())
    	 		return -1;
    	 	else
    	 		nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, Integer);
    	 }
    }

    /**
     * Lisää syötetyn arvon oikeaan paikkaan puussa, ja
     * toteuttaa mahdolliset korjausoperaatiot.
     * @param arvo
     */
    public void lisaaSolmu(Integer arvo) {

    }

    /**
     * Poistaa syötetyn arvon puusta (ei välitä duplikaateista),
     * ja toteuttaa mahdolliset korjausoperaatiot.
     * @param arvo
     */
    public void poistaSolmu(Integer arvo) {

    }



    // Setterit ja getterit //

	public Map<Integer,List<Solmu>> getPuu() {
		return puu;
	}

	public void setPuu(Map<Integer,List<Solmu>> puu) {
		this.puu = puu;
	}
	
}
