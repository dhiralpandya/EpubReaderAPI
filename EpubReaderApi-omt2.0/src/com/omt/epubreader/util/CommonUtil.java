package com.omt.epubreader.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class contain common util 
 * @author dhiral
 *
 */
public class CommonUtil {
  public static final String EXTENSION_NCX = ".ncx" ;
  public static final String EXTENSION_OPF = ".opf";
  public static final String EXTENSION_CSS = ".css";
  public static final String SEPERATOR = "<~>";
  public static InputStream getCopyInputStream(InputStream in)
  {
	  InputStream copy = null;
	  ByteArrayOutputStream bos = new ByteArrayOutputStream();
	  int ch = -1 ;
	  try {
		while((ch = in.read())!= -1 )
		  {
			  bos.write(ch);
		  }
		  copy = new ByteArrayInputStream(bos.toByteArray());
	} catch (IOException e) {
		System.out.println("Exception in getCopyInputStream:"+e);
		e.printStackTrace();
	}
	  return copy;
  }
}
