import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

class AddTeacher
{
    JComboBox <String>jc3 = new JComboBox <> ();
    JComboBox <String>jc1 = new JComboBox <> ();
    JComboBox <String>jc4 = new JComboBox <> ();
    JComboBox <String>jc2 = new JComboBox <> ();
    JComboBox <String>day1 = new JComboBox <> ();
    JComboBox <String>day2 = new JComboBox <> ();
    JComboBox <String>time = new JComboBox <> ();
    JComboBox <String>minute = new JComboBox <> ();
    double startTime,endTime;

    JFrame frame=new JFrame("American International University");
    ArrayList<String> d1 = new ArrayList<String>();
    public AddTeacher()
    {
        frame.setLocation(500,10);
        JLabel l7 = new JLabel("Select Samester :");
        l7.setBounds(20,20,150,20);
        frame.add(l7);

        ArrayList <String> samester = SqlCon.retSamester();
        for(int i=0; i<samester.size(); i++)
        {
            jc3.addItem(samester.get(i));
        }
        jc3.setSelectedIndex(-1);
        jc3.setEditable(false);
        jc3.setBounds(200,20,220,20);
        frame.add(jc3);

        JLabel l2 = new JLabel("Course Name : ");
        l2.setBounds(10,70,150,20);
        frame.add(l2);


        ArrayList <String> course = SqlCon.retCourse();
        for(int i=0; i<course.size(); i++)
        {

            jc1.addItem(course.get(i));
        }
        jc1.setSelectedIndex(-1);
        jc1.setEditable(false);
        jc1.setBounds(200,70,150,20);
        frame.add(jc1);
        jc1.addActionListener(e ->
        {
            String semester = jc3.getSelectedItem().toString();
            String selectCor = jc1.getSelectedItem().toString();
            ArrayList <String> section =SqlCon.getSection(selectCor,semester);
            jc4.removeAllItems();
            for(int j=0; j<section.size(); j++)
            {
                jc4.addItem(section.get(j));
            }
        });

        JLabel l8 = new JLabel("Section : ");
        l8.setBounds(10,120,150,20);
        frame.add(l8);

        jc4.setSelectedIndex(-1);
        jc4.setEditable(false);
        jc4.setBounds(200,120,150,20);
        frame.add(jc4);

        JLabel l1 = new JLabel("Day 1: ");
        l1.setBounds(10,170,50,20);
        frame.add(l1);

        d1.add("Sunday");
        d1.add("Monday");
        d1.add("Tuesday");
        d1.add("Wednesday");
        for(int j=0; j<d1.size(); j++)
        {
            day1.addItem(d1.get(j));
        }
        day1.setSelectedIndex(-1);
        day1.setBounds(80,170,120,20);
        frame.add(day1);
        day1.addActionListener(e ->
        {
            ArrayList<String> d2 = new ArrayList<String>(d1);
            for(int j=0; j<d2.size(); j++)
            {
                if(d2.get(j) == day1.getSelectedItem().toString())
                {
                    d2.remove(j);
                }
            }
            day2.removeAllItems();
            for(int k=0; k<d2.size(); k++)
            {
                day2.addItem(d2.get(k));
            }

        });

        JLabel l9 = new JLabel("Day 2: ");
        l9.setBounds(220,170,50,20);
        frame.add(l9);
        day2.setSelectedIndex(-1);
        day2.setBounds(280,170,120,20);
        frame.add(day2);

        JLabel l4 = new JLabel("Start Time :");
        l4.setBounds(10,220,150,20);
        frame.add(l4);

        for(int i=8; i<21; i++)
        {
            time.addItem(String.valueOf(i));
        }
        time.setSelectedIndex(-1);
        time.setBounds(200,220,50,20);
        frame.add(time);

        JLabel l5 = new JLabel(":");
        l5.setBounds(265,220,10,20);
        frame.add(l5);

        minute.addItem("00");
        minute.addItem("30");
        minute.setSelectedIndex(-1);
        minute.setBounds(280,220,50,20);
        frame.add(minute);
        minute.addActionListener( e ->
        {
            try{
                String cor_time=null;
                double x =Double.parseDouble(time.getSelectedItem().toString());
                double y =Double.parseDouble(minute.getSelectedItem().toString())/100;


                startTime = x + y ;
                String cor = jc1.getSelectedItem().toString();
                String quary = "select COR_DURATION from course_type where cor_Type = (select COR_TYPE from course where C_NAME = '"+cor+"')";
                ResultSet rs =SqlCon.getResult(quary);
                while(rs.next())
                {
                    cor_time=rs.getString(1);
                }
                endTime = x + y + Double.parseDouble(cor_time);
                double fraction = endTime - (int)endTime;
                double res = fraction/0.60;
                if((int)res == 1)
                {
                    endTime = (int) endTime +1;
                }
                String courseName = jc1.getSelectedItem().toString();
                String semester = jc3.getSelectedItem().toString();
                String day = day1.getSelectedItem().toString();
                ArrayList <String> teacher =SqlCon.getTeacher(semester,courseName,day,startTime,endTime);
                jc2.removeAllItems();
                for(int i=0; i<teacher.size(); i++)
                {
                    jc2.addItem(teacher.get(i));
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(minute,"Fill up Prevoius Data");
            }

        });

        JLabel l3 = new JLabel("Teacher :");
        l3.setBounds(10,270,150,20);
        frame.add(l3);

        jc2.setSelectedIndex(-1);
        jc2.setEditable(false);
        jc2.setBounds(200,270,200,20);
        frame.add(jc2);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,370,120,70);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try{
                if(endTime>22)
                {
                    String msg = "Sorry ! This Crossed Time Limit";
                    JOptionPane.showMessageDialog(minute,msg);
                }
                else{
                    String selectsamester = jc3.getSelectedItem().toString();
                    String selectday1 = day1.getSelectedItem().toString();
                    String selectday2 = day2.getSelectedItem().toString();
                    String selectCourse = jc1.getSelectedItem().toString();
                    String selectSction = jc4.getSelectedItem().toString();
                    String selectTeacher = jc2.getSelectedItem().toString();
                    startTime = startTime;
                    endTime=endTime;
                    SqlCon.addTeacher(selectsamester,selectday1,selectday2,selectCourse,selectSction,selectTeacher,startTime,endTime);
                    frame.setVisible(false);
                    //new AdminPanel();
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Fill All the Values !");
            }
        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,370,100,70);
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