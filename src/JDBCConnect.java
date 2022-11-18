import com.opencsv.CSVWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.io.*;
import java.lang.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JDBCConnect
{
    public JDBCConnect() throws IOException {
    }

    public static void main(String args[] ) throws ParseException, IOException {

        try {

            MyFrame3 frame3 = new MyFrame3();
            int x = 2;

            while (x > 1) {
                Thread.sleep(1000);
                x = frame3.getX();
                System.out.println(x);
            }

            if (x == 0) {
                MyFrame frame = new MyFrame();
                int i = 2;

                while (i > 1) {
                    Thread.sleep(1000);
                    i = frame.getI();
                    System.out.println(i);
                }

                String url = "jdbc:sqlanywhere:UserID=dba;Password=sql;Host=localhost:2638;ServerName=lol;DatabaseName=d4w";
                String url2 = url.replace("lol", frame.getDsn());
                System.out.println(url2);

                Connection con = DriverManager.getConnection(url2);

                PreparedStatement stmt = con.prepareStatement("SELECT patients_cart_num, firstname, surname, last_visit\n" +
                        "FROM patients INNER JOIN (SELECT pat_id, last_visit\n" +
                        "FROM (SELECT pat_id, MAX(app_date) AS last_visit\n" +
                        "FROM a_appointments\n" +
                        "WHERE pat_id IS NOT NULL\n" +
                        "GROUP BY pat_id) X\n" +
                        "WHERE last_visit BETWEEN ? AND ? // Период последнего визита в книге записи\n" +
                        "GROUP BY pat_id, last_visit) AS apps\n" +
                        "ON patients.patient_id = apps.pat_id");

                String period1 = frame.getDate1();
                String period2 = frame.getDate2();
                LocalDate locale1 = LocalDate.parse(period1);
                LocalDate locale2 = LocalDate.parse(period2);
                Timestamp first1 = Timestamp.valueOf(locale1.atTime(LocalTime.MIDNIGHT));
                Timestamp first2 = Timestamp.valueOf(locale2.atTime(LocalTime.MIDNIGHT));

                stmt.setTimestamp(1, first1);
                stmt.setTimestamp(2, first2);
                //ResultSet rs = stmt.executeQuery();
                //JTable table = new JTable(buildTableModel(rs));
                MyFrame2 frame2 = new MyFrame2();
                //frame2.add(new JScrollPane(table));
                frame2.setLocationRelativeTo(null);
                frame2.pack();

            }

            if (x == -1) {
                MyFrame4 frame4 = new MyFrame4();
                int i = 2;

                while (i > 1) {
                    Thread.sleep(1000);
                    i = frame4.getI();
                    System.out.println(i);
                }

                String url = "jdbc:sqlanywhere:UserID=dba;Password=sql;Host=localhost:2638;ServerName=lol;DatabaseName=d4w";
                String url2 = url.replace("lol", frame4.getDsn());
                System.out.println(url2);

                Connection con = DriverManager.getConnection(url2);

                PreparedStatement stmt = con.prepareStatement("SELECT one.patients_cart_num, one.firstname, one.surname, one.average\n" +
                        "FROM (SELECT patients_cart_num, patient_id, firstname, surname, average\n" +
                        "FROM patients INNER JOIN (SELECT accid, AVG(total) AS average\n" +
                        "FROM (SELECT send_acc_to_pat_id AS accid, total\n" +
                        "FROM patients_accounts\n" +
                        "WHERE lock_date_deletion IS NULL) Y\n" +
                        "GROUP BY accid) AS ACC\n" +
                        "ON patients.patient_id = ACC.accid\n" +
                        "WHERE average >= ?) AS one INNER JOIN (SELECT patient_id, firstname, surname\n" +
                        "FROM patients INNER JOIN (SELECT pat, visit\n" +
                        "FROM (SELECT patient_id AS pat, MAX(treat_date) as visit\n" +
                        "FROM \"treat\"\n" +
                        "GROUP BY pat) X\n" +
                        "WHERE visit BETWEEN ? AND ?) AS latest\n" +
                        "ON patients.patient_id = latest.pat) AS two\n" +
                        "ON one.patient_id = two.patient_id");

                String period1 = frame4.getDate1();
                String period2 = frame4.getDate2();
                LocalDate locale1 = LocalDate.parse(period1);
                LocalDate locale2 = LocalDate.parse(period2);
                Timestamp first1 = Timestamp.valueOf(locale1.atTime(LocalTime.MIDNIGHT));
                Timestamp first2 = Timestamp.valueOf(locale2.atTime(LocalTime.MIDNIGHT));

                stmt.setInt(1, frame4.getAvg());
                stmt.setTimestamp(2, first1);
                stmt.setTimestamp(3, first2);
                ResultSet rs2 = stmt.executeQuery();

                BufferedWriter writer = new BufferedWriter(new FileWriter("result.csv"));

                try {
                    while (rs2.next()) {
                        String card_num = rs2.getString(1);
                        String firstName = rs2.getString(2);
                        String lastName = rs2.getString(3);
                        String average = rs2.getString(4);

                        writer.write(card_num+";");
                        writer.write(firstName+";");
                        writer.write(lastName+";");
                        writer.write(average+";"+"\n");

                    }
                    writer.flush();

                    writer.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                JTable table = new JTable(buildTableModel(rs2));
                MyFrame2 frame2 = new MyFrame2();
                frame2.add(new JScrollPane(table));
                frame2.setLocationRelativeTo(null);
                frame2.pack();

            }


        }

        catch (SQLException sqe)
        {
            System.out.println("Unexpected exception : " +
                    sqe.toString() + ", sqlstate = " +
                    sqe.getSQLState());
            System.exit(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }



    }

    private static TableModel buildTableModel(ResultSet rs) throws SQLException, IOException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);


    }




}