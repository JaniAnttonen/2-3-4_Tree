package kakskolnelpuu;

public class Testiluokka {

	public static void main( String args[] ){
		/*Solmu ebin = new Solmu();
        ebin.lisaaArvo(4);
        ebin.lisaaArvo(2);*/
		Puu puu = new Puu();
		puu.lisaaArvoPuuhun(10);
        puu.lisaaArvoPuuhun(20);
        puu.lisaaArvoPuuhun(30);
        puu.lisaaArvoPuuhun(40);
        puu.lisaaArvoPuuhun(50);
        puu.lisaaArvoPuuhun(22);
        puu.lisaaArvoPuuhun(21);

		int vastaus = puu.etsi(14);
		
		System.out.println(vastaus);
	}
	
}
