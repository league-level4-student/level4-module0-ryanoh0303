package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelArtMaker implements MouseListener, ActionListener{
	private JButton save;
	private JButton load;
	private JFrame window;
	private JPanel jpanel;
	private GridInputPanel gip;
	private GridPanel gp;
	
	ColorSelectionPanel csp;
	private static final String DATA_FILE = "src/_02_Pixel_Art/saved.dat";
	
	public void start() {
		gip = new GridInputPanel(this);	
		jpanel = new JPanel();
		
		
		save = new JButton("Save");
		load = new JButton ("Load");

		
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);

		jpanel.add(save);
		jpanel.add(load);
		
		save.addActionListener(this);
		load.addActionListener(this);
		window.add(jpanel);
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	private static JPanel load() {
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (JPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace(); 
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save) {
					System.out.println("Save");
					try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
						oos.writeObject(gp);
					} catch (IOException r) {
						r.printStackTrace();
					}
				}
				if(e.getSource()==load) {
					JFrame jframe2 = new JFrame("Load");
					jframe2.add(load());
					jframe2.setSize(400,400);
					jframe2.setVisible(true);
				}
		
		
	}
}
