package com.linhnguyen.learningenglish;
/**
 * Created by Linh Nguyen on 12/10/2016.
 */
public class Works {
	int id;
	String Title;
	String Description;
	public Works(int id, String Title, String Description)
	{
		this.id=id;
		this.Title=Title;
		this.Description=Description;
	}
	public Works(String Title, String Description)
	{
		this.Title=Title;
		this.Description=Description;
	}

}
