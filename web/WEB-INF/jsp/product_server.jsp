<%@ page import="java.sql.*"%>  

<%  
    String s=request.getAttribute("val").toString();  
    
    if(s==null || s.trim().equals("")){  
        out.print("Please enter id");  
    }else{  
        int id=Integer.parseInt(s);  
        out.print(id);  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_inventory","root","1234");  
            PreparedStatement ps=con.prepareStatement("select * from product where pid=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                out.print(rs.getDouble("price"));  
            }  
            con.close();  
        }catch(Exception e){
            e.printStackTrace();
        }  
    }  
%>  