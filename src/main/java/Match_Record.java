import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.Date;

/**
 * Created by SachinMaurya on 3/23/2017.
 */
//class Address
//{
//    int House_No;
//    String Country,Area,State,Street,Post_Office;
//
//    public Address(int house_No, String country, String area, String state, String street, String post_Office) {
//        House_No = house_No;
//        Country = country;
//        Area = area;
//        State = state;
//        Street = street;
//        Post_Office = post_Office;
//    }
//
//    public Address() {
//    }
//}
class Result
{
    double DOB,First_Name,Last_Name,Age,Pincode,License_No,Adhar_No,Permanant_Address,Current_Address;

    boolean is_Same()
    {
        int f=0;
        if(this.First_Name > .9)
        {
            f++;
        }
        else if(this.Last_Name > .9)
        {
            f++;
        }
        else if(this.Age > .9)
        {
            f++;
        }
        else if(this.Pincode > .9)
        {
            f++;
        }
        else if(this.Permanant_Address > .9)
        {
            f++;
        }
        else if(this.Current_Address > .9)
        {
            f++;
        }
        if(f>4)
            return true;
        else
            return false;
    }
    public double getDOB() {
        return DOB;
    }

    public double getFirst_Name() {
        return First_Name;
    }

    public double getLast_Name() {
        return Last_Name;
    }

    public double getAge() {
        return Age;
    }

    public double getPincode() {
        return Pincode;
    }

    public double getLicense_No() {
        return License_No;
    }

    public double getAdhar_No() {
        return Adhar_No;
    }

    public double getPermanant_Address() {
        return Permanant_Address;
    }

    public double getCurrent_Address() {
        return Current_Address;
    }
}
class Applicant
{
    String First_Name,Last_Name,Cust_No,Street,Town;

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getCust_No() {
        return Cust_No;
    }

    public void setCust_No(String cust_No) {
        Cust_No = cust_No;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    public Long getZipcode() {
        return Zipcode;
    }

    public void setZipcode(Long zipcode) {
        Zipcode = zipcode;
    }

    Long Zipcode;

//    public Result Compare_Applicants(Applicant a)
//    {
//        Result res=new Result();
//        res.First_Name=Compare_Strings(this.First_Name,a.First_Name);
//        res.Last_Name=Compare_Strings(this.Last_Name,a.Last_Name);
//        res.DOB=Compare_Dates(this.DOB,a.DOB);
//        res.Pincode=Compare_Longs(this.Zipcode,a.Zipcode);
//        res.License_No=Compare_Longs(this.License_No,a.License_No);
//        res.Adhar_No=Compare_Longs(this.Adhar_No,a.Adhar_No);
//        res.Age=Compare_Integers(this.Age,a.Age);
//        res.Permanant_Address=Compare_Addresses(this.Permanant_Address,a.Permanant_Address);
//        res.Current_Address=Compare_Addresses(this.Current_Address,a.Current_Address);
//        return res;
//    }
//
//    private double Compare_Addresses(Address address1, Address address2) {
//        if(this.Compare_Strings(address1.Country,address2.Country) > .9 &&
//                this.Compare_Strings(address1.State,address2.State) > .9 &&
//                this.Compare_Strings(address1.Street,address2.Street) > .9 &&
//                this.Compare_Strings(address1.Area,address2.Street) > .9 &&
//                this.Compare_Strings(address1.Post_Office,address2.Post_Office) > .9 &&
//                this.Compare_Integers(address1.House_No,address2.House_No) > .9)
//        {
//            return 1;
//        }
//        return 0;
//    }

    public double Compare_Integers(int age, int age1) {
        if(age==age1)
            return 1.0;
        return 0;
    }

    public double Compare_Strings(String stringA, String stringB) {

        return StringUtils.getJaroWinklerDistance(stringA, stringB);
    }
    public double Compare_Longs(Long pincode, Long pincode1) {
        if(pincode==pincode1)
            return 1.0;
        return 0;
    }

    public double Compare_Dates(Date dob, Date dob1) {
        if(dob.compareTo(dob1)==0)
            return 1.0;
        return 0;
    }
}

public class Match_Record {
    public static void main(String []args) throws ClassNotFoundException, SQLException, UnknownHostException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","sachin123@");
        Statement stmt=con.createStatement();
        int Size=0,i=1;
        ResultSet rs=stmt.executeQuery("select count(*) from personal");
        while(rs.next())
        {
            Size=rs.getInt(1);
            System.out.println(Size);
        }
        Applicant record=new Applicant();
        while(i<Size){
            rs=stmt.executeQuery("select CUST_NO,FNAME,LNAME,STREET,ZIP,TOWN from personal limit "+i+",1");
            i++;
            while (rs.next())
            {
                record.setCust_No(rs.getString(1));
                record.setFirst_Name(rs.getString(2));
                record.setLast_Name(rs.getString(3));
                record.setStreet(rs.getString(4));
                record.setZipcode(rs.getLong(5));
                record.setTown(rs.getString(6));
            }
            rs=stmt.executeQuery("select CUST_NO,FNAME,LNAME,STREET,ZIP,TOWN from personal");
            while(rs.next())
            {
//                if(record.Compare_Strings(record.getCust_No(),rs.getString(1))==1)
//                {
//                    if(record.Compare_Strings(record.getFirst_Name(),rs.getString(2))>.9 &&
//                            record.Compare_Strings(record.getLast_Name(),rs.getString(3))>.9 &&
//                            record.Compare_Strings(record.getStreet(),rs.getString(4))>.9 &&
//                            record.Compare_Longs(record.getZipcode(),rs.getLong(5))==1 &&
//                            record.Compare_Strings(record.getTown(),rs.getString(6))>.9)
//                    {
//                        System.out.println("Matching record found"+" "+rs.getString(1)+" "+record.getCust_No());
//                    }
//                }
                if(!record.getCust_No().equals(rs.getString(1)))
                {
                    if (record.getFirst_Name().equals(rs.getString(2)))
                    {
                        System.out.print("Fname");
                    }
                    if(record.getLast_Name().equals(rs.getString(3)))
                    {
                        System.out.print("Lname");
                    }
                    if(record.getStreet().equals(rs.getString(4)))
                    {
                        System.out.print("Street");
                    }
                    if(record.getTown().equals(rs.getString(6)))
                    {
                        System.out.print("Town");
                    }
                    if(record.getZipcode() == rs.getLong(5))
                    {
                        System.out.print("Zipcode");
                    }
                }
                System.out.println();
            }
        }
        con.close();

//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//
//        client.close();


//        Applicant record2=new Applicant();
//        Result finalResult=record1.Compare_Applicants(record2);
//        if(finalResult.is_Same())
//            System.out.println("It can be same Record");
    }
}
