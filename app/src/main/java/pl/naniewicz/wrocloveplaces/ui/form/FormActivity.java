package pl.naniewicz.wrocloveplaces.ui.form;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.naniewicz.wrocloveplaces.R;

/**
 * Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class FormActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.til_email) TextInputLayout mEmailTextInputLayout;
    @Bind(R.id.til_password) TextInputLayout mPasswordTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_login)
    public void onLoginButtonClick() {
        checkForm();
    }

    private void checkForm() {
        if (mEmailTextInputLayout.getEditText() != null) {
            String email = mEmailTextInputLayout.getEditText().getText().toString();
            setEmailValidationError(!isEmailValid(email));
        }
        if (mPasswordTextInputLayout.getEditText() != null) {
            String password = mPasswordTextInputLayout.getEditText().getText().toString();
            setPasswordValidationError(!isPasswordValid(password));
        }
    }

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid(String password) {
        return !password.isEmpty();
    }

    public void setEmailValidationError(boolean state) {
        mEmailTextInputLayout.setErrorEnabled(state);
        if (state) mEmailTextInputLayout.setError(getString(R.string.error_invalid_email));
    }

    public void setPasswordValidationError(boolean state) {
        mPasswordTextInputLayout.setErrorEnabled(state);
        if (state) mPasswordTextInputLayout.setError(getString(R.string.error_empty_pass));
    }
}
