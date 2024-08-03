package bank.management.system;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.jar.JarFile;

public class FastCash extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    FastCash(String pin){
        this.pin=pin;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label=new JLabel("SELECT WITHDRAWAL AMOUNT ");
        label.setBounds(445,180,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,23));
        l3.add(label);

        b1 = new JButton("RS. 100");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125,128));
        b1.setBounds(410,274,150,35);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("RS. 500");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125,128));
        b2.setBounds(700,274,150,35);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("RS. 1000");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(410,318,150,35);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("RS. 2000");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125,128));
        b4.setBounds(700,318,150,35);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("RS. 5000");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125,128));
        b5.setBounds(410,362,150,35);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("RS. 10000");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65,125,128));
        b6.setBounds(700,362,150,35);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("BACK");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65,125,128));
        b7.setBounds(700,406,150,35);
        b7.addActionListener(this);
        l3.add(b7);



        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
        setLayout(null);//if we want to set elements according to our layout then first set it to null

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==b7){
            setVisible(false);
            new main_class(pin);
        } else  {
            String amount = ((JButton)e.getSource()).getText().substring(4);  //e.getsource is used to return an object.....getText() is used to et the text from buttons & by using substring we will only get the amount for example 100 i.e here RS. and space is removed and only we will get the amount
            Conn c= new Conn();
            Date date=new Date();
            try {
               //if we want to remove the data from data we use ResultSet

                ResultSet resultSet=c.statement.executeQuery("Select * from bank where pin='"+pin+"'");//execeuteQuery is used to fetch or remove value from table
                int balance=0;
                while(resultSet.next()){
                    if (resultSet.getString("type").equals("Deposit"))// if the data in the result set matches the type wala column with deposit value there we have to plus and equal the value
                    {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    }
                    else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                    }String num="17";
                if (e.getSource()!= b7 && balance <Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }

                c.statement.executeUpdate("Insert into bank values('" + pin + "','" + date + "','withdrawal','" + amount + "' )");
                JOptionPane.showMessageDialog(null,"Rs. "+amount+" Debited Successfully");

            }catch (Exception E){
                E.printStackTrace();
            }

            setVisible(false);
            new main_class(pin);
        }

    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
