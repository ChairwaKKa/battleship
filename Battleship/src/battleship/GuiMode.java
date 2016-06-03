package battleship;

/**
 * Grafischer Modus für das Spiel
 *
 * @author oliver2
 */
import javax.swing.JOptionPane;

public class GuiMode extends RunGame {

	public GuiMode()
	{
		super();
	}

	@Override
	String getUserInput(String request) {

		return JOptionPane.showInputDialog(request);
	}

	@Override
	void gameLoop() {
		// TODO Auto-generated method stub

	}

	@Override
	void gameInfo(String info) {
		// TODO Auto-generated method stub

	}

}
