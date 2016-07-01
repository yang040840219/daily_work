package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class SqlString {


	static final String parse(String query, Map<String,Object> paramMap) {

	 int length = query.length();
	 StringBuffer parsedQuery = new StringBuffer(length);
	 boolean inSingleQuote = false;
	 boolean inDoubleQuote = false;
	 int index = 1;
	 
	 for (int i = 0; i < length; i++) {
	  char c = query.charAt(i);
	  if (inSingleQuote) {
	   if (c == '\'') {
	    inSingleQuote = false;
	   }
	  } else if (inDoubleQuote) {
	   if (c == '"') {
	    inDoubleQuote = false;
	   }
	  } else {
	   if (c == '\'') {
	    inSingleQuote = true;
	   } else if (c == '"') {
	    inDoubleQuote = true;
	   } else if (c == ':' && i + 1 < length
	     && Character.isJavaIdentifierStart(query.charAt(i + 1))) {
	    int j = i + 2;
	    while (j < length
	      && Character.isJavaIdentifierPart(query.charAt(j))) {
	     j++;
	    }
	    String name = query.substring(i + 1, j);
	    c = '?';
	    i += name.length();

	    List indexList = (List) paramMap.get(name);
	    if (indexList == null) {
	     indexList = new LinkedList();
	     paramMap.put(name, indexList);
	    }
	    indexList.add(new Integer(index));

	    index++;
	   }
	  }
	  parsedQuery.append(c);
	 }

	 for (Iterator itr = paramMap.entrySet().iterator(); itr.hasNext();) {
	  Map.Entry entry = (Map.Entry) itr.next();
	  List list = (List) entry.getValue();
	  int[] indexes = new int[list.size()];
	  int i = 0;
	  for (Iterator itr2 = list.iterator(); itr2.hasNext();) {
	   Integer x = (Integer) itr2.next();
	   indexes[i++] = x.intValue();
	  }
	  entry.setValue(indexes);
	 }

	 return parsedQuery.toString();
	}


	
}
