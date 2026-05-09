

// any class should be open for extension and closed for modification

interface  Notifications{
    public  void send();
}

class EmailNotification implements Notifications{

    @Override
    public void send() {
        System.out.println("Sending Email Notification");
    }
}

class OTPNotification implements Notifications{

    @Override
    public void send() {
        System.out.println("Sending OTP Notification");
    }
}


class SMSNotification implements Notifications{

    @Override
    public void send() {
        System.out.println("Sending SMS Notification");
    }
}



public class OpenClosePrinciple {
}
