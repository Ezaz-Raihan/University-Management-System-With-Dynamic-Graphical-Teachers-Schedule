import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

class StudentPanel
{
    JComboBox <String>jc2 = new JComboBox <> ();
    ArrayList <Teacher> t_info = new ArrayList<>();
    ArrayList <String> sel_t_name =new ArrayList <> ();
    ArrayList <String> dep_name =new ArrayList <> ();
    JComboBox <String>jc = new JComboBox <String> ();
    Teacher selectTeacher;
    String serchName;
    public StudentPanel()
    {
        JFrame frame=new JFrame("American International University");

        JMenuBar menubar=new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu =new JMenu("Menu");
        menubar.add(menu);

        JMenuItem sami = new JMenuItem("Change Password");
        menu.add(sami);
        sami.addActionListener(e ->
        {
            String help = "Please Contact Admin "+"\n"+"to Change your Password";
            JOptionPane.showMessageDialog(sami,help);
        } );

        JMenuItem hlp =new JMenuItem("Help");
        menu.add(hlp);
        hlp.addActionListener(e ->
        {
            String help = "For Any Kind Of Help Please Call 01936911944 "+"\n"+"Or Mail me ezazraihansaif@hotmail.com";
            JOptionPane.showMessageDialog(hlp,help);

        } );
        try
        {

            String query ="select * from teacher";
            ResultSet result = SqlCon.getResult(query);
            while(result.next())
            {
                String id = result.getString(1);
                String name = result.getString(2);
                String phn = result.getString(3);
                String email = result.getString(4);
                String dep = result.getString(5);
                String posi = result.getString(6);
                String room = result.getString(7);
                Teacher tecObj = new Teacher(id,name,phn,email,dep,posi,room);
                t_info.add(tecObj);
            }
            for(int i=0; i<t_info.size(); i++)
            {
                jc2.addItem(t_info.get(i).retName());
            }
        }
        catch(Exception e)
        {
            System.out.println("Problem in Teccher DB");
        }

        JLabel l1 = new JLabel("Search Teacher :");
        l1.setBounds(10,30,150,20);
        frame.add(l1);

        JTextField enter = new JTextField();
        enter.setBounds(220,30,120,20);
        frame.add(enter);

        JButton search = new JButton("Search");
        search.setBounds(360,30,80,20);
        frame.add(search);
        search.addActionListener( e->
        {
            boolean flag=false;
            serchName = enter.getText();
            serchName =serchName.toLowerCase();
            if(serchName.length() == 0)
            {
                JOptionPane.showMessageDialog(search,"Enter Name First");
            }
            else
            {
                sel_t_name.clear();
                for(int i=0; i<t_info.size(); i++)
                {

                    if(t_info.get(i).retName().toLowerCase().contains(serchName))
                    {
                        sel_t_name.add(t_info.get(i).retName());
                        flag=true;
                    }
                }

                jc2.removeAllItems();
                {
                    for(int j=0; j<sel_t_name.size(); j++)
                    {
                        jc2.addItem(sel_t_name.get(j));
                    }
                }
                if(flag==false)
                {
                    JOptionPane.showMessageDialog(search,"No Teacher Found");
                }
            }

        });

        JLabel l2 = new JLabel("Select Teacher Name :");
        l2.setBounds(10,80,150,20);
        frame.add(l2);
        jc2.setSelectedIndex(-1);
        jc2.setEditable(false);
        jc2.setBounds(220,80,220,20);
        frame.add(jc2);
        jc2.addActionListener( e ->
        {
            String tName = String.valueOf(jc2.getSelectedItem());
            jc.removeAllItems();
            for(int i=0; i<t_info.size(); i++)
            {
                if(t_info.get(i).retName().equals(tName))
                {
                    jc.addItem(t_info.get(i).retDepartment());
                }
            }
        });

        JLabel l3 = new JLabel("Select Department :");
        l3.setBounds(10,130,150,20);
        frame.add(l3);

        jc.setSelectedIndex(-1);
        jc.setBounds(220,130,220,20);
        frame.add(jc);

        JButton info=new JButton("Teacher Info.");
        info.setBounds(180,180,150,40);
        frame.add(info);
        info.addActionListener( e->
        {
            try{
                String name =jc2.getSelectedItem().toString();
                String dep =jc.getSelectedItem().toString();
                for(int i=0; i<t_info.size(); i++)
                {
                    if(t_info.get(i).retName().equals(name) && t_info.get(i).retDepartment().equals(dep))
                    {
                        selectTeacher=t_info.get(i);
                    }
                }
                new ShowTeacher(selectTeacher,2);
                //frame.setVisible(false);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(info,"Select All Requirement");
            }
        });

        JButton show=new JButton("Show TSF");
        show.setBounds(180,230,150,40);
        frame.add(show);
        show.addActionListener(e->
        {
            try{
                String name =jc2.getSelectedItem().toString();
                String dep =jc.getSelectedItem().toString();
                for(int i=0; i<t_info.size(); i++)
                {
                    if(t_info.get(i).retName().equals(name) && t_info.get(i).retDepartment().equals(dep))
                    {
                        selectTeacher=t_info.get(i);
                    }
                }
                new TSF(selectTeacher);
                //frame.setVisible(false);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(show,"Select All Requirement");
            }

        });

        JButton notice=new JButton("See Notices");
        notice.setBounds(180,280,150,40);
        frame.add(notice);
        notice.addActionListener(e->
        {

            try{
                String name =jc2.getSelectedItem().toString();
                String dep =jc.getSelectedItem().toString();
                for(int i=0; i<t_info.size(); i++)
                {
                    if(t_info.get(i).retName().equals(name) && t_info.get(i).retDepartment().equals(dep))
                    {
                        selectTeacher=t_info.get(i);
                    }
                }
                new ShowNotice(selectTeacher,2);
                //frame.setVisible(false);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(notice,"Select All Requirement");
            }

        });

        JButton logout=new JButton("Log Out");
        logout.setBounds(170,340,170,50);
        frame.add(logout);
        logout.addActionListener(e->
        {
            frame.setVisible(false);
            new Login();
        });

        frame.setSize(500,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}