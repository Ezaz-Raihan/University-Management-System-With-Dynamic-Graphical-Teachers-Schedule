class TeacherClass
{
    private String day;
    private String start;
    private String end;

    public TeacherClass(String day,String start,String end)
    {
        this.day=day;
        this.start=start;
        this.end=end;
    }
    public String retDay()
    {
        return this.day;
    }
    public String retStart()
    {
        return this.start;
    }
    public String retEnd()
    {
        return this.end;
    }
}