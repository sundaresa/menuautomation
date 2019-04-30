package com.vmetry.pagetest;

import java.io.IOException;












import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.page.object.HomePagePO;
import com.page.object.LoginPo;
import com.vmetry.base.Initialization;
import com.vmetry.utilization.UtilityClass;

public class LoginTest extends Initialization {

	
	@BeforeClass
	public void precond() throws IOException {
		initialize();
	}
	
	@BeforeMethod
	public void startLogiPage()
	{
		wd.navigate().to(p.getProperty("testingURL"));
	}
	
	
	@Test(dataProvider="td")
	public void testLogin(String tcid,String mobno,String pass,String expected){
		
		LoginPo lgpo=PageFactory.initElements(wd, LoginPo.class);
		HomePagePO hppo=PageFactory.initElements(wd, HomePagePO.class);
		System.out.println("Test Case ID is: "+tcid);
		lgpo.enterMobileNo(mobno);
		lgpo.enterPassword(pass);
		lgpo.clickSignIn();
		if(tcid.equals("TC_001")) {
		String actual=hppo.getUserDispName();
		hppo.clickUserName();
		hppo.clickLogOut();
		Assert.assertEquals(expected, actual);
		}else {
			
			String acterrmsg=lgpo.getValidationMsg();
			Assert.assertEquals(expected, acterrmsg);
		}
		
		
		
	}
	@DataProvider(name="td")
	public static String[][] getData() throws IOException{
		
		System.out.println("Sheetname: "+p.getProperty("logintestdata"));
		String[][] data=UtilityClass.getExcelData(p.getProperty("logintestData"));
		return data;
		
		
		
	}

}
