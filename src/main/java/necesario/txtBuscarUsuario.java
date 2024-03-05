/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package necesario;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Luis mora
 */
public class txtBuscarUsuario
        extends JTextField
        implements ComponentListener {

    private boolean mayusculas = false;
    private boolean soloNumeros = false;
    private boolean soloLetras = false;

    private Dimension d = new Dimension(250, 42);
    private String placeholder = "";
    private Color phColor = new Color(0, 112, 192);
    private boolean band = true;
    private final JButton button = new JButton();
    private Color borderColor = new Color(0, 112, 192);
    private ImageIcon iconBlack = new ImageIcon(getClass().getResource("/necesario/txt_x1.png"));
    private ImageIcon iconWhite = new ImageIcon(getClass().getResource("/necesario/txt_x2.png"));
    private boolean xBlackIcon = false;
    private int heightBtn = this.d.height - 10;
    private Font f = new Font("Tahoma", Font.BOLD, 14);

    public txtBuscarUsuario() {
        setSize(this.d);
        setPreferredSize(this.d);
        setVisible(true);
        setMargin(new Insets(3, 6, 3, 6));

        this.setFont(f);
        this.setForeground(new Color(0, 112, 192));
        this.button.setText("");
        this.button.setBorderPainted(false);
        this.button.setContentAreaFilled(false);
        this.button.setMargin(new Insets(2, 2, 2, 2));
        this.button.setVisible(true);
        this.button.setFocusPainted(false);
        this.button.setCursor(new Cursor(12));
        this.button.setBackground(new Color(0, 112, 192));
        add(this.button);
        setVisible(true);
        addComponentListener(this);
        setSelectionColor(this.button.getBackground());
        updateButton();

        getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
                txtBuscarUsuario.this.band = (txtBuscarUsuario.this.getText().length() <= 0);
            }

            public void insertUpdate(DocumentEvent e) {
                txtBuscarUsuario.this.band = false;
            }

            public void changedUpdate(DocumentEvent de) {
            }
        });
        this.button.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                ((JTextField) txtBuscarUsuario.this.button.getParent()).setText("");
                ((JTextField) txtBuscarUsuario.this.button.getParent()).requestFocus();
                usuarios.Opciones.listar("");
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                txtBuscarUsuario.this.button.setOpaque(true);
                txtBuscarUsuario.this.button.setIcon(txtBuscarUsuario.this.xBlackIcon ? txtBuscarUsuario.this.iconBlack : txtBuscarUsuario.this.iconWhite);
            }

            public void mouseExited(MouseEvent e) {
                txtBuscarUsuario.this.button.setOpaque(false);
                txtBuscarUsuario.this.button.setIcon(null);
            }
        });

        addKeyListener(new EventoKey());

        this.setPlaceholder("Escribe algo...");
    }

    public class EventoKey extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            char car = e.getKeyChar();
            if (isSoloLetras()) {
                if ((car < 'a' || car > 'z') && (car < 'A' | car > 'Z')) {
                    if ((car != 'ñ') && (car != 'Ñ') && (car != 'á') && (car != 'Á') && (car != 'é') && (car != 'É') && (car != 'í')
                            && (car != 'Í') && (car != 'ó') && (car != 'Ó') && (car != 'ú') && (car != 'Ú') && (car != ' ')) {
                        e.consume();
                    }
                }
            }
            if (isSoloNumeros()) {
                if ((car < '0' || car > '9')) {
                    e.consume();
                }
            }
            if (isMayusculas()) {
                if (Character.isLowerCase(car)) {
                    String cad = ("" + car).toUpperCase();
                    car = cad.charAt(0);
                    e.setKeyChar(car);
                }
            }
        }

    }

    public boolean isxDarkIcon() {
        return this.xBlackIcon;
    }

    public void setxDarkIcon(boolean xDarkIcon) {
        this.xBlackIcon = xDarkIcon;
    }

    public Color getBotonColor() {
        return this.button.getBackground();
    }

    public void setBotonColor(Color botonColor) {
        this.button.setBackground(botonColor);
        setSelectionColor(this.button.getBackground());
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        updateBorder();
    }

    private void updateBorder() {
        Border border = BorderFactory.createLineBorder(this.borderColor, 2);
        setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, this.button.getSize().width + 5)));
    }

    private void updateButton() {
        this.heightBtn = (getSize().height - 10);
        this.button.setSize(new Dimension(this.heightBtn, this.heightBtn));
        this.button.setPreferredSize(new Dimension(this.heightBtn, this.heightBtn));

        this.button.setLocation(getWidth() - this.button.getWidth() - 5, 5);
        updateBorder();
    }

    public void componentResized(ComponentEvent e) {
        updateButton();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public Color getPhColor() {
        return this.phColor;
    }

    public void setPhColor(Color phColor) {
        this.phColor = phColor;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(this.phColor.getRed(), this.phColor.getGreen(), this.phColor.getBlue(), 90));

        g.drawString(this.band ? this.placeholder : "",
                getMargin().left,
                getSize().height / 2 + getFont().getSize() / 2);
    }

    public boolean isMayusculas() {
        return mayusculas;
    }

    public void setMayusculas(boolean mayusculas) {
        this.mayusculas = mayusculas;
    }

    public boolean isSoloNumeros() {
        return soloNumeros;
    }

    public void setSoloNumeros(boolean soloNumeros) {
        this.soloNumeros = soloNumeros;
        if (this.isSoloLetras()) {
            this.soloLetras = false;
            this.mayusculas = false;
        }

    }

    public boolean isSoloLetras() {
        return soloLetras;
    }

    public void setSoloLetras(boolean soloLetras) {
        this.soloLetras = soloLetras;
        if (this.isSoloNumeros()) {
            this.soloNumeros = false;
            this.mayusculas = false;
        }
    }

}
