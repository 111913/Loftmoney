package com.scorp.loftmoney.screens.login;

import com.scorp.loftmoney.remote.AuthApi;

public interface LoginPresenter {
    void performLogin(AuthApi authApi);
    void attachViewState(LoginView loginView);
    void disposeRequest();
}
