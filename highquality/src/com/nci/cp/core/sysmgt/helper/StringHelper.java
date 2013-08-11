package com.nci.cp.core.sysmgt.helper;

import java.util.StringTokenizer;

/**
 * <p>标题: weboa办公系统</p>
 * <p>描述: 关于字符串处理的辅助类</p>
 * <p>版权: Copyright (c) 2003</p>
 * <p>公司: 广东南方通信高科技有限公司</p>
 * @作者 
 * @版本 1.0
 */
public class StringHelper
{
   /**
    *将空字符串转换为""
    * @param 原始字符串
    * @return 返回转换后的字符串
    */
   public static final String convertStringNull(String strOrig)
   {
      String strReturn = "";
      if (strOrig == null || strOrig.equals("null"))
      {
         strReturn = "";
      }
      else
      {
         strReturn = trim(strOrig.trim());
      }
      return strReturn;
   }

	/**
	 * 从格式“CN=黄勇军/OU=企业发展部/OU=agd/O=ydb”  ——〉 “黄勇军/企业发展部/agd/ydb”
	 * @param ldapName
	 * @return
	 */
   public static final String getFullname(String ldapName) {

		String fullname = "";
		String[] str ;
       if (ldapName == null)
           return null;
       str = ldapName.split( "/");
       for (int i = 0; i < str.length; i++) {
			if (str[i].indexOf("=") > 0)
				fullname += str[i].substring(str[i].indexOf("=") + 1) + "/";
		}
		if (fullname.lastIndexOf('/') == fullname.length() - 1)
			fullname = fullname.substring(0, fullname.length() - 1);
		return fullname;
	}

   
   
   /**
    * 分解以特定分隔符分隔多个同一类型信息的字符串为字符串数组
    * @param strOrigin 原始字符串
    * @param separator 分隔符
    * @return
    */
   public static final String[] parserString(String strOrigin, String separator)
   {
      try
      {
         StringTokenizer st;
         String strItem;

         if (strOrigin == null)
         {
            return null;
         }
         st = new StringTokenizer(strOrigin, separator);
         String[] returnValue = new String[st.countTokens()];
         int index = 0;
         while (st.hasMoreTokens())
         {
            strItem = (String) st.nextToken();
            if (strItem != null && strItem.trim().length() != 0)
            {
               returnValue[index++] = strItem;
            }
         }
         return returnValue;
      }
      catch (Exception e)
      {
         return null;
      }
   }

   /**
    * 将传入的字符串转换为中文字符串，并将空字符串转换为""
    * @param strOrigin 原始字符串
    * @return 中文字符串
    */
   public static String toChineseStr(String strOrigin)
   {
      if (strOrigin == null || strOrigin.equals("null"))
      {
         strOrigin = "";
      }
      else
      {
         strOrigin = strOrigin.trim();
      }

      try
      {
         strOrigin = new String(strOrigin.getBytes("ISO8859_1"), "GBK");
      }
      catch (Exception e)
      {
      }
      return strOrigin;
   }

   /**
    * 将中文字符串转换为ISO8859_1编码格式，并将空字符串转换为""
    * @param strOrigin strOrigin 原始字符串（中文字符串）
    * @return
    */
   public static String toStandardStr(String strOrigin)
   {
      if (strOrigin == null || strOrigin.equals("null"))
      {
         strOrigin = "";
      }
      else
      {
         strOrigin = strOrigin.trim();
      }

      try
      {
         strOrigin = new String(strOrigin.getBytes("GBK"), "ISO8859_1");
      }
      catch (Exception e)
      {
      }
      return strOrigin;
   }

   /**
    *
    * @param s
    * @param separatorSign
    * @return String[]
    * @add hansomee 6/11/2003
    */

   public static String[] split(String s, String separatorSign)
   {
      try
      {
         if (s == null)
            return null;
         int index = 0;
         java.util.Vector vec = new java.util.Vector();
         while (true)
         {
            index = s.indexOf(separatorSign, index);
            if (index < 0)
               break;
            vec.addElement(new Integer(index++));
         }

         int size = vec.size();

         if (size <= 0)
         {
            String[] ret = new String[1];
            ret[0] = s;
            return ret;
         }

         String[] strarr = new String[size + 1];

         Integer[] indArr = new Integer[size];
         vec.copyInto(indArr);

         // sort the index array for getting the string.
         java.util.Arrays.sort(indArr);

         int ind = 0;
         int len = strarr.length;
         for (int j = 0; j < (len - 1); j++)
         {
            strarr[j] = s.substring(ind, indArr[j].intValue());
            ind = indArr[j].intValue() + 1;
         }
         if (len > 0)
            strarr[len - 1] = s.substring(ind);

         return strarr;
      }
      catch (Exception e)
      {
         return null;
      }
   }
 /**
    *将空格串" "或空指针转换为html的空格编码
    * @param 原始字符串
    * @return 返回转换后的字符串
    */
   public static  final String filterNullStringToHTMLSpace(String strOrigin)
   {
      String rets = "";
      if (strOrigin == null)
      {
         rets= "&nbsp;";
      }
      else if (strOrigin.length() == 0)
      {
         rets = "&nbsp;";
      }
      else
      {

        for(int i=0;i<strOrigin.length();i++)
        {
          if (strOrigin.charAt(i)==' ') {
            rets +=  "&nbsp;";

          }
          else {
            rets += strOrigin.charAt(i);

          }
        }

      }
      return rets;
   }

   /**
    * 将数字0转换为""，并将空字符串转换为""
    * @param strOrigin strOrigin 原始字符串（中文字符串）
    * @return
    * add by hansomee 3/5/2004
    */
   public static String convertZeroToSpace(String strOrigin)
   {
      if (strOrigin == null || strOrigin.equals("null") || strOrigin.equals("0"))
      {
         strOrigin = "";
      }
      else
      {
         strOrigin = strOrigin.trim();
      }

      return strOrigin;
   }

   public static String trim(String s)
   {
     try {
//       String whitespace = new String("　");
//       s= s.replaceFirst(whitespace,"");
//       s = new StringBuffer(s).reverse().toString();
//       s= s.replaceFirst(whitespace,"");
//       s = new StringBuffer(s).reverse().toString();
         s= s.trim();
     }catch(Exception e)
     {
       e.printStackTrace();
     }
     return s;
   }

 public static String toLowerCase(String strUp)
 {
   return strUp.toLowerCase();
 }

 /**
  * 替换字符串函数(add by ywj 2004-10-16),由于replaceAll方法
  * 在websphere中出现方法找不到的情况，这里方法名被修改。zxz（2005-03-19）
  * @param src 被操作的字符串
  * @param replace 被替换内容 
  * @param dest 替换内容
  * @return
  */
 public static String replaceAllStr(String src, String replace, String dest) {
    StringBuffer buf = new StringBuffer(src);
    String m_dest = "";
    if (dest != null) {
      m_dest = dest;
    }
    int i = 0;
    while ( (i = buf.indexOf(replace, i)) != -1) {
      buf = new StringBuffer(buf.subSequence(0, i) + m_dest +
                            buf.substring(i + replace.length()));
      i += m_dest.length();
    }
    return buf.toString();
  }
 
 protected static String encode(String string, String[] table)
 {
   if (string == null)
   {
     return "";
   }

   //过滤回车
   int n = string.length();
   int index = 0;
   while (index != -1)
   {
     index = string.indexOf("\n");
     if(index != -1)
     {
       string = string.substring(0,index)+string.substring(index+1,n);
     }
     n = string.length();
   }
   //过滤回车 end

   char character;
   StringBuffer buffer = new StringBuffer();

   // loop over all the characters of the String.
   for (int i = 0; i < n; i++)
   {
     character = string.charAt(i);

     /*if(character==' ')
     {
       System.out.println("yee");
       continue;
     }*/

     if (character < 256)
     {
       buffer.append(table[character]); // Htmlcode of these characters are added one by one
     }
     else
     {
       buffer.append("&#").append( (int) character).append(";");
     }
   }
   //String strResult = buffer.toString();
   //System.out.println("strResult1="+strResult);
   //strResult.replaceAll("<br>"," ");
   //System.out.println("strResult2="+strResult);
   return buffer.toString();
 }

 public static String replaceSpecialChar(String str)
 {
   str=StringHelper.convertStringNull(str);
   str=str.replace('\r',' ');
   str=str.replace('\n',' ');
   str=str.replace('"','“');
   return str;
 }

 public static String UnEncodeHtml(String str)
 {
   int index = 0,pos=0;
   if(str == null || str.length()==0) return str;
   StringBuffer buffer = new StringBuffer();
   do{
     index = str.indexOf('&');
     if(index != -1){
       pos = str.indexOf(';');
       if(str.charAt(index+1) == '#' && pos > 0){
         String tmp = str.substring(index+2,pos);
         char ch = (char)Integer.parseInt(tmp);
         buffer.append(ch);
       }else if(pos > 0){
         String tmp = str.substring(index,pos+1);
         if(tmp.equals("&quot;")){
            buffer.append('\"');
         }else if(tmp.equals("&amp;")){
           buffer.append('&');
         }else if(tmp.equals("&lt;")){
           buffer.append('<');
         }else if(tmp.equals("&gt;")){
           buffer.append('>');
         }else if(tmp.equals("&nbsp;")){
           buffer.append(' ');
         }
       }
       pos = pos + 1;
       if(pos <= str.length()) str = str.substring(pos,str.length());
     }
   }while(index != -1);
   if(str.length()>0)  buffer.append(str.substring(0, str.length()));
   return buffer.toString();
 }



  public static void main(String[] args)
  {
    String tests ="dsfaddsafsdfsd <dddd>   sdfsdfsdfsa　<dddd> 500";
//    String temp =tests.replaceAll("<dddd>","0000");
    String temp =tests.replaceAll("<dddd>","0d0");
    System.out.println("tests==========="+temp);
  }

}