class ClassTime
{
    public String CLassType, SubjectName, RoomNo, StartTime, FinishTime;
    public ClassTime()
    {
        this(" "," "," "," "," ");
    }
    public ClassTime(String CLassType,String SubjectName, String RoomNo, String StartTime, String FinishTime)
    {
        this.CLassType=CLassType;
        this.SubjectName=SubjectName;
        this.RoomNo=RoomNo;
        this.StartTime=StartTime;
        this.FinishTime=FinishTime;
    }
    public ClassTime(String CLassType, String RoomNo, String StartTime, String FinishTime)
    {
        this.CLassType=CLassType;
        this.SubjectName="Consulting";
        this.RoomNo=RoomNo;
        this.StartTime=StartTime;
        this.FinishTime=FinishTime;
    }
    public String getCLassType()
    {
        return this.CLassType;
    }
    public String getSubjectName()
    {
        return this.SubjectName;
    }
    public String getRoomNo()
    {
        return this.RoomNo;
    }
    public String getStartTime()
    {
        return this.StartTime;
    }
    public String getFinishTime()
    {
        return this.FinishTime;
    }
}