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

            System.out.println(arvonSolmu.toString());
            System.out.println("Poisto-operaatiota ei voida tehdä suoraan, tehdään korjauksia.");

            int seuraaja = etsiSeuraaja(arvonSolmu, arvoOsoite);

            /*
            To remove a value from the 2–3–4 tree:
Find the element to be deleted.
If the element is not in a leaf node, remember its location and continue searching until a leaf,
which will contain the element’s successor, is reached. The successor can be either the largest key that is
smaller than the one to be removed, or the smallest key that is larger than the one to be removed.

If the element is in a 2-node leaf, just make the adjustments below.
Make the following adjustments when a 2-node – except the root node – is encountered on the way to the leaf we want to remove:
If a sibling on either side of this node is a 3-node or a 4-node (thus having more than 1 key),
perform a rotation with that sibling:
The key from the other sibling closest to this node moves up to the parent key that overlooks the two nodes.
The parent key moves down to this node to form a 3-node.
The child that was originally with the rotated sibling key is now this node's additional child.
If the parent is a 2-node and the sibling is also a 2-node,
combine all three elements to form a new 4-node and shorten the tree.
(This rule can only trigger if the parent 2-node is the root, since all other 2-nodes along the way will have been modified
to not be 2-nodes. This is why "shorten the tree" here preserves balance;
this is also an important assumption for the fusion operation.)

If the parent is a 3-node or a 4-node and all siblings are 2-nodes, do a fusion operation with the parent and an adjacent sibling:
The adjacent sibling and the parent key overlooking the two sibling nodes come together to form a 4-node.
Transfer the sibling's children to this node.
Once the sought value is reached, it can now be placed at the removed entry's location without a problem because we have ensured that the leaf node has more than 1 key.
             */
            /*
             * Jos solmussa on vain yksi arvo ja
             * sillä on täten kaksi lapsisolmua
             */
            if (arvonSolmu.annaArvot().size()==1){
                System.out.println(arvonSolmu.toString());
            }

            else{
                // Haetaan poistettua arvoa lähin arvo
                arvonSolmu.irroitaLapsi(arvoOsoite);
                arvonSolmu.poistaArvo(arvoOsoite);
                arvonSolmu.lisaaArvo(seuraaja);
                System.out.println(arvonSolmu.toString());
            }

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

        // Tarvitsemme uuden solmun. Tämä on se.
		Solmu uusiOikea = new Solmu();

        /*
         * Tilanteessa, jossa halkaistava solmu on juurisolmu,
         * pitää luoda uusi juurisolmu.
         */
		if(halkaistavaSolmu==root){
			root = new Solmu();
			isa = root;
			root.yhdistaLapsi(0, halkaistavaSolmu);
		}

        /*
         * Jos halkaistava solmu ei ole juurisolmu,
         * asetetaan uuden solmun isäksi sama kuin vanha.
         */
		else
			isa = halkaistavaSolmu.annaIsa();

        // Lisätään halkaistun solmun keskimmäisin arvo isäsolmuun ja otetaan sen indeksi isäsolmussa.
		arvoIndeksi = isa.lisaaArvo(intC);
        // Mikä on isäsolmun koko?
		int n = isa.annaArvot().size();

        // Siirretään isän lapsia yhdellä indeksillä eteenpäin
		for( int j=n-1; j>arvoIndeksi; j--){
			Solmu valiaikainen = isa.irroitaLapsi(j);
			isa.yhdistaLapsi(j+1, valiaikainen);
		}

        // Yhdistetään uusi solmu isäsolmuunsa
		isa.yhdistaLapsi(arvoIndeksi+1, uusiOikea);

        // Derp
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

    /**
     * Etsii vertailtavaa arvoa seuraavan arvon puusta.
     *
     * Käytetään korjausoperaatioissa.
     * @param nykyinenSolmu
     * @param vertailtava
     * @return seuraava arvo
     */
    public int etsiSeuraaja(Solmu nykyinenSolmu, int vertailtava) {

        nykyinenSolmu = nykyinenSolmu.annaLapsi(vertailtava+1);

        /*
         * Iteroidaan solmut nykyisestä aina lehtisolmuun,
         * jossa seuraaja sijaitsee
         */
        while(true) {

            if (!nykyinenSolmu.onkoLehti())
                nykyinenSolmu = nykyinenSolmu.annaLapsi(0);
            else
                break;

        }

        int seuraaja = nykyinenSolmu.annaArvo(nykyinenSolmu.annaArvot().size()-1);
        System.out.println(seuraaja);
        return seuraaja;
    }

    /*
     * Moi Arttu 3====o - - ,
     */
    public Solmu annaJuuri() {
        return root;
    }

    public Solmu vasenSisko(Solmu nykyinen) {
        Solmu palautus;
        nykyinen.annaIsa();
        palautus = nykyinen.annaIsa().annaLapsi(nykyinen.annaIsa().etsiArvo());
    }
}
