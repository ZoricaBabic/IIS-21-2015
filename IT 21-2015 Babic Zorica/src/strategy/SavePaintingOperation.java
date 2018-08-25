package strategy;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import crtanje.NaslovnaPokretanje;

public class SavePaintingOperation implements Strategy{

	@Override
	public int doOperation(Object o, File f) {
		
		NaslovnaPokretanje np = (NaslovnaPokretanje) o;
		
		try {

			BufferedImage im = new BufferedImage(np.getPnlZaCrtanje().getWidth(), np.getPnlZaCrtanje().getHeight(), BufferedImage.TYPE_INT_ARGB);
			np.getPnlZaCrtanje().paint(im.getGraphics());
			ImageIO.write(im, "PNG", f);
			
			
		}catch(Exception e){
			
			
		}
		
		
		return 0;
	}

}