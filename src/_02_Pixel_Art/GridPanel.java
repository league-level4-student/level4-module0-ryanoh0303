package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridPanel extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;
	
	//1. Create a 2D array of pixels. Do not initialize it yet.

    Pixel[][] pixel;
	
	private Color color;
	
	public GridPanel(int w, int h, int r, int c) {
	
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		pixel = new Pixel[rows][cols];
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				pixel[i][j]= new Pixel(i,j);
			}
		}
		
		
		//3. Iterate through the array and initialize each element to a new pixel.
	
		
		
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {
		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		pixel[mouseX/pixelWidth][mouseY/pixelHeight].color =color;
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Iterate through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		for(int i=0; i<pixel.length; i++) {
			for(int j=0; j<pixel[i].length; j++) {
				g.setColor(color.black);
				g.drawRect(i*pixelWidth,j*pixelHeight , pixelWidth, pixelHeight);
				g.setColor(pixel[i][j].color);
				g.fillRect(i*pixelWidth,j*pixelHeight,pixelWidth,pixelHeight);
				
			}
		}
		//   Then, use drawRect to add a grid pattern to your display.
		
		
	}

}
