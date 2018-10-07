import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.lang.String;

class CreateTeacher
{
    JFrame frame=new JFrame("American International University");
    JComboBox <String>jc = new JComboBox <> ();
    ArrayList <String> dep_name = new ArrayList<>();
    public CreateTeacher()
    {

        frame.setLocation(500,10);
        JLabel l1 = new JLabel("Name : ");
        l1.setBounds(10,30,150,20);
        frame.add(l1);

        JTextField name=new JTextField();
        name.setBounds(200,30,200,20);
        frame.add(name);

        JLabel l3 = new JLabel("Phone Number : ");
        l3.setBounds(10,80,150,20);
        frame.add(l3);

        JTextField phn=new JTextField();
        phn.setBounds(200,80,200,20);
        frame.add(phn);

        JLabel l4 = new JLabel("Email :");
        l4.setBounds(10,130,150,20);
        frame.add(l4);

        JTextField email=new JTextField();
        email.setBounds(200,130,200,20);
        frame.add(email);

        JLabel l5 = new JLabel("Department :");
        l5.setBounds(10,180,150,20);
        frame.add(l5);

        try
        {
            String query ="select DEP_NAME from department";
            ResultSet result = SqlCon.getResult(query);
            while(result.next())
            {
                dep_name.add(result.getString(1));
            }
            for(int j=0; j<dep_name.size(); j++)
            {
                jc.addItem(dep_name.get(j));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Department DB problem");
        }
        jc.setEditable(false);
        jc.setBounds(200,180,220,20);
        frame.add(jc);

        JLabel l6 = new JLabel("Position :");
        l6.setBounds(10,230,150,20);
        frame.add(l6);

        JTextField posi=new JTextField();
        posi.setBounds(200,230,200,20);
        frame.add(posi);

        JLabel l7 = new JLabel("Room Number:");
        l7.setBounds(10,280,150,20);
        frame.add(l7);

        JTextField room=new JTextField();
        room.setBounds(200,280,200,20);
        frame.add(room);

        JLabel l8 = new JLabel("Password :");
        l8.setBounds(10,330,150,20);
        frame.add(l8);

        JTextField passwd=new JTextField();
        passwd.setBounds(200,330,200,20);
        frame.add(passwd);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,380,120,50);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try{
                boolean emailOk=false;
                boolean numOk=false;
                boolean passOk=false;
                String t_name = name.getText();
                String t_phone = phn.getText();
                String t_email = email.getText();
                String t_dep = jc.getSelectedItem().toString();
                String t_posi = posi.getText();
                String t_room = room.getText();
                String t_pass = passwd.getText();
                if(t_name.length()!=0 &&t_phone.length()!=0 && t_email.length()!=0 && t_dep.length()!=0 && t_posi.length()!=0 && t_pass.length()!=0)
                {
                    try
                    {
                        int i =Integer.parseInt(t_phone);
                        numOk =true;
                    }
                    catch(NumberFormatException nmf)
                    {
                        numOk =false;
                    }
                    if(numOk == false)
                    {
                        JOptionPane.showMessageDialog(confirm,"Enter A Valid Number");
                    }
                    if(t_email.contains("@")&&(t_email.contains(".com") || t_email.contains(".edu")))
                    {
                        emailOk=true;
                    }
                    if(emailOk == false)
                    {
                        JOptionPane.showMessageDialog(confirm,"Enter A Valid Email");
                    }
                    if(t_pass.length()>4)
                    {
                        passOk=true;
                    }
                    if(passOk == false)
                    {
                        JOptionPane.showMessageDialog(confirm,"Password is Too Short");
                    }
                    if(numOk == true && emailOk == true && passOk == true)
                    {
                        SqlCon.entryTeacher(t_name,t_phone,t_email,t_dep,t_posi,t_room,t_pass);
                        frame.setVisible(false);
                        //new AdminPanel();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(confirm,"Fill All the requiremnent");
                }
            }
            catch(Exception ex)
            {
                System.out.println("Connection Problem");
            }

        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,380,100,50);
        frame.add(cancel);
        cancel.addActionListener(e ->
        {
            frame.setVisible(false);
            //new AdminPanel();
        });


        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}