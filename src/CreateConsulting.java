import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

class CreateConsulting
{
    JFrame frame=new JFrame("American International University");
    ArrayList<String> day = new ArrayList<String>();
    JComboBox <String>dayset = new JComboBox <> ();
    JComboBox <String>samiset = new JComboBox <> ();
    JComboBox <String> time = new JComboBox <> ();
    JComboBox <String> minute = new JComboBox <> ();
    ArrayList <TeacherClass> allClass = new ArrayList <TeacherClass>();
    ArrayList <TeacherClass> selectdayClass = new ArrayList <TeacherClass>();
    public CreateConsulting(Teacher teacher)
    {

        frame.setLocation(600,10);
        JLabel l4 = new JLabel("Select Samester :");
        l4.setBounds(20,20,150,20);
        frame.add(l4);

        ArrayList <String> samester = SqlCon.retSamester();
        for(int i=0; i<samester.size(); i++)
        {
            samiset.addItem(samester.get(i));
        }
        samiset.setSelectedIndex(-1);
        samiset.setEditable(false);
        samiset.setBounds(200,20,220,20);
        frame.add(samiset);

        JLabel l1 = new JLabel("Day : ");
        l1.setBounds(10,80,150,20);
        frame.add(l1);

        day.add("Sunday");
        day.add("Monday");
        day.add("Tuesday");
        day.add("Wednesday");
        day.add("Thursday");

        for(int j=0; j<day.size(); j++)
        {
            dayset.addItem(day.get(j));
        }
        dayset.setSelectedIndex(-1);
        dayset.setBounds(200,80,200,20);
        frame.add(dayset);

        JLabel l2 = new JLabel("Consulting Start : ");
        l2.setBounds(10,140,150,20);
        frame.add(l2);

        for(int i=8; i<22; i++)
        {
            time.addItem(String.valueOf(i));
        }
        time.setSelectedIndex(-1);
        time.setBounds(200,140,50,20);
        frame.add(time);

        minute.addItem("00");
        minute.addItem("30");
        minute.setSelectedIndex(-1);
        minute.setBounds(280,140,50,20);
        frame.add(minute);

        JLabel l3 = new JLabel("Consulting Duration :");
        l3.setBounds(10,200,150,20);
        frame.add(l3);

        JTextField dur=new JTextField();
        dur.setBounds(200,200,80,20);
        frame.add(dur);

        JLabel l5 = new JLabel("Hour/Minute");
        l5.setBounds(300,200,100,20);
        frame.add(l5);

        JButton confirm=new JButton("Confirm");
        confirm.setBounds(100,280,120,70);
        frame.add(confirm);
        confirm.addActionListener(e ->
        {
            try{
                boolean flag=false;
                String samiName = samiset.getSelectedItem().toString();
                String selectday = dayset.getSelectedItem().toString();
                double x = Double.parseDouble(time.getSelectedItem().toString());
                double y = Double.parseDouble(minute.getSelectedItem().toString())/100;
                double startTime = x + y;
                double duration = Double.parseDouble(dur.getText());
                double endTime = startTime + duration;
                String samiId =null;
                String query1 = "select SAMI_ID from samester where SAMI_NAME = '"+samiName+"'";
                ResultSet result1 = SqlCon.getResult(query1);
                while(result1.next())
                {
                    samiId=result1.getString(1);
                }
                allClass = SqlCon.retAllTimes(samiId,teacher.id);
                for(int i=0; i<allClass.size(); i++)
                {
                    if(allClass.get(i).retDay().equals(selectday))
                    {
                        selectdayClass.add(allClass.get(i));
                    }
                }
                double fraction = endTime - (int)endTime;
                double res = fraction/0.60;
                if((int)res == 1)
                {
                    endTime = (int) endTime +1;
                }
                if(endTime>22)
                {
                    JOptionPane.showMessageDialog(confirm,"Time Limit Crossed");
                }
                else
                {
                    int classcounter = selectdayClass.size();
                    int consCounter=0;
                    for(int j=0; j<classcounter ; j++)
                    {
                        double t_classStart =Double.parseDouble(selectdayClass.get(j).retStart());
                        double t_classEnd =Double.parseDouble(selectdayClass.get(j).retEnd());
                        if(endTime <= t_classStart || t_classEnd <=startTime)
                        {
                            consCounter++;
                        }
                    }
                    if(classcounter == consCounter)
                    {
                        String query ="insert into consulting(SAMI_ID,TEC_ID,DAY,CONS_START,CONS_END) values ('"+samiId+"','"+teacher.id+"','"+selectday+"','"+startTime+"','"+endTime+"')";
                        SqlCon.st.execute(query);
                        frame.setVisible(false);
                        //new TeacherPanel(teacher);
                    }
                    else{
                        JOptionPane.showMessageDialog(confirm,"You Are Busy that Time");
                    }
                }
            }
            catch(NumberFormatException nmf)
            {
                JOptionPane.showMessageDialog(confirm,"Enter Numeric in Duration");
            }
            catch(Exception ex)
            {
                System.out.println("Problem In Consulting Adding");
            }
        });

        JButton cancel=new JButton("Cancel");
        cancel.setBounds(270,280,100,70);
        frame.add(cancel);
        cancel.addActionListener(e ->
        {
            frame.setVisible(false);
            //new TeacherPanel(teacher);
        });


        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}