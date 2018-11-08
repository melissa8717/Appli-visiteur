package view;



import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

public class Paragraphe extends JPanel {
	public Paragraphe(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		JLabel texte = new JLabel();
		texte = new JLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In faucibus finibus dolor. Sed sem nulla, aliquet eget accumsan vel, varius euismod neque. Mauris tincidunt lectus justo, sed dictum tellus eleifend non. Praesent at risus sed sem cursus porttitor in dapibus mi. Donec ornare justo sed nisl tincidunt faucibus. In hac habitasse platea dictumst. Morbi imperdiet mauris quis nunc euismod, quis auctor dui cursus. Nunc ultrices volutpat lorem ac accumsan. Aenean ornare metus ut lacus pulvinar, posuere vehicula justo condimentum. Duis ornare magna at velit vulputate egestas. Etiam eget ipsum at urna interdum pulvinar id in neque. Etiam nec tortor in nisi semper volutpat non ac justo. Fusce eu auctor leo, quis euismod arcu. Suspendisse potenti. Nunc posuere neque mi, id tempor nunc elementum ac. Proin a risus mollis, suscipit urna tincidunt, imperdiet elit.");
		texte.setFont(font);
		this.setBounds(102,102,500,500);
		this.setVisible(true);
		this.setSize(getMaximumSize());;
	}
	
}
