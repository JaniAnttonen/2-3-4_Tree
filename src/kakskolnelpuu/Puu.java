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
     * Moi
     */
    
    public void lisää(Integer){
    	Solmu nykyinenSolmu = root;
    	
    	while(true){
    		if(nykyinenSolmu.onkoTaysi())
    			{
    			halkaise(nykyinenSolmu);
    			nykyinenSolmu = nykyinenSolmu.haeIsa();
    				
    			nykyinenSolmu = haeSeuraavaLapsi(nykyinenSolmu, Integer);
    			}
    			
    		else if(nykyinenSolmu.onkoLehti())
    			break;
    		
    		else
    			nykyinenSolmu = haeSeuraavaLapsi(nykyinenSolmu, Integer);
    	}
    	
    	nykyinenSolmu.lisaaData(Integer);
    }
    
    /**
     * Hei
     */
    
    public void halkaiseSolmu(Solmu nykyinenSolmu){
    	Integer intB, intC;
    	Solmu isa, lapsi2, lapsi3;
    	int arvoIndeksi;
    	
    	intC = tamaSolmu.poistaData();
    	intB = tamaSolmu.poistaData();
    	lapsi2 = tamaSolmu.irroitaLapsi();
    	lapsi3 = tama.Solmu.irroitaLapsi();
    	
    	Solmu uusiOikea = new Solmu();
    	
    	if(tamaSolmu==root){
    		root = uusi Solmu();
    		isa = root;
    		root.yhdistaLapsi(0, tamaSolmu);
    	}
    	
    	else
    		isa = tamaSolmu.annaIsa
    	
    	arvoIndeksi = isa.lisaaData(intB);
    	int n = isa.annaKoko();
    	
    	for( int j=n-1; j>arvoIndeksi; j--){
    		Solmu valiaikainen = isa.irroitaLapsi(j);
    		isa.yhdistaLapsi(j+1, valiaikainen);
    	}
    	
    	isa.yhdistaLapsi(arvoIndeksi+1, uusiOikea);
    	
    	uusiOikea.lisaaData(intC);
    	uusiOikea.yhdistaLapsi(0, lapsi2);
    	uusiOikea.yhdistaLapsi(1, lapsi3);
    }
    
     /**
     * Hei
     */
    
    public Solmu haeSeuraavaLapsi(Solmu nykyinenSolmu, Integer nykyinenArvo){
    	int j;
    	
    	int koko = nykyinenSolmu.annaKoko();
    	for(j=0; j<koko; j++){
    		if(nykyinenArvo < nykyinenSolmu.annaArvo(j)){
    			return nykyinenSolmu.annaLapsi(j);
    		}
    		
    		return nykyinenSolmu.annaLapsi(j);
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
