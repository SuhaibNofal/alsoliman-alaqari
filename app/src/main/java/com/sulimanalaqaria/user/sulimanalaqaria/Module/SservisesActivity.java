package com.sulimanalaqaria.user.sulimanalaqaria.Module;



public class SservisesActivity {
    private int button;
    private int imageview;
    private String textView;
    private int id;

    public SservisesActivity(int imageview, String textView, int id) {
        this.id = id;
        this.imageview = imageview;
        this.textView = textView;
    }

    public int getIdServes() {
        return id;
    }

    public int getButton() {
        return button;
    }

    public int getImageview() {
        return imageview;
    }

    public String getTextView() {
        return textView;
    }
}
