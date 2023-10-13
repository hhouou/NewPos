package com.achpay.wallet.model.http.response;

public class LanguageInfo {
    public String language;
    public boolean checked;

    public LanguageInfo(String language, boolean checked) {
        this.language = language;
        this.checked = checked;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
