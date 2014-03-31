package kakskolnelpuu;

public class Testiluokka {

	public static void main( String args[] ){
		Puu puu = new Puu(3);
		puu.lisaaArvoPuuhun(15);
		
		
		int vastaus = puu.etsi(0);
		
		System.out.println(vastaus);
	}
	
}
