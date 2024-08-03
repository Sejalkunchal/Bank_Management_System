package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//We have used extends JFrame to make the frame visible and make use of buttons labels etc
//now use implements ActionListener to click events on button
public class login extends JFrame implements ActionListener {
    JLabel label1,label2,label3;   //declaring the JLabel variable to access it globally
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1,button2,button3;

  //----------------------------------------------------------------------------------

    login(){ //create a constructor login()
        super("Bank Management System"); //super should always be written after constructor

    //ADDING IMAGE NAME WITH BANK
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));//add a image
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);//scale he image
        ImageIcon i3=new ImageIcon(i2);// again converting into ImageIcon
        JLabel image=new JLabel(i3);//we cannot take direct images on the frame we need JLabel to take image on the frame
        image.setBounds(350,10,100,100);//to set position and size of a image
        add(image); //image here is from Jlable image

    //ADDING ANOTHER IMAGE WITH NAME CARD
        ImageIcon ii1=new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image ii2=ii1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon ii3=new ImageIcon(ii2);
        JLabel iimage=new JLabel(ii3);
        iimage.setBounds(630,350,100,100);
        add(iimage);



        //use of JLabel is to display any text on the frame
        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.WHITE);  //add color to te text
        label1.setFont(new Font("AvantGarde",Font.BOLD,38));
        label1.setBounds(230,125,450,40);
        add(label1);

        //label for card number
        label2 = new JLabel("Card No:");
        label2.setFont(new Font("Raleway",Font.BOLD,28));
        label2.setForeground(Color.WHITE);
        label2.setBounds(150,190,375,30);
        add(label2);

        //textFeild for cardnumber
        textField2= new JTextField(15);
        textField2.setBounds(325,190,230,30);
        textField2.setFont(new Font("Arial",Font.BOLD,14));
        add(textField2);

        //label for PIN number
        label3 =new JLabel("Pin No:");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Raleway",Font.BOLD,28));
        label3.setBounds(150,250,375,30);
        add(label3);

        //PasswordFeild for PIN Number
        passwordField3 = new JPasswordField(15);
        passwordField3.setFont(new Font("Arail",Font.BOLD,14));
        passwordField3.setBounds(325,250,230,30);
        add(passwordField3);

        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial",Font.BOLD,14));
        button1.setForeground(Color.WHITE); //color of text
        button1.setBackground(Color.BLACK); //color of background button
        button1.setBounds(300,300,100,30);
        button1.addActionListener(this);//whenever a button is clicked our system will get to know about the click and it will be stored in ActionEvent e
        //i.e actionEvent e will know that which button is clicked
        add(button1);

        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial",Font.BOLD,14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(430,300,100,30);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial",Font.BOLD,14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(300,350,230,30);
        button3.addActionListener(this);
        add(button3);

        //ADDING A BACKGROUND IMAGE
        ImageIcon iii1=new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image iii2=iii1.getImage().getScaledInstance(850,480,Image.SCALE_DEFAULT);
        ImageIcon iii3=new ImageIcon(iii2);
        JLabel iiimage=new JLabel(iii3);
        iiimage.setBounds(0,0,850,480);
        add(iiimage);


        setLayout(null);//by default the layout of the frame is border layout since we want things to be played according to x-axis and y-axis we need it to set it to null
        setSize(850,480); //size of frame
        setLocation(450,200);  //opens the frame at center by giving x-axis and y-axis
        setUndecorated(true);//to disable the option of minimize maximize and cross(exit)
        setVisible(true);    //use to display the frame and should always be at the bottom of the code

    }
    //---------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------
    //WHEN BUTTONS ARE CLICKED
    //we have to override ActionListerner with actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == button1){ //For SIGN IN
                Conn c = new Conn();
                String cardno = textField2.getText();
                String pin= passwordField3.getText();
                String q ="select* from login where card_number = '"+cardno+"' and pin = '"+pin+"' ";
                ResultSet resultSet=c.statement.executeQuery(q);//Store in result set //executeQuery is used for remove or get the values

                if (resultSet.next()) // to see if the data is actally stored in the resultSet we make use of resultSet.next();
                     {
                    setVisible(false);
                    new main_class(pin);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
                }


            }
            else if(e.getSource() == button2){  //For CLEAR
                textField2.setText("");//it will clear the textfield besides cardno
                passwordField3.setText(""); //it will clear the passwordField besides pin no
            }
            else if (e.getSource() ==button3) { //For SIGN UP
                new Signup();
                setVisible(false);
            }
        }
        catch (Exception E){  //take captial E because small e is already used by ActionEvent e
            E.printStackTrace(); //if there is any error it will get displayed
        }


    }

    public static void main(String[] args) {
        new login();
    }
}
