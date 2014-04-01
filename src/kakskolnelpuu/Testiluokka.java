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
        puu.lisaaArvoPuuhun(20);
        puu.lisaaArvoPuuhun(30);
        puu.lisaaArvoPuuhun(39);


		
		
		int vastaus = puu.etsi(15);
		
		System.out.println(vastaus);
	}
	
}
