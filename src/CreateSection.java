import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.ArrayList;


class CreateSection
{
    JFrame frame=new JFrame("American International University");
    ArrayList <String> course ;
    JComboBox <String>jc2;
    public CreateSection()
    {
        frame.setLocation(500,10);
        jc2 = new JComboBox <String> ();
        jc2.setEditable(true);

        JLabel l1 = new JLabel("Department :");
        l1.setBounds(10,40,80,20);
        frame.add(l1);


        String [] s1 = {"","Science &Information Tecnology","Business Administrator","Engineering","Art & Social Science"};
        JComboBox <String>jc1 = new JComboBox <> (s1);
        jc1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String selected = jc1.getSelectedItem().toString();
                course = SqlCon.returnTeacher(selected);
                jc2.removeAllItems();
                for(int i=0; i<course.size(); i++)
                {
                    jc2.addItem(course.get(i));
                }

            }
        });

        jc1.setEditable(false);
        jc1.setBounds(200,40,220,20);
        frame.add(jc1);

        JLabel l2 = new JLabel("Course Name : ");
        l2.setBounds(10,100,150,20);
        frame.add(l2);


        jc2.setEditable(false);
        jc2.setBounds(200,100,150,20);
        jc2.addActionListener(e ->
        {

        }

                             );
        frame.add(jc2);

        JLabel l3 = new JLabel("Section Name:");
        l3.setBounds(10,160,150,20);
        frame.add(l3);


        JTextField name=new JTextField();
        name.setBounds(200,160,200,20);
        frame.add(name);

        JLabel l6 = new JLabel("Room Number :");
        l6.setBounds(10,220,150,20);
        frame.add(l6);


        JTextField room=new JTextField();
        room.setBounds(200,220,200,20);
        frame.add(room);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,280,120,70);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try
            {
                String corName = jc2.getSelectedItem().toString();
                String section = name.getText().toUpperCase();
                String roomNo = room.getText();
                boolean added = SqlCon.addSection(corName,section,roomNo);
                if(added == true)
                {
                    frame.setVisible(false);
                    //new AdminPanel();
                }
                else
                {
                    String warning = "This Section Already Created!";
                    JOptionPane.showMessageDialog(null,warning);
                }
            }
            catch(Exception ex)
            {
                String wrong="Null Value Not Accept";
                JOptionPane.showMessageDialog(null,wrong);
            }
        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,280,100,70);
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