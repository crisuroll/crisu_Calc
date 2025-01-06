package calc_v1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine extends JFrame {
	/**
	 * ATRIBUTOS
	 */
	
	// Marco de la ventana
	private JFrame frame;
	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;
	// Panel norte que contiene el display
	private JPanel displayPanel;
	// Panel sur que contiene los botones
	private JPanel buttonPanel;
	// Display
	private JTextField display;
	// Botones
	private JButton n0;
	private JButton n1;
	private JButton n2;
	private JButton n3;
	private JButton n4;
	private JButton n5;
	private JButton n6;
	private JButton n7;
	private JButton n8;
	private JButton n9;
	private JButton divide;
	private JButton multiply;
	private JButton subtract;
	private JButton add;
	private JButton equal;
	private JButton reset;
	// Tipos de boton
	private enum ButtonType {REGULAR, OPERATOR};
	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private char operation;
	
	/**
	 * CONSTRUCTORA
	 */
	public Engine(String _msg) {
		super(_msg);
		this.frame = new JFrame();
		this.contentPanel = new JPanel();
		this.displayPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.display = new JTextField();
		this.n0 = new JButton("0");
		this.n1 = new JButton("1");
		this.n2 = new JButton("2");
		this.n3 = new JButton("3");
		this.n4 = new JButton("4");
		this.n5 = new JButton("5");
		this.n6 = new JButton("6");
		this.n7 = new JButton("7");
		this.n8 = new JButton("8");
		this.n9 = new JButton("9");
		this.divide = new JButton("/");
		this.multiply = new JButton("x");
		this.subtract = new JButton("-");
		this.add = new JButton("+");
		this.equal = new JButton("=");
		this.reset = new JButton("C");
		// Pensar si instanciar el enum, int y char
		setSettings();
		addActionEvent(this);
	}
	/**
	 * Método setSettings. Este método establece la configuración principal de todos los componentes visuales 
	 * de la ventana. Concretamente, se encarga de, entre otras cosas: poner los layouts de los paneles y 
	 * añadirlos, añadir los botones y llamar al método setFeaturesButton(), el display, establecer
	 * las características de los botones, tamaño de la ventana, localización, etc.
	 */
	private void setSettings() {
	    // Configurar el JTextField
	    this.display.setEditable(false);
	    this.display.setHorizontalAlignment(JTextField.RIGHT);
	    this.display.setFont(new Font("Arial", Font.BOLD, 24));
	    this.display.setPreferredSize(new Dimension(380, 60));
	    this.display.setBackground(Color.WHITE);

	    // Configurar el panel del display
	    this.displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    this.displayPanel.setPreferredSize(new Dimension(400, 80));
	    this.displayPanel.add(display);

	    // Configurar el panel de botones
	    this.buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
	    setFeaturesButton(this.n7, ButtonType.REGULAR);
	    setFeaturesButton(this.n8, ButtonType.REGULAR);
	    setFeaturesButton(this.n9, ButtonType.REGULAR);
	    setFeaturesButton(this.divide, ButtonType.OPERATOR);
	    setFeaturesButton(this.n4, ButtonType.REGULAR);
	    setFeaturesButton(this.n5, ButtonType.REGULAR);
	    setFeaturesButton(this.n6, ButtonType.REGULAR);
	    setFeaturesButton(this.multiply, ButtonType.OPERATOR);
	    setFeaturesButton(this.n1, ButtonType.REGULAR);
	    setFeaturesButton(this.n2, ButtonType.REGULAR);
	    setFeaturesButton(this.n3, ButtonType.REGULAR);
	    setFeaturesButton(this.subtract, ButtonType.OPERATOR);
	    setFeaturesButton(this.reset, ButtonType.OPERATOR);
	    setFeaturesButton(this.n0, ButtonType.REGULAR);
	    setFeaturesButton(this.equal, ButtonType.OPERATOR);
	    setFeaturesButton(this.add, ButtonType.OPERATOR);

	    // Añadir botones al panel de botones
	    this.buttonPanel.add(this.n7);
	    this.buttonPanel.add(this.n8);
	    this.buttonPanel.add(this.n9);
	    this.buttonPanel.add(this.divide);
	    this.buttonPanel.add(this.n4);
	    this.buttonPanel.add(this.n5);
	    this.buttonPanel.add(this.n6);
	    this.buttonPanel.add(this.multiply);
	    this.buttonPanel.add(this.n1);
	    this.buttonPanel.add(this.n2);
	    this.buttonPanel.add(this.n3);
	    this.buttonPanel.add(this.subtract);
	    this.buttonPanel.add(this.reset);
	    this.buttonPanel.add(this.n0);
	    this.buttonPanel.add(this.equal);
	    this.buttonPanel.add(this.add);

	    // Configurar la ventana principal
	    this.contentPanel.setLayout(new BorderLayout());
	    this.contentPanel.add(this.displayPanel, BorderLayout.NORTH);
	    this.contentPanel.add(this.buttonPanel, BorderLayout.CENTER);
	    this.frame.setLayout(new BorderLayout());
	    this.frame.add(this.contentPanel, BorderLayout.CENTER);
	    
		this.frame.setLocation(200, 200);
		this.frame.setSize(400, 400);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
	/**
	 * Contiene una condición que permite distinguir si el tipo de botón pasa como parámetro es de tipo 
	 * REGULAR u OPERATOR. En función de esto, pintará el botón de un color u otro. Puedes añadirle (y es algo 
	 * que se tendrá en cuenta) más características tales como cambio del tipo de letra, bordes, etc.
	 * 		• _button identifica el botón sobre el que se van a cambiar las características.
	 * 		• _type identifica de qué tipo es el botón sobre el que se van a cambiar las características.
	 * @param _button
	 * @param _type
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {

	}
	
	/**
	 * Este método registra los ActionListener para todos los botones de la aplicación. Es decir, para cada botón, 
	 * añade un ActionListener que recibe como parámetro el objeto this para poder identificar el objeto (botón) 
	 * que se pulsa.
	 */
	public void addActionEvent(Engine _eng) {

	}


	
	/**
	 * Comprueba qué operación se debe realizar. En otras palabras: mira el estado actual del atributo 
	 * this.operation y, en función de ese valor, lleva a cabo una operación u otra (con los atributos this.num1
	 * y this.num2, que representan los dos únicos operando que maneja nuestra calculadora), modificando el 
	 * atributo this.result y actualizando el texto en el display.
	 */
	public void operation() {

	}


	
	/**
	 * Este método se encarga de obtener la información que haya en el display (números introducidos y operación
	 * que se debe realizar) y llamar al método operation() para ejecutar dicha operación. Hay muchas formas de 
	 * llevar a cabo esta lógica... Piensa cuál podría ser la mejor manera de hacerlo. Por ejemplo, una manera 
	 * podría ser identificar el botón que se ha pulsado y añadir su texto al display y, cuando se pulse sobre el 
	 * botón =, entonces hacemos que se ejecute la operación que se haya indicado con los botones de operación 
	 * (sumar, restar, multiplicar o dividir). En cualquier caso e independientemente de la decisión que se
	 * tome, lo primero que debemos hacer en este método es recoger el tipo de botón que se ha pulsado (para poder 
	 * hacer la distinción) y el texto asociado a ese botón. Esto se puede hacer de la siguiente forma:
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {

	}

	
	/*
	 * Nota: como he dicho, hay muchas maneras de implementar la lógica del método actionPerformed(). Te aconsejo 
	 * que busques más información sobre las expresiones regulares (para saber más sobre expresiones regulares, 
	 * ver [4, 1, 3, 2]), ya que estas pueden ser realmente útiles para coger las partes del texto del display que 
	 * te interesen en cada momento, así como hacer un split() de dicho texto e identificar los distintos 
	 * elementos (que en nuestro caso, al ser una calculadora que únicamente trabaja con dos operandos, los tres 
	 * elementos presentes son: operando 1, símbolo de la operación y operando 2).
	 */
	
	
}
