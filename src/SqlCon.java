import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.lang.Integer;
class SqlCon
{

    public static Connection cn;
    public static Statement st;
    private static ResultSet rs;

    public static void EexcuteQuary(String query)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tsf","root","");
            st = cn.createStatement();
            rs =st.executeQuery(query);
        }
        catch(SQLException se)
        {
            System.out.println("Problem In Query ");
        }
        catch(Exception ex)
        {
            System.out.println("Error To Build Connection: "+ex);
        }
    }


    public static ResultSet getResult(String query)
    {
        EexcuteQuary(query);
        return rs;

    }
    public static void setSameter(String name)
    {
        try
        {
            String query = " insert into samester (sami_name) values (?)";
            PreparedStatement preparedStmt = cn.prepareStatement(query);

            preparedStmt.setString(1,name);
            preparedStmt.execute();
            cn.close();
        }
        catch(Exception e)
        {
            String wrong="This Samester Already Created!";
            JOptionPane.showMessageDialog(null,wrong);
        }
    }
    public static void getDate()
    {
        try
        {
            String quary ="Select * from student";
            ResultSet result = getResult(quary);
            System.out.println("Print from databaase");

            while(result.next()== true)
            {

                System.out.println("Id :"+result.getString(1));
                System.out.println("Name :"+result.getString(2));
                System.out.println("Samester :"+result.getString(3));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Input Error : "+ex);
        }
    }


    public static Admin adminlogin(String id,String password)
    {
        try
        {
            String query = "select * from admin";
            ResultSet result = getResult(query);
            while(result.next())
            {

                if(id.equals(result.getString(1))&& password.equals(result.getString(5)) )
                {
                    String name = result.getString(2);
                    String phn = result.getString(3);
                    String email = result.getString(4);
                    return new Admin(id,name,phn,email,password);
                }
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        return null;
    }


    public static Teacher teacherlogin(String id,String password)
    {

        String query = "select * from teacher";

        ResultSet result = getResult(query);

        try
        {
            while(result.next())
            {
                if(id.equals(result.getString(1))&& password.equals(result.getString(8)) )
                {
                    String	name =result.getString(2);
                    String	number =result.getString(3);
                    String	email =result.getString(4);
                    String  department =result.getString(5);
                    String	position =result.getString(6);
                    String room = result.getString(7);
                    String	passwd =result.getString(8);
                    return new Teacher(id,name,number,email,department,position,room,passwd);

                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Teacher Login Exception");
            return null;
        }
        return null;
    }

    public static boolean studentlogin(int id,String password)
    {

        String query = "select ID,S_PASS from student";
        ResultSet rs = getResult(query);

        int flag = 0;
        try
        {
            while(rs.next())
            {

                if(rs.getInt(1)== id && password.equals(rs.getString(2)) )
                {


                    flag = 1;

                }
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(flag == 1)
        {
            return true;
        }
        else return false;
    }

    public static void entryTeacher(String t_name,String t_phone,String t_email,String t_dep,String t_posi,String t_room,String t_pass)
    {
        try
        {
            String id=null;
            String t_id;
            String quary_id ="Select max(ID) from teacher";
            ResultSet result = getResult(quary_id);
            while(result.next())
            {
                id=result.getString(1);

            }
            if(id == null)
            {
                id = "100001";
                t_id=id;
            }
            else
            {
                int x=Integer.parseInt(id);
                x++;
                t_id=Integer.toString(x);
            }


            String quary ="insert into teacher(ID,TEC_NAME,TEC_NUM,TEC_EMAIL,TEC_DEP,TEC_POSI,ROOM_NO,TEC_PASS) values ('"+t_id+"','"+t_name+"','"+t_phone+"','"+t_email+"','"+t_dep+"','"+t_posi+"','"+t_room+"','"+t_pass+"')";
            st.execute(quary);
            cn.close();

        }
        catch(Exception e)
        {
            System.out.println("Problem in teacher table");
        }
    }
    public static ArrayList<String> returnTeacher(String department)
    {
        try
        {
            ArrayList<String> course = new ArrayList<String>();

            String quary = "Select C_NAME from course where DEP_NAME = '"+department+"'";
            ResultSet result = getResult(quary);

            while(result.next())
            {
                course.add(result.getString(1));
            }
            return course;

        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public static ArrayList<String> retSamester()
    {
        try
        {
            ArrayList <String> samester = new ArrayList<String>();
            String quary = "select SAMI_NAME from samester order by SAMI_ID desc";
            ResultSet result = getResult(quary);
            while(result.next())
            {
                samester.add(result.getString(1));
            }
            return samester;
        }
        catch(Exception e)
        {
            System.out.println("Problem in Samester");
            return null;
        }
    }
    public static ArrayList<String> retCourse()
    {
        try
        {
            ArrayList <String> course = new ArrayList<String>();
            String quary = "select C_NAME from course";
            ResultSet result = getResult(quary);
            while(result.next())
            {
                course.add(result.getString(1));
            }
            return course;
        }
        catch(Exception e)
        {
            System.out.println("Problem in Course");
            return null;
        }
    }
    public static ArrayList<String> getTeacher(String semester,String courseName,String day,double startTime,double endTime)
    {
        try
        {
            startTime=startTime-0.01;
            endTime=endTime+0.01;
            ArrayList <String> teacher = new ArrayList<String>();
            int samiId=0;
            String quary1="Select SAMI_ID FROM samester where SAMI_NAME = '"+semester+"'";
            ResultSet result1 = getResult(quary1);
            while(result1.next())
            {
                samiId=result1.getInt(1);
            }
            String query2 ="select teacher.TEC_NAME from teacher WHERE teacher.ID not in(select cor_by_sami.TEC_ID from cor_by_sami where '"+startTime+"'<cor_by_sami.START_AT and cor_by_sami.END_TIME<'"+endTime+"' and cor_by_sami.SAMI_ID='"+samiId+"' and cor_by_sami.DAY1='"+day+"') and teacher.TEC_DEP=(select course.DEP_NAME from course where course.C_NAME='"+courseName+"') and teacher.ID not in(select consulting.TEC_ID from consulting where consulting.SAMI_ID='"+samiId+"' and consulting.DAY='"+day+"' and '"+startTime+"'<consulting.CONS_START and consulting.CONS_END<'"+endTime+"')";
            ResultSet result2 = getResult(query2);
            while(result2.next())
            {
                teacher.add(result2.getString(1));
            }
            return teacher;
        }
        catch(SQLException se)
        {
            System.out.println("Problem in Course DB");
            return null;
        }
        catch(Exception e)
        {
            System.out.println("Null value from User");
            return null;
        }
    }
    public static ArrayList<String> getSection(String course,String Samester)
    {
        //check from book section table where this samiId and sec are not availabe
        try
        {
            ArrayList <String> section = new ArrayList<String>();
            String quary = "SELECT SEC_NAME FROM section WHERE section.COR_NAME = '"+course+"' AND section.ID not in (SELECT SEC_ID FROM cor_by_sami WHERE cor_by_sami.SAMI_ID in (SELECT SAMI_ID FROM samester WHERE samester.SAMI_NAME = '"+Samester+"'))";
            ResultSet result = getResult(quary);
            while(result.next())
            {
                section.add(result.getString(1));
            }
            return section;
        }
        catch(Exception e)
        {
            System.out.println("Problem in Course");
            return null;
        }
    }

    public static boolean addSection(String course,String secName,String room)
    {
        try
        {
            String quary = "select COR_NAME,SEC_NAME from section where COR_NAME ='"+course+"' and SEC_NAME ='"+secName+"'";
            ResultSet result = getResult(quary);
            int x = result.getRow();
            if(result.next() == false)
            {
                String query = "insert into section (COR_NAME,SEC_NAME,ROOM_NO) values ('"+course+"' , '"+secName+"' ,'"+room+"')";
                st.execute(query);
                cn.close();
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("DB Problem !");
            return false;
        }
    }
    public static boolean deleteData(String quary)
    {
        try
        {
            PreparedStatement preparedStmt = cn.prepareStatement(quary);
            preparedStmt.executeUpdate();
            cn.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Problem to delete");
            return false;
        }
    }
    public static void addTeacher(String samester,String day1,String day2,String course,String section,String teacher,double startTime,double endTime)
    {
        try
        {
            int samiId=0;
            int secId=0;
            String tecId=null;
            double corDuration=0;
            String quary1="Select SAMI_ID FROM samester where SAMI_NAME = '"+samester+"'";
            ResultSet result1 = getResult(quary1);
            while(result1.next())
            {
                samiId=result1.getInt(1);

            }
            String quary2 = "select ID from section where COR_NAME = '"+course+"' and SEC_NAME = '"+section+"'";
            ResultSet result2 = getResult(quary2);
            while(result2.next())
            {
                secId=result2.getInt(1);
            }

            String quary3 = "select ID from teacher where TEC_NAME = '"+teacher+"' and TEC_DEP = (select DEP_NAME from course where C_NAME = '"+course+"')";
            ResultSet result3 = getResult(quary3);
            while(result3.next())
            {
                tecId=result3.getString(1);
            }
            String query = "INSERT INTO cor_by_sami (SAMI_ID, SEC_ID, TEC_ID, DAY1 , DAY2, START_AT, END_TIME) VALUES ('"+samiId+"','"+secId+"','"+tecId+"','"+day1+"','"+day2+"','"+startTime+"','"+endTime+"')";
            st.execute(query);
        }

        catch(Exception e)
        {
            System.out.println("problem in Adding!");
        }
    }
    public static void sendNotice(String id,String date,String time,String notice)
    {
        try
        {
            String quary = "insert into tec_notice(TEC_ID,DATE,TIME,NOTICE) values ('"+id+"','"+date+"','"+time+"','"+notice+"')";
            st.execute(quary);
        }
        catch(Exception e)
        {
            System.out.println("Notice Not Added !");
        }
    }
    public static void setStatus(String id,String date,int status)
    {
        try
        {
            String query = "INSERT into tec_status (TEC_ID,DATE,STATUS) VALUES ('"+id+"','"+date+"','"+String.valueOf(status)+"')";
            st.execute(query);
        }
        catch(Exception ex)
        {
            System.out.println("Status Not Added");
        }
    }
    public static void editStatus(String id,String date,int status)
    {
        try
        {
            String query = "UPDATE tec_status set TEC_ID ='"+id+"',DATE ='"+date+"',STATUS = '"+String.valueOf(status)+"'";
            st.execute(query);
        }
        catch(Exception ex)
        {
            System.out.println("Status Not Added");
        }
    }
    public static ArrayList<TeacherClass> retAllTimes(String samiId,String techerId)
    {
        ArrayList<TeacherClass> allClass = new ArrayList<TeacherClass>();
        try
        {
            String query2 = "select  DAY1 ,START_AT,END_TIME from cor_by_sami where SAMI_ID='"+samiId+"' and TEC_ID='"+techerId+"'";
            ResultSet result2 = getResult(query2);
            while(result2.next())
            {
                String days = result2.getString(1);
                String start = result2.getString(2);
                String end = result2.getString(3);
                allClass.add(new TeacherClass(days,start,end));
            }
            String query3 = "select  DAY2 ,START_AT,END_TIME from cor_by_sami where SAMI_ID='"+samiId+"' and TEC_ID='"+techerId+"'";
            ResultSet result3 = getResult(query3);
            while(result3.next())
            {
                String days = result3.getString(1);
                String start = result3.getString(2);
                String end = result3.getString(3);
                allClass.add(new TeacherClass(days,start,end));
            }
            String query4 = "select  DAY ,CONS_START,CONS_END from consulting where SAMI_ID='"+samiId+"' and TEC_ID='"+techerId+"'";
            ResultSet result4 = getResult(query4);
            while(result4.next())
            {
                String days = result4.getString(1);
                String start = result4.getString(2);
                String end = result4.getString(3);
                allClass.add(new TeacherClass(days,start,end));
            }
            return allClass;
        }
        catch(Exception e)
        {
            System.out.println("Problem to Consulting DB");
            return null;
        }
    }
}
