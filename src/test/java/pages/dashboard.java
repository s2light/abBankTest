package pages;

public class dashboard {
   public static final String DASHBOARD_MAIN_MENU = "//a[contains(@class,'oxd-main-menu-item') and .//span[normalize-space()='%s']]";

   public static final String TOPBAR_MENU = "//nav[contains(@class,'oxd-topbar-body-nav')]//span[normalize-space()='%s']";
   public static final String TOPBAR_SUB_MENU = "//a[@role='menuitem'][normalize-space()='%s']";
   public static final String LISTBOX ="//div[@role='listbox']//span[contains(text(), '%s')]";


   public static final String INPUT = "//label[normalize-space()='%s']/ancestor::div[contains(@class,'oxd-input-group')]//input";
   public static final String BUTTON = "//button[@type='button' and normalize-space()='%s']";
   public static final String SUBMIT = "//button[@type='submit' and normalize-space()='%s']";

   public static final String SELECT = "(//div[label[text()='%s']]/following-sibling::div//div[contains(@class, 'oxd-select-text')])[1]";
   public static final String OPTION_SELECT = "//div[@role='listbox']//div[@role='option']//span[text()='%s']";
   public static final String RECORD_FOUND_TEXT ="//hr[@class='oxd-divider orangehrm-horizontal-margin']";

}
