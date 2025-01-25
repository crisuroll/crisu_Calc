package calc_v2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;

public class VentanaEmergente extends JDialog {
	public VentanaEmergente(JFrame parent) {
		super(parent, "Ventana de información", true);
        setSize(300, 150);
        setLayout(new FlowLayout());

        Icon icono = UIManager.getIcon("OptionPane.informationIcon");

        JLabel label = new JLabel("Calculadora realizada por Cris :3");
		JButton closeButton1 = new JButton("Muy chuli :D");
		JButton closeButton2 = new JButton("Mejorable u.u");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        
        closeButton1.setPreferredSize(new Dimension(80, 50));
        closeButton1.setUI(new BasicButtonUI());
        closeButton1.setFocusPainted(false);
        closeButton1.setBackground(new Color(255, 204, 213));
        closeButton1.setForeground(Color.DARK_GRAY);
        closeButton1.setBorder(BorderFactory.createLineBorder(Color.PINK));
        
        closeButton2.setPreferredSize(new Dimension(80, 50));
        closeButton2.setUI(new BasicButtonUI());
        closeButton2.setFocusPainted(false);
        closeButton2.setBackground(new Color(255, 204, 213));
        closeButton2.setForeground(Color.DARK_GRAY);
        closeButton2.setBorder(BorderFactory.createLineBorder(Color.PINK));

        JPanel panel = new JPanel();
        panel.add(new JLabel(icono));
        panel.add(label);

        closeButton1.addActionListener(e -> dispose());
        closeButton2.addActionListener(e -> dispose());

        add(panel);
        add(closeButton1);
        add(closeButton2);

        setLocationRelativeTo(parent);
		
		
	}
}
