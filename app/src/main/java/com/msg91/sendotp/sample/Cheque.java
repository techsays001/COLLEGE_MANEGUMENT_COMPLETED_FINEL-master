package com.msg91.sendotp.sample;

public class Cheque {

    private String image;
    private String status;

    private String user1;
    private String user2;

    private String user3;
    private String user4;

    private String user5;
    private String user6;

    public Cheque(String image, String status, String user1, String user2, String user3, String user4, String user5, String user6) {

        this.image = image;
        this.status = status;

        this.user1=user1;
        this.user2=user2;

        this.user3=user3;
        this.user4=user4;
        this.user5=user5;
        this.user6=user6;
    }

    public Cheque() {
    }


    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }

    public String getUser1() {
        return user1;
    }
    public String getUser2() {
        return user2;
    }
    public String getUser3() {
        return user3;
    }
    public String getUser4() {
        return user4;
    }
    public String getUser5() {
        return user5;
    }
    public String getUser6() {
        return user6;
    }
}




//   public String getPrize() { return  prize; }}
