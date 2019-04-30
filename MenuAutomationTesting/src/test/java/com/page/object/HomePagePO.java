package com.page.object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePagePO {

	@FindBy(how=How.CSS,using="a.loggedin-user-name")
	public WebElement userdispname;
	
	public String getUserDispName() {
		return userdispname.getText();
	}
	
	
	public void clickUserName() {
		userdispname.click();
	}
	
	
	@FindBy(how=How.LINK_TEXT,using="Log Out")
	public WebElement logout;
	public void clickLogOut() {
		logout.click();
		
	}
	
	
	
}


