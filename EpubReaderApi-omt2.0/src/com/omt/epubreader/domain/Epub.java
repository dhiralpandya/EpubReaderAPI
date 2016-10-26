package com.omt.epubreader.domain;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import com.omt.epubreader.parser.NcxParser;
import com.omt.epubreader.parser.OpfParser;
import com.omt.epubreader.util.CommonUtil;

import net.sf.zipme.ZipEntry;
import net.sf.zipme.ZipInputStream;
/**
 * This is main entry point of epub reader api
 * @author dhiral  
 */
public class Epub {
	
	private Vector tempFileName = new Vector();
	private Vector tempFileData = new Vector();
	private Book book = new Book();

 /**
  * This method return book object 
  * @param url : it is path of .epub file
  * @return Book
  * @author dhiral
  */
public Book getBook(InputStream inputStream)
   {
	   InputStream in = inputStream;
	   ZipInputStream zip = new ZipInputStream(in);
	   setFilePath(zip);
	   setFileData();
	   return book;
   }

   private void setFilePath(ZipInputStream zin)
   {
		try {
			for(ZipEntry zipEntry = zin.getNextEntry(); zipEntry != null; zipEntry = zin.getNextEntry()) {
				if(zipEntry.isDirectory()) {
					continue;
				}
				if(zipEntry.getName().toLowerCase().endsWith(CommonUtil.EXTENSION_CSS))
				{
					book.getCssFilePath().addElement(zipEntry.getName());
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					int ch = -1;
					while((ch = zin.read()) != -1)
					{
						bos.write(ch);
					}
					String data = new String(bos.toByteArray());
					book.getCssFileData().addElement(data);
				}
				if(zipEntry.getName().toLowerCase().endsWith(CommonUtil.EXTENSION_NCX))
				{
							
					NcxParser ncxp = new NcxParser(zin);
					book.setTableOfContents(ncxp.getTableOfContents());
					book.setAuthors(ncxp.getAuthors());
					book.setTitle(ncxp.getTitle());
					book.setChaptersFileName(ncxp.getContentDataFileName());
				}else
				if(zipEntry.getName().toLowerCase().endsWith(CommonUtil.EXTENSION_OPF))
				{
					
					OpfParser opfp = new OpfParser(zin);					
					book.setPublisher(opfp.getPublisher());
					book.setRights(opfp.getRights());
					book.setSource(opfp.getSource());
					book.setSubjects(opfp.getSubjects());
					book.setLanguage(opfp.getLanguage());
					
				}else
				{
					String fileName = zipEntry.getName();
					//System.out.println("File Name:"+fileName);
					String data = "";
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					int ch = -1;
					while((ch = zin.read())!=-1)
					{
						bos.write(ch);
					}
					data = new String(bos.toByteArray());
					tempFileData.addElement(data);
					tempFileName.addElement(fileName);
				}
			}
		} catch (IOException e) {
			System.out.println("Exception in setFilePath in EPUB Class:"+e);
			e.printStackTrace();
		}
		
   }
   
   private void setFileData()
   {	   
	   
	   Vector fileName = book.getChaptersFileName();
	   //System.out.println("Chepter File Name :"+fileName);
	   Vector chapterData = new Vector();
	   int size = fileName.size();
	   int tempFileSize = tempFileData.size();
	   try {
		    for(int i=0 ; i < size ; i++ )
			   {
				  String file = fileName.elementAt(i).toString();
				  for(int j = 0 ; j < tempFileSize ; j++)
				  {
				     if(tempFileName.elementAt(j).toString().indexOf(file)>-1)
				     {
				    	 chapterData.addElement(tempFileData.elementAt(j));
				     }
				  }
			   }
		   
	} catch (Exception e) {
		System.out.println("Exception in setFileData Method:"+e);
		e.printStackTrace();
	}
	if(chapterData.size()>0)
	{
		book.setChaptersData(chapterData);
	}
   }
  
}
