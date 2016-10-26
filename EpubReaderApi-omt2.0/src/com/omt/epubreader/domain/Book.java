package com.omt.epubreader.domain;

import java.util.Vector;

import net.sf.zipme.ZipInputStream;

/**
 * This is Object of Book contain all content
 * @author dhiral
 *
 */

public class Book {
 
	private Vector tableOfContents = new Vector();
	private Vector authors = new Vector();
	private Vector chaptersFileName = new Vector(); 
	private Vector chaptersData = new Vector();
	private String publisher = "";
	private String rights = "";
	private String language = "";
	private Vector subjects = new Vector();
	private Vector cssFilePath = new Vector();
	private Vector cssFileData = new Vector();
	private String source = "";
	private String title = "";
	
	public Book() {
		
	}

	public Vector getTableOfContents() {
		return tableOfContents;
	}

	public void setTableOfContents(Vector tableOfContents) {
		this.tableOfContents = tableOfContents;
	}

	public Vector getAuthors() {
		return authors;
	}

	public void setAuthors(Vector authors) {
		this.authors = authors;
	}

	

	public Vector getChaptersFileName() {
		return chaptersFileName;
	}

	public void setChaptersFileName(Vector chaptersFileName) {
		this.chaptersFileName = chaptersFileName;
	}

	public Vector getChaptersData() {
		return chaptersData;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getRights() {
		return rights;
	}

	public String getLanguage() {
		return language;
	}

	public Vector getSubjects() {
		return subjects;
	}

	public String getSource() {
		return source;
	}

	public void setChaptersData(Vector chaptersData) {
		this.chaptersData = chaptersData;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setSubjects(Vector subjects) {
		this.subjects = subjects;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Vector getCssFilePath() {
		return cssFilePath;
	}

	public Vector getCssFileData() {
		return cssFileData;
	}

	public void setCssFilePath(Vector cssFilePath) {
		this.cssFilePath = cssFilePath;
	}

	public void setCssFileData(Vector cssFileData) {
		this.cssFileData = cssFileData;
	}
	
	
	
}
