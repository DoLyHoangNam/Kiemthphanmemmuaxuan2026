describe('Bài tập Thực hành Cypress - SauceDemo', () => {

  it('Kịch bản 1 & 2: Kiểm tra Đăng nhập', () => {
    cy.visit('https://www.saucedemo.com');
    // Đăng nhập sai
    cy.get('#user-name').type('invalid_user');
    cy.get('#password').type('wrong_password');
    cy.get('#login-button').click();
    cy.get('.error-message-container').should('contain', 'Username and password do not match');

    // Đăng nhập đúng
    cy.get('#user-name').clear().type('standard_user');
    cy.get('#password').clear().type('secret_sauce');
    cy.get('#login-button').click();
    cy.url().should('include', '/inventory.html');
  });

  it('Kịch bản 3 & Bổ sung: Thêm và Xóa sản phẩm', () => {
    cy.visit('https://www.saucedemo.com');
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();

    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_badge').should('have.text', '1');

    cy.get('.inventory_item').first().find('.btn_inventory').click(); // Nút "Remove"
    cy.get('.shopping_cart_badge').should('not.exist');
  });

  it('Kịch bản Bổ sung: Quy trình thanh toán', () => {
    cy.visit('https://www.saucedemo.com');
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();

    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_link').click();
    cy.get('#checkout').click();

    cy.get('#first-name').type('John');
    cy.get('#last-name').type('Doe');
    cy.get('#postal-code').type('12345');
    cy.get('#continue').click();

    cy.url().should('include', '/checkout-step-two.html');
  });
});