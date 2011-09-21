package skillsplanner.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class WISP extends javax.swing.JFrame{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private JButton Tab3;
	private JButton Tab2;
	private JButton Tab1;
	private JPanel Tabs;
	private JLabel LOGO;
	private JPanel ClassArea;
	private JPanel RightPane;
	private JPanel LeftPane;
	private JPanel ExtraCrap;
	private JPanel SkillContainer;
	private JButton Calculator;
	private JButton Overview;
	private JButton General;
	private JButton Tab4;
	private JPanel TabArea;
	private JSplitPane SkillArea;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenu4;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				WISP inst = new WISP();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public WISP() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("WISP");
				this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
			{
				SkillArea = new JSplitPane();
				getContentPane().add(SkillArea, BorderLayout.CENTER);
				SkillArea.setDividerLocation(445);
				SkillArea.setPreferredSize(new java.awt.Dimension(641, 453));
				SkillArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				{
					LeftPane = new JPanel();
					SkillArea.add(LeftPane, JSplitPane.LEFT);
				}
				{
					RightPane = new JPanel();
					SkillArea.add(RightPane, JSplitPane.RIGHT);
				}
			}
			{
				TabArea = new JPanel();
				BorderLayout TabAreaLayout = new BorderLayout();
				TabArea.setLayout(TabAreaLayout);
				getContentPane().add(TabArea, BorderLayout.NORTH);
				TabArea.setPreferredSize(new java.awt.Dimension(790, 40));
				{
					LOGO = new JLabel();
					TabArea.add(LOGO, BorderLayout.WEST);
					LOGO.setText("LOGO");
					LOGO.setPreferredSize(new java.awt.Dimension(154, 29));
				}
				{
					Tabs = new JPanel();
					FlowLayout TabsLayout = new FlowLayout();
					TabsLayout.setAlignment(FlowLayout.LEFT);
					TabsLayout.setVgap(0);
					TabsLayout.setHgap(0);
					TabArea.add(Tabs, BorderLayout.CENTER);
					Tabs.setLayout(TabsLayout);
					Tabs.setPreferredSize(new java.awt.Dimension(636, 47));
					{
						SkillContainer = new JPanel();
						FlowLayout SkillContainerLayout = new FlowLayout();
						SkillContainerLayout.setHgap(0);
						SkillContainerLayout.setVgap(0);
						SkillContainerLayout.setAlignment(FlowLayout.LEFT);
						SkillContainer.setLayout(SkillContainerLayout);
						Tabs.add(SkillContainer);
						SkillContainer.setPreferredSize(new java.awt.Dimension(443, 38));
						{
							General = new JButton();
							SkillContainer.add(General);
							General.setPreferredSize(new java.awt.Dimension(88, 38));
						}
						{
							Tab4 = new JButton();
							SkillContainer.add(Tab4);
							Tab4.setPreferredSize(new java.awt.Dimension(88, 38));
						}
						{
							Tab3 = new JButton();
							SkillContainer.add(Tab3);
							Tab3.setPreferredSize(new java.awt.Dimension(88, 38));
						}
						{
							Tab2 = new JButton();
							SkillContainer.add(Tab2);
							Tab2.setPreferredSize(new java.awt.Dimension(88, 38));
						}
						{
							Tab1 = new JButton();
							SkillContainer.add(Tab1);
							Tab1.setPreferredSize(new java.awt.Dimension(88, 38));
						}
					}
					{
						ExtraCrap = new JPanel();
						FlowLayout ExtraCrapLayout = new FlowLayout();
						ExtraCrapLayout.setHgap(0);
						ExtraCrapLayout.setVgap(0);
						ExtraCrapLayout.setAlignment(FlowLayout.RIGHT);
						ExtraCrap.setLayout(ExtraCrapLayout);
						Tabs.add(ExtraCrap);
						ExtraCrap.setPreferredSize(new java.awt.Dimension(188, 38));
						{
							Calculator = new JButton();
							ExtraCrap.add(Calculator);
							Calculator.setPreferredSize(new java.awt.Dimension(88, 38));
						}
						{
							Overview = new JButton();
							ExtraCrap.add(Overview);
							Overview.setPreferredSize(new java.awt.Dimension(88, 38));
						}
					}
				}
			}
			{
				ClassArea = new JPanel();
				getContentPane().add(ClassArea, BorderLayout.WEST);
				ClassArea.setPreferredSize(new java.awt.Dimension(152, 518));
			}
			this.setSize(800, 600);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("New");
					}
					{
						openFileMenuItem = new JMenuItem();
						jMenu3.add(openFileMenuItem);
						openFileMenuItem.setText("Open");
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("Save");
					}
					{
						saveAsMenuItem = new JMenuItem();
						jMenu3.add(saveAsMenuItem);
						saveAsMenuItem.setText("Save As ...");
					}
					{
						closeFileMenuItem = new JMenuItem();
						jMenu3.add(closeFileMenuItem);
						closeFileMenuItem.setText("Close");
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Exit");
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Edit");
					{
						cutMenuItem = new JMenuItem();
						jMenu4.add(cutMenuItem);
						cutMenuItem.setText("Cut");
					}
					{
						copyMenuItem = new JMenuItem();
						jMenu4.add(copyMenuItem);
						copyMenuItem.setText("Copy");
					}
					{
						pasteMenuItem = new JMenuItem();
						jMenu4.add(pasteMenuItem);
						pasteMenuItem.setText("Paste");
					}
					{
						jSeparator1 = new JSeparator();
						jMenu4.add(jSeparator1);
					}
					{
						deleteMenuItem = new JMenuItem();
						jMenu4.add(deleteMenuItem);
						deleteMenuItem.setText("Delete");
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Help");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("Help");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
