import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkIndexByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.RemoteTransportException;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by SachinMaurya on 3/23/2017.
 */

class Applicant
{
    String First_Name;
    String Last_Name;
    String Cust_No;
    String Street;
    String Town;
    String Zipcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

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

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

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

//    public static void checkRecord(SearchResponse sr,Applicant input)
//    {
//        for(SearchHit res:sr.getHits())
//        {
//            if(input.getFirst_Name().equals(res.getSource().get("FirstName")) && input.getLast_Name().equals(res.getSource().get("LastName"))
//                    && input.getStreet().equals(res.getSource().get("Street")) && input.getTown().equals(res.getSource().get("Place_of_Residence"))
//                    && input.getZipcode().equals(res.getSource().get("Zip")))
//            {
//                System.out.println("Record is matched with customer id : "+ res.getSource().get("Customer_no"));
//            }
//        }
//    }
//    public static void checkRecord(PreBuiltTransportClient client,SearchResponse sr,Applicant input,String str) throws ExecutionException, InterruptedException {
//
//        UpdateRequest updateRequest;
//        for(SearchHit res:sr.getHits())
//        {
//            if(input.getFirst_Name().equals(res.getSource().get("FirstName")) && str.equals("FirstName"))
//            {
//                updateRequest = new UpdateRequest("customer", "personal_info",input.getCust_No())
//                    .script(new Script(ScriptType.INLINE,"painless","ctx._source.check.add(params.data)",f));
//                client.update(updateRequest).get();
//            }
//            else if(input.getLast_Name().equals(res.getSource().get("LastName")) && str.equals("LastName"))
//            {
//                System.out.println("Record is matched with customer id : "+ res.getSource().get("Customer_no") + " By LastName");
//            }
//            else if(input.getStreet().equals(res.getSource().get("Street")) && str.equals("Street"))
//            {
//                System.out.println("Record is matched with customer id : "+ res.getSource().get("Customer_no") + " By Street");
//            }
//            else if(input.getTown().equals(res.getSource().get("Place_of_Residence")) && str.equals("Town"))
//            {
//                System.out.println("Record is matched with customer id : "+ res.getSource().get("Customer_no") + " By Town");
//            }
//            else if(input.getZipcode().equals(res.getSource().get("Zip")) && str.equals("Zip"))
//            {
//                System.out.println("Record is matched with customer id : "+ res.getSource().get("Customer_no") + " By Zip");
//            }
//        }
//    }


    public static void updataInput(SearchResponse sr,Applicant input,PreBuiltTransportClient client,String rel) throws ExecutionException, InterruptedException {
        List<String> l=new ArrayList<String>();
        UpdateRequest updateRequest;
        Map<String,Object> f=new HashMap<String, Object>();
        for(SearchHit res:sr.getHits())
        {
            l.add(res.getId());
        }
        f.put("data", l);
        updateRequest = new UpdateRequest("customer", "personal_info",input.getCust_No())
                .script(new Script(ScriptType.INLINE,"painless","ctx._source."+rel+".add(params.data)",f));
        try {
            client.update(updateRequest).get();
        }catch (RemoteTransportException e){
           e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (ExecutionException e){

            updateRequest = new UpdateRequest("customer", "personal_info",input.getId())
                    .script(new Script(ScriptType.INLINE,"painless","ctx._source."+rel+"=params.data",f));
            client.update(updateRequest).get();
        }

        for (String id :
                l) {
            f.put("data2",input.getId());
            try {
                updateRequest = new UpdateRequest("customer", "personal_info", id)
                        .script(new Script(ScriptType.INLINE, "painless", "ctx._source."+rel+".add(params.data2)", f));
                client.update(updateRequest).get();
            }catch(ExecutionException e){
                updateRequest = new UpdateRequest("customer", "personal_info",id)
                        .script(new Script(ScriptType.INLINE,"painless","ctx._source."+rel+"=params.data2",f));
                client.update(updateRequest).get();
            }

        }
    }

    public static void main(String []args) throws ClassNotFoundException, SQLException, UnknownHostException, ExecutionException, InterruptedException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","sachin123@");
//        Statement stmt=con.createStatement();
//        int Size=0,i=1;
//        ResultSet rs=stmt.executeQuery("select count(*) from personal limit 100");
//        while(rs.next())
//        {
//            Size=rs.getInt(1);
//            System.out.println(Size);
//        }
//
//
//        con.close();

        Applicant input=new Applicant();
        input.setCust_No("CU00008333343056");
        input.setFirst_Name("Ömer");
        input.setLast_Name("Braun");
        input.setStreet("Fangdieckstraße 14");
        input.setTown("Koblenz");
        input.setZipcode("57474  ");
        input.setId("AVtcqDtr-hedAcx8Wh2Y");

        Settings settings = Settings.builder()
                .put("cluster.name", "jvmti-cluster")
                .put("client.transport.sniff", true)
                .build();
            PreBuiltTransportClient client = new PreBuiltTransportClient(settings);

            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        Map<String,Object> f=new HashMap<String, Object>();
        f.put("data","hello");
                UpdateRequest updateRequest = new UpdateRequest("a", "b", "1")
                .script(new Script(ScriptType.INLINE,"painless","ctx._source.doc=params.data",f));
        client.update(updateRequest).get();



        SearchResponse sr=client.prepareSearch("customer").setTypes("personal_info")
                .setQuery(QueryBuilders.matchQuery("FirstName",input.getFirst_Name()))
                .execute().get();
        Match_Record.updataInput(sr,input,client,"first_name_rel");
        sr=client.prepareSearch("customer").setTypes("personal_info")
                .setQuery(QueryBuilders.matchQuery("LastName",input.getLast_Name()))
                .execute().get();
        Match_Record.updataInput(sr,input,client,"last_name_rel");
        sr=client.prepareSearch("customer").setTypes("personal_info")
                .setQuery(QueryBuilders.matchQuery("Street",input.getStreet()))
                .execute().get();
        Match_Record.updataInput(sr,input,client,"street_rel");
        sr=client.prepareSearch("customer").setTypes("personal_info")
                .setQuery(QueryBuilders.matchQuery("Zip",input.getZipcode()))
                .execute().get();
        Match_Record.updataInput(sr,input,client,"zip_rel");
        sr=client.prepareSearch("customer").setTypes("personal_info")
                .setQuery(QueryBuilders.matchQuery("Place_of_Residence",input.getTown()))
                .execute().get();
        Match_Record.updataInput(sr,input,client,"place_of_residence_rel");


        UpdateByQueryRequestBuilder updateByQuery = UpdateByQueryAction.INSTANCE.newRequestBuilder(client);

        f.put("data", input.getCust_No());

        BulkIndexByScrollResponse response=updateByQuery
                .source("customer")
                .filter(termQuery("FirstName",input.getFirst_Name()))
                .script(new Script(ScriptType.INLINE,"painless","ctx._source.FirstName_Rel.add(params.data)",f))
                .get();
        response=updateByQuery
                .source("customer")
                .filter(termQuery("LastName",input.getLast_Name()))
                .script(new Script(ScriptType.INLINE,"painless","ctx._source.LastName_Rel.add(params.data)",f))
                .get();
        response=updateByQuery
                .source("customer")
                .filter(termQuery("Zip",input.getZipcode()))
                .script(new Script(ScriptType.INLINE,"painless","ctx._source.Zipcode_Rel.add(params.data)",f))
                .get();
        response=updateByQuery
                .source("customer")
                .filter(termQuery("Place_of_Residence",input.getTown()))
                .script(new Script(ScriptType.INLINE,"painless","ctx._source.Town_Rel.add(params.data)",f))
                .get();
        response=updateByQuery
                .source("customer")
                .filter(termQuery("Street",input.getStreet()))
                .script(new Script(ScriptType.INLINE,"painless","ctx._source.Street_Rel.add(params.data)",f))
                .get();

//        UpdateRequest updateRequest = new UpdateRequest("a", "b", "1")
//                .script(new Script(ScriptType.INLINE,"painless","ctx._source.check.add(params.data)",f));
//        client.update(updateRequest).get();

        client.close();
    }
}
