package PAGES;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BASE_CLASS.wait_type;

public class homepage { 

	WebDriver dr;
	wait_type wt;
	
	public homepage(WebDriver dr)  
	{ 
		this.dr=dr;
		wt=new wait_type(dr);
	}
	
	
 By add_lap=By.xpath("//div[@class='list-group']/a[3]");
 By select_lap=By.linkText("MacBook air");
 By username=By.id("nameofuser");
 
 public String read_name() 
	{
	  WebElement  we_readname=wt.waitForElement(username, 30);
	   return we_readname.getText();  //reads the  name of the user after successful login
	}
	 
	public void set_product1()
	{

		dr.findElement(By.linkText("Iphone 6 32gb")).click();  // adds product iphone 6 32GB
	}
	
	public void add_laptop()
	{
		
	  WebElement we_lap=wt.elementToBeClickable(add_lap, 60);
	  we_lap.click();              //traverse to laptop page
	 
	
   }
	
	 
	public void select_laptop() throws InterruptedException
	{
		
		WebElement we_sel_lap=wt.elementToBeClickable(select_lap, 60);
		  we_sel_lap.click();  // adds product macbook air 
		
	}
	 
	
	public void add_product1()
	{

		this.set_product1();  
		
	}
	
	
}
