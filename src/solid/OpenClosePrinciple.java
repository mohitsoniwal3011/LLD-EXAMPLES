

// any class should be open for extension and closed for modification

interface  Notifications{
    public  void send();
}

class EmailNotification implements Notifications{

    @Override
    public void send() {
        System.out.println("Sending Email factory.Notification");
    }
}

class OTPNotification implements Notifications{

    @Override
    public void send() {
        System.out.println("Sending OTP factory.Notification");
    }
}


class SMSNotification implements Notifications{

    @Override
    public void send() {
        System.out.println("Sending SMS factory.Notification");
    }
}



public class OpenClosePrinciple {
}
