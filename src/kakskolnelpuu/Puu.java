package kakskolnelpuu;
import java.util.Map;
import java.util.List;

/**
 * 2-3-4 -puun rakennetta ylläpitävä luokka.
 * Lisäys-, korjaus- ja poisto-operaatioiden toteutus yhteistyössä Solmu-luokan kanssa.
 *
 * @author Jani Anttonen
 * @author Miika Länsi-Seppänen
 */
public class Puu {

    /**
     * Kommentti
     */
     
    private Solmu root = new Solmu();

    /**
     * Tyhjän puun konstruktori, loput tehdään metodeilla.
     */
	public Puu(){}
	
    /**
     * Kommentti
     */
     
    public int etsi(Integer avain){
    	Solmu nykyinenSolmu = root;
    	int lapsiIndeksi;
    	while (true)
    	 {
    	 	if((lapsiIndeksi=nykyinenSolmu.etsiArvo(avain)) != -1)
    	 		return lapsiIndeksi;
    	 	else if(nykyinenSolmu.onkoLehti())
    	 		return -1;
    	 	else
    	 		nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, avain);
    	 }
    }
    
    /**
     * Etsii solmun johon syötetty arvo kuuluu,
     * ja lisää sen sinne Solmu-luokan metodia käyttäen.
     * @param arvo
     */
    public void lisaa(Integer arvo){
    	Solmu nykyinenSolmu = root;
    	
    	while(true){
    		if(nykyinenSolmu.onkoTaysi())
    			{
    			halkaiseSolmu(nykyinenSolmu);
    			nykyinenSolmu = nykyinenSolmu.annaIsa();
    				
    			nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, arvo);
    			}
    			
    		else if(nykyinenSolmu.onkoLehti())
    			break;
    		
    		else
    			nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, arvo);
    	}
    	
    	nykyinenSolmu.lisaaArvo(arvo);
    }
    
    /**
     * Hei
     */
    
    public void halkaiseSolmu(Solmu nykyinenSolmu){
    	Integer intB, intC;
    	Solmu isa, lapsi2, lapsi3;
    	int arvoIndeksi;
    	
    	intC = nykyinenSolmu.poistaArvo();
    	intB = nykyinenSolmu.poistaArvo();
    	lapsi2 = nykyinenSolmu.irroitaLapsi();
    	lapsi3 = nykyinenSolmu.irroitaLapsi();
    	
    	Solmu uusiOikea = new Solmu();
    	
    	if(nykyinenSolmu==root){
    		root = new Solmu();
    		isa = root;
    		root.yhdistaLapsi(0, nykyinenSolmu);
    	}
    	
    	else
    		isa = nykyinenSolmu.annaIsa();
    	
    	arvoIndeksi = isa.lisaaArvo(intB);
    	int n = isa.annaKoko();
    	
    	for( int j=n-1; j>arvoIndeksi; j--){
    		Solmu valiaikainen = isa.irroitaLapsi(j);
    		isa.yhdistaLapsi(j+1, valiaikainen);
    	}
    	
    	isa.yhdistaLapsi(arvoIndeksi+1, uusiOikea);
    	
    	uusiOikea.lisaaArvo(intC);
    	uusiOikea.yhdistaLapsi(0, lapsi2);
    	uusiOikea.yhdistaLapsi(1, lapsi3);
    }
    
     /**
     * Hei
     */
    
    public Solmu etsiSeuraavaLapsi(Solmu nykyinenSolmu, Integer nykyinenArvo){
    	int j;
    	
    	int koko = nykyinenSolmu.annaKoko();
    	for(j=0; j<koko; j++){
    		if(nykyinenArvo < nykyinenSolmu.annaArvo(j)){
    			return nykyinenSolmu.annaLapsi(j);
    		}
    		
    		return nykyinenSolmu.annaLapsi(j);
    	}
    }
}
