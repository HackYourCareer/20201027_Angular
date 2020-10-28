import { AppPage } from './app.po';
import { browser, logging } from 'protractor';
import { protractor } from 'protractor/built/ptor';

describe('workspace-project App', () => {
  let page: AppPage;
  const EC = protractor.ExpectedConditions;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Beer Portal');
  });

  it('should display login form', () => {
    const loginButton = page.getLoginButton();
    browser.wait(EC.elementToBeClickable(loginButton), 5000);
    loginButton.click();
    expect(page.getLoginForm().isPresent()).toBeTruthy();
    expect(page.getSigninButton().isPresent()).toBeTruthy();
    expect(page.getLogoutButton().isPresent()).toBeFalsy();
  });

  it('should login to Beer Portal', () => {
    page.fillCredentials();
    expect(page.getLogoutButton().isPresent()).toBeTruthy();
    expect(page.getLoginForm().isPresent()).toBeFalsy();
    expect(page.getSigninButton().isPresent()).toBeFalsy();
    expect(page.getBeerList().count()).toBeGreaterThan(0);
    expect(page.getBeerTitle()).toEqual('Beer List');
  });

  it('should open Beer Details', () => {
    const beerItem = page.getBeerList().first();
    browser.wait(EC.elementToBeClickable(beerItem), 5000);
    beerItem.click();
    expect(page.getBeerTitle()).not.toEqual('Beer List');
  });

  it('should logout from Beer Portal', () => {
    const logoutButton = page.getLogoutButton();
    browser.wait(EC.elementToBeClickable(logoutButton), 5000);
    logoutButton.click();
    expect(page.getLoginButton().isPresent()).toBeTruthy();
    expect(page.getSigninButton().isPresent()).toBeTruthy();
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
