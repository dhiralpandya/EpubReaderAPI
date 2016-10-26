package com.omt.epubreader.parser;

import java.io.InputStream;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;


/**
 * This class perform parsing of  .ncx file 
 * @author dhiral
 *
 */
public class NcxParser {

		private InputStream in = null;
		private String title = "";
		private Vector authors = new Vector();
		private Vector tableOfContents = new Vector();
		private Vector contentDataFileName = new Vector();		
		private final String TAG_TITLE = "doctitle";
		private final String TAG_AUTHOR = "docauthor";
		private final String TAG_NAVMAP = "navmap";
		private final String TAG_TEXT = "text";
		private final String TAG_NAVPOINT = "navpoint";
		private final String TAG_NAVLABEL = "navlabel";
		private final String TAG_CONTENT = "content";
		public NcxParser(InputStream  in ) {
			this.in = in;
			getBookNcxInfo();
		}
		private void getBookNcxInfo()
		{
			KXmlParser pars = new KXmlParser();
			try {
				pars.setInput(in, "UTF-8");
				while(pars.next()!= XmlPullParser.END_DOCUMENT)
				{
					if(pars.getEventType() == XmlPullParser.START_TAG)
					{
						if(pars.getName().toLowerCase().equals(TAG_TITLE))
						{
							pars.next();
							pars.next();
							if(pars.getName().toLowerCase().equals(TAG_TEXT))
							pars.next();
							title = pars.getText();
						}else
						if(pars.getName().toLowerCase().equals(TAG_AUTHOR))
						{
							pars.next();
							pars.next();
							if(pars.getName().toLowerCase().equals(TAG_TEXT))
							pars.next();
							authors.addElement(pars.getText());
							
						}else
						if(pars.getName().toLowerCase().equals(TAG_NAVMAP))
						{
							while(!( pars.getEventType() == XmlPullParser.END_TAG && pars.getName().toLowerCase().equals(TAG_NAVMAP) ) )
							{
								pars.next();
								if(pars.getEventType() == XmlPullParser.START_TAG && pars.getName().toLowerCase().equals(TAG_NAVPOINT))
								{
									pars.next();// Goto TAG_NAVPOINT text Part
									pars.next();// Goto Next Tag TAG_NAVLABEL
									if(pars.getName().toLowerCase().equals(TAG_NAVLABEL))
									{
										pars.next();// Goto TAG_NAVLABEL text part
										pars.next();// Goto Next TAG_TEXT 
										if(pars.getName().toLowerCase().equals(TAG_TEXT))
										{
											pars.next();// Goto TAG_TEXT text part
											tableOfContents.addElement(pars.getText());
							
										}
									}
									
								}else
								if(pars.getEventType() == XmlPullParser.START_TAG && pars.getName().toLowerCase().equals(TAG_CONTENT))
								{
									if(pars.getAttributeCount()>0)
									{
										contentDataFileName.addElement(pars.getAttributeValue(0));
									}
								}
								
							}
						}
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		public String getTitle() {
			return title;
		}
		public Vector getAuthors() {
			return authors;
		}
		public Vector getTableOfContents() {
			return tableOfContents;
		}
		public Vector getContentDataFileName() {
			return contentDataFileName;
		}
		
		
}
