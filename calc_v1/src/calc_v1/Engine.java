package calc_v1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Engine extends JFrame implements ActionListener {
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
	private JButton add;
	private JButton subtract;
	private JButton multiply;
	private JButton divide;
	private JButton pow;
	private JButton sqr;
	private JButton perc;
	private JButton equal;
	private JButton reset;
	private JButton extra;
	// Tipos de boton
	private enum ButtonType {REGULAR, OPERATOR, EXTRA};
	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private char operation;
	
	/**
	 * CONSTRUCTORA
	 */
	public Engine() {
		this.frame = new JFrame("crisu_Calc :3 owo");
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
		this.add = new JButton("+");
		this.subtract = new JButton("-");
		this.multiply = new JButton("x");
		this.divide = new JButton("/");
		this.equal = new JButton("=");
		this.pow = new JButton("^");
		this.sqr = new JButton("√");
		this.perc = new JButton("%");
		this.reset = new JButton("C");
		this.extra = new JButton("☆");
		// Pensar si instanciar el enum, int y char
		setSettings();
		addActionEvent();
		
		// ME GUSTARIA AÑADIR BOTONES DE PARENTESIS.
	}
	
	/**
	 * Método setSettings(). Establece la configuración principal de todos los componentes visuales de la ventana.
	 */
	private void setSettings() {
	    // Configurar el JTextField
	    this.display.setEditable(false);
	    this.display.setHorizontalAlignment(JTextField.RIGHT);
	    this.display.setFont(new Font("Arial", Font.BOLD, 20));
	    this.display.setPreferredSize(new Dimension(380, 60));
	    this.display.setBackground(Color.WHITE);

	    // Configurar el panel del display
	    this.displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    this.displayPanel.setPreferredSize(new Dimension(400, 80));
	    this.displayPanel.add(this.display);

	    // Configurar el panel de botones
	    // this.buttonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	    this.buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
	    setFeaturesButton(this.pow, ButtonType.OPERATOR);
	    setFeaturesButton(this.sqr, ButtonType.OPERATOR);
	    setFeaturesButton(this.perc, ButtonType.OPERATOR);
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
	    setFeaturesButton(this.extra, ButtonType.EXTRA);

	    // Añadir botones al panel de botones
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
	    this.buttonPanel.add(this.extra);

	    // Configurar la ventana principal
	    this.contentPanel.setLayout(new BorderLayout());
	    this.contentPanel.add(this.displayPanel, BorderLayout.NORTH);
	    this.contentPanel.add(this.buttonPanel, BorderLayout.CENTER);
	    this.frame.setLayout(new BorderLayout());
	    this.frame.add(this.contentPanel, BorderLayout.CENTER);
	    
		this.frame.setLocation(550, 250);
		this.frame.setSize(400, 400);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Método setFeaturesButton(). Permite distinguir si el tipo de botón que pasa como parámetro es de tipo REGULAR u OPERATOR. En función de esto, 
	 * personaliza el botón de una forma u otra.
	 * @param _button identifica el botón sobre el que se van a cambiar las características.
	 * @param _type identifica de qué tipo es el botón.
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {
	    _button.setFocusPainted(false); // Elimina el borde azul al seleccionarlo
	    _button.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
	    _button.setPreferredSize(new Dimension(75, 75));

	    // Comprobamos si es REGULAR u OPERATOR
	    if (_type == ButtonType.REGULAR) {
	        _button.setBackground(new Color(251, 248, 204));
	        _button.setForeground(Color.BLACK);
	        _button.setFont(new Font("Arial", Font.BOLD, 18));
	    } else if (_type == ButtonType.OPERATOR) {
	        _button.setBackground(new Color(204, 229, 255));
	        _button.setForeground(Color.BLACK);
	        _button.setFont(new Font("Arial", Font.BOLD, 18));
	    } else if (_type == ButtonType.EXTRA) {
	    	_button.setBackground(new Color(255, 204, 213));
	    	_button.setForeground(Color.BLACK);
	    }

	    // Hover   
	    _button.addMouseListener(new MouseAdapter() {
	    	
	    	// Al pasar el cursor sobre un botón, el color se oscurece
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

	        // Cuando el cursor ya no está sobre el botón, vuelve a su color original
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
	 * Método addActionEvent(). Registra los ActionListener para todos los botones de la aplicación. Es decir, para cada botón, 
	 * añade un ActionListener que recibe como parámetro el objeto this para poder identificar el botón que se pulsa.
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
	    this.extra.addActionListener(this);
	}
	
	/**
	 * Método operation(). Comprueba qué operación se debe realizar. Mira el estado actual del atributo this.operation y, en función de ese valor, 
	 * lleva a cabo una operación u otra, modificando el atributo this.result y actualizando el texto en el display.
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
	                this.display.setText("Syntax ERROR: Division by zero");
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
	 * Método actionPerformed(). Se encarga de obtener la información que haya en el display (números introducidos y operación que se debe realizar) 
	 * y llamar al método operation() para ejecutar dicha operación. Por ejemplo, una manera 
	 * podría ser identificar el botón que se ha pulsado y añadir su texto al display y, cuando se pulse sobre el 
	 * botón =, entonces hacemos que se ejecute la operación que se haya indicado con los botones de operación 
	 * (sumar, restar, multiplicar o dividir). En cualquier caso e independientemente de la decisión que se
	 * tome, lo primero que debemos hacer en este método es recoger el tipo de botón que se ha pulsado (para poder 
	 * hacer la distinción) y el texto asociado a ese botón. Esto se puede hacer de la siguiente forma:
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
	    String op = e.getActionCommand();
	    switch (op) {
	        case "C":
	            this.display.setText("");
	            this.num1 = 0;
	            this.num2 = 0;
	            this.operation = '0';
	            break;
	        case "=":
	            String displayText = this.display.getText().trim();

	            // Expresión regular: \\s(?=[+x/-]) busca un espacio en blanco antes del operador.
	            // (?<=[+x/-])\\s busca un espacio en blanco después del operador.
	            // El operador | (OR) separa ambas expresiones regulares.
	            // Con esto conseguimos dividir el texto del display de la calculadora eliminando
	            // los espacios en blanco entre operadores, y si no hay un espacio en blanco, en el caso de "-"
	            // el número mantiene el signo y ya no sería operador, sino indicador de que es negativo.
	            String[] parts = displayText.split("\\s(?=[+x/-^√%])|(?<=[+x/-^√%])\\s");
	            
	            if (parts.length == 3) {
	                this.num1 = Integer.parseInt(parts[0].trim());
	                this.operation = parts[1].trim().charAt(0);
	                this.num2 = Integer.parseInt(parts[2].trim());
	                operation();
	            } else if (parts.length == 2) {
	            	this.num1 = Integer.parseInt(parts[0].trim());
	            	this.operation = parts[1].trim().charAt(0);
	            	operation();
	    		} else {
	                this.display.setText("Syntax ERROR");
	            }
	            break;
	        case "-":
	            // Si está vacío o termina con un espacio (es decir, antes hay un operador), 
	        	// se interpreta como número negativo
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
	            if (!this.display.getText().endsWith(" ")) {
	                this.display.setText(this.display.getText() + " " + op + " ");
	            }
	            break;
	        default:
	            this.display.setText(this.display.getText() + op);
	            break;
	    }
	}
	
	
}
