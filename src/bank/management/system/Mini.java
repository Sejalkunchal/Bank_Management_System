package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Mini extends JFrame implements ActionListener {
    JButton button;
    String pin;
    Mini(String pin){
        this.pin=pin;
        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);

        JLabel label1=new JLabel();
        label1.setBounds(20,140,400,200);
        add(label1);

        JLabel label2=new JLabel("TechCoder A.V");
        label2.setFont(new Font("System",Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        JLabel label3=new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        JLabel label4= new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        try{
            Conn c= new Conn();
            ResultSet resultSet= c.statement.executeQuery("select * from login where pin='"+pin+"'  ");
            while (resultSet.next()){
                label3.setText("Card Number: "+ resultSet.getString("card_number").substring(0,4) + "XXXXXXXXXXXX"+ resultSet.getString("card_number").substring(12));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {

          Conn c= new Conn();
            //if we want to remove the data from data we use ResultSet

            ResultSet resultSet=c.statement.executeQuery("Select * from bank where pin='"+pin+"'");//execeuteQuery is used to fetch or remove value from table
            int balance=0;
            while(resultSet.next()){
                label1.setText(label1.getText()+ "<html>"+resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+"<br><br><html>");

                if (resultSet.getString("type").equals("Deposit"))// if the data in the result set matches the type wala column with deposit value there we have to plus and equal the value
                {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }
                else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            label4.setText("Your Total Balance is RS "+balance);

        }catch (Exception e){
            e.printStackTrace();
        }

        button= new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.setBackground(Color.black);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new Mini("");
    }
}
