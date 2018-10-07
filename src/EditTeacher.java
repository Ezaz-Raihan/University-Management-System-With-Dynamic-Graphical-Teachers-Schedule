import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class EditTeacher
{
    SqlCon connection = new SqlCon();

    public EditTeacher(Teacher teacher)
    {
        JFrame frame=new JFrame("American International University");

        JLabel l1 = new JLabel("Name : ");
        l1.setBounds(10,40,150,20);
        frame.add(l1);

        JTextField name=new JTextField();
        name.setBounds(200,40,200,20);
        frame.add(name);
        name.setText(teacher.name);

        JLabel l3 = new JLabel("Phone Number : ");
        l3.setBounds(10,100,150,20);

        frame.add(l3);

        JTextField phn=new JTextField();
        phn.setBounds(200,100,200,20);
        phn.setText(teacher.phoneNumber);
        frame.add(phn);

        JLabel l4 = new JLabel("Email :");
        l4.setBounds(10,170,150,20);
        frame.add(l4);

        JTextField email=new JTextField();
        email.setBounds(200,170,200,20);
        email.setText(teacher.email);
        frame.add(email);


        JLabel l6 = new JLabel("Position :");
        l6.setBounds(10,230,150,20);
        frame.add(l6);

        JTextField posi=new JTextField();
        posi.setBounds(200,230,200,20);
        posi.setText(teacher.position);
        frame.add(posi);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,340,120,70);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try{
                teacher.name = name.getText();
                teacher.phoneNumber = phn.getText();
                teacher.email = email.getText();
                teacher.position = posi.getText();
                try{
                    String query = "update teacher set TEC_NAME='"+teacher.name+"',TEC_NUM='"+teacher.phoneNumber+"',TEC_EMAIL = '"+teacher.email+"',TEC_POSI = '"+teacher.position+"' where ID ='"+teacher.id+"'";
                    SqlCon.st.execute(query);
                    frame.setVisible(false);
                    new TeacherPanel(teacher);
                }
                catch(Exception ex)
                {
                    System.out.println("Teacher Edit Problem");
                }
            }
            catch(Exception ex)
            {
                String wrong="Null Value Not Accept";
                JOptionPane.showMessageDialog(null,wrong);
            }

        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,340,100,70);
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