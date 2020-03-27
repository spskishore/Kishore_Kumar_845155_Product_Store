package TESTS;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BASE_CLASS.utilities;
import BASE_CLASS.wait_type;
import EXCEL_UTILITY.excel_product;
import PAGES.add_tocart;
import PAGES.checkout_page;
import PAGES.homepage;
import PAGES.reg_pom;

public class test_firefox_i extends excel_product {
	
		WebDriver dr;
			
			utilities ut; 
			wait_type wt;
			
			reg_pom rp;
			homepage hp;
			add_tocart ap;
			checkout_page cp;
			
			
			
			String url="https://www.demoblaze.com/"; 
			
			@BeforeClass
			public void bm() {
				ut=new utilities();
				dr=ut.launch_browser("FIREFOX", url); //launches the chrome browser
				
			}
			
			
		  @Test(dataProvider="project")
		  public void TC1(String s_uid,String s_pwd,String l_uid,String l_pwd,String el,String cn,String tm,String a) throws InterruptedException, IOException {
			  
			  
			  wt=new wait_type(dr);
			  ut=new utilities();
			  
			  rp=new reg_pom(dr);
			  rp.do_reg(s_uid, s_pwd);	 //performs sign-up operations
			  rp.alert();            //handles successful sign-up alert
		      Thread.sleep(3000);
		      
		  } 
		  
		  
		  @Test(dataProvider="project")
		  public void TC2(String s_uid,String s_pwd,String l_uid,String l_pwd,String el,String cn,String tm,String a) throws InterruptedException, IOException { 
		      rp.do_login(l_uid, l_pwd); //performs sign-in operations
			  
			  Thread.sleep(5000);
			  
			  hp=new homepage(dr); 
			  String act_usr= hp.read_name(); //reads the name of the user after successful login
			  
			 ut.screenshot(dr);
			  
			  Assert.assertTrue(act_usr.contains(a)); //checks if the username in homepage is same as the name in excel
		  }
		  
		  @Test(dataProvider="project")
		  public void TC3(String s_uid,String s_pwd,String l_uid,String l_pwd,String el,String cn,String tm,String a) throws InterruptedException, IOException {
			  
		      hp.add_product1(); //adds the first product(iphone 6 32GB)
			  	  
			  Thread.sleep(3000);
			  ap=new add_tocart(dr); 
			  ap.set_cart(); 
			  ap.add_cart(el, cn, tm); //fills contact information
			  
			  Thread.sleep(3000);
			  hp.add_laptop(); //traverse to laptop page
			  hp.select_laptop();  // adds second product(macbook air)

			  ap.set_cart();
			  ap.add_alert(); 
			  
			  ap.goto_cart(); //traverse to final cart page
			  
			  cp=new checkout_page(dr);

			  String total_price= cp.get_totalprice(); //reads the final total price
			  System.out.println(total_price);
			  Assert.assertTrue(total_price.contains("1490"));  //checks for the total price as 1490
			  
			
				Thread.sleep(4000);

		  }
			 
		  @DataProvider(name="project")
		  public String[][] prov_data(){
			  
			  get_test_data(); //reads datas from excel file
			  return testdata;
		  } 
		  
		  @AfterClass
		  public void am()
		  {
			  
			  dr.close();  //closes the browser
			  
		  }
		}