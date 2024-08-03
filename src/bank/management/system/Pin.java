package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pin extends JFrame implements ActionListener {
    JButton b1,b2;
    JPasswordField p1,p2;
    String pin;
    Pin(String pin){
        this.pin=pin;

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("CHANGE YOUR PIN");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System",Font.BOLD,16));
        label1.setBounds(430,180,150,35);
        l3.add(label1);  //label will be displayed above the image

        JLabel label2 = new JLabel("NEW PIN:");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System",Font.BOLD,16));
        label2.setBounds(430,220,400,35);
        l3.add(label2);  //label will be displayed above the image

        p1=new JPasswordField();
        p1.setBackground(new Color(65,125,128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(600,220,180,25);
        p1.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(p1); //textfield will be displayed above the image

        JLabel label3 = new JLabel("RE-ENTER NEW PIN:");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("System",Font.BOLD,16));
        label3.setBounds(430,250,400,35);
        l3.add(label3);  //label will be displayed above the image

        p2=new JPasswordField();
        p2.setBackground(new Color(65,125,128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(600,255,180,25);
        p2.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(p2); //textfield will be displayed above the image

        b1=new JButton("Change");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("Back");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setSize(1550,1080);
        setLayout(null);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try{
           String pin1= p1.getText();   //data from p1 and p2 are stored in pin1 and pin2
           String pin2=p2.getText();

           if(!pin1.equals(pin2)){  //if pin1 doest not match with pin2 then show msg
               JOptionPane.showMessageDialog(null,"Entered PIN Does Not Match");
               return;
           }
           if (e.getSource()==b1){
               if (p1.getText().equals("")){  // check if textfields are not empty
                   JOptionPane.showMessageDialog(null,"Enter New PIN");
                   return; //return keyword must be written compulsory because if the tetfields are empty then it is neccessary to stop executing the code further
               }
               if (p2.getText().equals("")){
                   JOptionPane.showMessageDialog(null,"Re-Enter New PIN");
                   return;
               }
               Conn c=new Conn();
               //we have stored the pin in three tables i.e bank,login,signup3
               //we have to change pin in these three tables
               String q1="update bank set pin ='"+pin1+"' where pin='"+pin+"'  ";
               String q2="update login set pin ='"+pin1+"' where pin='"+pin+"'  ";
               String q3="update signupthree set pin ='"+pin1+"' where pin='"+pin+"'  ";

               c.statement.executeUpdate(q1); //updating pins
               c.statement.executeUpdate(q2);
               c.statement.executeUpdate(q3);

               JOptionPane.showMessageDialog(null,"PIN changed Successfully");
               setVisible(false); //after succesfully updating this frame will be closed
               new main_class(pin); //main_class frame will be opened by returning the updated pin
           } else if (e.getSource()==b2) {
               new main_class(pin);
               setVisible(false);
           }

        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Pin("");
    }
}
