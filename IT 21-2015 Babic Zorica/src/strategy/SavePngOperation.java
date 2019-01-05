package strategy;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import mvc.Frame;

public class SavePngOperation implements Strategy{

	@Override
	public int doOperation(Object o, File f) {
		
		Frame frame = (Frame) o;
		
		try {

			BufferedImage im = new BufferedImage(frame.getPnlForDrawing().getWidth(), frame.getPnlForDrawing().getHeight(), BufferedImage.TYPE_INT_ARGB);
			frame.getPnlForDrawing().paint(im.getGraphics());
			ImageIO.write(im, "PNG", f);
		
			frame.setDone(false);
			
			
		}catch(Exception e){
			
			
		}
		
		frame.getBtnCmdbycmd().setEnabled(false);
		return 0;
	}

}