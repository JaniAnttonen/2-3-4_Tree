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
            lapsiIndeksi = nykyinenSolmu.etsiArvo(avain);
			if(lapsiIndeksi!=-1)
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

        // Lähdetään etsimään oikeaa paikkaa arvolle rootista
		Solmu nykyinenSolmu = root;

        // UI-tekstiä
        System.out.println("Lisätään arvo " + arvo);

        // Etsitään arvolle oikea paikka puusta
		nykyinenSolmu = etsiSolmuJohonVoiLisataArvon(nykyinenSolmu, arvo);

        // Lisätään arvo edellisen komennon määrittämään paikkaan
		nykyinenSolmu.lisaaArvo(arvo);

        // Tulostetaan käyttäjälle polku nykyiseen puuhun
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

		while (true) {
            if (nykyinenSolmu.onkoTaysi()) {
                halkaiseSolmu(nykyinenSolmu);
                nykyinenSolmu = nykyinenSolmu.annaIsa();
                nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, arvo);
            }
            else if (nykyinenSolmu.onkoLehti())
                break;
            else
                nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, arvo);
        }

        return nykyinenSolmu;
	}

    /**
     * Etsii puusta vastaavuutta syötetylle arvolle, poistaa sen ja
     * suorittaa mahdolliset korjausoperaatiot.
     * @param arvo
     * @return true/false riippuen siitä, tapahtuiko poisto
     */
    public boolean poista(int arvo) {

        // Lähdetään etsimään arvoa puun juurisolmusta lähtien.
        Solmu nykyinenSolmu = root;
        int lapsiIndeksi;

        // Alustetaan apumuuttujat
        Solmu arvonSolmu = nykyinenSolmu;
        int arvoOsoite;

        // Etsitään solmu jossa poistettava arvo sijaitsee.
        while(true) {
            lapsiIndeksi = nykyinenSolmu.etsiArvo(arvo);

            /*
             * Jos vastaavuus löytyi, otetaan koordinaatit
             * ylös ja lopetetaan suoritus
             */
            if(lapsiIndeksi!=-1) {
                arvoOsoite = lapsiIndeksi;
                arvonSolmu = nykyinenSolmu;
                break;
            }

            /*
             * Jos kohdattu solmu on lehtisolmu,
             * eikä samaa arvoa puusta löydetty, lopetetaan suoritus
             */
            else if(nykyinenSolmu.onkoLehti()) {
                arvoOsoite = -1;
                break;
            }

            // Nykyisestä solmusta ei löytynyt vastaavaa arvoa, jatketaan suoritusta
            else
                nykyinenSolmu = etsiSeuraavaLapsi(nykyinenSolmu, arvo);
        }

        // Tulokseton etsintä, lopetetaan suoritus.
        if (arvoOsoite==-1) {
            System.out.println("Poistettavaa arvoa ei löytynyt puusta, ei muuteta mitään.");
            return false;
        }

        /*
         * Jos solmulla, jossa haettu/poistettava arvo sijaitsee,
         * on lapsisolmuja, pitää korjausoperaatioita tehdä.
         */
        else if (!arvonSolmu.onkoLehti()) {
            System.out.println("Poisto-operaatiota ei voida tehdä suoraan, tehdään korjauksia.");

            // Haetaan poistettua arvoa lähin arvo
            int seuraaja = etsiSeuraaja(arvonSolmu, arvo);
        }

        /*
         * Jos solmu on lehti, jossa on enemmän kuin yksi arvo,
         * voidaan siitä poistaa suoraan haluttu arvo ilman korjausoperaatioita.
         */
        else if(arvonSolmu.annaArvot().size()>1)
                arvonSolmu.poistaArvo(arvoOsoite);


        // Testausjutut ja palautus onnistuneesta poistosta.
        System.out.println("Poistetaan arvo " + arvo);
        System.out.println(arvonSolmu.toString());
        return true;
    }

    /**
     * Halkaisee syötetyn täyden solmun.
     * @pre täysi halkaistavaSolmu
     * @param halkaistavaSolmu
     */
	public void halkaiseSolmu(Solmu halkaistavaSolmu){

        // Apumuuttujien alustusta
		Integer intB, intC;
		Solmu isa, lapsi2, lapsi3;
		int arvoIndeksi;

        /*
         * Koska täysi solmu sisältää kolme arvoa,
         * pitää kaksi niistä ottaa talteen halkaistaessa
         */
		intB = halkaistavaSolmu.poistaArvo();
		intC = halkaistavaSolmu.poistaArvo();

		/*
		 * Koska täysi solmu sisältää neljä lapsisolmua,
		 * pitää kaksi niistä ottaa talteen halkaistaessa
		 */
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
		arvoIndeksi = isa.lisaaArvo(intC);
        // Mikä on isäsolmun koko?
		int n = isa.annaArvot().size();


		for( int j=n-1; j>arvoIndeksi; j--){
			Solmu valiaikainen = isa.irroitaLapsi(j);
			isa.yhdistaLapsi(j+1, valiaikainen);
		}

		isa.yhdistaLapsi(arvoIndeksi+1, uusiOikea);

		uusiOikea.lisaaArvo(intB);
		uusiOikea.yhdistaLapsi(0, lapsi2);
		uusiOikea.yhdistaLapsi(1, lapsi3);

	}

	/**
	 * Vertailee ennaltaolevia arvoja uuteen arvoon ja päättää sen paikan puussa.
	 *
     * 2-3-4-Puun luontoon kuuluen, alotetaan täyttö aina puun pohjalta, joten siksi
     * puu etsii viimeistä lasta, johon arvo voitaisiin lisätä.
     * @pre nykyinenSolmu ei saa olla täysi eikä lehtisolmu.
	 * @param nykyinenSolmu
	 * @param vertailtavaArvo
	 * @return Lapsisolmu, johon vertailtavaArvo sopii
	 */
	public Solmu etsiSeuraavaLapsi(Solmu nykyinenSolmu, int vertailtavaArvo){

        // Alustetaan tarvittavat apumuuttujat
		int j=0;
        int koko = nykyinenSolmu.annaArvot().size();

        // Iteroidaan koko nykyistä solmua
        while(j<koko){

            /*
             * Jos arvo on pienempi kuin vertailtu arvo,
             * palautetaan arvon osoitteeksi vertailtavaa edeltävä lapsisolmu.
             */
			if(vertailtavaArvo < nykyinenSolmu.annaArvo(j)){
				return nykyinenSolmu.annaLapsi(j);
			}
            j++;
		}

        /*
         * Jos arvo on suurempi kuin kaikki vertaillut arvot,
         * palautetaan arvon osoitteeksi solmun viimeinen lapsisolmu.
         */
		return nykyinenSolmu.annaLapsi(j);
	}

    public int etsiSeuraaja(Solmu nykyinenSolmu, int vertailtava) {
return 1;
    }

    public Solmu annaJuuri() {
        return root;
    }
}
