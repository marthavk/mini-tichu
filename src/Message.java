import java.awt.Dimension;
import javax.swing.JTextArea;


public class Message extends JTextArea{
	public String message;
	
	public Message() {		
		setRows(9);
		setSize(new Dimension(800, 100));		
		setVisible(true);
		setEditable(false);
	}
	
	@Override
	public void append(String str) {
		message += str + "\n";
		this.setText(message);
		this.setCaretPosition(this.getDocument().getLength());
	}		
	
	public void clearMessage() {
		message = "";
	}
	
}
