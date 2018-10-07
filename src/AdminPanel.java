import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class AdminPanel
{
    public AdminPanel()
    {
        JFrame frame=new JFrame("American International University");

        JMenuBar menubar=new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu =new JMenu("Menu");
        menubar.add(menu);

        JMenuItem sami = new JMenuItem("Create Samester");
        menu.add(sami);
        sami.addActionListener(e ->
        {
            //frame.setVisible(false);
            new CreateSamester();
        } );

        JMenuItem hlp =new JMenuItem("Help");
        menu.add(hlp);
        hlp.addActionListener(e ->
        {
            String help = "For Any Kind Of Help Please Call 01936911944 "+"\n"+"Or Mail me ezazraihansaif@hotmail.com";
            JOptionPane.showMessageDialog(hlp,help);

        } );


        JButton ct=new JButton("Create Teacher");
        ct.setBounds(20,30,170,50);
        frame.add(ct);
        ct.addActionListener(e ->
        {
            //frame.setVisible(false);
            new CreateTeacher();
        } );

        JButton st=new JButton("show Teacher");
        st.setBounds(220,30,170,50);
        frame.add(st);
        st.addActionListener(e ->
        {
            //frame.setVisible(false);
            String quary = "select * from teacher";
            String TName = "teacher";
            new ShowTable(quary,TName);
        } );


        JButton cs=new JButton("Create Student");
        cs.setBounds(20,110,170,50);
        frame.add(cs);
        cs.addActionListener(e ->
        {
            //frame.setVisible(false);
            new CreateStudent();
        } );

        JButton ss=new JButton("Show Student");
        ss.setBounds(220,110,170,50);
        frame.add(ss);
        ss.addActionListener(e ->
        {
            //frame.setVisible(false);
            String quary = "select * from student";
            String TName = "student";
            new ShowTable(quary,TName);
        } );

        JButton cc=new JButton("Create Courses");
        cc.setBounds(20,190,170,50);
        frame.add(cc);
        cc.addActionListener(e ->
        {
            //frame.setVisible(false);
            new CreateCourse();
        } );

        JButton sc=new JButton("Show Courses");
        sc.setBounds(220,190,170,50);
        frame.add(sc);
        sc.addActionListener(e ->
        {
            //frame.setVisible(false);
            String quary = "SELECT C.ID,C.C_NAME,C.COR_TYPE,CT.COR_DURATION FROM COURSE C, COURSE_TYPE CT WHERE C.COR_TYPE = CT.COR_TYPE";
            String TName = "course";
            new ShowTable(quary,TName);
        } );

        JButton cr=new JButton("Add Section");
        cr.setBounds(20,270,170,50);
        frame.add(cr);
        cr.addActionListener(e ->
        {
            //frame.setVisible(false);
            new CreateSection();
        } );

        JButton ssec=new JButton("Show Section");
        ssec.setBounds(220,270,170,50);
        frame.add(ssec);
        ssec.addActionListener(e ->
        {
            //frame.setVisible(false);
            String quary="select * from section";
            String TName = "section";
            new ShowTable(quary,TName);
        } );

        JButton addTeacher=new JButton("Add Teacher & Class");
        addTeacher.setBounds(20,340,170,50);
        frame.add(addTeacher);
        addTeacher.addActionListener(e ->
        {
            //frame.setVisible(false);
            new AddTeacher();
        } );

        JButton showbooked=new JButton("Show Booked Section");
        showbooked.setBounds(220,340,170,50);
        frame.add(showbooked);
        showbooked.addActionListener(e ->
        {

            /*String quary = "select s.SAMI_NAME,c.COR_NAME,c.SEC_NAME,t.TEC_NAME from samester s , section c , teacher t where s.SAMI_ID ='1' and c.ID = '1' and t.ID = '1'";*/
            String quary="select semi.SAMI_NAME ,sec.COR_NAME,sec.SEC_NAME ,tec.TEC_NAME, cur.DAY1,cur.DAY2,cur.START_AT,cur.END_TIME from samester semi , section sec ,cor_by_sami cur,teacher tec where (semi.SAMI_ID = cur.SAMI_ID) and (sec.ID = cur.SEC_ID) and (tec.ID = cur.TEC_ID)";
            String TName = "cor_by_sami";
            //frame.setVisible(false);
            new ShowTable(quary,TName);
        } );

        JButton logout=new JButton("Log Out");
        logout.setBounds(120,410,180,50);
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