package kakskolnelpuu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * 2-3-4-puun graafinen käyttöliittymä
 * @author Arttu Laitinen
 * 
 *Kokonaisuus ei vielä ihan toimi halutulla tavalla
 */
public class UI extends JFrame implements ActionListener{


	// Instance attributes used in this example
	private	JPanel		firstPanel;
	private JPanel		treePanel;
	private	JTree		tree;
	private JButton		button1;
	private JTextField	textField1;
	static Puu			puu = new Puu();
	static DefaultMutableTreeNode juuriNode = new DefaultMutableTreeNode();

	// UI:n konstruktori, luo perus käyttöliittymän 2-3-4 Puun käytämiseksi
	public UI(){


		// JFramen ominaisuudet
		setTitle( "Graphical user interface" );
		setSize( 400, 400 );
		setResizable(true);

		setLocationRelativeTo(null);
		setBackground( Color.gray );
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);


		// Komponenttipaneeli
		firstPanel = new JPanel();
		JLabel label1 = new JLabel("Add a data element to the tree");
		firstPanel.add(label1);
		getContentPane().add( firstPanel );



		// button1
		button1 = new JButton("Add");
		button1.setToolTipText("Adds a value to the tree");
		button1.addActionListener(this);
		firstPanel.add(button1);

		// textField1
		textField1 = new JTextField("", 10);
		firstPanel.add(textField1);
		textField1.requestFocus();


		// JPaneeli, jossa JTree esitetään
		treePanel = new JPanel();
		Border secondPanelBorder = BorderFactory.createTitledBorder("Tree");
		treePanel.setBorder(secondPanelBorder);
		firstPanel.add( treePanel, BorderLayout.SOUTH );

		// AlkuUI:ssa oleva defaulttipuu, jtta se ei näyttäisi tyhjältä
		tree = new JTree();
		treePanel.add(tree);

	}
	// 
	public static void main( String args[] ){
		
		UI mainFrame	= new UI();
		mainFrame.setVisible( true );



	}

	/**
	 * Kun käyttäjä painaa button1-nappia, lisää metodi textField1:ssä olevan arvon puuhun.
	 * Kutsuu piirraPuu-metodia piirtääkseen JTree-rakenteen
	 * @author Arttu Laitinen
	 */
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == button1){
			if(textField1.getText()!=null){
				int lisattavaArvo = Integer.parseInt(textField1.getText());
				UI.puu.lisaaArvoPuuhun(lisattavaArvo);
				System.out.println(lisattavaArvo);

				piirraPuu(puu.annaJuuri(), juuriNode);


			}
		}
	}
	/**
	 * Aloittaa JTree-rakenteen piirtämisen Puun mukaiseksi juuresta. 
	 * JuuriNoden nimeksi haetaan Puun juuren arvojen toString-syöte.
	 * @author Arttu Laitinen
	 * @param juuriSolmu
	 */
	public void piirraPuu(Solmu juuriSolmu, DefaultMutableTreeNode juuriNode){
		treePanel.remove(tree);
		String juurenNimi = juuriSolmu.annaArvot().toString();
		juuriNode.setUserObject(juurenNimi);
		tree = new JTree(juuriNode);

		if (!(juuriSolmu.onkoLehti())){
			for(int i = 0 ; i < juuriSolmu.annaLapset().size() ; i++){
				String arvot = juuriSolmu.annaLapsi(i).annaArvot().toString();
				addNode(arvot, juuriNode);
				piirraLapsiSolmut(juuriSolmu.annaLapsi(i), juuriNode);
			}
		}
		
		treePanel.add(tree);
		firstPanel.revalidate();
	}
	/**
	 * Piirtää syötteenään saamansa solmun lasten vastaavat nodet JTree-rakennelmaan
	 * 
	 * @author Arttu Laitinen
	 * @param isaSolmu
	 * @param isaNode
	 */
	public void piirraLapsiSolmut(Solmu isaSolmu, DefaultMutableTreeNode isaNode){
		if (!(isaSolmu.onkoLehti())){
			for(int i = 0 ; i < isaSolmu.annaLapset().size() ; i++){
				String arvot = isaSolmu.annaLapsi(i).annaArvot().toString();
				addNode(arvot, isaNode);
				piirraLapsiSolmut(isaSolmu.annaLapsi(i), isaNode);
				}
			
		}
	}



	/**
	 * Metodi, joka lisää syötteenään saamansa Stringin nimisen DefaultMutableTreeNoden parentNode-noden lapseksi.
	 * 
	 * @author Arttu Laitinen
	 * @param fileName
	 * @param parentNode
	 * @return
	 */
	private DefaultMutableTreeNode addNode(String fileName, DefaultMutableTreeNode parentNode){
		DefaultMutableTreeNode newFile = new DefaultMutableTreeNode(fileName);
		parentNode.add(newFile);

		return newFile; 
	}
}



