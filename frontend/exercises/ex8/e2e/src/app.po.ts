import { browser, by, element } from 'protractor';

export class AppPage {
  private credentials = {
    username: 'admin',
    password: 'nimda'
  };

  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root fd-shellbar-title')).getText() as Promise<string>;
  }

  getLoginButton() {
    return element(by.css('#loginButton'));
  }

  getLoginForm() {
    return element(by.css('#loginForm'));
  }

  getLogoutButton() {
    return element(by.css('#logoutButton'));
  }

  getSigninButton() {
    return element(by.css('#signinButton'));
  }

  fillCredentials(credentials: any = this.credentials) {
    element(by.css('[name="userName"]')).sendKeys(credentials.username);
    element(by.css('[name="password"]')).sendKeys(credentials.password);
    element(by.css('#submitLoginButton')).click();
  }

  getBeerTitle() {
    return element(by.css('.fd-action-bar__title')).getText() as Promise<string>;
  }

  getBeerList() {
    return element.all(by.className('beer-list-item'));
  }

}
