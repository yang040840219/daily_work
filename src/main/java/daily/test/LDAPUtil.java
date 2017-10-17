package daily.test;

import java.util.Hashtable;  
 
import javax.naming.Context;  
import javax.naming.NamingException;  
import javax.naming.ldap.InitialLdapContext;  
import javax.naming.ldap.LdapContext;  
 
public class LDAPUtil {  
     
   /** 
    * 相关问题： 
    * 1.权限的控制， 
    * 2.匿名登录的验证 
    * 3.登录的方式：匿名，用户名密码验证 
    */  
   private String url;  
   private String basedn;  
   private String domain;  
   private   Hashtable<String, String> env = new Hashtable<String, String>();  
   public LDAPUtil(){  
       url = "";  
       basedn = "" ;  
       domain="";  
   }  
     
   public  boolean connect(String userName,String passwd) {  
          boolean result=false;  
          LdapContext ldapContext = null;  
          //用户名称，cn,ou,dc 分别：用户，组，域  
          env.put(Context.SECURITY_PRINCIPAL, userName);  
          //用户密码 cn 的密码  
          env.put(Context.SECURITY_CREDENTIALS, passwd);  
          //url 格式：协议://ip:端口/组,域   ,直接连接到域或者组上面  
          env.put(Context.PROVIDER_URL, url+basedn);  
          //LDAP 工厂  
          env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");  
          //验证的类型     "none", "simple", "strong"  
          env.put(Context.SECURITY_AUTHENTICATION, "simple");  
          try {  
           ldapContext = new InitialLdapContext(env, null);  
           result=true;  
           System.out.println("---connection is ready----");  
       } catch (NamingException e) {  
           //e.printStackTrace();  
           System.out.println("--- get connection failure ----");  
       }  
          return result;  
   }  
     
     
   public  boolean validateUser(String userName,String passwd){  
         
       String userdn="uid="+userName+","+domain;  
       String dn=userdn+","+basedn;  
         
       return connect(dn, passwd);  
   }  
     
   public static void main(String[] args) {  
       LDAPUtil util=new LDAPUtil();  
       /* 
        * uid=qsk1,ou=People,dc=jt-test,dc=com 
        */  
         
       util.validateUser("qsk1","123abc");  
   } 
   
}
