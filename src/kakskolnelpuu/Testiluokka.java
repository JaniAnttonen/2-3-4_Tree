package kakskolnelpuu;

public class Testiluokka {

	public static void main( String args[] ){
		Puu puu = new Puu();
		puu.lisaaArvoPuuhun(10);
        puu.lisaaArvoPuuhun(20);
        puu.lisaaArvoPuuhun(30);
        puu.lisaaArvoPuuhun(40);
        puu.lisaaArvoPuuhun(50);
        puu.lisaaArvoPuuhun(22);
        puu.poista(30);
        puu.poista(50);
		//int vastaus = puu.etsi(30);
		
		//System.out.println(vastaus);
	}
	
}
