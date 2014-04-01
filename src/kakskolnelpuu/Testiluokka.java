package kakskolnelpuu;

public class Testiluokka {

	public static void main( String args[] ){
		/*Solmu ebin = new Solmu();
        ebin.lisaaArvo(4);
        ebin.lisaaArvo(2);*/
		Puu puu = new Puu();
		puu.lisaaArvoPuuhun(15);
        puu.lisaaArvoPuuhun(3);
        puu.lisaaArvoPuuhun(2);
        puu.lisaaArvoPuuhun(4);
        puu.lisaaArvoPuuhun(6);
        puu.lisaaArvoPuuhun(1);


		
		
		int vastaus = puu.etsi(15);
		
		System.out.println(vastaus);
	}
	
}
