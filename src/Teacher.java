class Teacher
{
    public String name;
    public String id;
    public String phoneNumber;
    public String email;
    public String department;
    public String position;
    public String room;
    public String password;

    public Teacher() {}
    public Teacher(String id,String name,String phoneNumber,String email,String department,String position,String room,String password)
    {
        this.id=id;
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.department=department;
        this.position=position;
        this.room=room;
        this.password=password;
    }
    public Teacher(String id,String name,String phoneNumber,String email,String department,String position,String room)
    {
        this.id=id;
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.department=department;
        this.position=position;
        this.room=room;
    }
    public String retId()
    {
        return this.id;
    }
    public String retName()
    {
        return this.name;
    }

    public String retDepartment()
    {
        return this.department;
    }
}