import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class ChangePassword
{
    public ChangePassword(Teacher teacher)
    {
        JFrame frame=new JFrame("American International University");


        JLabel l2 = new JLabel("Enter Old Password:");
        l2.setBounds(10,60,150,20);
        frame.add(l2);

        JPasswordField oldpass=new JPasswordField();
        oldpass.setBounds(200,60,200,20);
        frame.add(oldpass);

        JLabel l3 = new JLabel("Enter New Password:");
        l3.setBounds(10,140,150,20);
        frame.add(l3);

        JPasswordField passwd1=new JPasswordField();
        passwd1.setBounds(200,140,200,20);
        frame.add(passwd1);

        JLabel l4 = new JLabel("Confirm New Password:");
        l4.setBounds(10,220,150,20);
        frame.add(l4);

        JPasswordField passwd2=new JPasswordField();
        passwd2.setBounds(200,220,200,20);
        frame.add(passwd2);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,320,120,70);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try{
                
                boolean success=false;
                String oldPassword = new String(oldpass.getPassword());
                String newPassword = new String(passwd1.getPassword());
                String confirmPass = new String(passwd2.getPassword());
                if(oldPassword.length()==0 || newPassword.length()==0 || confirmPass.length()==0 )
                {
                    JOptionPane.showMessageDialog(confirm,"Fill All Requirments");
                }
                else
                {
                    if(newPassword.length()<5)
                    {
                        JOptionPane.showMessageDialog(confirm,"Password Is Too Short");
                    }
                    else if(oldPassword.equals(teacher.password) && newPassword.equals(confirmPass))
                    {
                        success=true;
                        String query = "update teacher set TEC_PASS = '"+newPassword+"' where ID = '"+teacher.id+"'";
                        SqlCon.st.execute(query);
                        frame.setVisible(false);
                        new TeacherPanel(teacher);
                    }
                    else if(success == false)
                    {
                        JOptionPane.showMessageDialog(confirm,"Wrong Information");
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println("DB Problem");
            }
        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,320,100,70);
        frame.add(cancel);
        cancel.addActionListener(e ->
        {
            frame.setVisible(false);
            new TeacherPanel(teacher);
        });


        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}