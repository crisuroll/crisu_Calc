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
import javax.swing.JLabel;
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
	private JLabel base;
	private Base baseActual;
	// Display
	private JTextField display;
	// Botones
	private JButton del, brand, b2, b8, b10, b16, a, b, c, d, e, f, info, owner, n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, add, subtract, multiply, divide, pow, sqr, perc,
	equal, reset;
	// Tipos de boton
	private enum ButtonType {REGULAR, OPERATOR, BASE, HEX, EXTRA, BRAND};
	// Tipos de base
	private enum Base {B2, B8, B10, B16};
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
		this.base = new JLabel("Base: ");
		this.brand = new JButton("CASIO");
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
		this.del = new JButton("⌫");
		setSettings();
		addActionEvent();
	}
	
	/**
	 * Metodo addButtonsToPanel(). Añade todos los botones al manel de botones de manera simplificada con un
	 * bucle for each.
	 * @param buttons es un array de tipo JButton que contiene todos los botones.
	 */
	private void addButtonsToPanel(JButton[] buttons) {
	    for (JButton button : buttons) {
	        this.buttonPanel.add(button);
	    }
	}
	
	/**
	 * Metodo setSettings(). Establece la configuracion principal de todos los componentes visuales de la ventana.
	 */
	public void setSettings() {
	    // Configurar el display
	    this.display.setEditable(false);
	    this.display.setHorizontalAlignment(JTextField.RIGHT);
	    this.display.setFont(new Font("Arial", Font.BOLD, 20));
	    this.display.setPreferredSize(new Dimension(380, 50));
	    this.display.setBackground(Color.WHITE);

	    // Configurar subpaneles izquierdo y derecho
	    this.basePanel.setLayout(new BorderLayout());
	    this.basePanel.add(base);
	    this.brandPanel.setLayout(new BorderLayout());
	    this.brandPanel.add(brand);
	    
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
	    JButton[] regularButtons = {n0, n1, n2, n3, n4, n5, n6, n7, n8, n9};
	    JButton[] operatorButtons = {add, subtract, multiply, divide, pow, sqr, perc, equal};
	    JButton[] baseButtons = {b2, b8, b10, b16};
	    JButton[] hexButtons = {a, b, c, d, e, f};
	    JButton[] extraButtons = {del, reset, info, owner, brand};
	    for (JButton btn : regularButtons) setFeaturesButton(btn, ButtonType.REGULAR);
	    for (JButton btn : operatorButtons) setFeaturesButton(btn, ButtonType.OPERATOR);
	    for (JButton btn : baseButtons) setFeaturesButton(btn, ButtonType.BASE);
	    for (JButton btn : hexButtons) setFeaturesButton(btn, ButtonType.HEX);
	    for (JButton btn : extraButtons) setFeaturesButton(btn, ButtonType.EXTRA);
	    addButtonsToPanel(new JButton[]{b2, b8, b10, b16, a, b, c, info, d, e, f, owner, 
                pow, sqr, perc, add, n7, n8, n9, subtract, 
                n4, n5, n6, multiply, n1, n2, n3, divide, 
                reset, n0, equal, del});

	    // Configurar la ventana principal
	    this.contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    this.contentPanel.setLayout(new BorderLayout());
	    this.contentPanel.add(this.infoPanel, BorderLayout.NORTH);
	    this.contentPanel.add(this.displayPanel, BorderLayout.CENTER);
	    this.contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);
	    this.frame.setLayout(new BorderLayout());
	    this.frame.add(this.contentPanel, BorderLayout.CENTER);
		this.frame.setLocation(550, 150);
		this.frame.setSize(500, 620);
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
	    } else if (_type == ButtonType.BRAND) {
	    	_button.setPreferredSize(new Dimension(25, 30));
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
	    		} else if (_type == ButtonType.BASE) {
	    			_button.setBackground(new Color(160, 230, 190));
	    		} else if (_type == ButtonType.HEX) {
	    			_button.setBackground(new Color(190, 160, 255));
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
	    		} else if (_type == ButtonType.BASE) {
	    			_button.setBackground(new Color(204, 255, 213));
	    		} else if (_type == ButtonType.HEX) {
	    			_button.setBackground(new Color(213, 204, 255));
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
		JButton[] buttons = {b2, b8, b10, b16, a, b, c, d, e, f, n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, 
                add, subtract, multiply, divide, pow, sqr, perc, equal, reset, del, info, owner, brand};
		for (JButton button : buttons) {
			button.addActionListener(this);
		}
	}
	
	/**
	 * Metodo convertToDecimal(). Convierte el valor del display a decimal.
	 * @param value es el valor que muestra el display.
	 * @param base es la base en la que se encuentra el valor.
	 * @return retorna el valor ya convertido
	 */
    public int convertToDecimal(String value, Base base) {
        switch (base) {
            case B2: return Integer.parseInt(value, 2);
            case B8: return Integer.parseInt(value, 8);
            case B16: return Integer.parseInt(value, 16);
            default: return Integer.parseInt(value);
        }
    }

	/**
	 * Metodo convertFromDecimal(). Convierte el valor del display de base decimal a la base seleccionada.
	 * @param value es el valor que muestra el display.
	 * @param base es la base a la que se va a convertir el valor.
	 * @return retorna el valor ya convertido
	 */
    public String convertFromDecimal(int value, Base base) {
        switch (base) {
            case B2: return Integer.toBinaryString(value);
            case B8: return Integer.toOctalString(value);
            case B16: return Integer.toHexString(value).toUpperCase();
            default: return String.valueOf(value);
        }
    }
	
	/**
	 * Metodo updateBase(). Actualiza el texto de la base en la que se va a operar. No se encarga de cambiar de
	 * base. También llama a disableForBinary() y enableHex() para activar o desactivar los botones según la
	 * base elegida.
	 */
	public void updateBase(Base _base) {
	    switch (_base) {
	        case B2:
	            this.base.setText("Base: Binaria");
	            disableForBinary(false);
	            disableForOctal(true);
	            enableHex(false);
	            break;
	        case B8:
	        	this.base.setText("Base: Octal");
	        	disableForBinary(true);
	        	disableForOctal(false);
	        	enableHex(false);
	            break;
	        case B10:
	        	this.base.setText("Base: Decimal");
	        	disableForBinary(true);
	        	disableForOctal(true);
	        	enableHex(false);
	            break;
	        case B16:
	        	this.base.setText("Base: Hexadecimal");
	        	disableForBinary(true);
	        	disableForOctal(true);
	            enableHex(true);
	        	break;
	    }
	}

	/**
	 * Metodo disableForBinary(). Los botones se desactivan segun el caso de uso.
	 * @param enabled indica si los botones se van a activar (True) o no (False).
	 */
	public void disableForBinary(boolean enabled) {
		this.n2.setEnabled(enabled);
		this.n3.setEnabled(enabled);
		this.n4.setEnabled(enabled);
		this.n5.setEnabled(enabled);
		this.n6.setEnabled(enabled);
		this.n7.setEnabled(enabled);
		this.n8.setEnabled(enabled);
		this.n9.setEnabled(enabled);
	}
	
	/**
	 * Metodo disableForOctal(). Los botones se desactivan segun el caso de uso.
	 * @param enabled indica si los botones se van a activar (True) o no (False).
	 */
	public void disableForOctal(boolean enabled) {
		this.n8.setEnabled(enabled);
		this.n9.setEnabled(enabled);
	}

	/**
	 * Metodo enableHex(). Los botones se desactivan segun el caso de uso.
	 * @param enabled indica si los botones se van a activar (True) o no (False).
	 */
	public void enableHex(boolean enabled) {
	    this.a.setEnabled(enabled);
	    this.b.setEnabled(enabled);
	    this.c.setEnabled(enabled);
	    this.d.setEnabled(enabled);
	    this.e.setEnabled(enabled);
	    this.f.setEnabled(enabled);
	}
	
	/**
	 * Metodo operation(). Comprueba que operacion se debe realizar. Mira el estado actual del atributo 
	 * this.operation y, en funcion de ese valor, lleva a cabo una operacion u otra, modificando el atributo 
	 * this.result y actualizando el texto en el display.
	 */
	public void operation() {
	    try {
	        // Realizar la operación en decimal
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
		                this.display.setText("Syntax ERROR");
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
	                this.display.setText("Syntax ERROR");
	                return;
	        }
	        // Convertir y mostrar el resultado en la base actual
	        this.display.setText(convertFromDecimal(this.result, this.baseActual));
	    } catch (NumberFormatException ex) {
	        this.display.setText("Syntax ERROR");
	    }
	}
	
	/**
	 * Metodo actionPerformed(). Se encarga de obtener la información que haya en el display (numeros introducidos 
	 * y operacion que se debe realizar) y llamar al metodo operation() para ejecutar dicha operacion.
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
	    String op = e.getActionCommand();
	    switch (op) {
	        case "CASIO":
	            try {
	                Desktop desktop = Desktop.getDesktop();
	                if (desktop.isSupported(Desktop.Action.BROWSE)) {
	                    desktop.browse(new URI("https://www.casio.com/es/scientific-calculators/"));
	                }
	            } catch (IOException | URISyntaxException ex) {
	                ex.printStackTrace();
	            }
	            break;
	        case "INFO":
	            new VentanaEmergente(this).setVisible(true);
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
	        case "B2":
	        case "B8":
	        case "B10":
	        case "B16":
	            try {
	                // Si el display no está vacío y no contiene operadores
	                if (!this.display.getText().isEmpty() && !this.display.getText().trim().matches(".*[+\\-x/^√% ].*")) {
	                    // Convertir el número en el display de la base actual a decimal
	                    int currentValue = convertToDecimal(this.display.getText().trim(), this.baseActual);
	                    this.baseActual = Base.valueOf(op);
	                    this.display.setText(convertFromDecimal(currentValue, this.baseActual));
	                } else {
	                    this.baseActual = Base.valueOf(op);
	                }
	                updateBase(this.baseActual);
	            } catch (NumberFormatException ex) {
	                this.display.setText("Syntax ERROR");
	            }
	            break;
	        case "R":
	            this.display.setText("");
	            this.base.setText("Base: ");
	            this.baseActual = null;
	            this.num1 = 0;
	            this.num2 = 0;
	            this.operation = '0';
	            break;
	        case "⌫":
	            String currentText = this.display.getText();
	            if (!currentText.isEmpty()) {
	                if (currentText.endsWith(" ")) {
	                    this.display.setText(currentText.substring(0, currentText.length() - 3));
	                } else if (currentText.length() > 1 && currentText.charAt(currentText.length() - 2) == '-' &&
	                           !Character.isWhitespace(currentText.charAt(currentText.length() - 2))) {
	                    this.display.setText(currentText.substring(0, currentText.length() - 2));
	                } else if (currentText.length() == 1 && currentText.equals("-")) {
	                    this.display.setText("");
	                } else {
	                    this.display.setText(currentText.substring(0, currentText.length() - 1));
	                }
	            }
	            break;
	        case "=":
	            if (this.baseActual == null) {
	                this.display.setText("ERROR: No hay base");
	                break;
	            }
	            String displayText = this.display.getText().trim();
	            String[] parts = displayText.split("(?<=\\p{XDigit})\\s+(?=[+x/^√%-])|(?<=[+x/^√%-])\\s+");

	            try {
	                if (parts.length == 3) { // Dos números y un operador
	                    // Convertir los números a decimal desde la base actual
	                    this.num1 = convertToDecimal(parts[0].trim(), this.baseActual);
	                    this.operation = parts[1].trim().charAt(0);
	                    this.num2 = convertToDecimal(parts[2].trim(), this.baseActual);
	                    operation();
	                } else if (parts.length == 2) { // Un número y un operador
	                    this.num1 = convertToDecimal(parts[0].trim(), this.baseActual);
	                    this.operation = parts[1].trim().charAt(0);
	                    operation();
	                } else {
	                    this.display.setText("Syntax ERROR");
	                }
	            } catch (NumberFormatException ex) {
	                this.display.setText("Syntax ERROR");
	            }
	            break;
	        case "-":
	            if (this.display.getText().isEmpty() || this.display.getText().endsWith(" ")) {
	                this.display.setText(this.display.getText() + "-");
	            } else {
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

