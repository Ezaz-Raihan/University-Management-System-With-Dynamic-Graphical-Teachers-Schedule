import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class CreateStudent
{
    JFrame frame=new JFrame("American International University");
    JComboBox <String>jc = new JComboBox <> ();
    ArrayList <String> dep_name = new ArrayList<>();
    public CreateStudent()
    {
        frame.setLocation(500,10);

        JLabel l1 = new JLabel("Name : ");
        l1.setBounds(10,70,150,20);
        frame.add(l1);

        JTextField name=new JTextField();
        name.setBounds(200,70,200,20);
        frame.add(name);

        JLabel l3 = new JLabel("Department : ");
        l3.setBounds(10,150,150,20);
        frame.add(l3);

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
            jc.setSelectedIndex(-1);
        }
        catch(SQLException ex)
        {
            System.out.println("Department DB problem");
        }
        jc.setEditable(false);
        jc.setBounds(200,150,200,20);
        frame.add(jc);

        JLabel l4 = new JLabel("Password :");
        l4.setBounds(10,240,150,20);
        frame.add(l4);

        JTextField passwd=new JTextField();
        passwd.setBounds(200,240,200,20);
        frame.add(passwd);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,350,120,70);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try{
                String s_name = name.getText();
                String s_dep = jc.getSelectedItem().toString();
                String s_pass = passwd.getText();
                if(s_name.length()!=0 && s_dep.length()!=0 && s_pass.length()!=0)
                {
                    if(s_pass.length()<5)
                    {
                        JOptionPane.showMessageDialog(confirm,"Password Is Too Short");
                    }
                    else
                    {
                        String query ="insert into student (S_NAME,S_DEP,S_PASS) values ( '"+s_name+"', '"+s_dep+"' , '"+s_pass+"' )";
                        SqlCon.st.execute(query);
                        frame.setVisible(false);
                        //new AdminPanel();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(confirm,"Fill All Requirment");
                }
            }
            catch(Exception ex)
            {
                System.out.println("Problem in DB");
            }

        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,350,100,70);
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