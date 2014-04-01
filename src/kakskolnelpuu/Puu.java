package kakskolnelpuu;

/**
 * 2-3-4 -puun rakennetta ylläpitävä luokka.
 * Lis�ys-, korjaus- ja poisto-operaatioiden toteutus yhteisty�ss� Solmu-luokan kanssa.
 *
 * @author Jani Anttonen
 * @author Miika L�nsi-Sepp�nen
 */
public class Puu {

	private Solmu root = new Solmu();

    /**
     * Iteroi puuta kunnes löytää solmusta etsityn avaimen.
     * @param avain
     * @return 
     */
	public int etsi(int avain){
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
	 * Etsii puusta solmun johon sy�tetty arvo kuuluu,
	 * ja lis�� sen sinne Solmu-luokan metodia k�ytt�en.
	 * @param arvo
	 */
	public void lisaaArvoPuuhun(int arvo){
		Solmu nykyinenSolmu = root;
		nykyinenSolmu = etsiSolmuJohonVoiLisataArvon(nykyinenSolmu, arvo);
		nykyinenSolmu.lisaaArvo(arvo);
        System.out.println(nykyinenSolmu.toString());
	}

	/**
	 * Etsii puusta solmun, johon voi lis�t� annetun arvon.
	 * 
	 * @author Arttu Laitinen
	 * @param nykyinenSolmu
	 * @param arvo
	 * @return Solmu
	 */
	public Solmu etsiSolmuJohonVoiLisataArvon(Solmu nykyinenSolmu, int arvo){
		
		if (nykyinenSolmu.onkoLehti()){
			if(nykyinenSolmu.onkoTaysi()){
                System.out.println(nykyinenSolmu.toString());
				halkaiseSolmu(nykyinenSolmu);
			}
			else return nykyinenSolmu;
		}
		else nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, arvo);
		return etsiSolmuJohonVoiLisataArvon(nykyinenSolmu, arvo);
	}

	public void halkaiseSolmu(Solmu halkaistavaSolmu){
		Integer intB, intC;
		Solmu isa, lapsi2, lapsi3;
		int arvoIndeksi;

		intC = halkaistavaSolmu.poistaArvo();
		intB = halkaistavaSolmu.poistaArvo();

		//System.out.println("lasten m��r� = "+halkaistavaSolmu.annaLapset().size());
		
		//System.out.println(halkaistavaSolmu.annaLapset().size());
		lapsi3 = halkaistavaSolmu.irroitaLapsi(2); //lapsi3 = nykyinenSolmu -solmun lapsi indeksill� 2
		lapsi2 = halkaistavaSolmu.irroitaLapsi(1); //lapsi3 = nykyinenSolmu -solmun lapsi indeksill� 1


		Solmu uusiOikea = new Solmu();

		if(halkaistavaSolmu==root){
			root = new Solmu();
			isa = root;
			root.yhdistaLapsi(0, halkaistavaSolmu);
		}

		else
			isa = halkaistavaSolmu.annaIsa();

        // Lisätään halkaistun solmun keskimmäisin arvo isäsolmuun ja otetaan sen indeksi isäsolmussa.
		arvoIndeksi = isa.lisaaArvo(intB);
        // Mikä on isäsolmun koko?
		int n = isa.annaArvot().size();


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
	 * Etsii lapsista oikean solmun
	 * 
	 * @param nykyinenSolmu
	 * @param vertailtavaArvo
	 * @return
	 */
	public Solmu etsiSeuraavaLapsi(Solmu nykyinenSolmu, int vertailtavaArvo){
		int j=0;

		int koko = nykyinenSolmu.annaArvot().size();
		while(j<koko-1){
			if(vertailtavaArvo < nykyinenSolmu.annaArvo(j)){
				return nykyinenSolmu.annaLapsi(j);
			}
            j++;
		}
		return nykyinenSolmu.annaLapsi(j);
	}
}
