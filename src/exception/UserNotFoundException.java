package exception;

public class UserNotFoundException extends Exception{

    // TODO User bulunamadiginda "Boyle bir kullanici mevcut degildir" ikazi veren bir Exception tanimlayin...

    public UserNotFoundException(String message) {
        System.out.println(message);
    }
}
