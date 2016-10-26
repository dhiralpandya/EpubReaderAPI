package com.omt.epubreader.parser;

import java.io.InputStream;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;



/**
 * This class perform parsing of .opf file
 * @author dhiral
 *
 */
public class OpfParser {
	
	
	private InputStream in ;
	private String publisher = "";
	private Vector subjects = new Vector();
	private String source = "";
	private String rights = "";
	private String language = "";
	private final String TAG_PUBLISHER = "publisher";
	private final String TAG_SUBJECT = "subject";
	private final String TAG_SOUREC = "source";
	private final String TAG_RIGHTS = "rights";
	private final String TAG_LANGUAGE = "language";
	public OpfParser(InputStream in) {
		this.in = in;
		getBookOpfInfo();
	}
	
	
	
   private void getBookOpfInfo()
   {
	   KXmlParser pars = new KXmlParser();
	   try {
		pars.setInput(in, "UTF-8");
		while(pars.next()!= XmlPullParser.END_DOCUMENT)
		{
			
			if(pars.getEventType() == XmlPullParser.START_TAG)
			{
				if(pars.getName().toLowerCase().indexOf(TAG_PUBLISHER) > -1)
				{
					pars.next();
					publisher = pars.getText();
				}else
				if(pars.getName().toLowerCase().indexOf(TAG_SUBJECT) > -1)
				{
					pars.next();
					subjects.addElement(pars.getText());					
				}else
				if(pars.getName().toLowerCase().indexOf(TAG_SOUREC) > -1)
				{
					pars.next();
					source = pars.getText();
				}else
				if(pars.getName().toLowerCase().indexOf(TAG_LANGUAGE) > -1)
				{
					pars.next();
					language = pars.getText();
				}else
				if(pars.getName().toLowerCase().indexOf(TAG_RIGHTS) > -1)
				{
					pars.next();
					rights = pars.getText();
				}
			}
			   		
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }



	public String getPublisher() {
		return publisher;
	}
	
	
	
	public Vector getSubjects() {
		return subjects;
	}
	
	
	
	public String getRights() {
		return rights;
	}



	public String getSource() {
		return source;
	}



	public String getLanguage() {
		return language;
	}
     
	
   
}
