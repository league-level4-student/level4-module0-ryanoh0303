package _02_Pixel_Art;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorSelectionPanel extends JPanel implements MouseListener, ChangeListener, Serializable, ActionListener{
	private static final long serialVersionUID = 1L;
	private static final String DATA_FILE = "src/_02_Pixel_Art/saved.dat";
	
	public static final int MAX_COLOR = 256;
	
	private JSlider rSlider;
	private JSlider gSlider;
	private JSlider bSlider;
	
	
	private Color color;
	
	private int r = 0;
	private int g = 0;
	private int b = 0;
	
	private JLabel colorLabel;
	private BufferedImage colorImage;
	
	private JButton Save;
	private JButton Load;
	
	public ColorSelectionPanel() {
		rSlider = new JSlider(JSlider.VERTICAL);
		gSlider = new JSlider(JSlider.VERTICAL);
		bSlider = new JSlider(JSlider.VERTICAL);
		rSlider.setMinimum(0);
		rSlider.setMaximum(MAX_COLOR - 1);
		rSlider.setValue(0);
		gSlider.setMinimum(0);
		gSlider.setMaximum(MAX_COLOR - 1);
		gSlider.setValue(0);
		bSlider.setMinimum(0);
		bSlider.setMaximum(MAX_COLOR - 1);
		bSlider.setValue(0);
		rSlider.addChangeListener(this);
		gSlider.addChangeListener(this);
		bSlider.addChangeListener(this);
		
		addMouseListener(this);
		
		colorLabel = new JLabel();
		Save = new JButton("Save");
		Load = new JButton("Load");
		
		colorImage = new BufferedImage(MAX_COLOR, MAX_COLOR, BufferedImage.TYPE_INT_RGB);
		color = new Color(r, g, b);
		for(int i = 0; i < MAX_COLOR; i++) {
			for(int j = 0; j < MAX_COLOR; j++) {
				colorImage.setRGB(j, i, color.getRGB());
			}
		}
		Save.addActionListener(this);
		Load.addActionListener(this);
		colorLabel.setIcon(new ImageIcon(colorImage));
		add(colorLabel);
		add(Save);
		add(Load);
		//here add 
	
	
	
		add(new JLabel("red"));
		add(rSlider);
		add(new JLabel("green"));
		add(gSlider);
		add(new JLabel("blue"));
		add(bSlider);
	}

	
	public Color getSelectedColor() {
		return color;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
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
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider s = (JSlider)e.getSource();
		if(s == rSlider) {
			r = s.getValue();
		}
		else if(s == gSlider) {
			g = s.getValue();
		}
		else if(s == bSlider) {
			b = s.getValue();
		}
		
		color = new Color(r, g, b);

		for(int i = 0; i < MAX_COLOR; i++) {
			for(int j = 0; j < MAX_COLOR; j++) {
				colorImage.setRGB(j, i, color.getRGB());
			}
		}
		
		colorLabel.setIcon(new ImageIcon(colorImage));
		add(colorLabel);
	}
	private static JLabel load() {
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (JLabel) ois.readObject();
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
		if(e.getSource()==Save) {
			System.out.println("Save");
			try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(colorLabel);
			} catch (IOException r) {
				r.printStackTrace();
			}
		}
		if(e.getSource()==Load) {
			JFrame jframe2 = new JFrame("Load");
			jframe2.add(load());
			jframe2.setSize(400,400);
			jframe2.setVisible(true);
		}
	}
}

	

