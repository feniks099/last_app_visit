import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.text.ParseException;

public class MyFrame3 extends JFrame implements ActionListener{

    JButton button1;
    JButton button2;
    int x = 2;

    public int getX(){
        return this.x;
    }

    MyFrame3() throws ParseException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(7,1,50,15));

        JLabel label1 = new JLabel("Выберите отчет");

        button1 = new JButton();
        button1.setText("Список пациентов по дате последнего визита в КНИГЕ ЗАПИСИ");
        button1.addActionListener(this);
        button1.setFocusable(false);
        button1.setFont(new Font("Helvetica", Font.BOLD, 15));

        button2 = new JButton();
        button2.setText("Список пациентов и средних сумм счетов");
        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setFont(new Font("Helvetica", Font.BOLD, 15));

        JPanel panel1 = new JPanel();
        panel1.add(label1);
        panel1.setSize(150,150);
        panel1.setOpaque(false);

        JPanel panel2 = new JPanel();
        panel2.add(button1);
        panel2.setOpaque(false);

        JPanel panel3 = new JPanel();
        panel3.add(button2);
        panel3.setOpaque(false);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.setSize(700,700);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        URL iconURL = getClass().getResource("155.png");
        ImageIcon d4w = new ImageIcon(iconURL);
        this.setIconImage(d4w.getImage());
        this.getContentPane().setBackground(new Color(240,228,218));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        { if(e.getSource() == button1) {
            this.x = 0;
            this.dispose();}

            else if (e.getSource() == button2) {
                this.x = -1;
            this.dispose();}


        }

    }
}