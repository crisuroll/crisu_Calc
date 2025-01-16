package calc_v2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Clase Engine. Encargada de crear toda la interfaz y funcionalidad de la calculadora.
 * @author crisuroll
 */
public class Engine extends JFrame implements ActionListener {
	/**
	 * ATRIBUTOS
	 */
	// Marco de la ventana
	private JFrame frame;
	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;
	// Panel norte que contiene subpaneles
	private JPanel infoPanel;
	// Subpanel izquierdo que contiene la base
	private JPanel basePanel;
	// Subpanel derecho que contiene la marca
	private JPanel brandPanel;
	// Panel central que contiene el display
	private JPanel displayPanel; 
	// Panel sur que contiene los botones
	private JPanel buttonPanel;
	// Base
	private JTextField base;
	// Display
	private JTextField display;
	// Botones
	private JButton del, brand, b2, b8, b10, b16, a, b, c, d, e, f, info, owner, n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, add, subtract, multiply, divide, pow, sqr, perc,
	equal, reset, extra;
	// Tipos de boton
	private enum ButtonType {REGULAR, OPERATOR, BASE, HEX, EXTRA};
	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private char operation;
	
	/**
	 * CONSTRUCTORA Engine(). Inicializamos todos los atributos y llamamos a los metodos setSettings() y 
	 * setFeaturesButton() para personalizar la ventana y los botones.
	 */
	public Engine() {
		this.frame = new JFrame("crisu_Calc :3 owo");
		this.contentPanel = new JPanel();
		this.infoPanel = new JPanel();
		this.basePanel = new JPanel();
		this.brandPanel = new JPanel();
		this.displayPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.display = new JTextField();
		this.base = new JTextField();
		this.brand = new JButton("Marca");
		this.b2 = new JButton("B2");
		this.b8 = new JButton("B8");
		this.b10 = new JButton("B10");
		this.b16 = new JButton("B16");
		this.a = new JButton("A");
		this.b = new JButton("B");
		this.c = new JButton("C");
		this.d = new JButton("D");
		this.e = new JButton("E");
		this.f = new JButton("F");
		this.info = new JButton("INFO");
		this.owner = new JButton("OWNER");
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
		this.add = new JButton("+");
		this.subtract = new JButton("-");
		this.multiply = new JButton("x");
		this.divide = new JButton("/");
		this.equal = new JButton("=");
		this.pow = new JButton("^");
		this.sqr = new JButton("√");
		this.perc = new JButton("%");
		this.reset = new JButton("R");
		// this.extra = new JButton("★");
		this.del = new JButton("⌫");
		setSettings();
		addActionEvent();
	}
	
	/**
	 * Metodo setSettings(). Establece la configuracion principal de todos los componentes visuales de la ventana.
	 */
	public void setSettings() {
	    // Configurar el JTextField
	    this.display.setEditable(false);
	    this.display.setHorizontalAlignment(JTextField.RIGHT);
	    this.display.setFont(new Font("Arial", Font.BOLD, 20));
	    this.display.setPreferredSize(new Dimension(380, 60));
	    this.display.setBackground(Color.WHITE);

	    // Configurar subpaneles izquierdo y derecho
	    this.basePanel.setLayout(new BorderLayout());
	    this.brandPanel.setLayout(new BorderLayout());
	    
	    // Configurar el panel norte
	    this.infoPanel.setLayout(new GridLayout(1, 2, 50, 50));
	    this.infoPanel.add(basePanel, BorderLayout.WEST);
	    this.infoPanel.add(brandPanel, BorderLayout.EAST);
	    
	    // Configurar el panel del display
	    this.displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    this.displayPanel.setPreferredSize(new Dimension(400, 80));
	    this.displayPanel.add(this.display);

	    // Configurar el panel de botones
	    this.buttonPanel.setLayout(new GridLayout(8, 4, 5, 5));
	    setFeaturesButton(this.brand, ButtonType.EXTRA);
	    setFeaturesButton(this.del, ButtonType.EXTRA);
	    setFeaturesButton(this.info, ButtonType.EXTRA);
	    setFeaturesButton(this.owner, ButtonType.EXTRA);
	    setFeaturesButton(this.b2, ButtonType.BASE);
	    setFeaturesButton(this.b8, ButtonType.BASE);
	    setFeaturesButton(this.b10, ButtonType.BASE);
	    setFeaturesButton(this.b16, ButtonType.BASE);
	    setFeaturesButton(this.a, ButtonType.HEX);
	    setFeaturesButton(this.b, ButtonType.HEX);
	    setFeaturesButton(this.c, ButtonType.HEX);
	    setFeaturesButton(this.d, ButtonType.HEX);
	    setFeaturesButton(this.e, ButtonType.HEX);
	    setFeaturesButton(this.f, ButtonType.HEX);
	    setFeaturesButton(this.add, ButtonType.OPERATOR);
	    setFeaturesButton(this.subtract, ButtonType.OPERATOR);
	    setFeaturesButton(this.multiply, ButtonType.OPERATOR);
	    setFeaturesButton(this.divide, ButtonType.OPERATOR);
	    setFeaturesButton(this.reset, ButtonType.EXTRA);
	    setFeaturesButton(this.equal, ButtonType.OPERATOR);
	    setFeaturesButton(this.pow, ButtonType.OPERATOR);
	    setFeaturesButton(this.sqr, ButtonType.OPERATOR);
	    setFeaturesButton(this.perc, ButtonType.OPERATOR);
	    setFeaturesButton(this.n0, ButtonType.REGULAR);
	    setFeaturesButton(this.n1, ButtonType.REGULAR);
	    setFeaturesButton(this.n2, ButtonType.REGULAR);
	    setFeaturesButton(this.n3, ButtonType.REGULAR);
	    setFeaturesButton(this.n4, ButtonType.REGULAR);
	    setFeaturesButton(this.n5, ButtonType.REGULAR);
	    setFeaturesButton(this.n6, ButtonType.REGULAR);
	    setFeaturesButton(this.n7, ButtonType.REGULAR);
	    setFeaturesButton(this.n8, ButtonType.REGULAR);
	    setFeaturesButton(this.n9, ButtonType.REGULAR);

	    // Añadir botones al panel de botones
	    this.brandPanel.add(this.brand);
	    this.buttonPanel.add(this.b2);
	    this.buttonPanel.add(this.b8);
	    this.buttonPanel.add(this.b10);
	    this.buttonPanel.add(this.b16);
	    this.buttonPanel.add(this.a);
	    this.buttonPanel.add(this.b);
	    this.buttonPanel.add(this.c);
	    this.buttonPanel.add(this.info);
	    this.buttonPanel.add(this.d);
	    this.buttonPanel.add(this.e);
	    this.buttonPanel.add(this.f);
	    this.buttonPanel.add(this.owner);
	    this.buttonPanel.add(this.pow);
	    this.buttonPanel.add(this.sqr);
	    this.buttonPanel.add(this.perc);
	    this.buttonPanel.add(this.add);
	    this.buttonPanel.add(this.n7);
	    this.buttonPanel.add(this.n8);
	    this.buttonPanel.add(this.n9);
	    this.buttonPanel.add(this.subtract);
	    this.buttonPanel.add(this.n4);
	    this.buttonPanel.add(this.n5);
	    this.buttonPanel.add(this.n6);
	    this.buttonPanel.add(this.multiply);
	    this.buttonPanel.add(this.n1);
	    this.buttonPanel.add(this.n2);
	    this.buttonPanel.add(this.n3);
	    this.buttonPanel.add(this.divide);
	    this.buttonPanel.add(this.reset);
	    this.buttonPanel.add(this.n0);
	    this.buttonPanel.add(this.equal);
	    this.buttonPanel.add(this.del);

	    // Configurar la ventana principal
	    this.contentPanel.setLayout(new BorderLayout());
	    this.contentPanel.add(this.infoPanel, BorderLayout.NORTH);
	    this.contentPanel.add(this.displayPanel, BorderLayout.CENTER);
	    this.contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);
	    this.frame.setLayout(new BorderLayout());
	    this.frame.add(this.contentPanel, BorderLayout.CENTER);
		this.frame.setLocation(550, 150);
		this.frame.setSize(500, 600);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Metodo setFeaturesButton(). Permite distinguir si el tipo de boton que pasa como parametro es de tipo 
	 * REGULAR, OPERATOR o EXTRA. En función de esto, personaliza el boton de una forma u otra.
	 * @param _button identifica el boton sobre el que se van a cambiar las caracteristicas.
	 * @param _type identifica de que tipo es el boton.
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
		_button.setPreferredSize(new Dimension(45, 50));
		// Con estas dos opciones nos aseguramos de que el highlight azul desaparezca al hacer click en el boton
		_button.setUI(new BasicButtonUI());
	    _button.setFocusPainted(false);

	    // Personalizamos el botón según su tipo
	    if (_type == ButtonType.REGULAR) {
	        _button.setBackground(new Color(251, 248, 204));
	        _button.setForeground(Color.DARK_GRAY);
	        _button.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
	        _button.setFont(new Font("Arial", Font.BOLD, 15));
	    } else if (_type == ButtonType.OPERATOR) {
	        _button.setBackground(new Color(204, 229, 255));
	        _button.setForeground(Color.DARK_GRAY);
	        _button.setBorder(BorderFactory.createLineBorder(Color.CYAN));
	        _button.setFont(new Font("Arial", Font.BOLD, 15));
	    } else if (_type == ButtonType.EXTRA) {
	    	_button.setBackground(new Color(255, 204, 213));
	    	_button.setForeground(Color.WHITE);
	    	_button.setBorder(BorderFactory.createLineBorder(Color.PINK));
	    } else if (_type == ButtonType.BASE) {
	    	_button.setBackground(new Color(204, 255, 213));
	    	_button.setForeground(Color.DARK_GRAY);
	    	_button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	    } else if (_type == ButtonType.HEX) {
	    	_button.setBackground(new Color(213, 204, 255));
	    	_button.setForeground(Color.DARK_GRAY);
	    	_button.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
	    }

	    // Hover   
	    _button.addMouseListener(new MouseAdapter() {
	    	
	    	// Al pasar el cursor sobre un boton, el color se oscurece
	        @Override
	        public void mouseEntered(MouseEvent evt) {
	        	if (_type == ButtonType.REGULAR) {
	        		_button.setBackground(new Color(240, 238, 180));
	        	} else if (_type == ButtonType.OPERATOR) {
	        		_button.setBackground(new Color(180, 215, 240));
	        	} else if (_type == ButtonType.EXTRA) {
	        		_button.setBackground(new Color(255, 179, 193));
	        	}
	        }

	        // Cuando el cursor ya no esta sobre el botón, vuelve a su color original
	        @Override
	        public void mouseExited(MouseEvent evt) {
	        	if (_type == ButtonType.REGULAR) {
	        		_button.setBackground(new Color(251, 248, 204));
	        	} else if (_type == ButtonType.OPERATOR) {
	        		_button.setBackground(new Color(204, 229, 255));
	        	} else if (_type == ButtonType.EXTRA) {
	        		_button.setBackground(new Color(255, 204, 213));
	        	}
	        }
	    });
	}
	
	/**
	 * Metodo addActionEvent(). Registra los ActionListener para todos los botones de la aplicación. Es decir, 
	 * para cada boton, añade un ActionListener que recibe como parametro el objeto this para poder identificar 
	 * el boton que se pulsa.
	 */
	public void addActionEvent() {
	    this.n0.addActionListener(this);
	    this.n1.addActionListener(this);
	    this.n2.addActionListener(this);
	    this.n3.addActionListener(this);
	    this.n4.addActionListener(this);
	    this.n5.addActionListener(this);
	    this.n6.addActionListener(this);
	    this.n7.addActionListener(this);
	    this.n8.addActionListener(this);
	    this.n9.addActionListener(this);
	    this.add.addActionListener(this);
	    this.subtract.addActionListener(this);
	    this.multiply.addActionListener(this);
	    this.divide.addActionListener(this);
	    this.pow.addActionListener(this);
	    this.sqr.addActionListener(this);
	    this.perc.addActionListener(this);
	    this.equal.addActionListener(this);
	    this.reset.addActionListener(this);
	    this.owner.addActionListener(this);
	}
	
	/**
	 * Metodo operation(). Comprueba que operacion se debe realizar. Mira el estado actual del atributo 
	 * this.operation y, en funcion de ese valor, lleva a cabo una operacion u otra, modificando el atributo 
	 * this.result y actualizando el texto en el display.
	 */
	public void operation() {
	    switch (this.operation) {
	        case '+':
	            this.result = this.num1 + this.num2;
	            break;
	        case '-':
	            this.result = this.num1 - this.num2;
	            break;
	        case 'x':
	            this.result = this.num1 * this.num2;
	            break;
	        case '/':
	            if (this.num2 != 0) {
	                this.result = this.num1 / this.num2;
	            } else {
	                this.display.setText("No hagas eso :(");
	                return;
	            }
	            break;
	        case '^':
	        	this.result = num1;
	        	for (int i = 1; i < num2; i++) {
	        		this.result = this.result * num1;
	        	}
	        	break;
	        case '√':
	        	this.result = (int) Math.sqrt(num1);
	        	break;
	        case '%':
	        	this.result = (num1 * num2) / 100;
	        	break;
	        default:
	            this.result = 0;
	    }
	    this.display.setText(String.valueOf(this.result));
	}
	
	/**
	 * Metodo actionPerformed(). Se encarga de obtener la información que haya en el display (numeros introducidos 
	 * y operacion que se debe realizar) y llamar al metodo operation() para ejecutar dicha operacion.
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
	    String op = e.getActionCommand();
	    switch (op) {
	    	case "INFO":
	    		
	    		break;
	    	case "OWNER":
	    		try {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(new URI("https://github.com/crisuroll"));
                    }
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
	    		break;
	        case "R":
	            this.display.setText("");
	            this.num1 = 0;
	            this.num2 = 0;
	            this.operation = '0';
	            break;
	        case "=":
	            String displayText = this.display.getText().trim();

	            // Expresion regular: \\s(?=[+x/-]) busca un espacio en blanco antes del operador.
	            // (?<=[+x/-])\\s busca un espacio en blanco despues del operador.
	            // El operador | (OR) separa ambas expresiones regulares.
	            // Con esto conseguimos dividir el texto del display de la calculadora eliminando
	            // los espacios en blanco entre operadores, y si no hay un espacio en blanco, en el caso de "-"
	            // el numero mantiene el signo y ya no seria operador, sino indicador de que es negativo.
	            String[] parts = displayText.split("\\s(?=[+x/-^√%])|(?<=[+x/-^√%])\\s");
	            
	            if (parts.length == 3) { // Dos numeros y un operador
	                this.num1 = Integer.parseInt(parts[0].trim());
	                this.operation = parts[1].trim().charAt(0);
	                this.num2 = Integer.parseInt(parts[2].trim());
	                operation();
	            } else if (parts.length == 2) { // Un numero y un operador
	            	this.num1 = Integer.parseInt(parts[0].trim());
	            	this.operation = parts[1].trim().charAt(0);
	            	operation();
	    		} else {
	                this.display.setText("Syntax ERROR");
	            }
	            break;
	        case "-":
	            // Si esta vacio o termina con un espacio (es decir, antes hay un operador), 
	        	// se interpreta como numero negativo
	            if (this.display.getText().isEmpty() || this.display.getText().endsWith(" ")) {
	                this.display.setText(this.display.getText() + "-");
	            } else {
	                // Es un operador
	                this.display.setText(this.display.getText() + " - ");
	            }
	            break;
	        case "+":
	        case "x":
	        case "/":
	        case "^":
	        case "√":
	        case "%":
	        	this.display.setText(this.display.getText() + " " + op + " ");
	            break;
	        default:
	            this.display.setText(this.display.getText() + op);
	            break;
	    }
	}
	
	
}

