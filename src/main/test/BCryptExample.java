import org.mindrot.jbcrypt.BCrypt;

public class BCryptExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 加密密码
        String rawPassword = "123";
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        System.out.println("Hashed Password: " + hashedPassword); //$2a$10$Hi8lXIt9wobEDRN.73QDZ.VebntV0.YsxKBZszmZ3ZyVSApZEUcfa

        
        //驗證密碼
        String userPassword = "123"; //模擬用戶輸入的密碼是(明碼)
        boolean isPasswordMatch = BCrypt.checkpw(userPassword, "$2a$10$qgVZMdNoKQ/JwXr4eQS8SOu2cYBPE9Y.ox2QRX9y.wjIKQZ7S3OBe");
        System.out.println("Password Match: " + isPasswordMatch);
	}

}
