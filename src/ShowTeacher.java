import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

class ShowTeacher
{
    JFrame frame=new JFrame("American International University");
    JLabel id=new JLabel();
    JLabel name=new JLabel();
    JLabel phn=new JLabel();
    JLabel email=new JLabel();
    JLabel department = new JLabel();
    JLabel posi=new JLabel();
    JLabel room=new JLabel();

    public ShowTeacher(Teacher teacher,int source)
    {
        frame.setLocation(600,10);
        JLabel l7 = new JLabel("ID : ");
        l7.setBounds(10,30,150,20);
        frame.add(l7);


        id.setBounds(200,30,200,20);
        id.setText(teacher.id);
        frame.add(id);

        JLabel l1 = new JLabel("Name : ");
        l1.setBounds(10,80,150,20);
        frame.add(l1);


        name.setBounds(200,80,200,20);
        name.setText(teacher.name);
        frame.add(name);

        JLabel l3 = new JLabel("Phone Number : ");
        l3.setBounds(10,130,150,20);
        frame.add(l3);


        phn.setBounds(200,130,200,20);
        phn.setText(teacher.phoneNumber);
        frame.add(phn);

        JLabel l4 = new JLabel("Email :");
        l4.setBounds(10,180,150,20);
        frame.add(l4);


        email.setBounds(200,180,200,20);
        email.setText(teacher.email);
        frame.add(email);

        JLabel l5 = new JLabel("Department :");
        l5.setBounds(10,230,150,20);
        frame.add(l5);

        department.setBounds(200,230,220,20);
        department.setText(teacher.department);
        frame.add(department);

        JLabel l6 = new JLabel("Position :");
        l6.setBounds(10,280,150,20);
        frame.add(l6);

        posi.setBounds(200,280,200,20);
        posi.setText(teacher.position);
        frame.add(posi);

        JLabel l8 = new JLabel("Room Number :");
        l8.setBounds(10,330,150,20);
        frame.add(l8);

        room.setBounds(200,330,200,20);
        room.setText(teacher.room);
        frame.add(room);

        JButton cancel=new JButton("Back");
        cancel.setBounds(180,380,100,50);
        frame.add(cancel);
        cancel.addActionListener(e ->
        {
            if(source==1)
            {
                frame.setVisible(false);
                //new TeacherPanel(teacher);
            }
            if(source == 2)
            {
                frame.setVisible(false);
                //	new StudentPanel();
            }
        });


        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}