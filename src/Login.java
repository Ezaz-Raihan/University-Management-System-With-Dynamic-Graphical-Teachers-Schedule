import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

class Login
{

    File file = new File("save.txt");
    JTextField text;
    JPasswordField passwd;

    public void SAVE()
    {
        try
        {
            if(!file.exists()) file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write(text.getText());
            bw.newLine();
            bw.write(passwd.getPassword());
            bw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void UPDATE()
    {

        try
        {
            if(file.exists())
            {

                Scanner scan = new Scanner(file);
                text.setText(scan.nextLine());
                passwd.setText(scan.nextLine());
                scan.close();
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public Login()
    {
        JFrame j=new JFrame("American International University");
        j.setContentPane(new JLabel(new ImageIcon("picture.jpg")));
        JLabel lf=new JLabel("Welcome To Our University System");
        lf.setBounds(20,20,500,50);
        lf.setForeground(Color.red);
        lf.setFont(new Font("Times new Rooman",Font.PLAIN,30));
        lf.setHorizontalAlignment(SwingConstants.CENTER);



        JLabel l1=new JLabel("User Id :");
        l1.setBounds(20,110,200,20);
        l1.setForeground(Color.white);
        j.add(l1);

        text =new JTextField();
        text.setBounds(150,110,200,20);
        j.add(text);


        JLabel l2=new JLabel("Password :");
        l2.setBounds(20,170,100,20);
        l2.setForeground(Color.white);
        j.add(l2);


        passwd = new JPasswordField();
        passwd.setBounds(150,170,200,20);
        j.add(passwd);
        UPDATE();

        JCheckBox jc =new JCheckBox("Remember Me");
        jc.setBounds(150,210,115,20);
        j.add(jc);
        jc.addItemListener(e->
        {
            if(jc.isSelected())
            {
                SAVE();
            }
        });

        JButton login=new JButton("Log In");
        login.setBounds(140,260,100,50);
        j.add(login);
        login.addActionListener(e->
        {
            boolean loginSuccess = false;
            String enterId = text.getText();
            String password = new String(passwd.getPassword());
            try{
                
                if(enterId.length()<5)
                {
                    int id = Integer.parseInt(enterId);
                    boolean success = SqlCon.studentlogin(id,password);
                    if(success == true)
                    {
                        j.setVisible(false);
                        new StudentPanel();
                        loginSuccess=true;
                    }
                }
                else if(enterId.length()==5)
                {
                    Admin adminInfo = SqlCon.adminlogin(enterId,password);
                    if(adminInfo != null)
                    {
                        j.setVisible(false);
                        new AdminPanel();
                        loginSuccess=true;
                    }
                }
                if(enterId.length()>5)
                {
                    Teacher t_info = SqlCon.teacherlogin(enterId,password);
                    if(t_info != null)
                    {
                        j.setVisible(false);
                        new TeacherPanel(t_info);
                        loginSuccess=true;
                    }
                }
                if(loginSuccess == false)
                {
                    String wrong="Wrong! UserName or Password";
                    JOptionPane.showMessageDialog(login,wrong);
                }
            }
            catch(Exception ex)
            {
                String wrong="Enter User Id/Password First";
                JOptionPane.showMessageDialog(login,wrong);
            }

        });

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(280,260,100,50);
        j.add(cancel);
        cancel.addActionListener(e -> System.exit(0));

        j.setSize(1100,900);
        j.setLayout(null);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}