import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.*;
import java.util.Vector;

public class MyFrame4 extends JFrame implements ActionListener {

    JButton button;

    String date1 = "2022-02-01";
    String date2 = "2022-02-02";

    String dsn = "lol";

    int avg = 0;

    JTextField text1;
    JTextField text2;

    JTextField text3;

    JTextField text4;

    public int x = 2;

    public String getDate1(){
        return date1;
    }
    public String getDate2(){
        return date2;
    }

    public String getDsn(){
        return this.dsn;
    }

    public int getAvg(){
        return avg;
    }

    public int getI(){
        return x;
    }

    MyFrame4() throws ParseException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(5,1,50,50));

        text1 = new JTextField();
        text1.setPreferredSize(new Dimension(200,25));


        text2 = new JTextField();
        text2.setPreferredSize(new Dimension(200,25));


        text3 = new JTextField();
        text3.setPreferredSize(new Dimension(200,25));


        text4 = new JTextField();
        text4.setPreferredSize(new Dimension(200,25));

        JLabel period1 = new JLabel("Начало периода");
        JLabel period2 = new JLabel("Конец периода");
        JLabel avg = new JLabel("Средняя сумма счетов");
        JLabel dsn = new JLabel("Укажите имя сервера");

        JButton button = new JButton("GO");
        button.addActionListener(this);
        JPanel panel1 = new JPanel();
        panel1.add(button);
        panel1.setSize(new Dimension(50,50));

        JPanel panel2 = new JPanel();
        panel2.add(period1);
        panel2.add(text1);
        panel2.setSize(new Dimension(50,15));

        JPanel panel3 = new JPanel();
        panel3.add(period2);
        panel3.add(text2);
        panel3.setSize(new Dimension(50,15));

        JPanel panel4 = new JPanel();
        panel4.add(avg);
        panel4.add(text3);
        panel4.setSize(new Dimension(50,15));

        JPanel panel5 = new JPanel();
        panel5.add(dsn);
        panel5.add(text4);
        panel5.setSize(new Dimension(50,15));

        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        {
            this.date1 = text1.getText();
            this.date2 = text2.getText();
            this.avg = Integer.parseInt(text3.getText());
            this.dsn = text4.getText();

            System.out.println(date1);
            System.out.println(date2);
            this.x = 0;
            System.out.println(x + " ffff");

        }

    }
}