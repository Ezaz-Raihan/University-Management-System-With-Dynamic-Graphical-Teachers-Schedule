import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.SQLException;

class CreateCourse
{
    JFrame frame=new JFrame("American International University");
    public CreateCourse()
    {

        frame.setLocation(500,10);
        JLabel l1 = new JLabel("Name : ");
        l1.setBounds(10,50,150,30);
        frame.add(l1);

        JTextField name=new JTextField();
        name.setBounds(200,50,200,30);
        frame.add(name);

        JLabel l3 = new JLabel("Department :");
        l3.setBounds(10,120,150,30);
        frame.add(l3);


        String [] s1 = {"","Science &Information Tecnology","Business Administrator","Engineering","Art & Social Science"};
        JComboBox <String>jc1 = new JComboBox <> (s1);
        jc1.setEditable(false);
        jc1.setBounds(200,120,220,30);
        frame.add(jc1);

        JLabel l4 = new JLabel("Course Type :");
        l4.setBounds(10,200,150,30);
        frame.add(l4);


        String [] s2 = {null,"Theory","CS_Theory","Lab"};
        JComboBox <String>jc2 = new JComboBox <> (s2);
        jc2.setEditable(false);
        jc2.setBounds(200,200,220,30);
        frame.add(jc2);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,300,120,70);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try{
                String c_name = name.getText();
                String c_dep= jc1.getSelectedItem().toString();
                String c_type= jc2.getSelectedItem().toString();
                String query ="insert into course (C_NAME,DEP_NAME,COR_TYPE) values ( '"+c_name+"', '"+c_dep+"' , '"+c_type+"' )";
                SqlCon.st.execute(query);
                frame.setVisible(false);
                //new AdminPanel();
            }
            catch(SQLException ex2)

            {
                String wrong="Duplicate Section Not Accept";
                JOptionPane.showMessageDialog(confirm,wrong);
            }
            catch(Exception ex1)
            {
                String wrong="Fill All The Requirement";
                JOptionPane.showMessageDialog(confirm,wrong);
            }

        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,300,100,70);
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