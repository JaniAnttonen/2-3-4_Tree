package kakskolnelpuu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * 2-3-4-puun graafinen k�ytt�liittym�
 * @author Arttu Laitinen
 *
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

	// Constructor of main frame
	public UI(){


		// Set the frame characteristics
		setTitle( "Graphical user interface" );
		setSize( 400, 400 );
		setResizable(true);

		setLocationRelativeTo(null);
		setBackground( Color.gray );
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);


		// Create a panel to hold all other components
		firstPanel = new JPanel();
		JLabel label1 = new JLabel("Add a data element to the tree");
		firstPanel.add(label1);
		getContentPane().add( firstPanel );



		// Adding button1
		button1 = new JButton("Add");
		button1.setToolTipText("Adds a value to the tree");
		button1.addActionListener(this);
		firstPanel.add(button1);

		// Adding a textfield
		textField1 = new JTextField("", 10);
		firstPanel.add(textField1);
		textField1.requestFocus();


		// Create a panel to hold the tree being built
		treePanel = new JPanel();
		Border secondPanelBorder = BorderFactory.createTitledBorder("Tree");
		treePanel.setBorder(secondPanelBorder);
		firstPanel.add( treePanel, BorderLayout.SOUTH );

		// Adding the tree


		tree = new JTree();
		treePanel.add(tree);

	}
	// Main entry point for this example
	public static void main( String args[] ){
		// Create an instance of the test application
		UI mainFrame	= new UI();
		mainFrame.setVisible( true );



	}

	/**
	 * Kun k�ytt�j� painaa button1-nappia, lis�� metodi textField1:ss� olevan arvon puuhun.
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
	 * Aloittaa JTree-rakenteen piirt�misen Puun mukaiseksi juuresta. 
	 * JuuriNoden nimeksi haetaan Puun juuren arvojen toString-sy�te.
	 * @param juuriSolmu
	 */
	public void piirraPuu(Solmu juuriSolmu, DefaultMutableTreeNode juuriNode){
		treePanel.remove(tree);
		String juurenNimi = juuriSolmu.annaArvot().toString();
		juuriNode.setUserObject(juurenNimi);
		tree = new JTree(juuriNode);

		if (juuriSolmu.onkoLehti()==false){
			for(int i = 0 ; i < juuriSolmu.annaLapset().size() ; i++){
				String arvot = juuriSolmu.annaLapsi(i).annaArvot().toString();
				addNode(arvot, juuriNode);
				piirraLapsiSolmut(juuriSolmu.annaLapsi(i), juuriNode);
			}
		}
		
		treePanel.add(tree);
		firstPanel.revalidate();
	}

	public void piirraLapsiSolmut(Solmu isaSolmu, DefaultMutableTreeNode isaNode){
		if (!isaSolmu.onkoLehti()){
			for(int i = 0 ; i < isaSolmu.annaLapset().size() ; i++){
				String arvot = isaSolmu.annaLapsi(i).annaArvot().toString();
				addNode(arvot, isaNode);
				piirraLapsiSolmut(isaSolmu, isaNode);
			}
		}
	}




	private DefaultMutableTreeNode addNode(String fileName, DefaultMutableTreeNode parentNode){
		DefaultMutableTreeNode newFile = new DefaultMutableTreeNode(fileName);
		parentNode.add(newFile);

		return newFile; 
	}
}



